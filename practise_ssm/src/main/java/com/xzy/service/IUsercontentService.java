package com.xzy.service;

import com.utils.PageHelper;
import com.xzy.entity.Comment;
import com.xzy.entity.Usercontent;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */

import java.util.List;

public interface IUsercontentService extends IService<Usercontent> {

    /**
     * 查询所有Content并分页
     * @param content
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageHelper.Page<Usercontent> findAll( Integer pageNum, Integer pageSize);
    PageHelper.Page<Usercontent> findAll(Usercontent content, Integer pageNum, Integer pageSize);
    PageHelper.Page<Usercontent> findAll(Usercontent content, Comment comment, Integer pageNum, Integer pageSize);
    PageHelper.Page<Usercontent> findAllByUpvote(Usercontent content, Integer pageNum, Integer pageSize);

    /**
     * 添加文章
     * @param content
     */
    void addContent(Usercontent content);

    /**
     * 根据用户id查询文章集合
     * @param uid
     * @return
     */
    List<Usercontent> findByUserId(Long uid);

    /**
     * 查询所有文章
     * @return
     */
    List<Usercontent> findAll();

    /**
     * 根据文章id查找文章
     * @param id
     * @return
     */
    Usercontent findById(long id);
    /**
     * 根据文章id更新文章
     * @param content
     * @return
     */
    void updateContNumById(Usercontent content);

    public List<Usercontent> select();
    void updatecont(Usercontent usercontent);
}
