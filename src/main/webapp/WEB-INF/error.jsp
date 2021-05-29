<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>错误页面</title>
</head>
<body>
<script type="text/javascript">

function showDiv(){
	document.getElementById("show").style.display = "block";
}

</script>
	<h2>Exception: ${errorCode }</h2>
	<a href="javascript:void(0);" onclick="showDiv();"> 详细信息 </a>
	<div id="show" style="color: red; display: none;">${errorCode }
		${errorDesc }</div>
</body>
</html>