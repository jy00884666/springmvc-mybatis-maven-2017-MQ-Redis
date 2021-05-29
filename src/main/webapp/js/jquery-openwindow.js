/**
 * 分页控件
 * DEMO:
 * $("#bigpage").update_page({total_page:200, current_page:77, callbackfunc:func});
 * //具体的表单提交方法
 * function func(page){
 *	alert(page);
 * }
 */
(function ($) { 
	$.fn.update_page = function(options){
		
		/**
		 * 创建HTML元素方法
		 */
		var fill_tag = function (a_tag, a_class, a_id, a_name, a_html, a_pagenumber){
		    a_class = (a_class == '') ? '' : ' class="'+a_class+'"';
		    a_id = (a_id == '') ? '' : ' id="'+a_id+'"';
		    a_name = (a_name == '') ? '' : ' name="'+a_name+'"';
		    a_pagenumber = (a_pagenumber == '') ? '' : ' pagenumber="'+a_pagenumber+'"';
		    var code = '<'+a_tag+a_class+a_id+a_name+a_pagenumber+' >'+a_html+'</'+a_tag+'>';
		    return code;
		};
		
		var settings = {
				total_page : 0,
				current_page : 1,
			    a_tag : 'a href=\"javascript:void(0);\"',
			    s_tag : 'span',
			    a_class : '',
			    a_id : '',
			    a_name : '',
			    prev_str : '上一页',
			    next_str : '下一页',
			    disable_prev_class : 'current prev',
			    disable_next_class : 'current next',
			    select_class : 'current',
			    svh_class : 'svh',
			    pager_length : 5,//不包next 和 prev 必须为奇数
			    header_length : 1,
			    tailer_length : 1,
			    callbackfunc : undefined//提交的回调函数
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
	    var total_page = parseInt( settings.total_page , 10);
	    var current_page = parseInt( settings.current_page , 10);
	    var pager = new Array( settings.pager_length );
	    //header_length + tailer_length 必须为偶数
	    var main_length = settings.pager_length - settings.header_length - settings.tailer_length; //必须为奇数
	    
/*	    var total_page = settings.total_page;
	    var current_page = settings.current_page;*/
	    var a_tag = settings.a_tag;
	    var s_tag = settings.s_tag;
	    var a_class = settings.a_class;
	    var a_id = settings.a_id;
	    var a_name = settings.a_name;
	    var prev_str = settings.prev_str;
	    var next_str = settings.next_str;
	    var disable_prev_class = settings.disable_prev_class;
	    var disable_next_class = settings.disable_next_class;
	    var select_class = settings.select_class;
	    var svh_class = settings.svh_class;
	    var pager_length = settings.pager_length;//不包next 和 prev 必须为奇数
	    var header_length = settings.header_length;
	    var tailer_length = settings.tailer_length;
	    
	    var i;
	    var code = '';
	    if( total_page < current_page ){
	        //alert('总页数不能小于当前页数');
	        return _this;    
	    }    
		
	    //判断总页数是不是小于 分页的长度，若小于则直接显示
	    if( total_page < pager_length ){
	        for(i = 0; i < total_page; i++){
	            code += (i+1 != current_page) ? fill_tag(a_tag, a_class, a_id, a_name, i+1, i+1) : fill_tag(s_tag, select_class, a_id, a_name, i+1);
	        }
	    }else{
	    //如果总页数大于分页长度，则为一下函数
	        //先计算中心偏移量
	        var offset = ( pager_length - 1) / 2;
	        //分三种情况，第一种左边没有...
	        if( current_page <= offset + 1)
	        {
	            var tailer = '';
	            //前header_length + main_length 个直接输出之后加一个...然后输出倒数的    tailer_length 个
	            for( i = 0; i < header_length + main_length; i ++)
	                code += (i+1 != current_page) ? fill_tag(a_tag, a_class, a_id, a_name, i+1, i+1) : fill_tag(s_tag, select_class, a_id, a_name, i+1);
	            code += fill_tag(s_tag, svh_class, a_id, a_name, '...');
	            for(i = total_page; i > total_page - tailer_length; i --)
	                tailer = fill_tag(a_tag, a_class, a_id, a_name, i, i) + tailer;
	                
	            code += tailer;
	        }
	        //第二种情况是右边没有...
	        else if( current_page >= total_page - offset )
	        {
	            var header = '';
	            //后tailer_length + main_length 个直接输出之前加一个...然后拼接 最前面的 header_length 个
	            for( i = total_page; i >= total_page-main_length - 1; i --)
	                code = (( current_page != i ) ? fill_tag(a_tag, a_class, a_id, a_name, i, i) : fill_tag(s_tag, select_class, a_id, a_name, i)) + code;
	            code = fill_tag(s_tag, svh_class, a_id, a_name, '...') + code;
	            for( i = 0; i < header_length ; i++)
	                header +=  fill_tag(a_tag, a_class, a_id, a_name, i + 1, i+1);
	            
	            code = header + code;
	        }
	        //最后一种情况，两边都有...
	        else
	        {
	            var header = '';
	            var tailer = '';
	            //首先处理头部
	            for( i = 0; i < header_length; i ++)
	                header += fill_tag(a_tag, a_class, a_id, a_name, i + 1, i+1);
	            header += fill_tag(s_tag, svh_class, a_id, a_name, '...');
	            //处理尾巴
	            for(i = total_page; i > total_page - tailer_length; i --)
	                tailer = fill_tag(a_tag, a_class, a_id, a_name, i, i) + tailer;
	            tailer = fill_tag(s_tag, svh_class, a_id, a_name, '...') + tailer;
	            //处理中间
	            //计算main的中心点
	            var offset_m = ( main_length - 1 ) / 2;
	            var partA = '';
	            var partB = '';
	            var j;
	            var counter = (parseInt(current_page) + parseInt(offset_m));
	            for(i = j = current_page ; i <= counter; i ++, j --)
	            {
	                partA = (( i == j ) ? '' : fill_tag(a_tag, a_class, a_id, a_name, j, j)) + partA;
	                partB += ( i == j ) ? fill_tag(s_tag, select_class, a_id, a_name, i) : fill_tag(a_tag, a_class, a_id, a_name, i, i);
	            }
	            //拼接
	            code = header + partA + partB + tailer;
	            
	        }
	    }
	    
	    var prev = ( current_page == 1 ) ? fill_tag(s_tag, disable_prev_class, a_id, a_name, prev_str) : fill_tag(a_tag, a_class, a_name, a_name, prev_str, parseInt(current_page)-1);
	    var next = ( current_page == total_page ) ? fill_tag(s_tag, disable_next_class, a_id, a_name, next_str) : fill_tag(a_tag, a_class, a_name, a_name, next_str, parseInt(current_page)+1);
	    code = prev + code + next;
	    _this.html(code);
	    
	    _this.children("a").each(function(i, val){
	    	$(val).bind("click", function(){
	    		if(settings.callbackfunc){
	    			settings.callbackfunc($(this).attr("pagenumber"));
	    		}
	    	});
	    });
		
		return _this;
                
	};

})(jQuery); 

/**
 * TA控件
 * DEMO:
 * $("#taCode").fundMultiselect({selectedValues:["48", "51"]});
 */
(function ($) { 
	$.fn.taMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/tacode.json?math="+Math.random(),
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].taCode+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].taCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].taCode+":"+obj[i].taName+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 

/**
 * 基金控件
 * DEMO:
 * $("#fundCode").fundMultiselect({selectedValues:["481008", "481001"]});
 */
(function ($) { 
	$.fn.fundMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/fundcode.json?math="+Math.random(),
				multiple: true,
				filter: true,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].fundCode+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].fundCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].fundCode+":"+obj[i].fundAttr+":"+obj[i].fundAttrPinyin+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 

/**shashijie 2015-07-22 3.1资金优化 FDSDEV-478
 * 清算类型
 * DEMO:
 * $("#settleClassCode").fundMultiselect({selectedValues:["481008", "481001"]});
 */
(function ($) { 
	$.fn.settleClassMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/settleClassCode.json?math="+Math.random(),
				multiple: true,
				filter: true,
				selectedValues:""//[aa,bb,cc]默认选中项,传入List
			};
		//合并追加参数,设置默认选中项
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].code+"'";
       					//若有默认选中值
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						//循环判断选中项
	       						if(obj[i].code == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].code+":"+obj[i].name+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery);

/**shashijie 20160804 FDSDEV-880 客户余额报表增加,余额类型控件
 * DEMO:
 * $("#overType").overTypeSelect({selectedValues:"[48, 51]"});
 */
(function ($) { 
	$.fn.overTypeSelect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/overType.json?math="+Math.random(),
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].code+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].code == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].code+":"+obj[i].name+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery);

/**shashijie 20160805 FDSDEV-881 报表增加可用金额类型控件
 * DEMO:
 * $("#canOver").canOverSelect({selectedValues:"[48, 51]"});
 */
(function ($) { 
	$.fn.canOverSelect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/canOver.json?math="+Math.random(),
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].code+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].code == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].code+":"+obj[i].name+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery);

/**
 * 活动列表控件
 * DEMO:
 * $("#taCode").fundMultiselect({selectedValues:["48", "51"]});
 */
(function ($) { 
	$.fn.activeMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/active.json?math="+Math.random(),
				multiple: false,
				filter: true,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].activeNo+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].activeNo == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].activeNo+":"+obj[i].activeName+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 

/**
 * 新开窗口的空间
 * DEMO:
 *     $( "#advSoso" ).click(function(){
 *		$("#dialog-form").openWidow({
 *			url:"<%=root %>/common/cpsettledtcfg/doAdd",
 *			width:800,
 *			height:500,
 *			title:"新增",
 *			show:""
 *		});
 *    });
 */
(function ($) { 
	$.fn.openWidow = function (options) { 
		var divId = "dialog" + Math.round(Math.random() * 100); 
		var settings = { 
			id: divId, 
			width: 300, 
			height: 200, 
			modal: true, 
			buttons: { }, 
			show: "",//"explode", 
			hide: "",//"highlight", 
			title: "提示", 
			url: "", 
			close: function () { 
				$("#" + this.id).remove(); 
				//debugger 
				if (document.getElementById(this.id)) {
					document.body.removeChild(document.getElementById(this.id));
				}
			} 
		}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		$("body").append('<div id="' + settings.id + '" title="Dialog Title"><p class="loading"> </div>'); 
		// Dialog 
		$('#' + settings.id).dialog({ 
			autoOpen: false, 
			title: settings.title, 
			width: settings.width, 
			height: settings.height, 
			modal: true, 
			bgiframe: true, 
			show: settings.show, 
			hide: settings.hide, 
			buttons: settings.buttons, 
			close: settings.close, 
			open: function () { 
				$("#" + settings.id).html('<iframe src="' + settings.url + '" frameborder="0" height="100%" width="100%" id="dialogFrame" scrolling="auto"></iframe>'); 
			}, 
			resizeStop: function () { 
				$("#dialogFrame").css("width", parseInt($(this).css("width")) - 5); 
				$("#dialogFrame").css("height", parseInt($(this).css("height")) - 5); 
			} 
		}); 

		return $('#' + settings.id).dialog("open"); 
		 
	}; 
})(jQuery); 

(function ($) { 
	$.fn.openCustWindow = function (options) {
		var settings = {
			baseUrl: rootUrl,
			url: "/ajax/custno?callbackJsFunc=closeWindow", 
			width: 800, 
			height: 550,
			title:"客户基本信息查询"
		};
		if (options) { 
			$.extend(settings, options); 
		}
		
		return $("#"+this.id).openWidow({
			url:settings.baseUrl+settings.url,
			width:settings.width,
			height:settings.height,
			title:settings.title,
			show:""
		});
	};
})(jQuery); 


/**
 * 对方清算人控件
 * DEMO:
 * $("#thatPartCode").fundMultiselect({selectedValues:["48", "51"]});
 */
(function ($) { 
	$.fn.PartCodeMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/thatPartCode.json?taCodes=",
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url+settings.selectedValues,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(var i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].partCode+"'";
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].taCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].partCode+":"+obj[i].partName+"</option>";
       					_this.append(_html);
       				}
       			}
       			
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 
/**
 * 清算异常 对方清算人控件
 * DEMO:
 * $("#thatPartCode").fundMultiselect({selectedValues:["48", "51"]});
 */
(function ($) { 
	$.fn.PartCodeExMultiselect = function (options) { 
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/thatPartCodeEx.json",
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url+settings.selectedValues,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(var i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].partCode+"'";
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].taCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].partCode+":"+obj[i].partName+"</option>";
       					_this.append(_html);
       				}
       			}
       			
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 

/**
 * 基金白名单开通快赎的TA
 * @author xiaoyang.shi FDSDEV-638 股基T+1 20150824
 * DEMO:
 * $("#taCode").whiteTaMultiselect({selectedValues:["48", "51"]});
 */
(function ($) { 
	$.fn.whiteTaMultiselect = function (options) {
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/whiteTaCode.json?math="+Math.random(),
				multiple: true,
				filter: true,
				fundCodeId: undefined,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].taCode+"'";
       					
       					if(settings.selectedValues && settings.selectedValues != ""){
       						var selectedList = eval(settings.selectedValues.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].taCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].taCode+":"+obj[i].taName+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 

/**
 * 基金白名单开通快赎的基金
 * @author xiaoyang.shi FDSDEV-638 股基T+1 20150824
 * DEMO:
 * $("#fundCode").whiteFundMultiselect({selectedValues:["481008", "481001"]});
 */
(function ($) { 
	$.fn.whiteFundMultiselect = function (options) {
		var settings = {
				baseUrl: rootUrl,
				url: "/ajax/whiteFundCode.json?math="+Math.random()+"&taCodes=",
				multiple: true,
				filter: true,
				selectedValues:""//[aa,bb,cc]
			}; 
		if (options) { 
			$.extend(settings, options); 
		} 
		var _this =  $(this);
		if(settings.multiple){
			_this.prop("multiple", "multiple");
		}
	    $.getJSON(settings.baseUrl + settings.url+settings.selectedTaCode,
       		function(data){
	    		_this.empty();
       			if(data.list){
       				var obj = data.list;
       				for(i=0;i<obj.length;i++){
       					_html = "<option value='"+obj[i].fundCode+"'";
       					// && settings.selectedValues != 'null' 增加该判断解决反选TA时出现的没有同步刷新fundcode下拉条件的问题
       					if(settings.selectedFundCode && settings.selectedFundCode != "" && settings.selectedFundCode != 'null'){
       						var selectedList = eval(settings.selectedFundCode.replace("[","['").replace("]","']").replace(/\, /g,"','"));
	       					$.each(selectedList, function(x, val){
	       						if(obj[i].fundCode == val){
	       							_html += " selected=\"selected\" ";
	       							return false;
	       						}
	       					});
       					}
       					_html += ">"+obj[i].fundCode+":"+obj[i].fundAttr+":"+obj[i].fundAttrPinyin+"</option>";
       					_this.append(_html);
       				}
       			}
       			_this = _this.multiselect({multiple:settings.multiple});
       			if(settings.filter){
       				_this = _this.multiselectfilter();
       			}
       		}
        );
		return _this;
	};
})(jQuery); 
