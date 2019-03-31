package com.xzy.service.impl;

import com.xzy.entity.Comment;
import com.xzy.mapper.CommentMapper;
import com.xzy.service.ICommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> selectAll(Long id) {
        return commentMapper.selectAll(id);
    }

    @Override
    public List<Comment> findAllFirstComment(Long id) {
        return commentMapper.findAllFirstComment(id);
    }

    @Override
    public List<Comment> findAllChildrenComment(Long id, String sonid) {
        return commentMapper.findAllChildrenComment(id,sonid);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public void updateUpvNum(Comment comment) {
        commentMapper.updateUpvNum(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public void deleteChildrenComment(String son) {
        commentMapper.deleteChildrenComment(son);
    }

    @Override
    public Comment findSon() {
       return commentMapper.findSon();
    }


}
