<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>仓库信息查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/warehousejs.js"></script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<s:set name="temp" value="{'仓库编号','仓库名称','管理员编号'}"></s:set>
		<s:set name="splitepage" value="#session['splitepage']"></s:set>
		<s:set name="choice" value="#request['choice']"></s:set>
		<s:set name="condition" value="#request['condition']"></s:set>
		<s:set name="previouspage" value="#splitepage['currentPage']-1"></s:set>
		<s:set name="nextpage" value="#splitepage['currentPage']+1"></s:set>
		<fieldset>
			<legend>仓库信息查询</legend>
			<label>查询条件</label> <select id="choice">
				<s:iterator value="#temp" status="status">
					<s:if test="#choice==#status.index">
						<option value="<s:property value="#status.index"/>"
							selected="selected">
							<s:property />
						</option>
					</s:if>
					<s:else>
						<option value="<s:property value="#status.index"/>">
							<s:property />
						</option>
					</s:else>
				</s:iterator>
			</select> <input type="text" value="<s:property value="#condition"/>"
				id="condition"><input type="button" value="查询"
				onclick="queryWarehouse();">
			<div>
				<s:if test="#request['result']==null">
				</s:if>
				<s:elseif test="#request['result'].isEmpty">
			没有信息
			</s:elseif>
				<s:else>
					<table class="showtable" cellspacing="2" cellpadding="1">
						<thead>
							<tr>
								<td>仓库编号</td>
								<td>仓库名称</td>
								<td>管理员工号</td>
								<td>管理员姓名</td>
								<td>管理员联系电话</td>
								<s:if test="#session['userrole']==1">
								<td></td>
								<td></td>
								</s:if>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['result']">
								<tr>
									<td><s:property value="warehouseId" /></td>
									<td><a href="javascript:void(0);"
										onclick="queryWarehouseGoods('<s:property value="warehouseId"/>');"><s:property
												value="warehouseName" /></a></td>
									<td><s:property value="staff['staffId']" /></td>
									<td><s:property value="staff['staffName']" /></td>
									<td><s:property value="staff['staffPhonenumber']" /></td>
									<s:if test="#session['userrole']==1">
									<td><a href="javascript:void(0);"
										onclick="updateWarehouse('<s:property value="#choice"/>','<s:property value="#condition"/>','<s:property
						value="#splitepage['currentPage']" />','<s:property value="warehouseId" />');">编辑</a>
									</td>
									<td><a href="javascript:void(0);"
										onclick="deleteWarehouse('<s:property value="#choice"/>','<s:property value="#condition"/>','<s:property
						value="#splitepage['currentPage']" />','<s:property value="warehouseId" />');">删除</a>
									</td>
									</s:if>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<a
						href="<%=basePath%>warehouse/queryWarehouse.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&currentpage=1">首页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouse.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&currentpage=<s:property value="#previouspage"/>">上一页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouse.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&currentpage=<s:property value="#nextpage"/>">下一页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouse.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&currentpage=<s:property value="#splitepage['totalPage']"/>">尾页</a>总记录数：<s:property
						value="#splitepage['totalRecord']" /> 当前页：<s:property
						value="#splitepage['currentPage']" /> 总页数：<s:property
						value="#splitepage['totalPage']" />
						<input type="button" value="导出数据" onclick="exportWarehouse('<s:property value="#choice"/>','<s:property value="#condition"/>');"/>
				</s:else>
			</div>
		</fieldset>
	</div>
</body>
</html>
