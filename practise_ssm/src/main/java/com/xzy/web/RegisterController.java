package com.xzy.web;

import com.utils.CodeCaptchaServlet;
import com.utils.MD5Util;
import com.utils.SendEmail;
import com.xzy.entity.User;

import com.xzy.service.IRoleService;
import com.xzy.service.IRoleuserService;
import com.xzy.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by 12903 on 2018/4/19.
 */
@Controller
public class RegisterController {
    private final static Logger log = Logger.getLogger(RegisterController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired// redis数据库操作模板
    private RedisTemplate<String, String> redisTemplate;// jdbcTemplate HibernateTemplate
    @Autowired
    private IRoleuserService roleUserService;

    /**
     * 判断手机号是否已经被注册
     * @param phone
     * @return
     */
    @RequestMapping("/checkPhone")
    @ResponseBody
    public Map<Object, String> checkPhone(@RequestBody String phone) {
        String[] split = phone.split(":");
        String Final = split[1].substring(1,split[1].length()-2);
        log.debug("注册-判断手机号" + Final + "是否可用");
        User user = userService.findByPhone(Final);
        Map<Object, String> map = new HashMap<>();
        if (user == null) {
            //未注册
            map.put("message","success");
        } else {
            //已注册
            map.put("message","fail");
        }
        return map;
    }



    /**
     * 判断邮箱是否已经被注册
     * @param email
     * @return
     */
    @RequestMapping("/checkEmail")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestBody String email) {
        String[] split = email.split(":");
        String Final = split[1].substring(1,split[1].length()-2);
        log.debug("注册-判断邮箱" + Final + "是否可用");
        Map map = new HashMap<String, Object>();
        User user = userService.findByEmail(Final);
        if (user == null) {
            //未注册
            map.put("message", "success");
        } else {
            //已注册
            map.put("message", "fail");
        }
        return map;
    }


    /**
     * 判断验证码是否正确
     *
     * @param code
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public Map<String, Object> checkCode( @RequestBody String code) {
        log.debug("注册-判断验证码" + code + "是否可用");
        String[] split = code.split(":");
        String Final = split[1].substring(1,split[1].length()-2);
        Map map = new HashMap<String, Object>();
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String vcode = (String) attrs.getRequest().getSession().getAttribute(CodeCaptchaServlet.VERCODE_KEY);
        if (Final.equals(vcode)) {
            //验证码正确
            map.put("message", "success");
        } else {
            //验证码错误
            map.put("message", "fail");
        }

        return map;
    }


    /**
     * 用户注册
     *
     * @param model
     * @param email
     * @param password
     * @param phone
     * @param nickname
     * @param code
     * @return
     */
    @RequestMapping("/doRegister")
    public String doRegister(Model model, @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "password", required = false) String password,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "nickName", required = false) String nickname,
                             @RequestParam(value = "code", required = false) String code) {

        System.out.println("@@@@@@@@@@" + code);
        log.debug("==================注册...");
        if (StringUtils.isBlank(code)) {
            model.addAttribute("error", "非法注册，请重新注册！");
            return "../register";
        }

        int b = checkValidateCode(code);
        if (b == -1) {
            model.addAttribute("error", "验证码超时，请重新注册！");
            return "../register";
        } else if (b == 0) {
            model.addAttribute("error", "验证码不正确,请重新输入!");
            return "../register";
        }


        User user = userService.findByEmail(email);
        if (user != null) {
            model.addAttribute("error", "该用户已经被注册！");
            return "../register";
        } else {
            user = new User();
            user.setUsername(nickname);

            user.setUserpassword(MD5Util.encodeToHex("salt"+password));
            user.setUserphone(phone);
            user.setUseremail(email);
            user.setUserstatus("0");
            user.setUserenable("0");
            user.setUserhead("/images/icon_m.jpg");
            //邮件激活码
            String validateCode = MD5Util.encodeToHex("salt"+email + password);
            redisTemplate.opsForValue().set(email, validateCode, 24, TimeUnit.HOURS);// 24小时 有效激活 redis保存激活码

            userService.regist(user);

            log.info("注册成功");
            SendEmail.sendEmailMessage(email, validateCode);
            String message = email + "," + validateCode;
            model.addAttribute("message", message);
            return "/regist/registerSuccess";

        }
    }


    /**
     * 匹对验证码的正确性
     * @param code
     * @return
     */
    public int checkValidateCode(String code) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object vercode = attrs.getRequest().getSession().getAttribute("VERCODE_KEY");
        System.out.println("*************" + vercode);
        if (null == vercode) {
            return -1;
        }
        if (!code.equalsIgnoreCase(vercode.toString())) {
            return 0;
        }
        return 1;
    }


    /**
     * 发送邮件
     * @param model
     * @return
     */
    @RequestMapping("/sendEmail")
    @ResponseBody
    public  Map<String,Object> sendEmail(Model model) {
        Map map = new HashMap<String,Object>(  );
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String validateCode = attrs.getRequest().getParameter( "validateCode" );
        String email = attrs.getRequest().getParameter( "email" );
        SendEmail.sendEmailMessage(email,validateCode);
        map.put( "success","success" );
        return map;
    }


    /**
     * 返回注册页面
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model) {

        log.info("进入注册页面");

        return "../register";
    }

    /**
     * 激活验证
     * @param model
     * @return
     */
    @RequestMapping("/activecode")
    public String active(Model model) {
        log.info( "==============激活验证==================" );
        //判断   激活有无过期 是否正确
        //validateCode=
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String validateCode = attrs.getRequest().getParameter( "validateCode" );
        String email = attrs.getRequest().getParameter( "email" );
        String code = redisTemplate.opsForValue().get( email );

        //System.out.println("!!!!!!!!!!!!" + code);
        log.info( "验证邮箱为："+email+",邮箱激活码为："+code+",用户链接的激活码为："+validateCode );
        //判断是否已激活

        User userTrue = userService.findByEmail( email );
        if(userTrue!=null && "1".equals( userTrue.getUserstatus() )){
            //已激活
            model.addAttribute( "success","您已激活,请直接登录！" );
            return "../login";
        }

        if(code==null){
            //激活码过期
            model.addAttribute( "fail","您的激活码已过期,请重新注册！" );
            userService.deleteByEmail( email );
            return "/regist/activeFail";
        }

        if(StringUtils.isNotBlank( validateCode ) && validateCode.equals( code )){
            //激活码正确
            userTrue.setUserenable( "1" );
            userTrue.setUserstatus( "1" );
            userService.update( userTrue );
            model.addAttribute( "email",email );
            return "/regist/activeSuccess";
        }else {
            //激活码错误
            model.addAttribute( "fail","您的激活码错误,请重新激活！" );
            return "/regist/activeFail";
        }

    }
}