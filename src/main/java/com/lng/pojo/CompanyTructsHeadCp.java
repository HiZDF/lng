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
 * CompanyTructsHeadCp generated by hbm2java
 */
@Entity
@Table(name = "company_tructs_head_cp", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("公司车头车牌--CompanyTructsHeadCp")
public class CompanyTructsHeadCp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "公司编号")
	private Company company;
	@ApiModelProperty(value = "车牌号")
	private String trucksCp;

	public CompanyTructsHeadCp() {
	}

	public CompanyTructsHeadCp(String id, Company company, String trucksCp) {
		this.id = id;
		this.company = company;
		this.trucksCp = trucksCp;
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

	@Column(name = "trucks_cp", nullable = false, columnDefinition = "varchar(30) COMMENT '车牌号'")
	public String getTrucksCp() {
		return this.trucksCp;
	}

	public void setTrucksCp(String trucksCp) {
		this.trucksCp = trucksCp;
	}

}
