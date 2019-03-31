package com.xzy.entity;

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
public class Roleuser extends Model<Roleuser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色id
     */
    @TableId("role_userid")
	private Long roleUserid;
    /**
     * 用户id
     */
	@TableField("role_user_userid")
	private Long roleUserUserid;
    /**
     * 角色id
     */
	@TableField("role_user_roleid")
	private Long roleUserRoleid;


	public Long getRoleUserid() {
		return roleUserid;
	}

	public void setRoleUserid(Long roleUserid) {
		this.roleUserid = roleUserid;
	}

	public Long getRoleUserUserid() {
		return roleUserUserid;
	}

	public void setRoleUserUserid(Long roleUserUserid) {
		this.roleUserUserid = roleUserUserid;
	}

	public Long getRoleUserRoleid() {
		return roleUserRoleid;
	}

	public void setRoleUserRoleid(Long roleUserRoleid) {
		this.roleUserRoleid = roleUserRoleid;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleUserid;
	}

	@Override
	public String toString() {
		return "Roleuser{" +
			"roleUserid=" + roleUserid +
			", roleUserUserid=" + roleUserUserid +
			", roleUserRoleid=" + roleUserRoleid +
			"}";
	}
}
