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
 * TructsTradeZz generated by hbm2java
 */
@Entity
@Table(name = "tructs_trade_zz", catalog = "lng")
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("槽车租卖资质图片--TructsTradeZz")
public class TructsTradeZz implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	@ApiModelProperty(value = "槽车租卖编号")
	private TrucksTrade trucksTrade;
	@ApiModelProperty(value = "槽车资质")
	private String tructsTradeZz;

	public TructsTradeZz() {
	}

	public TructsTradeZz(TrucksTrade trucksTrade, String tructsTradeZz) {
		this.trucksTrade = trucksTrade;
		this.tructsTradeZz = tructsTradeZz;
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
	@JoinColumn(name = "tructs_trade_id", nullable = false,columnDefinition = "varchar(100) COMMENT '槽车租卖编号'")
	public TrucksTrade getTrucksTrade() {
		return this.trucksTrade;
	}

	public void setTrucksTrade(TrucksTrade trucksTrade) {
		this.trucksTrade = trucksTrade;
	}

	@Column(name = "tructs_trade_zz", nullable = false, columnDefinition = "varchar(100) COMMENT '槽车资质'")
	public String getTructsTradeZz() {
		return this.tructsTradeZz;
	}

	public void setTructsTradeZz(String tructsTradeZz) {
		this.tructsTradeZz = tructsTradeZz;
	}

}
