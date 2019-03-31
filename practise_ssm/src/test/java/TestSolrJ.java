import com.xzy.entity.Usercontent;
import com.xzy.service.IUsercontentService;
import com.xzy.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.IOException;
import java.util.List;


@ContextConfiguration(locations = {"classpath:spring-redis.xml","classpath:spring-mybatis.xml","classpath:applicationContext-activemq.xml","classpath:applicationContext-solr.xml"})

public class TestSolrJ extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IUsercontentService userContentService;
    @Autowired
    private SolrClient solrClient;
    @Test
    public void testSaveAll() throws IOException, SolrServerException {
        List<Usercontent> list = userContentService.findAll();
        if(list!=null && list.size()>0){
            for (Usercontent cont : list){
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
            }
        }

        solrClient.commit();
    }


}
