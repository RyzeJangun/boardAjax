
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
//취소기능
function cancel(){
	if(confirm("회원가입을 취소하시겠습니까?")==true){
	alert("취소 되었습니다.");
	window.history.back();//이전페이지 롤백
	}else{
		return;
	}
}
$(function(){
	
	//모든 공백 체크 정규식
	var empJ = /\s/g; 
	//아이디 정규식 6자 이상의 소문자 영문, 숫자만 사용 가능
	var idJ = /^[a-zA-Z0-9_\-]{6,12}$/;
	// 비밀번호 정규식 숫자, 문자, 특수문자 중 모두 포함(6~12자)해서 입력
    var pwJ = /^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{6,12}$/;
    
    var user_nameJ = /^[가-힣]{2,10}$|[a-zA-Z]{1,10}\s[a-zA-Z]{1,10}$/;
	
	//아이디 ajax
	 $('#idCheck').click(function(){
		 		var userid=$('#userid').val();
		 	if(idJ.test($('#userid').val())) {
				$.ajax({
					data : {userid : $('#userid').val()},
					url : "/user/idCkedAjax.do",
					type : "get",
					dataType : "JSON",
					success : function(data){
						    /* console.log("데이터값이 넘어왔다!"); */
							if(data == 1){
								$('#id_result').text('사용중인 아이디입니다.');
								$('#id_result').css('color', 'red');
							}else if(data == 0 && userid != "") {					
								$('#id_result').text('사용 가능한 아이디 입니다');
								$('#id_result').css('color', 'blue');
							}else if(userid == ""){			
								$('#id_result').text('아이디를 입력해주세요.');
						 		$('#id_result').css('color', 'red');	
								
							} 
						}
					});

		 	}else {
		 		console.log('false');
		 		$('#id_result').text('6자이상의 소문자 영문, 숫자만 사용 가능합니다.');
		 		$('#id_result').css('color', 'red');
		 	}
	 });
	
//비밀번호 확인 기능
	   $('#user_pwd').keyup(function() {
	      if(pwJ.test($('#user_pwd').val())) {
	         console.log('true');
	         $('#pwd_check').text('비밀번호 조건 충족');
	         $('#pwd_check').css('color', 'blue');
	      }else {
	         console.log('false');
	         $('#pwd_check').text('숫자, 문자, 특수문자  모두 포함(6~12자)해서 입력해주세요.');
	         $('#pwd_check').css('color', 'red');
	      }
	   });
//패스워드 일치 확인
	   $('#user_pwd2').keyup(function() {
	      if($('#user_pwd').val() != $(this).val()) {
	         $('#pwd2_check').text('비밀번호가 일치하지 않습니다.');
	         $('#pwd2_check').css('color', 'red');
	      }else {
	         $('#pwd2_check').text('비밀번호가 일치합니다.');
	         $('#pwd2_check').css('color','blue');
	      }
	  });
	  
	 //전송 클릭 시 유효성 검사
	   $('form').on('submit',function(){
	      var inval_Arr = new Array(2).fill(false);
	      //아이디 유효성 검사
	      if(idJ.test($('#userid').val())){
	         inval_Arr[0] = true;
	      }else {
	         inval_Arr[0] = false;
	         alert('아이디를 확인하세요.');
	         $("#userid").focus();
	         return false;
	      }
	      //비밀번호가 같은 경우 & 비밀번호 정규식
	      if(($('#user_pwd').val() == $('#user_pwd2').val())&& pwJ.test($('#user_pwd').val())){
	         inval_Arr[1] = true;
	      }else {
	         inval_Arr[1] = false;
	         alert('비밀번호를 확인하세요.');
	         $("#user_pwd").focus();
	         return false;
	      }
	      //이름 공백검사
	      if(user_nameJ.test($('#user_name').val())){
	    	  inval_Arr[2] = true;
	      }else{
	    	  inval_Arr[2] = false;
		      alert('이름을 입력하세요.');
		      $('#user_name').focus();
		      return false;
	      }
	  
	   });
});

</script>

<div class="container" style="margin-top: 50px">
	<form class="form-horizontal" method="post" action="/user/userJoin.do">
	    <div class="form-group">
	      <label class="col-sm-2 control-label">ID</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="userid" name="userid" type="text" title="ID" required="required">
	        <button type="button" class="btn btn-white" id="idCheck">ID중복체크</button>
	        <div id="id_result"></div>
	      </div>
	    </div>
	    <div class="form-group">
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="user_pwd" name="pwd" type="password" title="패스워드">
	        <div id="pwd_check"></div>
	      </div>
	    </div>
	    
	    <div class="form-group">
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드 확인</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="user_pwd2" type="password" title="패스워드 확인">
         <div id="pwd2_check"></div>
	      </div>
	    </div>

	    <div class="form-group">
	      <label for="disabledInput" class="col-sm-2 control-label">이름</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="user_name" name="user_name" type="text" title="이름">
	      </div>
	    </div>

	    <div class="col-md-offset-4">
			<input type="submit" class="btn btn-primary" value="회원가입">
			<button type="button" class="btn btn-danger" onclick="cancel();">취소</button>
	    </div>
	</form>
</div>


