package com.xzy.service.impl;

import com.utils.PageHelper;
import com.xzy.entity.Usercontent;
import com.xzy.service.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    HttpSolrClient solrClient;


    @Override
    public PageHelper.Page<Usercontent> findByKeyWords(String keyword, Integer pageNum, Integer pageSize) {

        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("title:" + keyword);

        //设置高亮
        query.setHighlight( true );
        query.addHighlightField( "title" );
        query.setHighlightSimplePre( "<span style='color:blue'>" );
        query.setHighlightSimplePost( "</span>" );


        //分页
        if(pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        if(pageSize == null || pageSize < 1){
            pageSize = 7;
        }
        query.setStart((pageNum - 1)*pageSize);
        query.setRows(pageSize);
        query.addSort("rpt_time", SolrQuery.ORDER.desc);

        try {
            QueryResponse response = solrClient.query(query);
            //获得高亮数据集合
            Map<String,Map<String, List<String>>> highlighting = response.getHighlighting();
            //获得结果集
            SolrDocumentList results = response.getResults();
            //获得总数
            long numFound = results.getNumFound();
            List<Usercontent> list = new ArrayList<>();
            for (SolrDocument solrDocument : results) {
                Usercontent content = new Usercontent();
                String id = (String)solrDocument.get("id");
                Object content1 = solrDocument.get( "content" );
                Object commentNum = solrDocument.get( "comment_num" );
                Object downvote = solrDocument.get( "downvote" );
                Object upvote = solrDocument.get( "upvote" );
                Object nickName = solrDocument.get( "nick_name" );
                Object imgUrl = solrDocument.get( "img_url" );
                Object uid = solrDocument.get( "u_id" );
                Object rpt_time = solrDocument.get( "rpt_time" );
                Object category = solrDocument.get( "category" );
                Object personal = solrDocument.get( "personal" );
                //取得高亮数据集合中的文章标题
                Map<String, List<String>> map = highlighting.get( id );
                String title = map.get( "title" ).get( 0 );
                content.setArticleid( Long.parseLong( id ) );
                content.setArticleComment( Integer.parseInt( commentNum.toString() ) );
                content.setArticleCai( Integer.parseInt( downvote.toString() ) );
                content.setArticleUpvote( Integer.parseInt( upvote.toString() ) );
                content.setArticleUsername( nickName.toString() );
                content.setArticleUserhead( imgUrl.toString() );
                content.setArticleUserid( Long.parseLong( uid.toString() ) );
                content.setArticeTitle( title );
                content.setArticleIsprivate( personal.toString() );
                Date date = (Date)rpt_time;
                content.setArticleUploadtime(date);
                List<String> clist = (ArrayList)content1;
                content.setArticleContent(clist.get(0).toString());
                content.setArticleSort( category.toString() );
                list.add( content );
            }

            PageHelper.startPage(pageNum, pageSize);//开始分页
            PageHelper.Page page = PageHelper.endPage();//分页结束
            page.setResult(list);
            page.setTotal(numFound);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addUserContent(Usercontent userContent) {
        if(userContent!=null){
            addDocument(userContent);
        }
    }

    @Override
    public void updateUserContent(Usercontent userContent) {
        if(userContent!=null){
            addDocument(userContent);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            solrClient.deleteById(id.toString());
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDocument(Usercontent cont){
        try {
            SolrInputDocument inputDocument = new SolrInputDocument();
            inputDocument.addField( "comment_num", cont.getArticleComment() );
            inputDocument.addField( "downvote", cont.getArticleCai() );
            inputDocument.addField( "upvote", cont.getArticleUpvote() );
            inputDocument.addField( "nick_name", cont.getArticleUsername());
            inputDocument.addField( "img_url", cont.getArticleUserhead() );
            inputDocument.addField( "rpt_time", cont.getArticleUploadtime() );
            inputDocument.addField( "content", cont.getArticleContent() );
            inputDocument.addField( "category", cont.getArticleSort());
            inputDocument.addField( "title", cont.getArticeTitle() );
            inputDocument.addField( "u_id", cont.getArticleUserid() );
            inputDocument.addField( "id", cont.getArticleid());
            inputDocument.addField( "personal", cont.getArticleIsprivate());
            solrClient.add( inputDocument );
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
