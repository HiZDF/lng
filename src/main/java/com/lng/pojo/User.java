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
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("前台用户--User")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "微信名称")
	private String wxName;
	@ApiModelProperty(value = "真实姓名")
	private String realName;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "注册时间")
	private String signDate;
	@ApiModelProperty(value = "上次登录时间")
	private String lastLoginTime;
	@ApiModelProperty(value = "登陆次数")
	private Integer loginTimes;
	@ApiModelProperty(value = "登陆状态(满50归零)")
	private Integer loginStatus;
	@ApiModelProperty(value = "账号状态0:无效,1:有效")
	private Integer accountStatus;
	@ApiModelProperty(value = "账号类型（wx,qq,pc）")
	private String accountType;
	@ApiModelProperty(value = "用户头像")
	private String userPortrait;
	@ApiModelProperty(value = "特殊功能(lng:增加，修改）多个逗号隔开")
	private String specFun;

	public User() {
	}

	public User( String account, String password, String accountType) {
		this.account = account;
		this.password = password;
		this.accountType = accountType;
	}

	public User(String account, String password, String wxName, String realName, String sex, String mobile,
			String signDate, String lastLoginTime, Integer loginTimes, Integer loginStatus, Integer accountStatus,
			String accountType, String userPortrait, String specFun) {
		this.account = account;
		this.password = password;
		this.wxName = wxName;
		this.realName = realName;
		this.sex = sex;
		this.mobile = mobile;
		this.signDate = signDate;
		this.lastLoginTime = lastLoginTime;
		this.loginTimes = loginTimes;
		this.loginStatus = loginStatus;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
		this.userPortrait = userPortrait;
		this.specFun = specFun;
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

	@Column(name = "account", nullable = false,columnDefinition = "varchar(50) COMMENT '账号'")
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", nullable = false, columnDefinition = "varchar(30) COMMENT '密码'")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "wx_name",columnDefinition = "varchar(100) COMMENT '微信名称'")
	public String getWxName() {
		return this.wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	@Column(name = "real_name", columnDefinition = "varchar(30) COMMENT '真实姓名'")
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "sex",columnDefinition = "varchar(3100) COMMENT '性别'")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "mobile", columnDefinition = "varchar(20) COMMENT '手机号'")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "sign_date", columnDefinition = "varchar(30) COMMENT '注册时间'")
	public String getSignDate() {
		return this.signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	@Column(name = "last_login_time", columnDefinition = "varchar(5100) COMMENT '上次登录时间'")
	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "login_times",columnDefinition = "int(11) COMMENT '登陆次数'")
	public Integer getLoginTimes() {
		return this.loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	@Column(name = "login_status",columnDefinition = "int(11) COMMENT '登陆状态(满50归零)'")
	public Integer getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Column(name = "account_status",columnDefinition = "int(11) COMMENT '账号状态0:无效,1:有效'")
	public Integer getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name = "account_type", nullable = false,columnDefinition = "varchar(30) COMMENT '账号类型 wx,qq,pc'")
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "user_portrait",columnDefinition = "varchar(100) COMMENT '用户头像'")
	public String getUserPortrait() {
		return this.userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}

	@Column(name = "spec_fun",columnDefinition = "varchar(256) COMMENT '特殊功能(lng:增加，修改）多个逗号隔开'")
	public String getSpecFun() {
		return this.specFun;
	}

	public void setSpecFun(String specFun) {
		this.specFun = specFun;
	}

}