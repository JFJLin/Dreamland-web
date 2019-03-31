package com.xzy.service.impl;

import com.xzy.entity.Upvote;
import com.xzy.mapper.UpvoteMapper;
import com.xzy.service.IUpvoteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
@Service
public class UpvoteServiceImpl extends ServiceImpl<UpvoteMapper, Upvote> implements IUpvoteService {
	@Autowired
    UpvoteMapper upvoteMapper;

    @Override
    public Upvote findByUidAndConId(Upvote upvote) {
        return upvoteMapper.findByUidAndConId(upvote);
    }

    @Override
    public int insertupv(Upvote upvote) {
        return upvoteMapper.insertupv(upvote);
    }

    @Override
    public void updateupv(Upvote upvote) {
        upvoteMapper.updateupv(upvote);
    }
}
