package com.xzy.web;
import com.utils.PageHelper.Page;
import com.xzy.entity.Comment;
import com.xzy.entity.User;
import com.xzy.entity.Usercontent;
import com.xzy.service.IUserService;
import com.xzy.service.IUsercontentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wly on 2018/1/9.
 */
@Component
public class BaseController {
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "X-Real-IP"};

    @Autowired
    private IUsercontentService userContentService;
    @Autowired
    private IUserService userService;

    public boolean isLogin(Long id){
        if(id != null ){
            User user = userService.findById( id );
            if(user!=null){
                return true;
            }
        }
        return false;
    }

    public User getUser(Long id){
        User user = userService.findById( id );
        return user;
    }

    public List<Usercontent> getUserContentList(Long uid){
        List<Usercontent> list = (List<Usercontent>) userContentService.findByUserId( uid );
        return list;
    }

    public List<Usercontent> getAllUserContentList(){
        List<Usercontent> list = userContentService.findAll();
        return list;
    }

    public Page<Usercontent> findAll(Integer pageNum, Integer pageSize){
        Page<Usercontent> page = userContentService.findAll(pageNum ,pageSize);
        return page;
    }
    public Page<Usercontent> findAll(Usercontent content, Integer pageNum, Integer pageSize){
        Page<Usercontent> page = userContentService.findAll( content,pageNum ,pageSize);
        return page;
    }


    public Page<Usercontent> findAll(Usercontent content, Comment comment, Integer pageNum, Integer pageSize){
        Page<Usercontent> page = userContentService.findAll( content,comment,pageNum ,pageSize);
        return page;
    }

    public Page<Usercontent> findAllByUpvote(Usercontent content, Integer pageNum, Integer pageSize){
        Page<Usercontent> page = userContentService.findAllByUpvote( content,pageNum ,pageSize);
        return page;
    }
    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse resp = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {}
        return session;
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @return
     */
    public static String getClientIpAddress() {
        HttpServletRequest request = getRequest();
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }


}
