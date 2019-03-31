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
public class Roleresource extends Model<Roleresource> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色资源id
     */
    @TableId("role_resourceid")
	private Long roleResourceid;
    /**
     * 角色id
     */
	@TableField("role_resource_roleid")
	private Long roleResourceRoleid;
    /**
     * 资源id
     */
	@TableField("role_resource_resourceid")
	private Long roleResourceResourceid;


	public Long getRoleResourceid() {
		return roleResourceid;
	}

	public void setRoleResourceid(Long roleResourceid) {
		this.roleResourceid = roleResourceid;
	}

	public Long getRoleResourceRoleid() {
		return roleResourceRoleid;
	}

	public void setRoleResourceRoleid(Long roleResourceRoleid) {
		this.roleResourceRoleid = roleResourceRoleid;
	}

	public Long getRoleResourceResourceid() {
		return roleResourceResourceid;
	}

	public void setRoleResourceResourceid(Long roleResourceResourceid) {
		this.roleResourceResourceid = roleResourceResourceid;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleResourceid;
	}

	@Override
	public String toString() {
		return "Roleresource{" +
			"roleResourceid=" + roleResourceid +
			", roleResourceRoleid=" + roleResourceRoleid +
			", roleResourceResourceid=" + roleResourceResourceid +
			"}";
	}
}
