$(document)
		.ready(
				function() {
					$("#supplybillid").val("SI" + createSupplyBillId());
					$("#supplybillid").attr("readonly", "readonly");
					$("#category")
							.change(
									function() {
										var choice = $(this).val();
										var provider = $("#provider").val();
										window.location.href = "supplybill/findGoodsByCategory.action?category="
												+ choice
												+ "&provider="
												+ provider;
									});
					$("#goods")
							.change(
									function() {
										var choice = $("#category").val();
										var provider = $("#provider").val();
										var goods = $(this).val();
										window.location.href = "supplybill/findGoodsById.action?category="
												+ choice
												+ "&provider="
												+ provider + "&goods=" + goods;

									});

					var choice = $("#choice").val();
					changeChoice(choice);
					$("#choice").change(function() {
						var choice = $(this).val();
						changeChoice(choice);
						$("#condition").val("");

					});
				});
function createSupplyBillId() {
	var id;
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();
	var hour = today.getHours();
	var minite = today.getMinutes();
	var second = today.getSeconds();
	if (month < 10)
		month = "0" + month;
	else
		month = "" + month;
	if (day < 10)
		day = "0" + day;
	else
		day = day + "";
	if (hour < 10)
		hour = "0" + hour;
	else
		hour = "" + hour;
	if (minite < 10)
		minite = "0" + minite;
	else
		minite = "" + minite;
	if (second < 10)
		second = "0" + second;
	else
		second = "" + second;
	id = year + month + day + hour + minite + second;
	return id;
}

function changeChoice(choice) {
	switch (choice) {
	case '0':
	case '1':
	case '2':
	case '4':
		$("#choice1").removeClass("hide");
		$("#choice2").removeClass("show");
		$("#choice1").addClass("show");
		$("#choice2").addClass("hide");
		// $("#result").addClass("result");
		break;
	case '3':
		$("#choice1").removeClass("show");
		$("#choice2").removeClass("hide");
		$("#choice1").addClass("hide");
		$("#choice2").addClass("show");
		// $("#result").removeClass("result");
		break;
	default:
		$("#choice1").removeClass("hide");
		$("#choice2").removeClass("show");
		$("#choice1").addClass("show");
		$("#choice2").addClass("hide");
		// $("#result").addClass("result");
		break;
	}
}

function querySupplyBill() {
	var choice = $("#choice").val();
	var condition = $("#condition").val();
	var t = dojo.widget.byId("#startdate");
	var startdate = dojo.widget.byId("startdate");
	var enddate = dojo.widget.byId("enddate");
	var start = startdate.getValue();
	var end = enddate.getValue();
	window.location.href = "supplybill/querySupplyBill.action?choice=" + choice
			+ "&condition=" + condition + "&startdate=" + start + "&enddate="
			+ end;
}

function deleteSupplyBill(choice, condition, startDate, endDate, currentPage,
		supplyBillId) {
	if (window.confirm("确定删除？")) {
		window.location.href = "supplybill/deleteSupplyBill.action?choice="
				+ choice + "&condition=" + condition + "&startdate="
				+ startDate + "&enddate=" + endDate + "&currentpage="
				+ currentPage + "&supplybillid=" + supplyBillId;
	}
}

function exportSupplyBill(choice, condition, startDate, endDate) {
	window.location.href = "supplybill/exportSupplyBill.action?choice="
			+ choice + "&condition=" + condition + "&startdate=" + startDate
			+ "&enddate=" + endDate;
}
