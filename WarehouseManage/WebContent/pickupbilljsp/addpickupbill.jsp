<%@page import="com.dao.StaffDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map staffMap = new StaffDAO().findStaffMap();
	Map category = new CategoryDAO().findAllCategoryMap();
	request.setAttribute("staff", staffMap);
	request.setAttribute("category", category);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<sx:head cache="false" compressed="false" debug="false" />
<base href="<%=basePath%>">
<title>添加提货记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/pickupbilljs.js"></script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<fieldset>
			<legend>新增提货记录</legend>
			<s:form action="pickupbill/addPickupBill.action" method="post">
				<s:textfield label="提货记录编号" name="pickupBill.ladingBillId"
					id="pickupbillid"></s:textfield>
				<s:select list="#request['staff']" label="提货员"
					name="pickupBill.staff.staffId" id="staff"
					value="%{#session['staff']}"></s:select>
				<s:select list="#request['category']" label="物品种类" id="category"
					value="%{#session['category']}"></s:select>
				<s:select list="#session['goods']" label="物品名称"
					name="pickupBill.goods.goodsId" id="goods"
					value="%{#session['selectgoods']}">
				<s:textfield value="%{#session['goodsamount']}" label="物品库存" readonly="true" size="10"></s:textfield>
				</s:select>
				<s:textfield label="提货数量" name="pickupBill.goodsAmount"></s:textfield>
				<s:textfield label="物品单位" value="%{#session['unit']}"
					readonly="true" cssStyle="width:50px;"></s:textfield>
				<sx:datetimepicker displayWeeks="5" label="提货日期"
					name="pickupBill.pickupDate" displayFormat="yyyy-MM-dd"
					value="%{'today'}"></sx:datetimepicker>
				<s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
