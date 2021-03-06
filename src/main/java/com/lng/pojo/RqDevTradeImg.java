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
 * RqDevTradeImg generated by hbm2java
 */
@Entity
@Table(name = "rq_dev_trade_img", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("燃气设备买卖详图--RqDevTradeImg")
public class RqDevTradeImg implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "燃气设备交易编号")
	private RqDevTrade rqDevTrade;
	@ApiModelProperty(value = "燃气设备详情图")
	private String reDevDetailImg;

	public RqDevTradeImg() {
	}

	public RqDevTradeImg(RqDevTrade rqDevTrade, String reDevDetailImg) {
		this.rqDevTrade = rqDevTrade;
		this.reDevDetailImg = reDevDetailImg;
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
	@JoinColumn(name = "rq_dev_trade_id", nullable = false, columnDefinition = "varchar(100) COMMENT '燃气设备交易编号'")
	public RqDevTrade getRqDevTrade() {
		return this.rqDevTrade;
	}

	public void setRqDevTrade(RqDevTrade rqDevTrade) {
		this.rqDevTrade = rqDevTrade;
	}

	@Column(name = "re_dev_detail_img", nullable = false, columnDefinition = "varchar(100) COMMENT '燃气设备详情图'")
	public String getReDevDetailImg() {
		return this.reDevDetailImg;
	}

	public void setReDevDetailImg(String reDevDetailImg) {
		this.reDevDetailImg = reDevDetailImg;
	}

}
