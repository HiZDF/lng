package com.lng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lng.pojo.Company;
import com.lng.pojo.GasFactory;
import com.lng.pojo.GasTrade;
import com.lng.pojo.GasTradeOrder;
import com.lng.pojo.GasTradeOrderLog;
import com.lng.pojo.GasType;
import com.lng.pojo.MessageCenter;
import com.lng.pojo.User;
import com.lng.service.CompanyService;
import com.lng.service.GasTradeOrderLogService;
import com.lng.service.GasTradeOrderService;
import com.lng.service.GasTradeService;
import com.lng.service.MessageCenterService;
import com.lng.service.UserService;
import com.lng.tools.CommonTools;
import com.lng.tools.CurrentTime;
import com.lng.tools.EmojiDealUtil;
import com.lng.util.GenericResponse;
import com.lng.util.PageResponse;
import com.lng.util.ResponseFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "燃气交易订单相关接口")
@RequestMapping("/gtOrder")
public class GasTradeOrderController {

	@Autowired
	private GasTradeOrderService gtoSeriver;
	@Autowired
	private GasTradeService gtService;
	@Autowired
	private CompanyService cpyService;
	@Autowired
	private UserService uService;
	@Autowired
	private GasTradeOrderLogService gtolService;
	@Autowired
	private MessageCenterService mcs;
	
	@PostMapping("/addGasTraderOrder")
	@ApiOperation(value = "添加燃气交易订单", notes = "添加燃气交易订单信息并增加订单日志")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), 
			@ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 30003, message = "该交易审核未通过或已下架，不能进行操作"),
			@ApiResponse(code = 30004, message = "该产品正在交易中，不能进行下单"),
			@ApiResponse(code = 30005, message = "您已下过单，不能重复下单")
	})
	@ApiImplicitParams({ @ApiImplicitParam(name = "gtId", value = "燃气交易编号", required = true),
			@ApiImplicitParam(name = "userId", value = "买气人编号", required = true),
			@ApiImplicitParam(name = "cpyId", value = "买气人所在公司", required = true),
			@ApiImplicitParam(name = "lxMobile", value = "购买人电话", required = true),
			@ApiImplicitParam(name = "price", value = "出价", required = true),
			@ApiImplicitParam(name = "remark", value = "备注"),
			@ApiImplicitParam(name = "lxrProv", value = "购买人省份", required = true),
			@ApiImplicitParam(name = "lxrCity", value = "城市", required = true),
			@ApiImplicitParam(name = "lxrAddress", value = "详细地址", required = true),
			@ApiImplicitParam(name = "lxrGpsInfo", value = "地理位置GPS信息"),
			@ApiImplicitParam(name = "distance", value = "距离位置(气源距离目的地距离)"), })
	public GenericResponse addGasTraderOrder(HttpServletRequest request) {
		String gtId = CommonTools.getFinalStr("gtId", request);
		String userId = CommonTools.getFinalStr("userId", request);
		String cpyId = CommonTools.getFinalStr("cpyId", request);
		String lxMobile = CommonTools.getFinalStr("lxMobile", request);
		Double price = CommonTools.getFinalDouble("price", request);
		String remark = CommonTools.getFinalStr("remark", request);
		String lxrProv = CommonTools.getFinalStr("lxrProv", request);
		String lxrCity = CommonTools.getFinalStr("lxrCity", request);
		String lxrAddress = CommonTools.getFinalStr("lxrAddress", request);
		String lxrGpsInfo = CommonTools.getFinalStr("lxrGpsInfo", request);
		Integer distance = CommonTools.getFinalInteger("distance", request);
		Integer status = 200;
		String gtoId = "";
		try {
			GasTrade gt = gtService.getEntityById(gtId);
			if(gt.getCheckStatus() == 1 && gt.getShowStatus() == 0){//审核通过且上架的交易才能增加订单
				if(!gt.getTradeOrderId().equals("")) {//存在确认订单
					status = 30003;
				}else {
					List<GasTradeOrder> gtoList = gtoSeriver.listComInfoByOpt(userId, gtId);
					if(gtoList.size() > 0) {//当前用户没下过订单
						status = 30005;
					}else {
						GasTradeOrder gto = new GasTradeOrder();
						gto.setOrderNo(CurrentTime.getRadomTime().substring(2));
						GasTrade gasTrade = gtService.getEntityById(gtId);
						gto.setGasTrade(gasTrade);
						User user = uService.getEntityById(userId);
						gto.setUser(user);
						Company company = cpyService.getEntityById(cpyId);
						gto.setCompany(company);
						gto.setLxMobile(lxMobile);
						gto.setPrice(price);
						gto.setRemark(EmojiDealUtil.changeEmojiToHtml(remark));
						gto.setLxrProv(EmojiDealUtil.changeEmojiToHtml(lxrProv));
						gto.setLxrCity(EmojiDealUtil.changeEmojiToHtml(lxrCity));
						gto.setLxrAddress(EmojiDealUtil.changeEmojiToHtml(lxrAddress));
						gto.setLxrGpsInfo(lxrGpsInfo);
						gto.setDistance(distance);
						gto.setAddTime(CurrentTime.getCurrentTime());
						gto.setOrderStatus(0);
						gtoId = gtoSeriver.addOrUpdate(gto);
						if(!gtoId.equals("")) {
							//增加日志
							GasTradeOrderLog gtoLog = new GasTradeOrderLog();
							GasTradeOrder gasTradeOrder = gtoSeriver.getEntityById(gtoId);
							gtoLog.setGasTradeOrder(gasTradeOrder);
							gtoLog.setOrderStatus(0);
							gtoLog.setOrderImgDetail("");
							gtoLog.setOrderDetailTxt("买家已下单，等待买家付款并上传缴费凭证");
							gtoLog.setAddTime(CurrentTime.getCurrentTime());
							gtolService.addOrUpdate(gtoLog);
							MessageCenter mc = new MessageCenter("",user.getRealName()+"想购买您发布的"+gt.getGasFactory().getName()+"燃气", user.getRealName()+"想购买您发布的"+gt.getGasFactory().getName()+"燃气", 0, CurrentTime.getCurrentTime(), 2,
									gtId, "gasTrade", "", gt.getAddUserId(), 0);
							mcs.saveOrUpdate(mc);
						}
					}
				}
			}else {
				status = 30003;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, gtoId);
	}

	@PostMapping("/dealGasTraderOrderLog")
	@ApiOperation(value = "燃气交易中各种业务逻辑处理", notes = "订单中的动作处理")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), 
		@ApiResponse(code = 200, message = "成功"),
		@ApiResponse(code = 50001, message = "数据未找到"),
		@ApiResponse(code = 30002, message = "该交易已存在确认订单，不能再进行确认操作"),
		@ApiResponse(code = 30003, message = "该交易审核未通过或已下架，不能进行操作"),
		@ApiResponse(code = 10002, message = "参数为空")
	})
	@ApiImplicitParams({ @ApiImplicitParam(name = "gtoId", value = "燃气交易订单编号", required = true),
			@ApiImplicitParam(name = "orderSta", value = "订单状态(-1-7)", required = true),
			@ApiImplicitParam(name = "oImgDetail", value = "详情图片(买家上传缴费凭证或者上传磅单时传递)"),
			@ApiImplicitParam(name = "pjScore", value = "订单完成时评价分数(评价时传递)"),
			@ApiImplicitParam(name = "pjContent", value = "评价内容(评价时传递)"),
	})
	public GenericResponse dealGasTraderOrderLog(HttpServletRequest request) {
		String gtoId = CommonTools.getFinalStr("gtoId", request);
		String oImgDetail = CommonTools.getFinalStr("oImgDetail", request);
		String pjContent = CommonTools.getFinalStr("pjContent", request);
		Integer pjScore = CommonTools.getFinalInteger("pjScore", request);
		Integer orderSta = CommonTools.getFinalInteger("orderSta", request);
		String userId = CommonTools.getFinalStr("userId", request);
		Integer status = 200;
		String gtolId = "";
		String currentTime = CurrentTime.getCurrentTime();
		try {
			if(userId.equals("") && gtoId.equals("")) {
				status = 10002;
			}else {
				//获取订单
				GasTradeOrder gasTradeOrder = gtoSeriver.getEntityById(gtoId);
				if(gasTradeOrder != null) {
					//获取燃气贸易
					GasTrade gt = gasTradeOrder.getGasTrade();
					if(gt != null) {
						if(gt.getCheckStatus() == 1 && gt.getShowStatus() == 0) {//审核通过且上架的才能进行操作
							if(orderSta.equals(2)) {//商家确认订单时
								//判断该燃气交易有无确认订单
								if(gt.getTradeOrderId().equals("")) {//无确认订单
									//并设置其他订单为取消状态
									List<GasTradeOrder> gtoList = gtoSeriver.getInfoBygtId(gt.getId());
									if(gtoList.size() > 0) {
										for(GasTradeOrder gto : gtoList) {
											if(!gto.getId().equals(gasTradeOrder.getId())) {
												gto.setOrderStatus(-1);
												//修改其他订单为取消状态
												gtoSeriver.addOrUpdate(gto);
												//增加订单日志
												gtolService.addOrUpdate(new GasTradeOrderLog(gto, -1, "","订单被商家取消", currentTime));
											}
										}
									}
									//设置燃气交易确认订单
									gt.setTradeOrderId(gtoId);
									gtService.saveOrUpdate(gt);
								}else {//已存在确认订单，不能进行操作
									status = 30002;
								}
							}
							if(status.equals(200)) {
								//修改订单状态
								gasTradeOrder.setOrderStatus(orderSta);
								if(orderSta.equals(7)) {//订单完成评价
									gasTradeOrder.setOrderPjNumber(pjScore);
									gasTradeOrder.setOrderPjDetail(EmojiDealUtil.changeEmojiToHtml(pjContent));
								}
								gtoSeriver.addOrUpdate(gasTradeOrder);
								//增加订单日志
								GasTradeOrderLog gtoLog = new GasTradeOrderLog();
								gtoLog.setGasTradeOrder(gasTradeOrder);
								gtoLog.setOrderStatus(orderSta);
								//买家上传缴费凭证、上传磅单时需要上传图
								if(orderSta.equals(1) || orderSta.equals(4) || orderSta.equals(5)) {
									gtoLog.setOrderImgDetail(CommonTools.dealUploadDetail(userId, "", oImgDetail));
								}
								String oDetailTxt = "";
								if(orderSta.equals(-1)) {
									oDetailTxt = "订单取消";//商户取消
								}else if(orderSta.equals(0)) {
									oDetailTxt = "待付款";//下单后等待付预付款0
								}else if(orderSta.equals(1)) {
									oDetailTxt = "待确认";//付款凭证上传后等待商家确认后状态修改为1
								}else if(orderSta.equals(2)) {
									oDetailTxt = "待发货";//商家确认无误后等待商家发货状态修改为2，确认有误时状态修改为0，直到确认完成
								}else if(orderSta.equals(3)) {
									oDetailTxt = "待收货";//商家发货后状态修改为3
								}else if(orderSta.equals(4)) {
									oDetailTxt = "待付款";//买家点击收货时上传磅单，状态修改为4
								}else if(orderSta.equals(5)) {
									oDetailTxt = "待商家确认";//买家上传尾款凭证后，状态修改为5
								}else if(orderSta.equals(6)) {
									oDetailTxt = "待买家评价";//商家确认后状态修改为6，确认有误时状态修改为4
								}else if(orderSta.equals(7)) {
									oDetailTxt = "买家已完成评价";//买家评价后状态修改为7，订单完成
								}
								gtoLog.setOrderDetailTxt(oDetailTxt);
								gtoLog.setAddTime(currentTime);
								gtolId = gtolService.addOrUpdate(gtoLog);
							}
						}else {
							status = 30003;
						}
					}else {
						status = 50001;
					}
				}else {
					status = 50001;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, gtolId);
	}

	@PutMapping("/cancelTradeOrder")
	@ApiOperation(value = "商/买家手动取消订单", notes = "商/买家手动取消订单，商家在买家下单0或者买家上传缴费凭证1时可以取消")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 50001, message = "数据未找到"), @ApiResponse(code = 70001, message = "无权限访问"),
			@ApiResponse(code = 30006, message = "不能删除已确认订单")
	})
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "燃气交易订单编号", required = true),
			@ApiImplicitParam(name = "userId", value = "当前登录用户编号", required = true)
	})
	public GenericResponse cancelTradeOrder(HttpServletRequest request) {
		String id = CommonTools.getFinalStr("id",request);
		String userId = CommonTools.getFinalStr("userId", request);
		Integer status = 200;
		try {
			GasTradeOrder gto = gtoSeriver.getEntityById(id);
			if (gto == null) {
				status = 50001;
			} else if(gto.getOrderStatus() >= 2){//订单确认后不能删除
				status = 30006;
			}else {
				String buyUserId = gto.getUser().getId();//买方用户编号
				String sellUserId = gto.getGasTrade().getAddUserId();//商家用户编号
				if(buyUserId.equals(userId) || sellUserId.equals(userId)) {
					gto.setOrderStatus(-1);
					gtoSeriver.addOrUpdate(gto);
					//增加订单日志
					String content = "";
					if(buyUserId.equals(userId)){//买家
						content = "订单被买家取消";
					}else {//商家
						content = "订单被商家取消";
					}
					gtolService.addOrUpdate(new GasTradeOrderLog(gto, -1, "",content, CurrentTime.getCurrentTime()));
				}else {
					status = 50001;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, "");
	}

	@PutMapping("/addGtOrderByPj")
	@ApiOperation(value = "提交燃气交易订单评价--不启用", notes = "提交燃气交易订单评价--不启用")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), 
		@ApiResponse(code = 200, message = "成功"),
		@ApiResponse(code = 50001, message = "数据未找到"), 
		@ApiResponse(code = 10001, message = "参数无效")
	})
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "燃气交易订单编号", required = true),
			@ApiImplicitParam(name = "pjNum", value = "1-3星（好，中，差评，未评价时为0）"),
			@ApiImplicitParam(name = "pjDetail", value = "评价内容")
	})
	public GenericResponse addGtOrderByPj(HttpServletRequest request) {
		String gtoId = CommonTools.getFinalStr("gtoId",request);
		Integer pjNum = CommonTools.getFinalInteger("pjNum",request);
		String pjDetail = CommonTools.getFinalStr("pjDetail",request);
		Integer status = 200;
		try {
			GasTradeOrder gto = gtoSeriver.getEntityById(gtoId);
			if (gto == null) {
				status = 50001;
			} else {
				if (pjNum > 0 && pjNum <= 3) {
					gto.setOrderPjNumber(pjNum);
					gto.setOrderStatus(7);
					gtoSeriver.addOrUpdate(gto);
					pjDetail = EmojiDealUtil.changeEmojiToHtml(pjDetail);
					//增加订单日志
					gtolService.addOrUpdate(new GasTradeOrderLog(gto, -1, "",pjDetail, CurrentTime.getCurrentTime()));
				}else {
					status = 10001;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, "");
	}

	@GetMapping("/getPageGtOrder")
	@ApiOperation(value = "获取燃气交易订单分页信息", notes = "获取燃气交易订单分页信息")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 50001, message = "数据未找到") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户编号"),
			@ApiImplicitParam(name = "cpyId", value = "公司编号"), 
			@ApiImplicitParam(name = "sDate", value = "开始时间"),
			@ApiImplicitParam(name = "eDate", value = "结束时间"),
			@ApiImplicitParam(name = "orderSta", value = "订单状态（-1到7）"), 
			@ApiImplicitParam(name = "page", value = "第几页"),
			@ApiImplicitParam(name = "limit", value = "每页多少条") })
	public PageResponse getPageGtOrder(HttpServletRequest request) {
		Integer status = 200;
		Page<GasTradeOrder> gtoList = null;
		String userId = CommonTools.getFinalStr("userId",request);
		String cpyId = CommonTools.getFinalStr("cpyId",request);
		String sDate = CommonTools.getFinalStr("sDate",request);
		String eDate = CommonTools.getFinalStr("eDate",request);
		Integer orderSta = CommonTools.getFinalInteger("orderSta", request);
		Integer page = CommonTools.getFinalInteger("page", request);
		Integer limit = CommonTools.getFinalInteger("limit", request);
		long count = 0;
		List<Object> list = new ArrayList<Object>();
		try {
			if (page == 0) {
				page = 1;
			}
			if (limit == 0) {
				limit = 10;
			}
			if (orderSta == 0) {
				orderSta = -1;
			}
			gtoList = gtoSeriver.listPageInfoByOpt(userId, cpyId, sDate,eDate, orderSta, page - 1, limit);
			count = gtoList.getTotalElements();
			if (count == 0) {
				status = 50001;
			}else {
				for(GasTradeOrder gto : gtoList) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("id", gto.getId());
					GasTrade gt = gto.getGasTrade();
					GasFactory gf = gt.getGasFactory();
					GasType gasType = gt.getGasType();
					map.put("gasTradeId", gt.getId());
					map.put("headImg", gt.getHeadImg());
					map.put("orderNo", gto.getOrderNo());
					map.put("title", gf.getProvince()+gf.getName()+gasType.getName());
					map.put("psArea", gt.getPsArea());
					map.put("sellPrice", gt.getGasPrice());
					map.put("sellCpyName", gt.getCompany().getName());
					map.put("buyCpyName", gto.getCompany().getName());
					Integer oStatus = gto.getOrderStatus();
					String orderStatusChi = "";
					map.put("orderStatus", oStatus);
					if(oStatus.equals(-1)) {
						orderStatusChi = "已取消";//商户取消
					}else if(oStatus.equals(0)) {
						orderStatusChi = "待付款";//下单后等待付预付款0
					}else if(oStatus.equals(1)) {
						orderStatusChi = "待商家确认";//付款凭证上传后等待商家确认后状态修改为1
					}else if(oStatus.equals(2)) {
						orderStatusChi = "待发货";//商家确认无误后等待商家发货状态修改为2，确认有误时状态修改为0，直到确认完成
					}else if(oStatus.equals(3)) {
						orderStatusChi = "待收货";//商家发货后状态修改为3
					}else if(oStatus.equals(4)) {
						orderStatusChi = "待付款";//买家点击收货时上传磅单，状态修改为4
					}else if(oStatus.equals(5)) {
						orderStatusChi = "待商家确认";//买家上传尾款凭证后，状态修改为5
					}else if(oStatus.equals(6)) {
						orderStatusChi = "待买家评价";//商家确认后状态修改为6，确认有误时状态修改为4
					}else if(oStatus.equals(7)) {
						orderStatusChi = "订单完成";//买家评价后状态修改为7，订单完成
					}
					map.put("orderStatusChi", orderStatusChi);
					map.put("addTime", gto.getAddTime());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.getPageJson(limit, page, count, status, list);
	}

	@GetMapping("/queryGtOrderByGtId")
	@ApiOperation(value = "根据燃气交易编号获取燃气交易订单详情", notes = "根据燃气交易编号获取燃气交易订单详情")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 50001, message = "数据未找到") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "gtId", value = "燃气交易编号"), 
			@ApiImplicitParam(name = "gtId", value = "燃气交易编号")
	})
	public GenericResponse queryGtOrderByGtId(String gtId) {
		Integer status = 200;
		List<GasTradeOrder> gtoList = null;
		try {
			gtoList = gtoSeriver.getInfoBygtId(gtId);
			if (gtoList.isEmpty()) {
				status = 50001;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, gtoList);
	}

	@GetMapping("/queryGtOrderByGtoId")
	@ApiOperation(value = "根据燃气交易订单编号获取燃气交易订单详情", notes = "根据燃气交易订单编号获取燃气交易订单详情")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 50001, message = "数据未找到") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "gtoId", value = "燃气交易订单编号"), 
			@ApiImplicitParam(name = "gtoId", value = "燃气交易订单编号")
	})
	public GenericResponse queryGtOrderByGtoId(String gtoId) {
		Integer status = 200;
		List<Object> list = new ArrayList<Object>();
		try {
			GasTradeOrder gt = gtoSeriver.getEntityById(gtoId);
			if (gt == null) {
				status = 50001;
			}else {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", gt.getId());
				map.put("orderNo", gt.getOrderNo());
				GasTrade gasTrade = gt.getGasTrade();
				GasFactory gf = gasTrade.getGasFactory();
				map.put("title", gf.getProvince()+gf.getName()+gf.getGasType().getName());
				String headImg = gasTrade.getHeadImg();
				if(headImg.equals("")) {
					headImg = gf.getGasType().getYzImg();
				}
				map.put("headImg", headImg);
				map.put("gasTradeId", gasTrade.getId());
				map.put("sellCpyName", gasTrade.getCompany().getName());
				map.put("buyUserName", gt.getUser().getRealName());
				map.put("buyCpyName", gt.getCompany().getName());
				map.put("buyUserMobile", gt.getLxMobile());
				map.put("price", gt.getPrice());
				map.put("remark", gt.getRemark());
				map.put("userProv", gt.getLxrProv());
				map.put("userCity", gt.getLxrCity());
				map.put("userAddress", gt.getLxrAddress());
				map.put("gpsInfo", gt.getLxrGpsInfo());
				map.put("distance", gt.getDistance());
				map.put("addTime", gt.getAddTime());
				Integer oStatus = gt.getOrderStatus();
				String orderStatusChi = "";
				map.put("orderStatus", oStatus);
				if(oStatus.equals(-1)) {
					orderStatusChi = "已取消";//商户取消
				}else if(oStatus.equals(0)) {
					orderStatusChi = "待付款";//下单后等待付预付款0
				}else if(oStatus.equals(1)) {
					orderStatusChi = "待商家确认";//付款凭证上传后等待商家确认后状态修改为1
				}else if(oStatus.equals(2)) {
					orderStatusChi = "待发货";//商家确认无误后等待商家发货状态修改为2，确认有误时状态修改为0，直到确认完成
				}else if(oStatus.equals(3)) {
					orderStatusChi = "待收货";//商家发货后状态修改为3
				}else if(oStatus.equals(4)) {
					orderStatusChi = "待付款";//买家点击收货时上传磅单，状态修改为4
				}else if(oStatus.equals(5)) {
					orderStatusChi = "待商家确认";//买家上传尾款凭证后，状态修改为5
				}else if(oStatus.equals(6)) {
					orderStatusChi = "待买家评价";//商家确认后状态修改为6，确认有误时状态修改为4
				}else if(oStatus.equals(7)) {
					orderStatusChi = "订单完成";//买家评价后状态修改为7，订单完成
				}
				map.put("orderStatusChi", orderStatusChi);
				map.put("pjNumber", gt.getOrderPjNumber());
				map.put("pjDetail", gt.getOrderPjDetail());
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, list);
	}
	
	@GetMapping("/queryGtoLogByGtoId")
	@ApiOperation(value = "根据订单编号获取燃气交易订单日志信息", notes = "根据订单编号获取燃气交易订单日志信息")
	@ApiResponses({ @ApiResponse(code = 1000, message = "服务器错误"), @ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 50001, message = "数据未找到") })
	@ApiImplicitParams({ @ApiImplicitParam(name = "gtoId", value = "燃气交易订单编号")})
	public GenericResponse queryGtoLogByGtoId(String gtoId) {
		Integer status = 200;
		List<Object> list = new ArrayList<Object>();
		try {
			List<GasTradeOrderLog> gtolList = gtolService.getGtLogList(gtoId);
			if (gtolList.isEmpty()) {
				status = 50001;
			}else {
				for(GasTradeOrderLog gtol : gtolList) {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("detailTxt", gtol.getOrderDetailTxt());
					map.put("detailImg", gtol.getOrderImgDetail());
					map.put("addTime", gtol.getAddTime());
					list.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, list);
	}
}
