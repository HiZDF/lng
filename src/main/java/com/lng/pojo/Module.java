package com.lng.pojo;
// Generated 2019-12-2 10:34:35 by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Module generated by hbm2java
 */
@Entity
@Table(name = "module", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("模块--Module")
public class Module implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "模块名称")
	private String modName;
	@ApiModelProperty(value = "模块地址")
	private String modUrl;
	@ApiModelProperty(value = "模块排序")
	private int modOrder;
	@ApiModelProperty(value = "模块类型")
	private int modType;

	public Module() {
	}

	public Module(String modName, String modUrl, int modOrder, int modType) {
		this.modName = modName;
		this.modUrl = modUrl;
		this.modOrder = modOrder;
		this.modType = modType;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "mod_name", nullable = false, columnDefinition = "varchar(20) COMMENT '模块名称'")
	public String getModName() {
		return this.modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	@Column(name = "mod_url", nullable = false, columnDefinition = "varchar(100) COMMENT '模块地址'")
	public String getModUrl() {
		return this.modUrl;
	}

	public void setModUrl(String modUrl) {
		this.modUrl = modUrl;
	}

	@Column(name = "mod_order", nullable = false, columnDefinition = "int(11) COMMENT '模块排序'")
	public int getModOrder() {
		return this.modOrder;
	}

	public void setModOrder(int modOrder) {
		this.modOrder = modOrder;
	}

	@Column(name = "mod_type", nullable = true, columnDefinition = "int(11) COMMENT '模块类型(1:系统配置，2：功能模块)'")
	public int getModType() {
		return modType;
	}

	public void setModType(int modType) {
		this.modType = modType;
	}

}
