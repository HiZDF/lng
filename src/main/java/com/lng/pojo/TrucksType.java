package com.lng.pojo;
// Generated 2019-12-2 10:34:35 by Hibernate Tools 5.0.6.Final

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TrucksType generated by hbm2java
 */
@Entity
@Table(name = "trucks_type", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("槽车类型--TrucksType")
public class TrucksType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "车辆种类（油罐车。。。。）")
	private String name;
	@ApiModelProperty(value = "车辆类型（1：普货车，2：危货车）")
	private int type;

	public TrucksType() {
	}

	public TrucksType(String name, int type) {
		this.name = name;
		this.type = type;
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

	@Column(name = "name", nullable = false, columnDefinition = "varchar(50) COMMENT '车辆种类（油罐车。。。。）'")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false,columnDefinition = "int(11) COMMENT '车辆类型（1：普货车，2：危货车）")
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}
}