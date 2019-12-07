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
 * GasTradeOrder generated by hbm2java
 */
@Entity
@Table(name = "gas_trade_order", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("燃气交易订单--GasTradeOrder")
public class GasTradeOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "公司编号")
	private Company company;
	@ApiModelProperty(value = "燃气交易编号")
	private GasTrade gasTrade;
	@ApiModelProperty(value = "买气人编号")
	private User user;
	@ApiModelProperty(value = "购买人电话")
	private String lxMobile;
	@ApiModelProperty(value = "出价")
	private double price;
	@ApiModelProperty(value = "备注")
	private String remark;
	@ApiModelProperty(value = "购买人省份")
	private String lxrProv;
	@ApiModelProperty(value = "城市")
	private String lxrCity;
	@ApiModelProperty(value = "详细地址")
	private String lxrAddress;
	@ApiModelProperty(value = "地理位置GPS信息")
	private String lxrGpsInfo;
	@ApiModelProperty(value = "距离位置(气源距离目的地距离)")
	private Integer distance;
	@ApiModelProperty(value = "添加时间")
	private String addTime;
	@ApiModelProperty(value = "订单状态")
	private int orderStatus;
	@ApiModelProperty(value = "评价分数")
	private Integer orderPjNumber;
	@ApiModelProperty(value = "评价内容")
	private String orderPjDetail;

	public GasTradeOrder() {
	}

	public GasTradeOrder(Company company, GasTrade gasTrade, User user, String lxMobile, double price,
			String remark, String lxrProv, String lxrCity, String lxrAddress, String lxrGpsInfo, Integer distance,
			String addTime, int orderStatus, Integer orderPjNumber, String orderPjDetail) {
		this.company = company;
		this.gasTrade = gasTrade;
		this.user = user;
		this.lxMobile = lxMobile;
		this.price = price;
		this.remark = remark;
		this.lxrProv = lxrProv;
		this.lxrCity = lxrCity;
		this.lxrAddress = lxrAddress;
		this.lxrGpsInfo = lxrGpsInfo;
		this.distance = distance;
		this.addTime = addTime;
		this.orderStatus = orderStatus;
		this.orderPjNumber = orderPjNumber;
		this.orderPjDetail = orderPjDetail;
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
	@JoinColumn(name = "company_id", nullable = false, columnDefinition = "varchar(100) COMMENT '买气人所在公司'")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gas_trade_id", nullable = false, columnDefinition = "varchar(100) COMMENT '燃气交易编号'")
	public GasTrade getGasTrade() {
		return this.gasTrade;
	}

	public void setGasTrade(GasTrade gasTrade) {
		this.gasTrade = gasTrade;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, columnDefinition = "varchar(100) COMMENT '买气人编号'")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "lx_mobile", nullable = false, columnDefinition = "varchar(30) COMMENT '购买人电话'")
	public String getLxMobile() {
		return this.lxMobile;
	}

	public void setLxMobile(String lxMobile) {
		this.lxMobile = lxMobile;
	}

	@Column(name = "price", nullable = false, precision = 10, columnDefinition = "COMMENT '出价'")
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注'")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "lxr_prov", nullable = false, columnDefinition = "varchar(100) COMMENT '购买人省份'")
	public String getLxrProv() {
		return this.lxrProv;
	}

	public void setLxrProv(String lxrProv) {
		this.lxrProv = lxrProv;
	}

	@Column(name = "lxr_city", nullable = false, columnDefinition = "varchar(100) COMMENT '购买人城市'")
	public String getLxrCity() {
		return this.lxrCity;
	}

	public void setLxrCity(String lxrCity) {
		this.lxrCity = lxrCity;
	}

	@Column(name = "lxr_address", nullable = false, columnDefinition = "varchar(200) COMMENT '详细地址'")
	public String getLxrAddress() {
		return this.lxrAddress;
	}

	public void setLxrAddress(String lxrAddress) {
		this.lxrAddress = lxrAddress;
	}

	@Column(name = "lxr_gps_info", columnDefinition = "varchar(200) COMMENT '地理位置GPS信息'")
	public String getLxrGpsInfo() {
		return this.lxrGpsInfo;
	}

	public void setLxrGpsInfo(String lxrGpsInfo) {
		this.lxrGpsInfo = lxrGpsInfo;
	}

	@Column(name = "distance",columnDefinition = "int(11) COMMENT '地理位置GPS信息'")
	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Column(name = "add_time", nullable = false, columnDefinition = "varchar(50) COMMENT '距离位置(气源距离目的地距离)'")
	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "order_status", nullable = false, columnDefinition = "int(11) COMMENT '订单状态'")
	public int getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "order_pj_number", columnDefinition = "int(11) COMMENT '评价分数1-3星（好，中，差评，未评价时为0）'")
	public Integer getOrderPjNumber() {
		return this.orderPjNumber;
	}

	public void setOrderPjNumber(Integer orderPjNumber) {
		this.orderPjNumber = orderPjNumber;
	}

	@Column(name = "order_pj_detail", columnDefinition = "varchar(200) COMMENT '评价内容'")
	public String getOrderPjDetail() {
		return this.orderPjDetail;
	}

	public void setOrderPjDetail(String orderPjDetail) {
		this.orderPjDetail = orderPjDetail;
	}

}
