package com.xzy.service.impl;

import com.xzy.entity.Resource;
import com.xzy.mapper.ResourceMapper;
import com.xzy.service.IResourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
	
}
