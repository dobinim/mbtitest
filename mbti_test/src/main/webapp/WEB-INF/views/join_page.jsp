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
<meta id="_csrf" name="csrf" content="${_csrf.token}"/> <!-- 페이지 위조 요청 방지를 위한 태그 -->

<title>MBTI TEST MAIN 2</title>

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
<style>
#signupbtn {
	background-color : black;
	color : white;
}

#name_Ok, #name_notOk {
	display : none;
}
</style>
<body>
<div class = "container mx-auto">
	<div id = "header">
		<p>MBTI 테스트</p>
	</div>
	<div id = "main">
		<p id = "introduction">우선, 당신의 정보를 입력해봅시다.</p>
		<div class = "container mx-auto">
			<form method = "post" action = "join" id = "signup">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
				<input type = "text" id = "name" name = "mName" placeholder = "당신의 이름을 입력하세요.">
				<input type = "text" id = "pw" name = "mPw" placeholder = "비밀번호를 입력하세요.">
				<input type = "text" id = "nickname" name = "mNickname" placeholder = "닉네임을 입력하세요.">
				<button type = "button" class = "btn btn-light" onclick = "name_Check()">중복 확인</button>
				<span style = "color:gray;">&nbsp;2~8자리의 영문 또는 한글, 특수문자 제외</span>	<br/>
				<span id = "name_Ok">사용 가능한 닉네임입니다.</span>
				<span id = "name_notOk">사용할 수 없는 닉네임입니다.</span><br/>
<!-- 			<input type = "text" id = "mbti" name = "mbti" placeholder = "당신의 MBTI를 입력하세요."> -->
				<select id = "mbti" name = "mbti">
					<option value = "">MBTI를 선택하세요</option>
					<option value = "ENFJ">ENFJ</option>
					<option value = "ENFP">ENFP</option>
					<option value = "ENTJ">ENTJ</option>
					<option value = "ENTP">ENTP</option>					
					<option value = "ESFJ">ESFJ</option>					
					<option value = "ESFP">ESFP</option>					
					<option value = "ESTJ">ESTJ</option>					
					<option value = "ESTP">ESTP</option>					
					<option value = "INFJ">INFJ</option>					
					<option value = "INFP">INFP</option>					
					<option value = "INTJ">INTJ</option>					
					<option value = "INTP">INTP</option>					
					<option value = "ISFJ">ISFJ</option>					
					<option value = "ISFP">ISFP</option>					
					<option value = "ISTJ">ISTJ</option>					
					<option value = "ISTP">ISTP</option>					
				</select>
				<button type = "submit" id = "signupbtn">등록</button>
			</form>
		</div>
		<button type = "button" id = "loginBtn" onclick = "location.href='login_page'">로그인 페이지로 이동</button>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#signupbtn").click(function(){
		if(confirm("등록하시겠어요?") == true) {
			$("#signup").submit(function(event){
				event.preventDefault();
				$.ajax({
					url : $("#signup").attr("action"),
					type : $("#signup").attr("method"),
					data : $("#signup").serialize(),
					success : function(data) {
						if (data.search("join success") > -1) {
							alert("등록이 완료되었습니다.");
							location.href="join_page";
						} else {
							alert("등록에 실패했습니다. 다시 시도해주세요.");
						}
					},
					error : function(){
						alert("등록할 수 없습니다. 다시 시도해주세요.");
					}
				});
			});
		} else {
			return;
		}
	});	
});

function name_Check() {
	let mNickname = $("#nickname").val();
	$.ajax({
		url : "nameCheck",
		type : "get",
		data : {mNickname : mNickname},
		success : function(cnt){
			if(cnt == 0) {
				$("#name_Ok").css("display", "inline-block");
				$("#name_notOk").css("display", "none");
			} else {
				$("#name_Ok").css("display", "none");
				$("#name_notOk").css("display", "inline-block");
			}
		},
		error : function(){
			alert("오류 발생. 다시 시도해주세요.");
		}
	});
}
</script>
</body>
</html>