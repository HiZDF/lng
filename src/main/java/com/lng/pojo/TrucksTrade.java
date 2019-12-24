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
 * TrucksTrade generated by hbm2java
 */
@Entity
@Table(name = "trucks_trade", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("货车租卖--TrucksTrade")
public class TrucksTrade implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "车头品牌")
	private TrucksHeadPp trucksHeadPp;
	@ApiModelProperty(value = "车头类型")
	private TrucksHeadType trucksHeadType;
	@ApiModelProperty(value = "车辆种类")
	private TrucksType trucksType;
	@ApiModelProperty(value = "公司编号")
	private String companyId;
	@ApiModelProperty(value = "车辆主图")
	private String mainImg;
	@ApiModelProperty(value = "车牌号码")
	private String trucksNo;
	@ApiModelProperty(value = "上牌年份")
	private String spYear;
	@ApiModelProperty(value = "储罐容积")
	private int potVolume;
	@ApiModelProperty(value = "储罐上牌年月")
	private String spYearPot;
	@ApiModelProperty(value = "储罐品牌")
	private String potPpId;
	@ApiModelProperty(value = "购置年份")
	private String buyYear;
	@ApiModelProperty(value = "车辆行驶里程")
	private int xsDistance;
	@ApiModelProperty(value = "车辆价格")
	private int price;
	@ApiModelProperty(value = "车辆注册地")
	private  String regPlace;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "联系人")
	private String lxName;
	@ApiModelProperty(value = "联系电话")
	private String lxTel;
	@ApiModelProperty(value = "审核状态(0:未审核,1:审核通过,2:审核未通过)")
	private int checkStatus;
	@ApiModelProperty(value = "审核时间")
	private String checkTime;
	@ApiModelProperty(value = "上/下架状态（0：上架，1：下架）")
	private int showStatus;
	@ApiModelProperty(value = "上传人员")
	private String addUserId;
	@ApiModelProperty(value = "添加时间")
	private String addTime;
	@ApiModelProperty(value = "上传人员类型（1：后台管理人员，2：普通用户）")
	private int userType;
	@ApiModelProperty(value = "热度（默认为0）")
	private int hot;
	@ApiModelProperty(value = "贸易类型（1：租赁，2：买卖）")
	private int tradeType;
	@ApiModelProperty(value = "运输范围（租赁）")
	private String area;
	@ApiModelProperty(value = "气源类型")
	private String qyTypeId;
	@ApiModelProperty(value = "尾气排放标准")
	private WqPfbz wqPfbz;
	@ApiModelProperty(value = "是否发生事故")
	private int accidentFlag;
	@ApiModelProperty(value = "车头行驶证")
	private String trucksHeadxsz;
	@ApiModelProperty(value = "罐车行驶证")
	private String gcXsz;
	@ApiModelProperty(value = "车辆运营证")
	private String tructsYyz;
	@ApiModelProperty(value = "储罐检验合格证")
	private String potJyz;
	@ApiModelProperty(value = "安全阀校验报告")
	private String aqfBg;
	
	public TrucksTrade() {
	}

	public TrucksTrade(TrucksHeadPp trucksHeadPp, TrucksHeadType trucksHeadType, TrucksType trucksType,
			String companyId, String mainImg, String trucksNo, String spYear, int potVolume, String spYearPot,
			String potPpId, String buyYear, int xsDistance, int price, String regPlace, String remark, String lxName, String lxTel, int checkStatus,
			String checkTime, int showStatus, String addUserId, String addTime, int userType, int hot, int tradeType,
			String area, String qyTypeId, WqPfbz wqPfbz, int accidentFlag, String trucksHeadxsz, String gcXsz,
			String tructsYyz, String potJyz, String aqfBg) {
		this.trucksHeadPp = trucksHeadPp;
		this.trucksHeadType = trucksHeadType;
		this.trucksType = trucksType;
		this.companyId = companyId;
		this.mainImg = mainImg;
		this.trucksNo = trucksNo;
		this.spYear = spYear;
		this.potVolume = potVolume;
		this.spYearPot = spYearPot;
		this.potPpId = potPpId;
		this.buyYear = buyYear;
		this.xsDistance = xsDistance;
		this.price = price;
		this.regPlace  = regPlace;
		this.remark = remark;
		this.lxName = lxName;
		this.lxTel = lxTel;
		this.checkStatus = checkStatus;
		this.checkTime = checkTime;
		this.showStatus = showStatus;
		this.addUserId = addUserId;
		this.addTime = addTime;
		this.userType = userType;
		this.hot = hot;
		this.tradeType = tradeType;
		this.area = area;
		this.qyTypeId = qyTypeId;
		this.wqPfbz = wqPfbz;
		this.accidentFlag = accidentFlag;
		this.trucksHeadxsz = trucksHeadxsz;
		this.gcXsz = gcXsz;
		this.tructsYyz = tructsYyz;
		this.potJyz = potJyz;
		this.aqfBg = aqfBg;
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
	@JoinColumn(name = "head_pp_id", nullable = false,columnDefinition = "varchar(100) COMMENT '车头品牌'")
	public TrucksHeadPp getTrucksHeadPp() {
		return this.trucksHeadPp;
	}

	public void setTrucksHeadPp(TrucksHeadPp trucksHeadPp) {
		this.trucksHeadPp = trucksHeadPp;
	}

	@ManyToOne(fetch = FetchType.EAGER)//等同于lazy="false"
	@JoinColumn(name = "head_type_id", nullable = false,columnDefinition = "varchar(100) COMMENT '车头类型'")
	public TrucksHeadType getTrucksHeadType() {
		return this.trucksHeadType;
	}

	public void setTrucksHeadType(TrucksHeadType trucksHeadType) {
		this.trucksHeadType = trucksHeadType;
	}

	@ManyToOne(fetch = FetchType.EAGER)//等同于lazy="false"
	@JoinColumn(name = "trucks_type_id", nullable = false,columnDefinition = "varchar(100) COMMENT '车头种类'")
	public TrucksType getTrucksType() {
		return this.trucksType;
	}

	public void setTrucksType(TrucksType trucksType) {
		this.trucksType = trucksType;
	}

	@Column(name = "company_id", nullable = false,columnDefinition = "varchar(100) COMMENT '公司编号'")
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "main_img", nullable = false,columnDefinition = "varchar(100) COMMENT '车辆主图'")
	public String getMainImg() {
		return this.mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	@Column(name = "trucks_no", nullable = false, columnDefinition = "varchar(50) COMMENT '车辆号码'")
	public String getTrucksNo() {
		return this.trucksNo;
	}

	public void setTrucksNo(String trucksNo) {
		this.trucksNo = trucksNo;
	}

	@Column(name = "sp_year", nullable = false,columnDefinition = "varchar(50) COMMENT '上牌年份'")
	public String getSpYear() {
		return this.spYear;
	}

	public void setSpYear(String spYear) {
		this.spYear = spYear;
	}

	@Column(name = "pot_volume", nullable = false,columnDefinition = "varchar(50) COMMENT '储罐容积")
	public int getPotVolume() {
		return this.potVolume;
	}

	public void setPotVolume(int potVolume) {
		this.potVolume = potVolume;
	}
	@Column(name = "sp_year_pot",columnDefinition = "varchar(100) COMMENT '储罐上牌年月'")
	public String getSpYearPot() {
		return spYearPot;
	}

	public void setSpYearPot(String spYearPot) {
		this.spYearPot = spYearPot;
	}

	@Column(name = "pot_pp_id", nullable = false, columnDefinition = "varchar(100) COMMENT '储罐品牌")
	public String getPotPpId() {
		return this.potPpId;
	}

	public void setPotPpId(String potPpId) {
		this.potPpId = potPpId;
	}

	@Column(name = "buy_year",  columnDefinition = "varchar(50) COMMENT '购置年份'")
	public String getBuyYear() {
		return this.buyYear;
	}

	public void setBuyYear(String buyYear) {
		this.buyYear = buyYear;
	}

	@Column(name = "xs_distance", nullable = false ,columnDefinition = "int(11) COMMENT '车辆行驶里程'")
	public int getXsDistance() {
		return this.xsDistance;
	}

	public void setXsDistance(int xsDistance) {
		this.xsDistance = xsDistance;
	}

	@Column(name = "remark",columnDefinition = "varchar(255) COMMENT '备注'")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "lx_name",columnDefinition = "varchar(30) COMMENT '联系人'")
	public String getLxName() {
		return this.lxName;
	}

	public void setLxName(String lxName) {
		this.lxName = lxName;
	}

	@Column(name = "lx_tel",columnDefinition = "varchar(30) COMMENT'联系电话'")
	public String getLxTel() {
		return this.lxTel;
	}

	public void setLxTel(String lxTel) {
		this.lxTel = lxTel;
	}

	@Column(name = "check_status", nullable = false,columnDefinition = "int(11) COMMENT'审核状态(0:未审核,1:审核通过,2:审核未通过)'")
	public int getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "check_time", columnDefinition = "varchar(50) COMMENT'审核时间'")
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "show_status", nullable = false, columnDefinition = "int(11) COMMENT'上/下架状态（0：上架，1：下架）'")
	public int getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(int showStatus) {
		this.showStatus = showStatus;
	}

	@Column(name = "add_user_id", nullable = false,columnDefinition = "varchar(100) COMMENT'上传人员'")
	public String getAddUserId() {
		return this.addUserId;
	}

	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	@Column(name = "add_time", nullable = false,columnDefinition = "varchar(50) COMMENT'添加时间'")
	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "price",nullable = false, columnDefinition = "int(11) COMMENT'车辆价格'")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	@Column(name = "reg_place", columnDefinition = "varchar(100) COMMENT'注册地'")
	public String getRegPlace() {
		return regPlace;
	}

	public void setRegPlace(String regPlace) {
		this.regPlace = regPlace;
	}

	@Column(name = "user_type", nullable = false,columnDefinition = "int(11) COMMENT'上传人员类型（1：后台管理人员，2：普通用户）'")
	public int getUserType() {
		return this.userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Column(name = "hot", nullable = false,columnDefinition = "int(11) COMMENT'热度（默认为0）'")
	public int getHot() {
		return this.hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	@Column(name = "trade_type", nullable = false,columnDefinition = "int(11) COMMENT'贸易类型（1：租赁，2：买卖）'")
	public int getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	@Column(name = "area",columnDefinition = "varchar(200) COMMENT'运输范围（租赁）'")
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "qy_type_id", nullable = false,columnDefinition = "varchar(100) COMMENT'气源类型'")
	public String getQyTypeId() {
		return this.qyTypeId;
	}

	public void setQyTypeId(String qyTypeId) {
		this.qyTypeId = qyTypeId;
	}
	@ManyToOne(fetch = FetchType.EAGER)//等同于lazy="false"
	@JoinColumn(name = "wq_pfbz_id", nullable = false,columnDefinition = "varchar(100) COMMENT '尾气排放标准编号'")
	public WqPfbz getWqPfbz() {
		return wqPfbz;
	}

	public void setWqPfbz(WqPfbz wqPfbz) {
		this.wqPfbz = wqPfbz;
	}
	@Column(name = "accident_flag",columnDefinition = "int(11) COMMENT'是否发生事故（1：是,2：否，0：不填）'")
	public int getAccidentFlag() {
		return accidentFlag;
	}

	public void setAccidentFlag(int accidentFlag) {
		this.accidentFlag = accidentFlag;
	}
	@Column(name = "tructs_head_xsz",columnDefinition = "varchar(100) COMMENT'车头行驶证（危货）'")
	public String getTrucksHeadxsz() {
		return trucksHeadxsz;
	}

	public void setTrucksHeadxsz(String trucksHeadxsz) {
		this.trucksHeadxsz = trucksHeadxsz;
	}
	@Column(name = "gc_xsz",columnDefinition = "varchar(100) COMMENT'罐车行驶证（危货）'")
	public String getGcXsz() {
		return gcXsz;
	}

	public void setGcXsz(String gcXsz) {
		this.gcXsz = gcXsz;
	}
	@Column(name = "tructs_yyz",columnDefinition = "varchar(100) COMMENT'车辆运营证（危货）'")
	public String getTructsYyz() {
		return tructsYyz;
	}

	public void setTructsYyz(String tructsYyz) {
		this.tructsYyz = tructsYyz;
	}
	@Column(name = "pot_jyz",columnDefinition = "varchar(100) COMMENT'储罐检验合格证（危货）'")
	public String getPotJyz() {
		return potJyz;
	}

	public void setPotJyz(String potJyz) {
		this.potJyz = potJyz;
	}
	@Column(name = "aqf_bg",columnDefinition = "varchar(100) COMMENT'安全阀校验报告（危货）'")
	public String getAqfBg() {
		return aqfBg;
	}

	public void setAqfBg(String aqfBg) {
		this.aqfBg = aqfBg;
	}
	
	
}
