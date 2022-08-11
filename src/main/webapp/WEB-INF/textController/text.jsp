<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试demo</title>
    <link href="${root }/css/css.css" rel="stylesheet" type="text/css"/>
    <link href="${root }/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${root }/js/common.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-openwindow.js"></script>
    <script type="text/javascript">
        
        /*子窗口 新增、修改窗口*/
        var detailWindow;
        
        $(function () {
            
            /*分页*/
            $("#bigpage").update_page({
                "total_page": "${pages}",
                "current_page": "${pagenum}",
                callbackfunc: doQuerySubmit
            });
            
            /*查询*/
            $("#queryBtn").click(function () {
                doQuerySubmit(1);
            });
            
            /*删除*/
            $("#deleteBtn").click(function () {
                if (!$(":radio:checked").val()) {
                    alert("请先选择相应规则！");
                    return;
                }
                
                var data = {
                    "id": $(":radio:checked").val()
                };
                doSubmitNonForm(data, "${root}/textController/textDelete", deleteSuccess);
            });
            
            /*新增*/
            $("#addBtn").click(function () {
                detailWindow = $("#dialog-form").openWidow({
                    url: "${root}/textController/textAdd",
                    width: 300,
                    height: 300,
                    title: "新增页面",
                    show: ""
                });
            });
            
            /*修改*/
            $("#updateBtn").click(function () {
                if (!$(":radio:checked").val()) {
                    alert("请先选择相应记录！");
                    return;
                }
                
                detailWindow = $("#dialog-form").openWidow({
                    url: "${root}/textController/textUpdate?id=" + $(":radio:checked").val(),
                    width: 300,
                    height: 300,
                    title: "修改页面",
                    show: ""
                });
            });
            
            /*发送消息*/
            $("#sendBtn").click(function () {
                $('#form1').attr("action", "${root }/textController/textSend").submit();
            });
            
            /*缓存消息*/
            $("#redisBtn").click(function () {
                $('#form1').attr("action", "${root }/textController/textRedis").submit();
            });
            
        });
        
        /*分页查询*/
        function doQuerySubmit(page) {
            $("#pageNum").val(page);
            $("#form1").submit();
        }
        
        //弹出子页面回调函数
        closeWindow = function () {
            $(detailWindow).dialog('close');
            $("#form1").submit();
        };
        
        /*删除成功回调方法*/
        function deleteSuccess(data) {
            // 最好通过后台统一传递参数判断请求是否正常接受
            if (data == undefined || data.showInfo == undefined) {
                alert(data.errorDesc);
            } else {
                alert(data.showInfo);
            }
            doQuerySubmit(1);
        }
    
    </script>
</head>
<body>
<form action="${root }/textController/queryList" method="post" id="form1">
    <table width="100%" align="center" cellpadding="0" cellspacing="0" border="1">
        <tr>
            <td height="30" align="center">
                <div style="margin-left: 80px;">
                    <input type="button" id="queryBtn" style="margin-left:20px" value="查询"/>
                    <input type="button" id="addBtn" style="margin-left:20px" value="新增"/>
                    <input type="button" id="updateBtn" style="margin-left:20px" value="修改"/>
                    <input type="button" id="deleteBtn" style="margin-left:20px" value="删除"/>
                    <input type="button" id="sendBtn" style="margin-left:20px" value="发送消息"/>
                    <input type="button" id="redisBtn" style="margin-left:20px" value="缓存信息"/>
                    <input id="pageNum" name="pageNum" type="hidden" value="${vo.pageNum }"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="20"/>
                </div>
            </td>
        </tr>
    </table>
    <table width="95%" align="center" cellpadding="0" cellspacing="0" border="1">
        <tr>
            <td height="40">
                <table width="100%" cellpadding="4" cellspacing="1" border="1">
                    <tr>
                        <th width="5%" align="center"></th>
                        <th width="10%" align="center">基金代码</th>
                        <th width="5%" align="center">科目名称</th>
                        <th width="10%" align="center">余额</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(list)>0 }">
                            <c:forEach items="${list}" var="vo">
                                <tr align="center">
                                    <td><input type="radio" name="id" value="${vo.id}"/></td>
                                    <td>${vo.jjdm}</td>
                                    <td>${vo.kmmc }</td>
                                    <td>${vo.yue}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="8">查无数据</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </td>
        </tr>
    </table>
    <c:if test="${fn:length(list)>0 }">
        <table width="95%" cellpadding="0" cellspacing="0" align="center" border="1">
            <tr>
                <td>
                    <div id="bigpage"></div>
                </td>
            </tr>
        </table>
    </c:if>
</form>
<div id="dialog-form" style="display: none;"></div>
</body>
</html>