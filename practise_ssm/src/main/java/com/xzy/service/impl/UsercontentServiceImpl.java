package com.xzy.service.impl;

import com.utils.PageHelper;
import com.xzy.entity.Comment;
import com.xzy.entity.Usercontent;
import com.xzy.mapper.CommentMapper;
import com.xzy.mapper.UsercontentMapper;
import com.xzy.service.IUsercontentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
@Service
public class UsercontentServiceImpl extends ServiceImpl<UsercontentMapper, Usercontent> implements IUsercontentService {

    @Autowired
    UsercontentMapper usercontentMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageHelper.Page<Usercontent> findAll(Integer pageNum, Integer pageSize) {
        //分页查询
        System.out.println("第"+pageNum+"页");
        System.out.println("每页显示："+pageSize+"条");
        PageHelper.startPage(pageNum, pageSize);//开始分页
        List<Usercontent> list =  usercontentMapper.select();
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
        //分页完后得吧查询的结果放入endPage
        endPage.setResult(list);
        return endPage;
    }

    @Override
    public PageHelper.Page<Usercontent> findAll(Usercontent content, Integer pageNum, Integer pageSize) {
        //分页查询
        System.out.println("第"+pageNum+"页");
        System.out.println("每页显示："+pageSize+"条");
        PageHelper.startPage(pageNum, pageSize);//开始分页
        List<Usercontent> list =  usercontentMapper.select();
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
        //分页完后得吧查询的结果放入endPage
        endPage.setResult(list);
        return endPage;
    }

    @Override
    public PageHelper.Page<Usercontent> findAll(Usercontent content, Comment comment, Integer pageNum, Integer pageSize) {
        System.out.println("进入分页查询");
        //分页查询
        System.out.println("第"+pageNum+"页");
        System.out.println("每页显示："+pageSize+"条");
        PageHelper.startPage(pageNum, pageSize);//开始分页
        List<Usercontent> list =  usercontentMapper.select();

        System.out.println("*************" + list);
        //List<Comment> comments = commentMapper.select(comment);

        PageHelper.Page endPage = PageHelper.endPage();//分页结束
         endPage.setResult(list);
        return (PageHelper.Page<Usercontent>) endPage.getResult();
    }

    @Override
    public PageHelper.Page<Usercontent> findAllByUpvote(Usercontent content, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开始分页
        List<Usercontent> list = usercontentMapper.findAllByUpvote();
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
        return endPage;

    }

    public void addContent(Usercontent content) {
        usercontentMapper.insert(content);
    }

    @Override
    public List<Usercontent> findByUserId(Long uid) {

        Usercontent userContent = new Usercontent();
        userContent.setArticleUserid(uid);
        List<Usercontent> list = usercontentMapper.select();
        return list;
    }

    @Override
    public List<Usercontent> findAll() {
        return usercontentMapper.select();
    }

    @Override
    public Usercontent findById(long id) {
        Usercontent usercontent = new Usercontent();
        usercontent.setArticleid(id);
        return usercontentMapper.findById(id);
    }

    @Override
    public void updateContNumById(Usercontent content) {
        usercontentMapper.updateContNumById(content);
    }


    @Override
    public List<Usercontent> select() {
        return usercontentMapper.select();
    }

    @Override
    public void updatecont(Usercontent usercontent) {
        usercontentMapper.updatecont(usercontent);
    }


}
