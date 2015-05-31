<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/index.css">
</head>
<body class="body_index">
 
	<center style="margin-top: 10%;">
	 <label style="font-size: 40px;font-style: italic;font-weight: bold;">欢迎登录仓库管理系统</label>
	 <br>
	 <br>
		<div>
			<fieldset class="fieldset_index">
				<legend style="position: relative;">
					<b>欢迎登录</b>
				</legend>
				<s:form action="loginAction" method="post" validate="true">
				   <s:textfield label="用户名" name="admin.adminName" size="16"></s:textfield>
				   <br>
				   <s:password label="密    码" name="admin.adminPassword" size="16"></s:password>
				   <s:submit value="登录"></s:submit>
					</s:form>
			</fieldset>
		</div>
	</center>
</body>
</html>