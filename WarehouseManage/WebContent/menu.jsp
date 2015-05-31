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
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.10.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/menu.css">
</head>
<body>
<s:if test="#session['username']==null">
<jsp:forward page="index.jsp"></jsp:forward>
</s:if>
<s:elseif test="#session['username']==''">
<jsp:forward page="index.jsp"></jsp:forward>
</s:elseif>
<s:else>
	<div id="topmenu">
		<ul>
			<li><a href="javascript:void(0);">物品管理</a>
				<ul>
					<s:if test="#session['userrole']==1">
						<li><a href="<%=basePath%>goodsjsp/addgoods.jsp">新增物品</a></li>
					</s:if>
					<li><a href="<%=basePath%>goodsjsp/querygoods.jsp">物品查询</a></li>
				</ul></li>
			<li><a href="javascript:void(0);">供应商管理</a>
				<ul>
					<s:if test="#session['userrole']==1">
						<li><a href="<%=basePath%>providerjsp/addprovider.jsp">新增供应商</a>
						</li>
					</s:if>
					<li><a href="<%=basePath%>providerjsp/queryprovider.jsp">供应商查询</a>
					</li>
				</ul></li>
			<li><a href="javascript:void(0);">出库管理</a>
				<ul>
					
						<li><a
							href="<%=basePath%>pickupbill/findGoodsByCategory.action">出&nbsp;&nbsp;库</a>
						</li>
					<li><a href="<%=basePath%>pickupbilljsp/querypickupbill.jsp">出库查询</a>
					</li>
				</ul></li>
			<li><a href="javascript:void(0);">入库管理</a>
				<ul>
				
						<li><a
							href="<%=basePath%>supplybill/findGoodsByCategory.action">入&nbsp;&nbsp;库</a>
						</li>
					<li><a href="<%=basePath%>supplybilljsp/querysupplybill.jsp">入库查询</a>
					</li>
				</ul></li>
			<li><a href="javascript:void(0);">仓库管理</a>
				<ul>
					<li><a href="<%=basePath%>warehousejsp/querywarehouse.jsp">仓库查询</a>
					</li>
					<s:if test="#session['userrole']==1">
						<li><a href="<%=basePath%>warehousejsp/addwarehouse.jsp">新增仓库</a>
						</li>
					</s:if>
				</ul></li>
			<li><a href="javascript:void(0);">员工管理</a>
				<ul>
					<li><a href="<%=basePath%>/staffjsp/querystaff.jsp">员工查询</a></li>
					<s:if test="#session['userrole']==1">
						<li><a href="<%=basePath%>/staffjsp/addstaff.jsp">新增员工</a></li>
					</s:if>
				</ul></li>
			<li><a href="logoutAction.action">退出</a></li>
		</ul>
	</div>
	</s:else>
</body>
</html>
