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
 * CompanyZz generated by hbm2java
 */
@Entity
@Table(name = "company_zz", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("公司执照--CompanyZz")
public class CompanyZz implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "公司编号")
	private Company company;
	@ApiModelProperty(value = "公司资质图片")
	private String companyZzImg;

	public CompanyZz() {
	}

	public CompanyZz(Company company, String companyZzImg) {
		this.company = company;
		this.companyZzImg = companyZzImg;
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

	@Column(name = "company_zz_img", nullable = false, columnDefinition = "varchar(100) COMMENT '公司资质图片'")
	public String getCompanyZzImg() {
		return this.companyZzImg;
	}

	public void setCompanyZzImg(String companyZzImg) {
		this.companyZzImg = companyZzImg;
	}

}
