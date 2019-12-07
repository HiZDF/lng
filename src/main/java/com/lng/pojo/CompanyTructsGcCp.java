package com.lng.pojo;
// Generated 2019-12-2 10:34:35 by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * CompanyTructsGcCp generated by hbm2java
 */
@Entity
@Table(name = "company_tructs_gc_cp", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("公司挂车车牌--CompanyTructsGcCp")
public class CompanyTructsGcCp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "公司编号")
	private Company company;
	@ApiModelProperty(value = "挂车车牌号")
	private String trucksGch;

	public CompanyTructsGcCp() {
	}

	public CompanyTructsGcCp(String id, Company company, String trucksGch) {
		this.id = id;
		this.company = company;
		this.trucksGch = trucksGch;
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

	@ManyToOne(fetch = FetchType.EAGER)//等同于lazy="false"
	@JoinColumn(name = "company_id", nullable = false, columnDefinition = "varchar(100) COMMENT '公司编号'")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "trucks_gch", nullable = false, columnDefinition = "varchar(30) COMMENT '挂车车牌号'")
	public String getTrucksGch() {
		return this.trucksGch;
	}

	public void setTrucksGch(String trucksGch) {
		this.trucksGch = trucksGch;
	}

}