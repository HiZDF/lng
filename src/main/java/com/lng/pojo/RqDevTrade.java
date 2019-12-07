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
 * RqDevTrade generated by hbm2java
 */
@Entity
@Table(name = "rq_dev_trade", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("燃气设备买卖--RqDevTrade")
public class RqDevTrade implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "公司编号")
	private Company company;
	@ApiModelProperty(value = "设备类目")
	private RqDevType rqDevType;
	@ApiModelProperty(value = "设备种类")
	private RqDevType1 rqDevType1;
	@ApiModelProperty(value = "燃气设备主图")
	private String mainImg;
	@ApiModelProperty(value = "设备名称")
	private String devName;
	@ApiModelProperty(value = "设备型号")
	private String devNo;
	@ApiModelProperty(value = "生产厂家")
	private String devPp;
	@ApiModelProperty(value = "官方报价")
	private double devPrice;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "联系人")
	private String lxName;
	@ApiModelProperty(value = "联系电话")
	private String lxTel;
	@ApiModelProperty(value = "审核状态")
	private int checkStatus;
	@ApiModelProperty(value = "审核时间")
	private String checkTime;
	@ApiModelProperty(value = "上/下架状态")
	private int showStatus;
	@ApiModelProperty(value = "上传人员")
	private String addUserId;
	@ApiModelProperty(value = "添加时间")
	private String addTime;
	@ApiModelProperty(value = "上传人员类型")
	private int userType;
	@ApiModelProperty(value = "热度")
	private int hot;

	public RqDevTrade() {
	}

	public RqDevTrade(Company company, RqDevType rqDevType, RqDevType1 rqDevType1, String mainImg,
			String devName, String devNo, String devPp, double devPrice, String description, String lxName,
			String lxTel, int checkStatus, String checkTime, int showStatus, String addUserId, String addTime,
			int userType, int hot) {
		this.company = company;
		this.rqDevType = rqDevType;
		this.rqDevType1 = rqDevType1;
		this.mainImg = mainImg;
		this.devName = devName;
		this.devNo = devNo;
		this.devPp = devPp;
		this.devPrice = devPrice;
		this.description = description;
		this.lxName = lxName;
		this.lxTel = lxTel;
		this.checkStatus = checkStatus;
		this.checkTime = checkTime;
		this.showStatus = showStatus;
		this.addUserId = addUserId;
		this.addTime = addTime;
		this.userType = userType;
		this.hot = hot;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", nullable = false, columnDefinition = "varchar(100) COMMENT '公司编号'")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dev_type_id1", nullable = false, columnDefinition = "varchar(100) COMMENT '设备类目'")
	public RqDevType getRqDevType() {
		return this.rqDevType;
	}

	public void setRqDevType(RqDevType rqDevType) {
		this.rqDevType = rqDevType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dev_type_id2", nullable = false, columnDefinition = "varchar(100) COMMENT '设备种类'")
	public RqDevType1 getRqDevType1() {
		return this.rqDevType1;
	}

	public void setRqDevType1(RqDevType1 rqDevType1) {
		this.rqDevType1 = rqDevType1;
	}

	@Column(name = "main_img", nullable = false, columnDefinition = "varchar(100) COMMENT '燃气设备主图'")
	public String getMainImg() {
		return this.mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	@Column(name = "dev_name", nullable = false, columnDefinition = "varchar(100) COMMENT '设备名称'")
	public String getDevName() {
		return this.devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	@Column(name = "dev_no", nullable = false, columnDefinition = "varchar(50) COMMENT '设备型号'")
	public String getDevNo() {
		return this.devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	@Column(name = "dev_pp", nullable = false, columnDefinition = "varchar(50) COMMENT '生产厂家'")
	public String getDevPp() {
		return this.devPp;
	}

	public void setDevPp(String devPp) {
		this.devPp = devPp;
	}

	@Column(name = "dev_price", nullable = false, precision = 10, columnDefinition = "COMMENT '官方报价'")
	public double getDevPrice() {
		return this.devPrice;
	}

	public void setDevPrice(double devPrice) {
		this.devPrice = devPrice;
	}

	@Column(name = "description", columnDefinition = "varchar(65535) COMMENT '描述'")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "lx_name", columnDefinition = "varchar(30) COMMENT '联系人'")
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

	@Column(name = "show_status", nullable = false, columnDefinition = "int(11) COMMENT '上/下架状态（0：上架，1：下架）'")
	public int getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(int showStatus) {
		this.showStatus = showStatus;
	}

	@Column(name = "add_user_id", nullable = false, columnDefinition = "varchar(100) COMMENT '上传人员'")
	public String getAddUserId() {
		return this.addUserId;
	}

	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	@Column(name = "add_time", nullable = false, columnDefinition = "varchar(50) COMMENT '添加时间'")
	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "user_type", nullable = false, columnDefinition = "int(11) COMMENT '上传人员类型（1：后台管理人员，2：普通用户）'")
	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Column(name = "hot", nullable = false, columnDefinition = "int(11) COMMENT '热度（默认为0）'")
	public int getHot() {
		return this.hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

}