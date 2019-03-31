package com.interceptor;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.utils.PageHelper;
import com.xzy.entity.Usercontent;
import com.xzy.mapper.UsercontentMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * 首页过滤器，jsp页面动态获取数据
 */

public class IndexJspFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * filter 初始化时，注解的 bean 还没初始化，加 @Autowired 注解不会起作用，
         * 所以通过 ApplicationContext 手动获取 UserContentMapper 对象。
         */
        System.out.println("===========自定义过滤器==========");
        ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        UsercontentMapper userContentMapper = ctx.getBean(UsercontentMapper.class);
        PageHelper.startPage(null, null);//开始分页
        List<Usercontent> list =  userContentMapper.select();
        System.out.println("=====================" + list.size());
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
        endPage.setResult(list);
       // System.out.println("--------------------" + endPage.getResult());
        request.setAttribute("page", endPage );
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
