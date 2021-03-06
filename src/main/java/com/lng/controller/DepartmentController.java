package com.lng.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lng.pojo.Department;
import com.lng.service.DepartmentService;
import com.lng.service.SuperDepService;
import com.lng.tools.CommonTools;
import com.lng.util.Constants;
import com.lng.util.GenericResponse;
import com.lng.util.ResponseFormat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "身份管理")
@RequestMapping(value = "/role")
public class DepartmentController {

	@Autowired
	private DepartmentService ds;
	@Autowired
	private SuperDepService sds;
	
	@PostMapping("addRole")
	@ApiOperation(value = "增加身份",notes = "增加身份接口信息")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 50003, message = "数据已存在"),
		@ApiResponse(code = 70001, message = "无权限访问"),
		@ApiResponse(code = 10002, message = "参数为空")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleName", value = "身份名称",  required = true),
		@ApiImplicitParam(name = "description", value = "描述")
	})
	public GenericResponse addRole(HttpServletRequest request,String roleName,String description) {
		Integer status = 200;
		String uId = "";
		if(CommonTools.checkAuthorization(CommonTools.getLoginUserId(request), CommonTools.getLoginRoleName(request),Constants.ADD_ROLE)) {
			try {
				roleName = CommonTools.getFinalStr(roleName);
				description = CommonTools.getFinalStr(description);
				if(!roleName.equals("")) {
					if(ds.findSpecInfo(roleName).size() > 0) {
						status = 50003;
					}else {
						Department dep = new Department(roleName,description,0);
						uId = ds.addOrUpDepartment(dep);
					}
				}else {
					status = 10002;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status = 1000;
			}
		}else {
			status = 70001;
		}
		return ResponseFormat.retParam(status, uId);
	}
	
	@PutMapping("upRole")
	@ApiOperation(value = "修改身份",notes = "修改身份接口信息")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 50003, message = "数据已存在"),
		@ApiResponse(code = 50001, message = "数据未找到"),
		@ApiResponse(code = 70001, message = "无权限访问")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleId", value = "身份编号",  required = true),
		@ApiImplicitParam(name = "roleName", value = "身份名称",  required = true),
		@ApiImplicitParam(name = "description", value = "描述")
	})
	public GenericResponse upRole(HttpServletRequest request,String roleId,String roleName,String description) {
		Integer status = 200;
		if(CommonTools.checkAuthorization(CommonTools.getLoginUserId(request), CommonTools.getLoginRoleName(request),Constants.UP_ROLE)) {
			try {
				roleId = CommonTools.getFinalStr(roleId);
				if(roleId.equals("")) {
					status = 50001;
				}else {
					Department dep = ds.getEntityById(roleId);
					if(dep == null) {
						status = 50001;
					}else {
						if(roleName.equals(dep.getDepName())) {//身份相同时不判断
							dep.setDescription(description);
							ds.addOrUpDepartment(dep);
						}else {
							if(ds.findSpecInfo(roleName).size() == 0) {
								dep.setDepName(roleName);
								dep.setDescription(description);
								ds.addOrUpDepartment(dep);
							}else {
								status = 50003;
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status = 1000;
			}
		}else {
			status = 70001;
		}
		return ResponseFormat.retParam(status, "");
	}
	
	@GetMapping("queryRole")
	@ApiOperation(value = "获取身份信息",notes = "身份编号为空时表示全部身份")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误")
	,@ApiResponse(code = 50001, message = "数据未找到")})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleId", value = "身份编号")
	})
	public GenericResponse queryRole(String roleId) {
		Integer status = 200;
		List<Department> dList = new ArrayList<Department>();
		try {
			if(roleId.equals("")) {
				dList = ds.findSpecInfo("");
			}else {
				Department dep = ds.getEntityById(roleId);
				if(dep == null) {
					status = 50001;
				}else {
					dList.add(dep);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = 1000;
		}
		return ResponseFormat.retParam(status, dList);
	}
	
	@GetMapping("delRole")
	@ApiOperation(value = "删除身份信息",notes = "删除身份")
	@ApiResponses({@ApiResponse(code = 1000, message = "服务器错误"),
		@ApiResponse(code = 10002, message = "参数为空"),
		@ApiResponse(code = 50003, message = "数据已存在"),
		@ApiResponse(code = 50001, message = "数据未找到"),
		@ApiResponse(code = 70001, message = "无权限访问")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleId", value = "身份编号")
	})
	public GenericResponse delRole(HttpServletRequest request,String roleId) {
		Integer status = 200;
		if(CommonTools.checkAuthorization(CommonTools.getLoginUserId(request),CommonTools.getLoginRoleName(request), Constants.UP_ROLE)) {
			try {
				roleId = CommonTools.getFinalStr(roleId);
				if(roleId.equals("")) {
					status = 10002;
				}else {
					Department dep = ds.getEntityById(roleId);
					if(dep == null) {
						status = 50001;
					}else {
						//查询该身份有无绑定管理人员
						if(sds.listInfoByDepId(roleId).size() == 0) {
							ds.delInfoById(roleId);
						}else {
							status = 50003;
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				status = 1000;
			}
		}else {
			status = 70001;
		}
		
		return ResponseFormat.retParam(status, "");
	}
}
