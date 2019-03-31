package com.xzy.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
	@TableId(value="userid", type= IdType.AUTO)
	private Long userid;
    /**
     * 用户邮箱
     */
	private String useremail;
    /**
     * 用户密码
     */
	private String userpassword;
    /**
     * 用户电话
     */
	private String userphone;
    /**
     * 用户昵称
     */
	private String username;
    /**
     * 用户激活状态
     */
	private String userstatus;
    /**
     * 用户头像
     */
	private String userhead;
    /**
     * 用户是否可用
     */
	private String userenable;


	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}

	public String getUserenable() {
		return userenable;
	}

	public void setUserenable(String userenable) {
		this.userenable = userenable;
	}

	@Override
	protected Serializable pkVal() {
		return this.userid;
	}

	@Override
	public String toString() {
		return "User{" +
			"userid=" + userid +
			", useremail=" + useremail +
			", userpassword=" + userpassword +
			", userphone=" + userphone +
			", username=" + username +
			", userstatus=" + userstatus +
			", userhead=" + userhead +
			", userenable=" + userenable +
			"}";
	}
}
