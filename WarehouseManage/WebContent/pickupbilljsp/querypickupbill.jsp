<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<sx:head cache="false" compressed="false" debug="false" />
<base href="<%=basePath%>">
<title>提货记录查询</title>
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
		<s:set name="choice" value="#request['choice']"></s:set>
		<s:set name="condition" value="#request['condition']"></s:set>
		<s:set name="startdate" value="#request['startdate']"></s:set>
		<s:set name="enddate" value="#request['enddate']"></s:set>
		<s:set name="splitepage" value="#session['splitepage']"></s:set>
		<s:set name="previouspage" value="#splitepage['currentPage']-1"></s:set>
		<s:set name="nextpage" value="#splitepage['currentPage']+1"></s:set>
		<s:set name="temp" value="{'物品编号','提货员编号','提货记录编号','提货日期','仓库编号'}"></s:set>
		<fieldset>
			<legend>提货信息</legend>
			<div>
				<label>查询条件</label><select id="choice">
					<s:iterator value="#temp" status="status">
						<s:if test="#choice==#status.index">
							<option value="<s:property value="#status.index"/>"
								selected="selected">
								<s:property />
							</option>
						</s:if>
						<s:else>
							<option value="<s:property value="#status.index"/>">
								<s:property />
							</option>
						</s:else>
					</s:iterator>
				</select>
			</div>
			<div id="choice1">
				<input id="condition" type="text"
					value="<s:property value="#condition"/>"> <input
					type="button" value="查询" onclick="queryPickupBill();">
			</div>
			<div id="choice2">
				<sx:datetimepicker label="起始日期" displayWeeks="5"
					displayFormat="yyyy-MM-dd" id="startdate" value="%{#startdate}"></sx:datetimepicker>
				<sx:datetimepicker label="结束日期" displayWeeks="5"
					displayFormat="yyyy-MM-dd" id="enddate" value="%{#enddate}"></sx:datetimepicker>
				<input type="button" value="查询" onclick="queryPickupBill();">
			</div>
			<div id="result">
				<s:if test="#request['result']==null">
				</s:if>
				<s:elseif test="#request['result'].isEmpty">
		          没有信息！
		       </s:elseif>
				<s:else>
					<table class="showtable" cellspacing="2" cellpadding="1">
						<thead>
							<tr>
								<td>记录编号</td>
								<td>提货员编号</td>
								<td>提货员姓名</td>
								<td>物品编号</td>
								<td>物品类别</td>
								<td>物品名称</td>
								<td>提货数量</td>
								<td>物品单位</td>
								<td>提货日期</td>
								<s:if test="#session['userrole']==1">
									<td></td>
								</s:if>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request['result']">
								<tr>
									<td><s:property value="ladingBillId" />
									</td>
									<td><s:property value="staff['staffId']" />
									</td>
									<td><s:property value="staff['staffName']" />
									</td>
									<td><s:property value="goods['goodsId']" />
									</td>
									<td><s:property value="goods['category']['categoryName']" />
									</td>
									<td><s:property value="goods['goodsName']" />
									</td>
									<td><s:property value="goodsAmount" />
									</td>
									<td><s:property value="goods['unit']['unitName']" />
									</td>
									<td><s:date name="pickupDate" format="yyyy-MM-dd"
											var="format1" /> <s:property value="#format1" />
									</td>
									<s:if test="#session['userrole']==1">
										<td><a href="javascript:void(0);"
											onclick="deletePickupBill('<s:property value="#choice"/>','<s:property value="#condition"/>','<s:property value="#startdate"/>','<s:property value="#enddate"/>','<s:property
						value="#splitepage['currentPage']" />','<s:property value="ladingBillId"/>');">删除</a>
										</td>
									</s:if>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<a
						href="<%=basePath%>pickupbill/queryPickupBill.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&startdate=<s:property value="#startdate"/>&enddate=<s:property value="#enddate"/>&currentpage=1">首页</a>
					<a
						href="<%=basePath%>pickupbill/queryPickupBill.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&startdate=<s:property value="#startdate"/>&enddate=<s:property value="#enddate"/>&currentpage=<s:property value="#previouspage"/>">上一页</a>
					<a
						href="<%=basePath%>pickupbill/queryPickupBill.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&startdate=<s:property value="#startdate"/>&enddate=<s:property value="#enddate"/>&currentpage=<s:property value="#nextpage"/>">下一页</a>
					<a
						href="<%=basePath%>pickupbill/queryPickupBill.action?choice=<s:property value="#choice"/>&condition=<s:property value="#condition"/>&startdate=<s:property value="#startdate"/>&enddate=<s:property value="#enddate"/>&currentpage=<s:property value="#splitepage['totalPage']"/>">尾页</a> 总记录数：<s:property
						value="#splitepage['totalRecord']" /> 当前页：<s:property
						value="#splitepage['currentPage']" /> 总页数：<s:property
						value="#splitepage['totalPage']" />
					<input type="button" value="导出数据"
						onclick="exportPickupBill('<s:property value="#choice"/>','<s:property value="#condition"/>','<s:property value="#startdate"/>','<s:property value="#enddate"/>');">
				</s:else>
			</div>
		</fieldset>
	</div>
</body>
</html>
