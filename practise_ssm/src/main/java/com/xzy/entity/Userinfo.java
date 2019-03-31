package com.xzy.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Userinfo extends Model<Userinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户详细信息表id
     */
	@TableId(value="user_infoid", type= IdType.AUTO)
	private Long userInfoid;
    /**
     * 用户id
     */
	@TableField("user_info_userid")
	private Long userInfoUserid;
    /**
     * 用户姓名
     */
	@TableField("user_info_name")
	private String userInfoName;
    /**
     * 用户性别
     */
	@TableField("user_info_sex")
	private String userInfoSex;
    /**
     * 用户生日
     */
	@TableField("user_info_birth")
	private Date userInfoBirth;
    /**
     * 用户爱好
     */
	@TableField("user_info_hobby")
	private String userInfoHobby;
    /**
     * 用户地址
     */
	@TableField("use_info_add")
	private String useInfoAdd;


	public Long getUserInfoid() {
		return userInfoid;
	}

	public void setUserInfoid(Long userInfoid) {
		this.userInfoid = userInfoid;
	}

	public Long getUserInfoUserid() {
		return userInfoUserid;
	}

	public void setUserInfoUserid(Long userInfoUserid) {
		this.userInfoUserid = userInfoUserid;
	}

	public String getUserInfoName() {
		return userInfoName;
	}

	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}

	public String getUserInfoSex() {
		return userInfoSex;
	}

	public void setUserInfoSex(String userInfoSex) {
		this.userInfoSex = userInfoSex;
	}

	public Date getUserInfoBirth() {
		return userInfoBirth;
	}

	public void setUserInfoBirth(Date userInfoBirth) {
		this.userInfoBirth = userInfoBirth;
	}

	public String getUserInfoHobby() {
		return userInfoHobby;
	}

	public void setUserInfoHobby(String userInfoHobby) {
		this.userInfoHobby = userInfoHobby;
	}

	public String getUseInfoAdd() {
		return useInfoAdd;
	}

	public void setUseInfoAdd(String useInfoAdd) {
		this.useInfoAdd = useInfoAdd;
	}

	@Override
	protected Serializable pkVal() {
		return this.userInfoid;
	}

	@Override
	public String toString() {
		return "Userinfo{" +
			"userInfoid=" + userInfoid +
			", userInfoUserid=" + userInfoUserid +
			", userInfoName=" + userInfoName +
			", userInfoSex=" + userInfoSex +
			", userInfoBirth=" + userInfoBirth +
			", userInfoHobby=" + userInfoHobby +
			", useInfoAdd=" + useInfoAdd +
			"}";
	}
}
