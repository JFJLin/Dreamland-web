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
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
	@TableId(value="resourceid", type= IdType.AUTO)
	private Long resourceid;
    /**
     * 资源名称
     */
	private String resourcename;
    /**
     * 资源对应url
     */
	private String resourceurl;
    /**
     * 资源是否可用
     */
	private String resourceenable;
    /**
     * 资源备注
     */
	private String resourceremark;


	public Long getResourceid() {
		return resourceid;
	}

	public void setResourceid(Long resourceid) {
		this.resourceid = resourceid;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getResourceurl() {
		return resourceurl;
	}

	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
	}

	public String getResourceenable() {
		return resourceenable;
	}

	public void setResourceenable(String resourceenable) {
		this.resourceenable = resourceenable;
	}

	public String getResourceremark() {
		return resourceremark;
	}

	public void setResourceremark(String resourceremark) {
		this.resourceremark = resourceremark;
	}

	@Override
	protected Serializable pkVal() {
		return this.resourceid;
	}

	@Override
	public String toString() {
		return "Resource{" +
			"resourceid=" + resourceid +
			", resourcename=" + resourcename +
			", resourceurl=" + resourceurl +
			", resourceenable=" + resourceenable +
			", resourceremark=" + resourceremark +
			"}";
	}
}
