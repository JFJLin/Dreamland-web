package com.xzy.mapper;

import com.xzy.entity.Upvote;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface UpvoteMapper extends BaseMapper<Upvote> {

    Upvote findByUidAndConId(Upvote upvote);
    int insertupv(Upvote upvote);
    void updateupv(Upvote upvote);
}