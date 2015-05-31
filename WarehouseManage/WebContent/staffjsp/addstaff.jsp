<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<sx:head compressed="false" debug="false" cache="false"/>
<base href="<%=basePath%>">
<title>新增职工</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/staffjs.js"></script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<fieldset>
			<legend>新增职工</legend>
			<s:form action="staff/addStaff" method="post">
			<s:textfield label="职工号" name="staff.staffId" id="staffid"></s:textfield>
			<s:radio list="#{'0':'提货员','1':'仓库管理员'}" label="职位" name="staff.staffRole" value="0"></s:radio>
			<s:textfield label="姓名" name="staff.staffName"></s:textfield>
			<s:radio list="#{'0':'男','1':'女'}" name="staff.staffSex" label="性别" value="0" ></s:radio>
			<sx:datetimepicker label="出生日期" displayWeeks="5" displayFormat="yyyy-MM-dd" name="staff.staffBirthday"  toggleType="explode" value="%{'1990-01-01'}"></sx:datetimepicker>
			<s:textfield label="联系电话" name="staff.staffPhonenumber" maxlength="11"></s:textfield>
			<s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
