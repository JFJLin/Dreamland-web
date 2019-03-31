package com.xzy.web;

import com.utils.DateUtils;
import com.utils.PageHelper;
import com.utils.StringUtil;
import com.xzy.entity.Comment;
import com.xzy.entity.User;
import com.xzy.entity.Usercontent;
import com.xzy.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexJspController extends BaseController {

    private final static Logger log = Logger.getLogger(IndexJspController.class);


    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUsercontentService userContentService;
    @Autowired
    private IUpvoteService upvoteService;
    @Autowired
    private SolrService solrService;

    @RequestMapping("/index_list")
    public String findAllList(Model model, @RequestParam(value = "keyword",required = false) String keyword,
                              @RequestParam(value = "pageNum",required = false) Integer pageNum ,
                              @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        log.info( "===========进入index_list=========" );
        User user = (User)getSession().getAttribute("user");
        if(user!=null){
            model.addAttribute( "user",user );
        }
        if(StringUtils.isNotBlank(keyword)){
            PageHelper.Page<Usercontent> page = solrService.findByKeyWords( keyword ,pageNum,pageSize);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
        }else {
            PageHelper.Page<Usercontent> page =  findAll(pageNum,pageSize);
            model.addAttribute( "page",page );
        }
        return "../index";
    }

    @RequestMapping("/reply")
    @ResponseBody
    public Map<String, Object> reply(Model model, @RequestBody String content_id) {
        String[] split = content_id.split(":");
        String Final = split[1].substring(0,split[1].length()-1);
        List<Comment> list = commentService.findAllFirstComment(Long.parseLong(Final));
        Map<String, Object> map = new HashMap<>();
        if(list!=null && list.size()>0){
            for(Comment c:list){
              //  System.out.println("---" +c.getCommentId() + "-------"  + c.getCommentSondisscusid() );
                List<Comment> coments = commentService.findAllChildrenComment( c.getCommentArticleid(), c.getCommentSondisscusid() );
                //System.out.println("*********" + coments);
                if(coments!=null && coments.size()>0){
                    for(Comment com:coments){
                        if(com.getCommentBydisscusid()!=null ){
                            User byUser = userService.findById( com.getCommentBydisscusid() );
                            com.setByUser( byUser );
                        }

                    }
                }
                c.setComList( coments );
            }
        }
        map.put("list",list);
        return map;
    }


    @RequestMapping("/comment")
    @ResponseBody
    public Map<String,Object> comment(Model model, @RequestParam(value = "id",required = false) Long id ,
                                           @RequestParam(value = "content_id",required = false) Long content_id ,
                                           @RequestParam(value = "uid",required = false) Long uid ,
                                           @RequestParam(value = "by_id",required = false) Long bid ,
                                           @RequestParam(value = "oSize",required = false) String oSize,
                                           @RequestParam(value = "comment_time",required = false) String comment_time,
                                           @RequestParam(value = "upvote",required = false) Integer upvote){

        System.out.println("id:" + id + "content_id:" + content_id + "uid:" + uid + "bid:" + bid + "oSize:" + oSize + "comment_time:" + comment_time + "upvote:" + upvote);

        Map map = new HashMap<String,Object>(  );
        User user = (User)getSession().getAttribute("user");
        if(user == null){
            map.put( "data","fail" );
            return map;
        }
        if(id == null ){

            Date date = DateUtils.StringToDate( comment_time, "yyyy-MM-dd HH:mm:ss" );

            Comment comment = new Comment();
            comment.setCommentContent( oSize );
            comment.setCommentTime( date );
            comment.setCommentArticleid( content_id );
            comment.setCommentId( uid );
            if(upvote==null){
                upvote = 0;
            }
            comment.setCommentBydisscusid( bid );
            comment.setCommentUpvotenum( upvote );
            comment.setCommentDisscusid(uid);
            User u = userService.findById( uid );
            comment.setUser( u );
            commentService.insertComment( comment );
            map.put( "data",comment );

            Usercontent userContent = userContentService.findById( content_id );
            Integer num = userContent.getArticleComment();
            userContent.setArticleComment( num+1 );
            userContentService.updateContNumById(userContent);

        }else {
            //点赞
            Comment c = commentService.findById(id);
            //System.out.println("&&&&&&&" + c.toString());
            c.setCommentUpvotenum( upvote );
            commentService.updateUpvNum( c );
        }
        return map;

    }


    @RequestMapping("/deleteComment")
    @ResponseBody
    public Map<String,Object>  deleteComment(Model model, @RequestParam(value = "id",required = false) Long id,
                                             @RequestParam(value = "uid",required = false) Long uid,
                                             @RequestParam(value = "con_id",required = false) Long con_id,
                                             @RequestParam(value = "fid",required = false) Long fid) {

        System.out.println("id:" + id + "uid:" + uid + "cod_id:" + con_id + "fid:" + fid);
        int num = 0;
        Map map = new HashMap<String,Object>(  );
        User user = (User)getSession().getAttribute("user");
        if(user == null){
            map.put( "data","fail" );
        }else{
            if(user.getUserid().equals( uid )){
                Comment comment = commentService.findById( id );
                if(StringUtils.isBlank( comment.getCommentSondisscusid() )){
                    if(fid != null){
                        //去除id
                        Comment fcomm = commentService.findById( fid );
                        String child = StringUtil.getString( fcomm.getCommentSondisscusid(), id );
                        fcomm.setCommentSondisscusid( child );
                        commentService.updateUpvNum(fcomm);
                    }
                    commentService.deleteComment(id);
                    num = num + 1;
                }else {
                    String children = comment.getCommentSondisscusid();
                    commentService.deleteChildrenComment(children);
                    String[] arr = children.split( "," );

                    commentService.deleteComment(id);

                    num = num + arr.length + 1;

                }
                Usercontent content = userContentService.findById( con_id );
                if(content != null){
                    if(content.getArticleComment() - num >= 0){
                        content.setArticleComment( content.getArticleComment() - num );
                    }else {
                        content.setArticleComment( 0 );
                    }

                    userContentService.updateContNumById(content);
                }
                map.put( "data",content.getArticleComment() );
            }else {
                map.put( "data","no-access" );
            }
        }

        return  map;
    }

   /* @RequestMapping("/comment_child")
    @ResponseBody
    public Map<String,Object> addCommentChild(Model model, @RequestParam(value = "id",required = false) Long id ,
                                              @RequestParam(value = "content_id",required = false) Long content_id ,
                                              @RequestParam(value = "uid",required = false) Long uid ,
                                              @RequestParam(value = "by_id",required = false) Long bid ,
                                              @RequestParam(value = "oSize",required = false) String oSize,
                                              @RequestParam(value = "comment_time",required = false) String comment_time,
                                              @RequestParam(value = "upvote",required = false) Integer upvote) {
        Map map = new HashMap<String,Object>();
        User user = (User)getSession().getAttribute("user");
        if(user == null){
            map.put( "data","fail" );
            return map;
        }

        System.out.println(id + "--" + content_id + "--" + uid + "--" + bid);
        Date date = DateUtils.StringToDate( comment_time, "yyyy-MM-dd HH:mm:ss" );

        Comment comment = new Comment();
        comment.setCommentContent( oSize );
        comment.setCommentTime( date );
        comment.setCommentArticleid( content_id );
        comment.setCommentDisscusid(uid);
        //comment.setCommentId( uid );

        if(upvote==null){
            upvote = 0;
        }
        comment.setCommentBydisscusid( bid );
        comment.setCommentUpvotenum( upvote );
        User u = userService.findById( uid );
        comment.setUser( u );
        commentService.insertComment( comment );

        Comment com2 = commentService.findSon();
        System.out.println("$$$$$$$$$$$$" + com2);

        Comment com = commentService.findById( id );
        //System.out.println("^^^^^^^^^^" + com);
        //System.out.println("%%%%%%%%%%" + comment);
        if(StringUtils.isBlank( com.getCommentSondisscusid() )){
            com.setCommentSondisscusid( com2.getCommentId().toString() );
        }else {
            com.setCommentSondisscusid( comment.getCommentSondisscusid()+","+com2.getCommentId() );
        }
        commentService.updateUpvNum(com);
        map.put( "data",comment );

        Usercontent userContent = userContentService.findById( content_id );
        Integer num = userContent.getArticleComment();
        userContent.setArticleComment( num+1 );
        userContentService.updateContNumById( userContent );
        return map;

    }*/
}
