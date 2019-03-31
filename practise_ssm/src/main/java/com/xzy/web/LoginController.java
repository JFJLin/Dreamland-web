package com.xzy.web;

import com.utils.Constants;
import com.utils.MD5Util;
import com.utils.RandStringUtils;
import com.xzy.entity.User;
import com.xzy.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController extends BaseController {
    private final static Logger log = Logger.getLogger( LoginController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping("/doLogin")
    public String doLogin(Model model, @RequestParam(value = "username",required = false) String email,
                          @RequestParam(value = "password",required = false) String password,
                          @RequestParam(value = "code",required = false) String code,
                          @RequestParam(value = "telephone",required = false) String telephone,
                          @RequestParam(value = "phone_code",required = false) String phone_code,
                          @RequestParam(value = "state",required = false) String state,
                          @RequestParam(value = "pageNum",required = false) Integer pageNum ,
                          @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        //判断是否是手机登录
        if (StringUtils.isNotBlank(telephone)) {
            //手机登录
            String yzm = redisTemplate.opsForValue().get( telephone );//从redis获取验证码
            System.out.println(phone_code + "$$$$$$$$$$$$$$" + yzm);
            if(phone_code.equals(yzm)){
                //验证码正确
                User user = userService.findByPhone(telephone);
                getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                log.info("手机快捷登录成功");
                return "/personal/personal";

            }else {
                //验证码错误或过期
                model.addAttribute("error","phone_fail");
                return  "../login";
            }

        } else {
            //账号登录

            if (StringUtils.isBlank(code)) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            int b = checkValidateCode(code);
            if (b == -1) {
                model.addAttribute("error", "fail");
                return "../login";
            } else if (b == 0) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            password = MD5Util.encodeToHex(Constants.SALT + password);
            User user = userService.login(email, password);
            if (user != null) {
                if ("0".equals(user.getUserstatus())) {
                    //未激活
                    model.addAttribute("email", email);
                    model.addAttribute("error", "active");
                    return "../login";
                }
                log.info("用户登录登录成功");
                //用户登陆成功后存入session，实现15分钟免登陆
                getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                return "/personal/personal";
            } else {
                log.info("用户登录登录失败");
                model.addAttribute("email", email);
                model.addAttribute("error", "fail");
                return "../login";
            }

        }

    }

    // 匹对验证码的正确性
    public int checkValidateCode(String code) {
        Object vercode = getRequest().getSession().getAttribute("VERCODE_KEY");
        if (null == vercode) {
            return -1;
        }
        if (!code.equalsIgnoreCase(vercode.toString())) {
            return 0;
        }
        return 1;
    }


    /**
     * 15分钟免登陆的映射方法
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model) {
        User user = (User)getSession().getAttribute("user");
        if(user!=null){
            return "/personal/personal";
        }
        return "../login";
    }




    @Autowired// redis数据库操作模板
    private RedisTemplate<String, String> redisTemplate;// jdbcTemplate HibernateTemplate

    @Autowired
    @Qualifier("jmsQueueTemplate") //指定为消息队列模式，而不是发布订阅模式
    private JmsTemplate jmsTemplate;// mq消息模板.

    /**
     * 发送手机验证码
     * @param telephone
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Map<String,Object> index( @RequestBody final String Final ) {
        System.out.println("进入方法了、、、、、、、、、");
        Map map = new HashMap<String,Object>(  );
        try { //  发送验证码操作
            final String code = RandStringUtils.getCode();
            String[] split = Final.split(":");
            String telephone = split[1].substring(1,split[1].length()-2);
            redisTemplate.opsForValue().set(telephone, code, 60, TimeUnit.SECONDS);// 60秒 有效 redis保存验证码
            //System.out.println("========" + redisTemplate.opsForValue().get(telephone));
            log.debug("--------短信验证码为："+code);
            // 调用ActiveMQ jmsTemplate，发送一条消息给MQ
            jmsTemplate.send("login_msg", new MessageCreator() {
                public Message createMessage(javax.jms.Session session) throws JMSException {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setString("telephone",telephone);
                    mapMessage.setString("code", code);
                    return mapMessage;
                }
            });
        } catch (Exception e) {
            map.put( "msg",false );
        }
        map.put( "msg",true );
        return map;
    }


    /**
     * 退出登录的映射方法
     * @return
     */
    @RequestMapping("/loginout")
    public String exit(){
        log.info("退出登录");
        //移除用户的session信息
        getSession().removeAttribute("user");
        //使 Session 失效，释放资源
        getSession().invalidate();
        //最后返回登录页面
        return "../login";
    }
}
