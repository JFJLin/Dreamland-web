package com.xzy.service;

import com.utils.PageHelper;
import com.xzy.entity.Usercontent;

public interface SolrService {
    PageHelper.Page<Usercontent> findByKeyWords(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加文章到solr索引库中
     * @param userContent
     */
    void addUserContent(Usercontent userContent);

    /**
     * 根据solr索引库
     * @param userContent
     */
    void updateUserContent(Usercontent userContent);

    /**
     * 根据文章id删除索引库
     * @param userContent
     */
    void deleteById(Long id);
}
