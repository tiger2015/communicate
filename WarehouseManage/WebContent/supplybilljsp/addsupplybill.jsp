<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.dao.ProviderDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map providerMap=new ProviderDAO().findAllProviderMap();
	Map category=new CategoryDAO().findAllCategoryMap();
	request.setAttribute("provider", providerMap);
	request.setAttribute("category", category);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<sx:head cache="false" compressed="false" debug="false"/>
<base href="<%=basePath%>">
<title>添加供应记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/supplybilljs.js"></script>
</head>
<body>
<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<fieldset>
			<legend>新增供货记录</legend>
			<s:form action="supplybill/addSupplyBill.action" method="post">
		     <s:textfield label="供货记录编号" name="supplyBill.supplyBillId" id="supplybillid"></s:textfield>
			 <s:select list="#request['provider']" label="供应商" name="supplyBill.provider.providerId" id="provider" value="%{#session['provider']}"></s:select>
			 <s:select list="#request['category']" label="物品种类" id="category" value="%{#session['category']}"></s:select>
			 <s:select list="#session['goods']" label="物品名称" name="supplyBill.goods.goodsId" id="goods" value="%{#session['selectgoods']}"></s:select>
			 <s:textfield label="最大库存" value="%{#session['goodsmaxamount']}" readonly="true" size="10"></s:textfield>
			 <s:textfield label="供货数量" name="supplyBill.goodsAmount"></s:textfield>
		     <s:textfield label="物品单位" value="%{#session['unit']}" readonly="true" cssStyle="width:50px;"></s:textfield>
			 <sx:datetimepicker displayWeeks="5" label="供货日期" name="supplyBill.supplyDate" displayFormat="yyyy-MM-dd" value="%{'today'}"></sx:datetimepicker>
			 <s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
