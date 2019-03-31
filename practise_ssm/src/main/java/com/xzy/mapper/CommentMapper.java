package com.xzy.mapper;

import com.xzy.entity.Comment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据文章id查询所有评论
     * @param id
     * @return
     */
    List<Comment> selectAll(Long id);

    /**
     * 根据文章id查询所有一级评论
     * @param id
     * @return
     */
    List<Comment> findAllFirstComment(Long id);

    /**
     * 根据文章id和二级评论ids查询出所有二级评论
     * @return
     */
    List<Comment> findAllChildrenComment(@Param("cid")long cid, @Param("children")String children);

    /**
     * 插入评论并返回主键id 返回值是影响行数  id在Comment对象中
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    Comment findById(Long id);

    void updateUpvNum(Comment comment);

    void deleteChildrenComment(String son);
    void deleteComment(Long id);
    Comment findSon();
}