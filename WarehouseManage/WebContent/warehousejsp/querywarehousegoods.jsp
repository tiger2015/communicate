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
<title>仓库存放的物品</title>
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
		<s:set name="splitepage" value="#session['splitepage']"></s:set>
		<s:set name="warehouseid" value="#request['warehouseid']"></s:set>
		<s:set name="previouspage" value="#splitepage['currentPage']-1"></s:set>
		<s:set name="nextpage" value="#splitepage['currentPage']+1"></s:set>
		<fieldset>
			<legend>仓库信息</legend>
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
								<td>物品编号</td>
								<td>物品名称</td>
								<td>物品类别</td>
								<td>物品库存</td>
								<td>物品单位</td>
								<td>物品单价</td>
								<td>最大库存</td>
								<td>警戒库存</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['result']">
								<tr>
									<td><s:property value="goodsId" /></td>
									<td><s:property value="goodsName" /></td>
									<td><s:property value="category['categoryName']" /></td>
									<td><s:property value="goodsAmount" /></td>
									<td><s:property value="unit['unitName']" /></td>
									<td><s:property value="goodsPrice" />￥</td>
									<td><s:property value="goodsMaxAmount" /></td>
									<td><s:property value="goodsMinAmount" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<a
						href="<%=basePath%>warehouse/queryWarehouseGoods.action?warehouseid=<s:property value="#warehouseid"/>&currentpage=1">首页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouseGoods.action?warehouseid=<s:property value="#warehouseid"/>&currentpage=<s:property value="#previouspage"/>">上一页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouseGoods.action?warehouseid=<s:property value="#warehouseid"/>&currentpage=<s:property value="#nextpage"/>">下一页</a>
					<a
						href="<%=basePath%>warehouse/queryWarehouseGoods.action?warehouseid=<s:property value="#warehouseid"/>&currentpage=<s:property value="#splitepage['totalPage']"/>">尾页</a>总记录数：<s:property
						value="#splitepage['totalRecord']" /> 当前页：<s:property
						value="#splitepage['currentPage']" /> 总页数：<s:property
						value="#splitepage['totalPage']" />
				</s:else>
			</div>
		</fieldset>
	</div>
</body>
</html>
