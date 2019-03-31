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
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
	@TableId(value="roleid", type= IdType.AUTO)
	private Long roleid;
    /**
     * 角色名称
     */
	private String rolename;
    /**
     * 角色值
     */
	private String rolevalue;
    /**
     * 角色可用url
     */
	private String roleenableurl;
    /**
     * 角色是否可用
     */
	private String roleenable;
    /**
     * 备注
     */
	private String roleremark;


	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolevalue() {
		return rolevalue;
	}

	public void setRolevalue(String rolevalue) {
		this.rolevalue = rolevalue;
	}

	public String getRoleenableurl() {
		return roleenableurl;
	}

	public void setRoleenableurl(String roleenableurl) {
		this.roleenableurl = roleenableurl;
	}

	public String getRoleenable() {
		return roleenable;
	}

	public void setRoleenable(String roleenable) {
		this.roleenable = roleenable;
	}

	public String getRoleremark() {
		return roleremark;
	}

	public void setRoleremark(String roleremark) {
		this.roleremark = roleremark;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleid;
	}

	@Override
	public String toString() {
		return "Role{" +
			"roleid=" + roleid +
			", rolename=" + rolename +
			", rolevalue=" + rolevalue +
			", roleenableurl=" + roleenableurl +
			", roleenable=" + roleenable +
			", roleremark=" + roleremark +
			"}";
	}
}
