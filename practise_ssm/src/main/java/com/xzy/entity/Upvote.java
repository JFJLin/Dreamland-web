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
public class Upvote extends Model<Upvote> {

    private static final long serialVersionUID = 1L;

    /**
     * 文章点赞数id
     */
	private Long upvoteid;
    /**
     * 点赞用户id
     */
	@TableField("upvote_userid")
	private Long upvoteUserid;
    /**
     * 文章id
     */
	@TableField("upvote_articleid")
	private Long upvoteArticleid;
    /**
     * 点赞用户ip
     */
	@TableField("upvote_useradd")
	private String upvoteUseradd;
    /**
     * 点赞
     */
	@TableField("upvote_control")
	private String upvoteControl;
    /**
     * 踩
     */
	@TableField("upvote_caicontrol")
	private String upvoteCaicontrol;
    /**
     * 点赞时间
     */
	@TableField("upvote_time")
	private Date upvoteTime;


	public Long getUpvoteid() {
		return upvoteid;
	}

	public void setUpvoteid(Long upvoteid) {
		this.upvoteid = upvoteid;
	}

	public Long getUpvoteUserid() {
		return upvoteUserid;
	}

	public void setUpvoteUserid(Long upvoteUserid) {
		this.upvoteUserid = upvoteUserid;
	}

	public Long getUpvoteArticleid() {
		return upvoteArticleid;
	}

	public void setUpvoteArticleid(Long upvoteArticleid) {
		this.upvoteArticleid = upvoteArticleid;
	}

	public String getUpvoteUseradd() {
		return upvoteUseradd;
	}

	public void setUpvoteUseradd(String upvoteUseradd) {
		this.upvoteUseradd = upvoteUseradd;
	}

	public String getUpvoteControl() {
		return upvoteControl;
	}

	public void setUpvoteControl(String upvoteControl) {
		this.upvoteControl = upvoteControl;
	}

	public String getUpvoteCaicontrol() {
		return upvoteCaicontrol;
	}

	public void setUpvoteCaicontrol(String upvoteCaicontrol) {
		this.upvoteCaicontrol = upvoteCaicontrol;
	}

	public Date getUpvoteTime() {
		return upvoteTime;
	}

	public void setUpvoteTime(Date upvoteTime) {
		this.upvoteTime = upvoteTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.upvoteid;
	}

	@Override
	public String toString() {
		return "Upvote{" +
			"upvoteid=" + upvoteid +
			", upvoteUserid=" + upvoteUserid +
			", upvoteArticleid=" + upvoteArticleid +
			", upvoteUseradd=" + upvoteUseradd +
			", upvoteControl=" + upvoteControl +
			", upvoteCaicontrol=" + upvoteCaicontrol +
			", upvoteTime=" + upvoteTime +
			"}";
	}
}
