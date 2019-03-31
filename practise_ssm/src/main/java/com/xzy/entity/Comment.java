package com.xzy.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论表id
     */
	@TableId(value="comment_id", type= IdType.AUTO)
	private Long commentId;
    /**
     * 文章id
     */
	@TableField("comment_articleid")
	private Long commentArticleid;
    /**
     * 评论者id
     */
	@TableField("comment_disscusid")
	private Long commentDisscusid;
    /**
     * 被评论者id
     */
	@TableField("comment_bydisscusid")
	private Long commentBydisscusid;
    /**
     * 评论内容
     */
	@TableField("comment_content")
	private String commentContent;
    /**
     * 评论时间
     */
	@TableField("comment_time")
	private Date commentTime;
    /**
     * 子评论id
     */
	@TableField("comment_sondisscusid")
	private String commentSondisscusid;
    /**
     * 评论点赞数
     */
	@TableField("comment_upvotenum")
	private Integer commentUpvotenum;


	@Transient
	private User user;

	@Transient
	private User byUser;

	@Transient
	private List<Comment> comList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getByUser() {
		return byUser;
	}

	public void setByUser(User byUser) {
		this.byUser = byUser;
	}

	public List<Comment> getComList() {
		return comList;
	}

	public void setComList(List<Comment> comList) {
		this.comList = comList;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getCommentArticleid() {
		return commentArticleid;
	}

	public void setCommentArticleid(Long commentArticleid) {
		this.commentArticleid = commentArticleid;
	}

	public Long getCommentDisscusid() {
		return commentDisscusid;
	}

	public void setCommentDisscusid(Long commentDisscusid) {
		this.commentDisscusid = commentDisscusid;
	}

	public Long getCommentBydisscusid() {
		return commentBydisscusid;
	}

	public void setCommentBydisscusid(Long commentBydisscusid) {
		this.commentBydisscusid = commentBydisscusid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentSondisscusid() {
		return commentSondisscusid;
	}

	public void setCommentSondisscusid(String commentSondisscusid) {
		this.commentSondisscusid = commentSondisscusid;
	}

	public Integer getCommentUpvotenum() {
		return commentUpvotenum;
	}

	public void setCommentUpvotenum(Integer commentUpvotenum) {
		this.commentUpvotenum = commentUpvotenum;
	}

	@Override
	protected Serializable pkVal() {
		return this.commentId;
	}

	@Override
	public String toString() {
		return "Comment{" +
			"commentId=" + commentId +
			", commentArticleid=" + commentArticleid +
			", commentDisscusid=" + commentDisscusid +
			", commentBydisscusid=" + commentBydisscusid +
			", commentContent=" + commentContent +
			", commentTime=" + commentTime +
			", commentSondisscusid=" + commentSondisscusid +
			", commentUpvotenum=" + commentUpvotenum +
			"}";
	}
}
