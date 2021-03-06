<%@page import="com.dao.WarehouseDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.dao.UnitDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map goodsUnit = new UnitDAO().findAllUnitMap();
	Map goodsCategory = new CategoryDAO().findAllCategoryMap();
	Map warehouse = new WarehouseDAO().findAllWarehouseMap();
	request.setAttribute("unit", goodsUnit);
	request.setAttribute("category", goodsCategory);
	request.setAttribute("warehouse", warehouse);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新增物品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsjs.js"></script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<fieldset>
			<legend>新增物品</legend>
			<s:form action="goods/addGoods" method="post">
				<s:textfield label="物品编号" name="goods.goodsId" id="goodsid"></s:textfield>
				<s:textfield label="物品名称" name="goods.goodsName"></s:textfield>
				<s:select list="#request['category']" label="物品类别"
					name="goods.category.categoryId">
				</s:select>
				<s:select list="#request['unit']" label="单位"
					name="goods.unit.unitId"></s:select>
				<s:textfield label="物品单价" name="goods.goodsPrice"></s:textfield>
				<s:select list="#request['warehouse']" label="存放仓库"
					name="goods.warehouse.warehouseId"></s:select>
				<s:textfield label="最大库存" name="goods.goodsMaxAmount"></s:textfield>
				<s:textfield label="警戒库存" name="goods.goodsMinAmount"></s:textfield>
				<s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
