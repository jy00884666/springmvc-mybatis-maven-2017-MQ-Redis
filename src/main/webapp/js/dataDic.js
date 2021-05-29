/**
 * 数据字典
 */
// 清算状态 0-无需清算 1-未清算 3-清算中 4-清算成功 5-清算失败
var settleStatuses=[{"key":"0","value":"无需清算"},{"key":"1","value":"未清算"},{"key":"3","value":"清算中"},{"key":"4","value":"清算成功"},{"key":"5","value":"清算失败"}];
var unusualTransTypes=[{"key":"0","value":"正常交易"},{"key":"1","value":"异常交易"}];

/** 投资者类型 */
var dic_balanceRetreatType=[{"key":"1","value":"1-可退余额资金"},{"key":"2","value":"2-撤单退款资金"}];

/** 划款指令处理状态 */
var dic_paySuiteDealStatType4Manual=[{"key":"1","value":"1-未处理"},{"key":"2","value":"2-已导出"}];

/** 投资者类型 */
var dic_invstType=[{"key":"0","value":"0-机构"},{"key":"1","value":"1-个人"}];
/** 证件类型 */
var dic_idType=[{"key":"0","value":"0-身份证"},{"key":"1","value":"1-护照"},{"key":"2","value":"2-军官证"},{"key":"3","value":"3-士兵证"},{"key":"4","value":"4-港澳居民来往内地通行证"},{"key":"5","value":"5-户口本"},{"key":"6","value":"6-外国护照"},{"key":"7","value":"7-其他"},{"key":"8","value":"8-文职证"},{"key":"9","value":"9-警官证"},{"key":"A","value":"A-台胞证"}];
/** 客户状态 */
var dic_custStats=[{"key":"0","value":"0-正常"},{"key":"1","value":"1-销户"}];
/** 币种 */
var dic_curCode=[{"key":"156","value":"人民币"}];
/** 是否默认银行卡 */
var dic_isDflt=[{"key":"0","value":"否"},{"key":"1","value":"是"}];
/** 记录状态 */
var dic_recStat=[{"key":"0","value":"有效"},{"key":"1","value":"无效"}];
/** 记录复核标志 */
/** 2015-08-26 guopeng.li FDSDEV-638股基T+1新增 */
var dic_checkFlag=[{"key":"0","value":"未复核"},{"key":"1","value":"已复核"},{"key":"2","value":"复核拒绝"}];

/** 2015-10-12 guanxiaolei FDSDEV-638股基T+1新增 */
var dic_confirmFlag=[{"key":"","value":"全部"}, {"key":"0","value":"未确认"},{"key":"1","value":"已确认"}];
/** 审核标志 */
var dic_checkStatus=[{"key":"1","value":"未审核"},{"key":"2","value":"审核通过"},{"key":"3","value":"审核不通过"}];
/** 匹配审核标志 */
var dic_matchCheckFlag=[{"key":"0","value":"无需匹配审核"},{"key":"1","value":"未匹配审核"},{"key":"2","value":"匹配审核通过"},{"key":"3","value":"匹配审核不通过"}];
/** 存入类型 */
var dic_depositType=[{"key":"0","value":"0-自划款"},{"key":"1","value":"1-退款"},{"key":"2","value":"2-POS机"},{"key":"3","value":"3-不明客户资金存入"},{"key":"4","value":"4-同卡可退转可用"},{"key":"5","value":"5-同卡可用转可退"}];
/** 交易申请标志 */
var dic_txAppFlag=[{"key":"0","value":"0-申请成功"},{"key":"1","value":"1-申请失败"},{"key":"2","value":"2-撤单"},{"key":"3","value":"3-强制撤单"}];
/** zhao.zhang FDSDEV-750 储蓄罐买基金 015年12月21日 新增交易方式 start */
/** 交易方式 */
var dic_txPmtMode=[{"key":"01","value":"01-网关支付"},{"key":"02","value":"02-储蓄罐支付"}];
/** zhao.zhang FDSDEV-750 储蓄罐买基金 015年12月21日 新增交易方式 end */
/** 交易付款标志 */
var dic_txPmtFlag=[{"key":"0","value":"0-不需付款"},{"key":"1","value":"1-未付款"},{"key":"2","value":"2-付款成功"},{"key":"3","value":"3-付款失败"},{"key":"4","value":"4-付款中"},{"key":"5","value":"5-撤单中"},{"key":"6","value":"6-已撤单"},{"key":"7","value":"7-撤单失败"}];
/** 交易对账标志 */
var dic_txCompFlag=[{"key":"0","value":"0-不需对账"},{"key":"1","value":"1-未对账"},{"key":"2","value":"2-匹配"},{"key":"3","value":"3-资金单边"},{"key":"4","value":"4-交易单边"},{"key":"5","value":"5-金额或状态不一致"}];
/** 批量申购单的处理状态 */
var dic_batchStatus=[{"key":"0","value":"0-未处理"},{"key":"1","value":"1-批量开户中"},{"key":"2","value":"2-批量申购中"},{"key":"3","value":"3-批量处理完成"},{"key":"4","value":"4-交易结束"},{"key":"5","value":"5-异常结束"},{"key":"6","value":"6-已资金存入"}];
/** 划款方向（清算方向） */
var dic_settleDirect=[{"key":"1","value":"1-收款"},{"key":"2","value":"2-付款"}];
/** 指令明细处理状态 */
var dic_payOrderDealStat=[{"key":"1","value":"1-未生成总指令"},{"key":"2","value":"2-已生成指令"},{"key":"3","value":"3-已导出"},{"key":"4","value":"4-已处理"}];
/** 指令支付状态 */
var dic_payOrderPmtStat=[{"key":"0","value":"0-成功"},{"key":"1","value":"1-失败"},{"key":"2","value":"2-未付款"}];

/** shashijie 2016-09-28 ALM-2994 增加清算类型 */
/** 清算类型 FDSDEV-815跨TA转换-guanxiaolei-20160509-增加超级转换转出款清算类型 */
/** FDSDEV-873-理财通二期项目-20160722-guanxiaolei-清算类型名称“清算理财通分红强赎款”调整为“清算理财通强赎款” */
var dic_settleClass=[{"key":"1","value":"1-清算TA"},{"key":"2","value":"2-清算个人客户"},{"key":"3","value":"3-清算支付机构"},
                     {"key":"4","value":"4-清算TA费用"},{"key":"5","value":"5-清算公司费用"},
                     {"key":"6","value":"6-清算客户赎回退款"},{"key":"7","value":"7-清算客户撤单退款"},
                     {"key":"8","value":"8-清算异常客户"},{"key":"9","value":"9-清算客户确认失败退款"},
                     {"key":"11","value":"11-清算客户非货币基金快赎款"},
                     {"key":"12","value":"12-清算公司非货币基金快赎服务费"},
                     {"key":"13","value":"13-清算垫资账户"},
                     {"key":"14","value":"14-清算理财通（广发归集户）"},
                     {"key":"15","value":"15-清算广发归集户"},
                     {"key":"16","value":"16-清算理财通强赎款"},
                     {"key":"17","value":"17-清算储蓄罐"},
                     {"key":"18","value":"18-清算储蓄罐支付退款"},
                     {"key":"19","value":"19-清算储蓄罐撤单退款"},
                     {"key":"20","value":"20-清算个人-超级转换转出款"},
                     {"key":"21","value":"21-清算超级转换转出失败款"},
                     {"key":"22","value":"22-清算机构客户"},
                     {"key":"23","value":"23-清算机构-超级转换转出款"}
                     ];
/** 费用类型（根据业务类型转换为对应的费用类型） */
var dic_feeType_forOrder = [{"key":"130","value":"认购费"},{"key":"122","value":"申购费"},{"key":"124","value":"赎回费"},{"key":"136","value":"转换费"},{"key":"139","value":"定投费"}];

/** （支付系统参数字典） */
/** 计费类型 */
var dic_feeRateMode=[{"key":"00","value":"固定费用"},{"key":"01","value":"浮动费用"}];
/** 业务代码 modify by xiaoyang.shi FDSDEV-638 新增股基T+1 20150824 */
var dic_busiCode=[{"key":"100101","value":"支付"},{"key":"100201","value":"T+0"},{"key":"100301","value":"验卡"},{"key":"100401","value":"鉴权"},{"key":"100501","value":"T+1"}];
/** 产品类型 */
var dic_productType=[{"key":"***","value":"全部"},{"key":"103","value":"货币类"},{"key":"999","value":"非货币类"}];
/** 启用标志 */
var dic_avlState=[{"key":"0","value":"启用"},{"key":"1","value":"停用"}];
/** 手续费交收模式 */
var dic_feeMode=[{"key":"00","value":"净额交收"},{"key":"01","value":"全额交收"}];
/** 支付方式 */
var dic_payMode=[{"key":"04","value":"代扣"},{"key":"01","value":"自划款"}];
/** 终端类型 */
var dic_termType=[{"key":"*","value":"全部"},{"key":"2","value":"WEB"},{"key":"5","value":"APP"},{"key":"4","value":"WAP"},{"key":"1","value":"SYS"}];
/** 验卡方式 */
var dic_vrfyMode=[{"key":"00","value":"B2C"},{"key":"01","value":"无磁无密"},{"key":"03","value":"小额打款"},{"key":"04","value":"直连验卡"},{"key":"05","value":"快捷验卡"}];
/** 鉴权方式 */
var dic_authenticationMode=[{"key":"00","value":"B2C"},{"key":"01","value":"小额打款"},{"key":"02","value":"微信"},{"key":"03","value":"直连鉴权"},{"key":"04","value":"快捷鉴权"}];
/** 计费方式 */
var dic_chargingMode=[{"key":"00","value":"交易金额"},{"key":"01","value":"保有量"}];
/** 费用类型 */
var dic_feeType=[{"key":"00","value":"支付机构费用"},{"key":"01","value":"收单费用"}];
/** 比对类型 */
var dic_CheckStatusType=[{"key":"0","value":"未比对"},{"key":"1","value":"比对成功"},{"key":"2","value":"比对失败"}];
/* start shashijie 2015-10-13 FDSDEV-671 资金优化,增加查询条件 */
/** 资金变动类型 */
var dic_balanceChgType=[{"key":"0","value":"0-自划款"},
                        {"key":"1","value":"1-赎回退款"},
                        {"key":"2","value":"2-POS机"},
                        {"key":"3","value":"3-同卡可退转可用"},
                        {"key":"4","value":"4-同卡可用转可退"},
                        {"key":"5","value":"5-资金到账匹配"},
                        {"key":"6","value":"6-取消资金到账"},
                        {"key":"7","value":"7-撤单退款"},
                        {"key":"8","value":"8-清算撤单退款"},
                        {"key":"9","value":"9-清算可退资金"},
                        {"key":"10","value":"10-撤单退款失败"},
                        {"key":"11","value":"11-可退资金退款失败"}];
/* end shashijie 2015-10-13 FDSDEV-671 资金优化,增加查询条件 */

/** 划款指令明细付款状态 */
var dic_payOrderPmtStatNow=[{"key":"0","value":"0-成功"},{"key":"1","value":"1-失败"},
                         {"key":"2","value":"2-未付款"},{"key":"3","value":"3-付款中"}];
/** 划款指令处理状态 */
var dic_paySuiteDealStat = [{"key":"1","value":"1-未处理"},{"key":"2","value":"2-已导出"},
                            {"key":"3","value":"3-已导入结果"},{"key":"4","value":"4-已设置付款结果"},
                            {"key":"5","value":"5-部分设置付款结果"}];
/** 划付通道 */
/** 2015-08-26 guopeng.li FDSDEV-638股基T+1新增 */
var dic_rowChannelIds=[{"key":"C1","value":"C1-民生银企直连"},{"key":"C2","value":"C2-民生超级网银"}];
/** 垫资划款指令生成清算类型 add by xiaoyang.shi FDSDEV-638股基T+1新增 */
var dic_dzSettleClass=[{"key":"11","value":"11-清算客户非货币基金快赎款"},
                     {"key":"12","value":"12-清算公司非货币基金快赎服务费"}];
// jquery方法
// domId表示select控件的id
// data表示数据字典的数据
// value 可不传，表示默认选中的值
jQuery.forEachDataSelect=function(domId,data,value,isAll){
	var str='';
	if(isAll==null||isAll==true){
		str +='<option value="">全部</option>';
	}
	
	$.each(data,function(i,entry){
		if(entry.key==value){
			str += '<option value="'+entry.key+'" selected="selected">'+entry.value+'</option>';
		}else{
			str += '<option value="'+entry.key+'">'+entry.value+'</option>';
		}
	});
    $('#'+domId).html(str);
};

/**
 * 翻译数据字典常量
 * 
 * 例如：$dicGetVal('dic_curCode','156'),返回‘人民币’
 * 
 * @param data字典名
 * @param key
 *            字典关键字
 * @returns value 字典值
 * 
 */
jQuery.dicGetVal=function(data,key){
	var val=key;
	$.each(data,function(i,entry){
		if(entry.key==key){
			val= entry.value;
			return
		}
	});
	return val;
};
/** start zhangzhao 2015年10月21日11:03:08 FDSDEV-681 分销机构资金控管报表 （理财通） */
var dic_disCode=[{"key":"LCT00K001","value":"LCT00K001-理财通"}];

/** start shashijie 2016-11-17 ALM-4352 券商资管,增加收付款类型 */
/** FDSDEV-873-理财通二期项目-20160722-guanxiaolei-修改收款类型，原来的有歧义 */
var dis_paymentType = [{"key":"1","value":"1-应收分销机构款"},{"key":"2","value":"2-应付分销机构赎回款"},{"key":"3","value":"3-应付分销机构分红款"},{"key":"4","value":"4-应付分销机构强赎款"}]
/** end zhangzhao 2015年10月21日11:03:08 FDSDEV-681 分销机构资金控管报表 （理财通） */
/** end shashijie 2016-11-17 ALM-4352 券商资管,增加收付款类型 */

/** FDSDEV-750 储蓄罐买基金 张钊 2015年12月22日 储蓄罐业务类型* */
 var dic_busiType =[{"key":"5","value":"应收储蓄罐-支付款"},{"key":"6","value":"应付储蓄罐支付退款"},{"key":"7","value":"应付储蓄罐-存入款"},{"key":"8","value":"应收-法透还款账户-垫资款"},{"key":"9","value":"应收-好买金融账户-垫资款"},{"key":"10","value":"应收-保险账户-垫资款"}];


/** FDSDEV-853 非民生卡绑定优化 by shaoyang.li 20160708 start */

/** 民生银行卡绑定的文件类型* */
var dic_minshengBindFileType = [{"key":"0","value":"0-储蓄罐"},{"key":"1","value":"1-非储蓄罐"}];
/** 民生银行卡绑定的调用类型* */
var dic_minshengBindCallType = [{"key":"0","value":"0-定时"},{"key":"1","value":"1-手动"}];
/** 民生银行卡绑定的处理状态* */
var dic_minshengBindFileState = [{"key":"0","value":"0-已生成"},{"key":"1","value":"1-已发送"},
                                 {"key":"2","value":"2-已接收"},{"key":"3","value":"3-已处理"},
                                 {"key":"4","value":"4-异常"}];
/** FDSDEV-853 非民生卡绑定优化 by shaoyang.li 20160708 end */

/** shashijie 20160907 ALM-1835 处理状态 */
var dic_dealStatus=[{"key":"0","value":"0-已处理"},{"key":"1","value":"1-未处理"}];
/** shashijie 20160907 ALM-1835 数据来源 */
var dic_dataSource=[{"key":"1","value":"1-中台"},{"key":"2","value":"2-后台"}];








