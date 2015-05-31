$(document)
		.ready(
				function() {
					$("#goodsid").val("01" + createGoodsId());
					$("#goodsid").attr("readonly", "readonly");
					$("#goodscategory").change(function() {
						$("#goodsid").removeAttr("readonly");
						var choice = $(this).val();
						if (choice < 10)
							choice = "0" + choice;
						$("#goodsid").val(choice + createGoodsId());
						$("#goodsid").attr("readonly", "readonly");
					});
					// 查询
					$("#choice").change(function() {
						var choice = $(this).val();
						if (choice == 0 || choice == 1) {
							$("#id_or_name_condition").removeClass("hide");
							$("#goodscategory").removeClass("show");
							$("#id_or_name_condition").addClass("show");
							$("#goodscategory").addClass("hide");
						} else if (choice == 2) {
							$("#id_or_name_condition").removeClass("show");
							$("#goodscategory").removeClass("hide");
							$("#id_or_name_condition").addClass("hide");
							$("#goodscategory").addClass("show");
						}
						$("#condition").val("");
					});

					// 查询物品
					$("#querygoods")
							.click(
									function() {
										var choice = $("#choice").val();
										if (choice == null || choice == "") {
											choice = $("#choice option:first")
													.val();
										}
										var condition = $("#condition").val();
										var goodscategory = $("#category")
												.val();
										if (goodscategory == null
												|| goodscategory == "") {
											goodscategory = $(
													"#category option:first")
													.val();
										}
										var href_v;
										switch (choice) {
										case '0':
										case '1':
											href_v = condition;
											break;
										case '2':
											href_v = goodscategory;
											break;
										default:
											href_v = condition;
											break;
										}
										window.location.href = "goods/queryGoods.action?choice="
												+ choice
												+ "&condition="
												+ href_v;
									});
					// 当重新加载时
					var choice = $("#choice").val();
					if (choice == null || choice == "")
						choice = "0";
					switch (choice) {
					case '0':
					case '1':
						$("#id_or_name_condition").removeClass("hide");
						$("#goodscategory").removeClass("show");
						$("#id_or_name_condition").addClass("show");
						$("#goodscategory").addClass("hide");
						break;
					case '2':
						$("#id_or_name_condition").removeClass("show");
						$("#goodscategory").removeClass("hide");
						$("#id_or_name_condition").addClass("hide");
						$("#goodscategory").addClass("show");
						break;
					default:
						$("#id_or_name_condition").removeClass("hide");
						$("#goodscategory").removeClass("show");
						$("#id_or_name_condition").addClass("show");
						$("#goodscategory").addClass("hide");
						break;
					}
				});

// 生成物品编号
function createGoodsId() {
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

function updateGoods(choice, conditiion, currentPage, goodsId) {
	window.location.href = "goods/findGoodsById.action?choice=" + choice
			+ "&condition=" + conditiion + "&currentpage=" + currentPage
			+ "&goodsid=" + goodsId;
}

function deleteGoods(choice, conditiion, currentPage, goodsId) {
	if (window.confirm("确定删除？")) {
		window.location.href = "goods/deleteGoods.action?choice=" + choice
				+ "&condition=" + conditiion + "&currentpage=" + currentPage
				+ "&goodsid=" + goodsId;
	}
}

function exportGoods(choice, conditiion) {
	window.location.href = "goods/exportGoods.action?choice=" + choice
			+ "&condition=" + conditiion;
}