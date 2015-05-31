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
<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/welcome.css">
<script type="text/javascript">
	function goback(){
		history.go(-1);
	}
</script>
</head>
<body>
	<div class="topdiv">
		<div>
			<jsp:include page="menu.jsp" flush="false"></jsp:include>
		</div>
	</div>
	<div class="centerdiv">
		<h3>操作失败!</h3>
		<a href="javascript:void(0);" onclick="goback();">返回</a>
	</div>
</body>
</html>