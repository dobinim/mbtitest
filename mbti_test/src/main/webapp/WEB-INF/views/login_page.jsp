<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/> 
<meta id="_csrf" name="csrf" content="${_csrf.token}"/> 

<title>MBTI TEST : LOGIN PAGE</title>

<!--jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<!--popper jquery -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!--bootstrap-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!--latest javascript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!--fontawesome icon-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" 
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<!--google icon -->
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<div class = "container mx-auto" id = "main">
	<div id = "header">
		MBTI TEST : LOGIN 
	</div>
	<div id = "body">
		<span id = "logAlert" class = "text-center" style = "display:none;"></span>
		<form id = "loginBody" action = "login" method = "post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type = "text" id = "name" name = "mName" placeholder = "아이디를 입력하세요.">
			<input type = "password" id = "pw" name = "mPw" placeholder = "비밀번호를 입력하세요.">
			<button type = "submit" id = "loginBtn">로그인</button>
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	<c:choose>
		<c:when test = "${not empty log}">
			$("#logAlert").text("로그인 필요");
		</c:when>
		<c:when test = "${not empty logout}">
			$("#logAlert").text("로그아웃 성공");
		</c:when>
		<c:when test = "${not empty error}">
			$("#logAlert").text("로그인 실패");
		</c:when>
		<c:otherwise>
			$("#logAlert").text("환영합니다!");
		</c:otherwise>
	</c:choose>
});
</script>
</body>
</html>