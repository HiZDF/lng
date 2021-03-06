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
 * LngMessage generated by hbm2java
 */
@Entity
@Table(name = "lng_message", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("LNG行情留言--LngMessage")
public class LngMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "用户编号")
	private User user;
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "添加时间")
	private String addTime;
	@ApiModelProperty(value = "审核状态")
	private int checkStatus;
	@ApiModelProperty(value = "审核时间")
	private String checkTime;
	@ApiModelProperty(value = "显示状态")
	private int showStatus;
	@ApiModelProperty(value = "支持数")
	private int zcTimes;

	public LngMessage() {
	}

	public LngMessage(User user, String content, String addTime, int checkStatus, String checkTime,
			int showStatus,int zcTimes) {
		this.user = user;
		this.content = content;
		this.addTime = addTime;
		this.checkStatus = checkStatus;
		this.checkTime = checkTime;
		this.showStatus = showStatus;
		this.zcTimes = zcTimes;
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
	@JoinColumn(name = "user_id", nullable = false, columnDefinition = "varchar(100) COMMENT '用户编号'")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "content", nullable = false, columnDefinition = "varchar(200) COMMENT '内容'")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "add_time", nullable = false, columnDefinition = "varchar(200) COMMENT '添加时间'")
	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Column(name = "check_status", nullable = false, columnDefinition = "varchar(200) COMMENT '审核状态(0:未审核,1:审核通过,2:审核未通过)'")
	public int getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "check_time", length = 50, columnDefinition = "varchar(50) COMMENT '审核时间'")
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "show_status", nullable = false, columnDefinition = "int(11) COMMENT '显示状态（0：显示，1：隐藏）'")
	public int getShowStatus() {
		return this.showStatus;
	}

	public void setShowStatus(int showStatus) {
		this.showStatus = showStatus;
	}

	@Column(name = "zc_times", nullable = false, columnDefinition = "int(11) COMMENT '支持数'")
	public int getZcTimes() {
		return zcTimes;
	}

	public void setZcTimes(int zcTimes) {
		this.zcTimes = zcTimes;
	}

}
