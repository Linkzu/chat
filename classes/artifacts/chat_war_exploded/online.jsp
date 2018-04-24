<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	window.setInterval(function(){
		window.location.href="UserInfoManagerServlet?type=online";
	},1000);
	
</script>
</head>
<body>
	<div style="height:32px;width:120px">
		<div style="height:32px;width:32px;float:left">${nickname}</div>
		<c:forEach items="${list}" var="u">
			<c:if test="${nickname!=u.nickname }">
				<div style="height:32px;width:32px;float:left">${u.nickname}</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>