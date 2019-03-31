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
public class Loginlog extends Model<Loginlog> {

    private static final long serialVersionUID = 1L;

	@TableId(value="login_logid", type= IdType.AUTO)
	private Long loginLogid;
    /**
     * 用户id
     */
	@TableField("login_userid")
	private Long loginUserid;
    /**
     * 登陆者ip
     */
	@TableField("login_logip")
	private String loginLogip;
    /**
     * 登录时间
     */
	@TableField("login_time")
	private Date loginTime;


	public Long getLoginLogid() {
		return loginLogid;
	}

	public void setLoginLogid(Long loginLogid) {
		this.loginLogid = loginLogid;
	}

	public Long getLoginUserid() {
		return loginUserid;
	}

	public void setLoginUserid(Long loginUserid) {
		this.loginUserid = loginUserid;
	}

	public String getLoginLogip() {
		return loginLogip;
	}

	public void setLoginLogip(String loginLogip) {
		this.loginLogip = loginLogip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.loginLogid;
	}

	@Override
	public String toString() {
		return "Loginlog{" +
			"loginLogid=" + loginLogid +
			", loginUserid=" + loginUserid +
			", loginLogip=" + loginLogip +
			", loginTime=" + loginTime +
			"}";
	}
}
