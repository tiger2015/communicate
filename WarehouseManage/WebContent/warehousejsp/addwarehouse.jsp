<%@page import="com.dao.StaffDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map staffMap=new StaffDAO().findStaffMap();
	request.setAttribute("staff", staffMap);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新增仓库</title>
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
		<fieldset>
			<legend>新增仓库</legend>
			<s:form action="warehouse/addWarehouse.action" method="post">
		     <s:textfield label="仓库编号" name="warehouse.warehouseId" id="warehouseid"></s:textfield>
		     <s:textfield label="仓库名称" name="warehouse.warehouseName"></s:textfield>
			<s:select list="#request['staff']" label="仓库管理员"
					name="warehouse.staff.staffId">
				</s:select>
			<s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
