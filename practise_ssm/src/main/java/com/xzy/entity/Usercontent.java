package com.xzy.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public class Usercontent extends Model<Usercontent> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
	private Long articleid;
    /**
     * 用户id
     */
	@TableField("article_userid")
	private Long articleUserid;
    /**
     * 文章标题
     */
	@TableField("artice_title")
	private String articeTitle;
    /**
     * 文章分类
     */
	@TableField("article_sort")
	private String articleSort;
    /**
     * 文章内容
     */
	@TableField("article_content")
	private String articleContent;
    /**
     * 是否私有
     */
	@TableField("article_isprivate")
	private String articleIsprivate;
    /**
     * 上传时间
     */
	@TableField("article_uploadtime")
	private Date articleUploadtime;
    /**
     * 用户头像url
     */
	@TableField("article_userhead")
	private String articleUserhead;
    /**
     * 用户昵称
     */
	@TableField("article_username")
	private String articleUsername;
    /**
     * 点赞
     */
	@TableField("article_upvote")
	private Integer articleUpvote;
    /**
     * 踩
     */
	@TableField("article_cai")
	private Integer articleCai;
    /**
     * 评论数
     */
	@TableField("article_comment")
	private Integer articleComment;


	public Long getArticleid() {
		return articleid;
	}

	public void setArticleid(Long articleid) {
		this.articleid = articleid;
	}

	public Long getArticleUserid() {
		return articleUserid;
	}

	public void setArticleUserid(Long articleUserid) {
		this.articleUserid = articleUserid;
	}

	public String getArticeTitle() {
		return articeTitle;
	}

	public void setArticeTitle(String articeTitle) {
		this.articeTitle = articeTitle;
	}

	public String getArticleSort() {
		return articleSort;
	}

	public void setArticleSort(String articleSort) {
		this.articleSort = articleSort;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleIsprivate() {
		return articleIsprivate;
	}

	public void setArticleIsprivate(String articleIsprivate) {
		this.articleIsprivate = articleIsprivate;
	}

	public Date getArticleUploadtime() {
		return articleUploadtime;
	}

	public void setArticleUploadtime(Date articleUploadtime) {
		this.articleUploadtime = articleUploadtime;
	}

	public String getArticleUserhead() {
		return articleUserhead;
	}

	public void setArticleUserhead(String articleUserhead) {
		this.articleUserhead = articleUserhead;
	}

	public String getArticleUsername() {
		return articleUsername;
	}

	public void setArticleUsername(String articleUsername) {
		this.articleUsername = articleUsername;
	}

	public Integer getArticleUpvote() {
		return articleUpvote;
	}

	public void setArticleUpvote(Integer articleUpvote) {
		this.articleUpvote = articleUpvote;
	}

	public Integer getArticleCai() {
		return articleCai;
	}

	public void setArticleCai(Integer articleCai) {
		this.articleCai = articleCai;
	}

	public Integer getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(Integer articleComment) {
		this.articleComment = articleComment;
	}

	@Override
	protected Serializable pkVal() {
		return this.articleid;
	}

	@Override
	public String toString() {
		return "Usercontent{" +
			"articleid=" + articleid +
			", articleUserid=" + articleUserid +
			", articeTitle=" + articeTitle +
			", articleSort=" + articleSort +
			", articleContent=" + articleContent +
			", articleIsprivate=" + articleIsprivate +
			", articleUploadtime=" + articleUploadtime +
			", articleUserhead=" + articleUserhead +
			", articleUsername=" + articleUsername +
			", articleUpvote=" + articleUpvote +
			", articleCai=" + articleCai +
			", articleComment=" + articleComment +
			"}";
	}
}
