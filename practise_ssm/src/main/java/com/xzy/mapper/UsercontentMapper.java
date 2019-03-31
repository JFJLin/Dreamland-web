package com.xzy.mapper;

import com.xzy.entity.Usercontent;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface UsercontentMapper extends BaseMapper<Usercontent> {


    List<Usercontent> select();
    List<Usercontent> findAllByUpvote();
    Usercontent findById(Long id);
    void updatecont(Usercontent usercontent);
    void updateContNumById(Usercontent usercontent);
}