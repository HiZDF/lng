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
 * GasFactory generated by hbm2java
 */
@Entity
@Table(name = "gas_factory", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("液厂--GasFactory")
public class GasFactory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "液质类型编号")
	private GasType gasType;
	@ApiModelProperty(value = "液厂名称")
	private String name;
	@ApiModelProperty(value = "液厂名称拼音码")
	private String namePy;
	@ApiModelProperty(value = "液厂图片")
	private String facImage;
	@ApiModelProperty(value = "省")
	private String province;
	@ApiModelProperty(value = "市")
	private String city;
	@ApiModelProperty(value = "县")
	private String county;
	@ApiModelProperty(value = "详细地址")
	private String address;
	@ApiModelProperty(value = "联系人")
	private String lxName;
	@ApiModelProperty(value = "联系电话")
	private String lxTel;
	@ApiModelProperty(value = "添加时间")
	private String addTime;
	@ApiModelProperty(value = "液质报告")
	private String yzbgImg;
	@ApiModelProperty(value = "审核状态")
	private int checkStatus;
	@ApiModelProperty(value = "审核时间")
	private String checkTime;
	@ApiModelProperty(value = "液厂负责人")
	private String owerUserId;

	public GasFactory() {
	}

	public GasFactory( GasType gasType, String name, String namePy, String facImage, String province, String city,
			String county, String address, String lxName, String lxTel, String addTime, String yzbgImg, int checkStatus,
			String checkTime,String owerUserId) {
		this.gasType = gasType;
		this.name = name;
		this.namePy = namePy;
		this.facImage = facImage;
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.lxName = lxName;
		this.lxTel = lxTel;
		this.addTime = addTime;
		this.yzbgImg = yzbgImg;
		this.checkStatus = checkStatus;
		this.checkTime = checkTime;
		this.owerUserId = owerUserId;
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
	@JoinColumn(name = "gas_type_id", nullable = false, columnDefinition = "varchar(100) COMMENT '液质类型编号'")
	public GasType getGasType() {
		return this.gasType;
	}

	public void setGasType(GasType gasType) {
		this.gasType = gasType;
	}

	@Column(name = "name", nullable = false, columnDefinition = "varchar(100) COMMENT '液厂名称'")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fac_image", nullable = true, columnDefinition = "varchar(100) COMMENT '液厂图片'")
	public String getFacImage() {
		return this.facImage;
	}

	public void setFacImage(String facImage) {
		this.facImage = facImage;
	}

	@Column(name = "province", columnDefinition = "varchar(50) COMMENT '省'")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", columnDefinition = "varchar(50) COMMENT '市'")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "county", columnDefinition = "varchar(100) COMMENT '县'")
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "address", columnDefinition = "varchar(150) COMMENT '详细地址'")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "lx_name",  columnDefinition = "varchar(30) COMMENT '联系人'")
	public String getLxName() {
		return this.lxName;
	}

	public void setLxName(String lxName) {
		this.lxName = lxName;
	}

	@Column(name = "lx_tel", columnDefinition = "varchar(30) COMMENT '联系电话'")
	public String getLxTel() {
		return this.lxTel;
	}

	public void setLxTel(String lxTel) {
		this.lxTel = lxTel;
	}

	@Column(name = "add_time", columnDefinition = "varchar(50) COMMENT '添加时间'")
	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "yzbg_img", columnDefinition = "varchar(100) COMMENT '液质报告'")
	public String getYzbgImg() {
		return this.yzbgImg;
	}

	public void setYzbgImg(String yzbgImg) {
		this.yzbgImg = yzbgImg;
	}

	@Column(name = "check_status", nullable = false, columnDefinition = "int(11) COMMENT '审核状态(0:未审核,1:审核通过,2:审核未通过)'")
	public int getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "check_time", columnDefinition = "varchar(50) COMMENT '审核时间'")
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "ower_user_id", columnDefinition = "varchar(100) COMMENT '液厂负责人'")
	public String getOwerUserId() {
		return owerUserId;
	}

	public void setOwerUserId(String owerUserId) {
		this.owerUserId = owerUserId;
	}

	@Column(name = "name_py", nullable = false, columnDefinition = "varchar(100) COMMENT '液厂名称拼音码'")
	public String getNamePy() {
		return namePy;
	}

	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}

}