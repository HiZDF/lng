package com.lng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lng.pojo.SuperDep;
import com.lng.pojo.SuperUser;
import com.lng.service.DepartmentService;
import com.lng.service.SuperDepService;
import com.lng.service.SuperService;
import com.lng.tools.CommonTools;
import com.lng.tools.CurrentTime;
import com.lng.tools.MD5;
import com.lng.util.GenericResponse;
import com.lng.util.ResponseFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "后台用户管理")
@RequestMapping(value = "/super")
public class SuperController {
	
	@Autowired
	private SuperService ss;
	@Autowired
	private DepartmentService ds;
	@Autowired
	private SuperDepService sds;
	
	@PostMapping("addUser")
	@ApiOperation(value = "增加用户",notes = "增加用户接口信息--超级管理员用")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 50003, message = "数据已存在"),
		@ApiResponse(code = 70001, message = "无权限访问")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "用户账号", defaultValue = "wmk", required = true),
		@ApiImplicitParam(name = "password", value = "用户密码", defaultValue = "123456", required = true),
		@ApiImplicitParam(name = "realName", value = "用户姓名", defaultValue = "王杰", required = true),
		@ApiImplicitParam(name = "sex", value = "用户性别", defaultValue = "男", required = true),
		@ApiImplicitParam(name = "mobile", value = "手机号"),
		@ApiImplicitParam(name = "roleId", value = "身份编号", required = true),
	})
	public GenericResponse addUser(HttpServletRequest request,String account,String password,String realName,String sex,String mobile,String roleId) {
		Integer status = 200;
		String uId = "";
		String roleName = CommonTools.getLoginRoleName(request);
		try {
			if(roleName.equals("超级管理员")) {
				if(ss.findInfoByOpt(account, "").size() > 0) {
					status = 20005;
				}else {
					SuperUser user = new SuperUser(account, new MD5().calcMD5(password), realName, sex, mobile,
							CurrentTime.getCurrentTime(), "", 0, 1);
					uId = ss.addOrUpUser(user);
					if(!uId.equals("")) {
						//绑定用户部门关系
						SuperDep sd = new SuperDep(ds.getEntityById(roleId),user,CurrentTime.getCurrentTime());
						sds.addOrUpSuperDep(sd);
					}
				}
			}else {
				status = 70001;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, uId);
	}
	
	@PutMapping("upUser")
	@ApiOperation(value = "修改指定用户的信息",notes = "修改用户信息，为空时不修改--超级管理员用")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 50001, message = "数据未找到"),
		@ApiResponse(code = 70001, message = "无权限访问")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uId", value = "用户编号", required = true),
		@ApiImplicitParam(name = "realName", value = "用户姓名"),
		@ApiImplicitParam(name = "password", value = "用户密码"),
		@ApiImplicitParam(name = "mobile", value = "手机号"),
		@ApiImplicitParam(name = "accountStatus", value = "账号状态")
	})
	public GenericResponse upUser(HttpServletRequest request,String uId,String realName,String password,String mobile,Integer accountStatus) {
		Integer status = 200;
		String roleName = CommonTools.getLoginRoleName(request);
		try {
			if(roleName.equals("超级管理员")) {
				SuperUser user  = ss.getEntityById(uId);
				if(user != null) {
					if(password == null || password == "") {
						
					}else {
						user.setPassword(new MD5().calcMD5(password));
					}
					if(realName == null || realName == "") {
						
					}else {
						user.setRealName(realName);
					}
					if(mobile == null || mobile.equals("")) {
						
					}else {
						user.setMobile(mobile);
					}
					if(accountStatus != null) {
						if(accountStatus.equals(1) || accountStatus.equals(0)) {
							user.setAccountStatus(accountStatus);
						}
					}
					ss.addOrUpUser(user);
				}else {
					status = 50001;
				}
			}else {
				status = 70001;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, "");
	}
	
	@PutMapping("upUserRole")
	@ApiOperation(value = "修改指定用户身份信息",notes = "修改用户信息，为空时不修改")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 50001, message = "数据未找到"),
		@ApiResponse(code = 70001, message = "无权限访问"),
		@ApiResponse(code = 10002, message = "参数为空")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uId", value = "用户编号", required = true),
		@ApiImplicitParam(name = "roleIdStr", value = "身份编号组合")
	})
	public GenericResponse upUserRole(HttpServletRequest request,String uId,String roleIdStr) {
		Integer status = 200;
		String roleName = CommonTools.getLoginRoleName(request);
		try {
			if(roleName.equals("超级管理员")) {
				if(roleIdStr == null || roleIdStr == "") {
					status = 10002;
				}else {
					//先删除指定用户全部身份
					sds.delBatch(sds.listSpecInfoByUserId(uId));
					//增加新身份
					String[] roleIdArr = roleIdStr.split(",");
					for(int i = 0 ; i < roleIdArr.length ; i++) {
						sds.addOrUpSuperDep(new SuperDep(ds.getEntityById(roleIdArr[i]),ss.getEntityById(uId),CurrentTime.getCurrentTime()));
					}
				}
			}else {
				status = 70001;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, "");
	}
	
	@GetMapping("queryUser")
	@ApiOperation(value = "获取用户的信息列表",notes = "当uId不为空时，表示获取指定用户信息，当为空时，表示获取后台全部人员列表")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),@ApiResponse(code = 50001, message = "数据未找到")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uId", value = "用户编号(空时不查询)"),
		@ApiImplicitParam(name = "roleId", value = "用户身份编号(空时不查询)")
	})
	public GenericResponse queryUser(String uId,String roleId) {
		Integer status = 200;
		List<Object> list_d = new ArrayList<Object>();
		List<SuperUser> suList = new ArrayList<SuperUser>();
		try {
			if(uId == null || uId == "") {
				suList = ss.findAllInfo();
			}else {
				suList.add(ss.getEntityById(uId));
			}
			if(suList.size() > 0) {
				for(SuperUser su : suList) {
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("userId", su.getId());
					map_d.put("account", su.getAccount());
					map_d.put("realName", su.getRealName());
					map_d.put("sex", su.getSex());
					map_d.put("mobile", su.getMobile());
					map_d.put("lastLoginTime", su.getLastLoginTime());
					map_d.put("accountStatus", su.getAccountStatus());
					//获取当前人员角色
					List<SuperDep> sdList = sds.listSpecInfoByUserId(uId);
					String roleName = "";
					if(sdList.size() > 0) {
						for(SuperDep sd : sdList) {
							roleName += sd.getDepartment().getDepName() + ",";
						}
						map_d.put("roleName", roleName.substring(0, roleName.length() - 1));
					}else {
						map_d.put("roleName", "");
					}
					list_d.add(map_d);
				}
			}else {
				status = 50001;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, list_d);
	}
}