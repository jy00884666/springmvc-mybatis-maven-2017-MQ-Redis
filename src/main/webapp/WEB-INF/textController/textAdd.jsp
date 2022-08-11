<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试demo</title>
    <script type="text/javascript" src="${root }/js/common.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${root }/js/jquery-openwindow.js"></script>
    <script type="text/javascript">
        
        
        $(function () {
            /*新增*/
            $("#saveBtn").click(function () {
                $.ajax({
                    url: "${root}/textController/textAddDo.json",
                    type: 'POST',
                    data: {
                        "jjdm": $("#jjdm").val(),
                        "kmmc": $("#kmmc").val(),
                        "yue": $("#yue").val()
                    },
                    dataType: 'json',
                    statusCode: {
                        404: function () {
                            console.info('404');
                        },
                        200: function (data) {
                            alert(data.showInfo);
                        },
                        500: function (v) {
                            console.info(v);
                        },
                        504: function (v) {
                            console.info(v);
                        }
                    },
                    success: function (data) {
                        alert(data.showInfo);
                        window.parent.closeWindow();
                    }
                });
            });
            
            /*关闭*/
            $("#closeWindow").click(function () {
                window.parent.closeWindow();
            });
            
        });
    
    
    </script>
</head>
<body>
<form action="" method="post" id="sform">
    <table width="100%" align="center" border="1">
        <tr>
            <td>基金代码</td>
            <td><input id="jjdm"/></td>
        </tr>
        <tr>
            <td>科目名称</td>
            <td><input id="kmmc"/></td>
        </tr>
        <tr>
            <td>余额</td>
            <td><input id="yue"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" id="saveBtn" style="margin-left:20px" value="保存"/>
                <input type="button" id="closeWindow" style="margin-left:20px" value="关闭"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>