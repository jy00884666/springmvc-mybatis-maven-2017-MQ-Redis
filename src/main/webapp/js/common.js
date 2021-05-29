/**
 * json方式表单提交
 * 
 * @param id
 * @param url
 * @param success
 */
function doSubmit(id, url, success) {
	var data = encodeParams($("#" + id).serializeArray());
	$.ajax({
		url : url,
		type : 'POST',
		data : {
			jsonparams : JSON.stringify(data)
		},
		dataType : 'json',
		beforeSend : function() {
			$("<div id='myShow' ><img alt='loading' src='/springmvc-mybatis-maven/images/loading.gif'/></div>").css({
				display : "",
				top : "50%",
				left : "50%",
				position : "absolute"
			}).appendTo("body");
		},
		success : function(data) {
			success(data);
		},
		complete : function(data) {
			$('#myShow').remove();
		},
		error : function(data) {
			alert("处理异常！");
		},
		statusCode : {
			404 : function() {
				console.info('404');// page
			},
			500 : function(v) {
				console.info(v);
			},
			504 : function(v) {
				console.info(v);
			}
		}
	});
};

/**
 * AJAX表单提交
 * 
 * @param id
 * @param url
 * @param success
 */
function doAjaxFormSubmit(formId, success) {

	var ajax_option = {
		dataType : "json",
		beforeSend : function() {
			$("<div id='myShow' ><img alt='loading' src='/springmvc-mybatis-maven/images/loading.gif'/></div>").css({
				display : "",
				top : "50%",
				left : "50%",
				position : "absolute"
			}).appendTo("body");
		},
		success : function(data) {
			success(data);
		},
		complete : function(data) {
			$('#myShow').remove();
		},
		error : function(data) {
			alert("处理异常！");
		}
	};

	$("#" + formId).ajaxSubmit(ajax_option);
}

/**
 * post表单提交
 * 
 * @param id
 * @param url
 * @param success
 */
function doSubmitPost(id, url, success) {
	var data = encodeParams($("#" + id).serializeArray());
	var props = "";
	for ( var p in data) {
		props += p + "=" + data[p] + "&";
	}
	$.ajax({
		url : url,
		type : 'POST',
		data : props,
		beforeSend : function() {
			$("<div id='myShow' ><img alt='loading' src='/springmvc-mybatis-maven/images/loading.gif'/></div>").css({
				display : "",
				top : "50%",
				left : "50%",
				position : "absolute"
			}).appendTo("body");
		},
		success : function(data) {
			success(data);
		},
		complete : function(data) {
			$('#myShow').remove();
		},
		error : function(data) {
			alert("处理异常！");
		},
		statusCode : {
			404 : function() {
				console.info('404');// page
			},
			500 : function(v) {
				console.info(v);
			},
			504 : function(v) {
				console.info(v);
			}
		}

	});
};
function encodeParam(data) {
	for (key in data) {
		if (data[key] == "")
			delete data[key];
	}
	return data;
};

function encodeParams(req) {
	var data = {};
	$.each(req, function(i, param) {
		if (param.value != null && param.value != "") {
			if (data[this.name]) {
				if ($.isArray(data[this.name])) {
					data[this.name].push(this.value);
				} else {
					data[this.name] = [ data[this.name], this.value ];
				}
			} else {
				data[this.name] = this.value;
			}
		}
	});
	return data;
};

/**
 * ajax json方式提交请求
 * 
 * @param data
 *            请求参数，可以自己转为json格式的数据对象
 * @param url
 *            请求地址
 * @param success
 *            回调方法
 */
function doSubmitNonForm(data, url, success) {
	$.ajax({
		url : url,
		type : 'POST',
		data : {
			jsonparams : JSON.stringify(data)
		},
		dataType : 'json',
		beforeSend : function() {
			$("<div id='myShow' ><img alt='loading' src='/springmvc-mybatis-maven/images/loading.gif'/></div>").css({
				display : "",
				top : "50%",
				left : "50%",
				position : "absolute"
			}).appendTo("body");
		},
		success : function(data) {
			success(data);
		},
		complete : function(data) {
			$('#myShow').remove();
		},
		error : function(data) {
			alert("处理异常！");
		},
		statusCode : {
			404 : function() {
				console.info('404');// page
			},
			500 : function(v) {
				console.info(v);
			},
			504 : function(v) {
				console.info(v);
			}
		}
	});
};

/**
 * 数字格式化
 * 
 * @param number
 *            数字
 * @param decimals
 *            小数位数，默认2位
 * @param dec_point
 *            小数位分隔符，参数为空则，默认'.'
 * @param thousands_sep
 *            千分位分隔符，参数为空则，默认','
 * @author zhiping.li
 * @date 20140731
 */
function number_format(number, decimals, dec_point, thousands_sep) {

	// 默认显示2位小数点
	decimals = (typeof decimals === 'undefined') ? 2 : decimals,
	// 去除非数字字符
	number = (number + '').replace(/[^0-9+-Ee.]/g, '');
	// 检查数字的有效性
	var n = !isFinite(+number) ? 0 : +number, prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
	// 默认千分位 ','
	sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
	// 默认小数点 '.'
	dec = (typeof dec_point === 'undefined') ? '.' : dec_point, s = '',

	toFixedFix = function(n, prec) {
		var k = Math.pow(10, prec);
		return '' + Math.round(n * k) / k;
	};
	// Fix for IE parseFloat(0.55).toFixed(0) = 0;
	s = (prec ? toFixedFix(n, prec) : ('' + Math.round(n))).split('.');
	// 增加千分位
	var rgx = new RegExp(/(\d+)(\d{3})/);
	while (rgx.test(s[0])) {
		s[0] = s[0].replace(rgx, '$1' + sep + '$2');
	}
	// 小数位补零
	if ((s[1] || '').length < prec) {
		s[1] = s[1] || '';
		s[1] += new Array(prec - s[1].length + 1).join('0');
	}
	return s.join(dec);
}

// 打开加载动画
function openLoadGif() {
	$("<div id='myShow' ><img alt='loading' src='/springmvc-mybatis-maven/images/loading.gif'/></div>").css({
		display : "",
		top : "50%",
		left : "50%",
		position : "absolute"
	}).appendTo("body");
}

// 关闭加载动画
function closeLoadGif() {
	$('#myShow').remove();
}

/**
 * shashijie 2015-08-20 FDSDEV-638 日期格式转换
 * 
 * @param data
 *            时间戳或date对象
 * @param type
 *            支持类型:注意大小写 "yyyyMMdd" "yyyy-MM-dd" "yyyy-MM-dd hh:mm:ss"
 * @returns {String}
 */
function formatDate(data, type) {
	var value;
	var now = new Date(data);
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();

	// 补充"0"
	if (month < 10) {
		month = "0" + month;
	}
	if (date < 10) {
		date = "0" + date;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	if (second < 10) {
		second = "0" + second;
	}

	if (type == "yyyyMMdd") {
		value = year + "" + month + "" + date;
	} else if (type == "yyyy-MM-dd") {
		value = year + "-" + month + "-" + date;
	} else {
		value = year + "-" + month + "-" + date + "   " + hour + ":" + minute + ":" + second;
	}
	return value
}