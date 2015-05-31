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
<title>新增供应商</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>css/welcome.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/providerjs.js"></script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="../menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<fieldset>
			<legend>新增供应商</legend>
			<s:form method="post" action="provider/addProvider.action">
				<s:textfield label="供应商编号" name="provider.providerId"
					id="providerid"></s:textfield>
				<s:textfield label="供应商名称" name="provider.providerName"></s:textfield>
				<s:textfield label="供应商地址" name="provider.providerAddress"></s:textfield>
				<s:textfield label="联系电话" name="provider.providerPhonenumber"
					maxlength="11"></s:textfield>
				<s:textfield label="电子邮件" name="provider.providerEmail"></s:textfield>
				<s:textfield label="联系人" name="provider.providerContact"></s:textfield>
				<s:submit value="提交"></s:submit>
			</s:form>
		</fieldset>
	</div>
</body>
</html>
