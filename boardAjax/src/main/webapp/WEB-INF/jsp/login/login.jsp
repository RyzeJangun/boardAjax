<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
//회원가입 루트
function joinPath(){
	document.location.href="/user/userInsert.do";
}

//로그인 기능
function btnLogin(){
	var userid=$("#userid").val(); //태그의 value 속성값
	var pwd=$("#pwd").val();
	if(userid==""){
		alert("아이디를 입력하세요.");
		$("#userid").focus();
		return;
	}else if(pwd==""){
		alert("비밀번호를 입력하세요.");
		$("#pwd").focus();
		return;
	}else {
		document.form1.action="/login/login_check.do";
		document.form1.submit();	
	}	
	 
}
</script>


<form id="sendForm" name="form1" method="post">
	<!-- <input type="hidden" id="platform" name="platform" value=""> -->
	<div class="container col-md-offset-2 col-sm-6" style="margin-top: 100px;">
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input id="userid" type="text" class="form-control valiChk" name="userid" placeholder="id" title="ID">
			</div>
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				<input id="pwd" type="password" class="form-control valiChk" name="pwd" placeholder="Password" title="Password">
			</div>
			<br />
			<c:if test="${error == 'error'}">
				<div class="input-group mb-3" style="color:red;">
					아이디 또는 비밀번호가 일치하지 않습니다.
				</div>
			</c:if>
		<br>
		<div class="col-md-offset-4">
			<button type="submit" class="btn btn-primary" onclick="btnLogin();">로그인</button>
			<button type="button" id="#" class="btn btn-warning" onclick="location.href='/login/login.do'">취소</button>
			<button type="button" class="btn btn-info" onclick="joinPath();">회원가입</button>
		</div>
	</div>
</form>

