package com.xzy.service;

import com.xzy.entity.Upvote;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface IUpvoteService extends IService<Upvote> {

    Upvote findByUidAndConId(Upvote upvote);
    int insertupv(Upvote upvote);
    void updateupv(Upvote upvote);
}
