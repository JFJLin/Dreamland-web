package com.xzy.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xzy.entity.Upvote;
import com.xzy.entity.User;
import com.xzy.entity.Usercontent;
import com.xzy.service.IUpvoteService;
import com.xzy.service.IUsercontentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
@Controller
@RequestMapping("/upvote")
public class UpvoteController extends BaseController{

    private static final Logger log = Logger.getLogger(UpvoteController.class);

    @Autowired
    IUsercontentService iUsercontentService;
    @Autowired
    IUpvoteService iUpvoteService;

    @RequestMapping("/upvotes")
    @ResponseBody
    public Map<String,Object> upvote(Model model, @RequestBody String result){

        String id,upvote;

        String[] split = result.split(",");
        id = split[0].split(":")[1];
        upvote = split[2].split(":")[1].substring(0,split[2].split(":")[1].length()-1);

        System.out.println("=========" + id);
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) getSession().getAttribute("user");
        //System.out.println("%%%%%%%%%%%" + user);
        if(user == null){
            //未登录，失败，所以不能点赞，踩或者评论
            map.put("data","fail");
            return map;
        }
        Upvote upvote1 = new Upvote();
        upvote1.setUpvoteUserid(user.getUserid());
        upvote1.setUpvoteArticleid(Long.parseLong(id));
        Upvote upv = iUpvoteService.findByUidAndConId(upvote1);
        if(null != upv){
            log.info( upv.toString()+"============" );
        }
        Usercontent usercontent = iUsercontentService.findById(Long.parseLong(id));

        System.out.println(usercontent.toString());
        if(Integer.parseInt(upvote) == -1){
            //点击的是踩
            if(null != upv){
                //upv对象不空
                if("1".equals(upv.getUpvoteCaicontrol())){
                    //是1，表示已经踩过了
                    map.put("data","down");
                    return map;
                }else{
                    //不是1，没踩过，将其值设为1,并且设置点赞时间等其他属性，更新Upvote
                    upv.setUpvoteControl(upv.getUpvoteControl());
                   upv.setUpvoteCaicontrol("1");
                   upv.setUpvoteTime(new Date());
                   upv.setUpvoteUseradd(getClientIpAddress());
                   iUpvoteService.updateupv(upv);
                }

            }else{
                //upv对象是空的,new一个Upvote，设置属性并插入
                upvote1.setUpvoteArticleid(Long.parseLong(id));
                upvote1.setUpvoteUserid(user.getUserid());
                upvote1.setUpvoteCaicontrol("1");
                upvote1.setUpvoteControl("");
                upvote1.setUpvoteTime(new Date());
                upvote1.setUpvoteUseradd(getClientIpAddress());
                iUpvoteService.insertupv(upvote1);
            }
            usercontent.setArticleCai(usercontent.getArticleCai() + Integer.parseInt(upvote));
        }else{
            //点击的是赞
            if(null != upv){
                if("1".equals(upv.getUpvoteControl())){
                    map.put("data","done");
                    return map;
                }else{
                    upv.setUpvoteCaicontrol(upv.getUpvoteCaicontrol());
                    upv.setUpvoteControl("1");
                    upv.setUpvoteTime(new Date());
                    upv.setUpvoteUseradd(getClientIpAddress());
                    iUpvoteService.updateupv(upv);
                }
            }else{
                upvote1.setUpvoteArticleid(Long.parseLong(id));
                upvote1.setUpvoteUserid(user.getUserid());
                upvote1.setUpvoteControl("1");
                upvote1.setUpvoteTime(new Date());
                upvote1.setUpvoteCaicontrol("");
                upvote1.setUpvoteUseradd(getClientIpAddress());
                iUpvoteService.insertupv(upvote1);
            }
            usercontent.setArticleUpvote(usercontent.getArticleUpvote() + Integer.parseInt(upvote));
        }
        //更新个人用户的文章信息
        iUsercontentService.updatecont(usercontent);
        map.put("data","success");
        return map;
    }

	
}
