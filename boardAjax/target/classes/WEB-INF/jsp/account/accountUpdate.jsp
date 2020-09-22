<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="${path}/js/common.js"></script>
<script>
function cancel(){
	if(confirm("목록으로 돌아가시겠습니까?")==true){
	document.location.replace("/account/accountList.do");
	}else{
		return;
	}
}

function update(){
	 if(confirm("회계정보를 수정 하시겠습니까?")==true){
		document.form1.action="/account/accountUpdate.do";
		document.form1.submit();
		alert("회계정보가 수정되었습니다.");
	 }else {
		 return;
	 }
}

$(function(){
	//관 ajax
	$('#profitCost').click(function(){
		var profit_cost = $('#profitCost').val();
		$.ajax({
			data : {category : profit_cost},
			url : "/account/selectCombo.do",
			type : "get",
			datatype : "JSON",
			success : function(result){
				  var code = result.codeData;
				  if(code.length > 0){  
				  $('#bigGroup').find('option').remove();
				  $('#bigGroup').append("<option value=''>전체</option>");
				  $('#middleGroup').find('option').remove();
				  $('#middleGroup').append("<option value=''>해당없음</option>");
				  $('#smallGroup').find('option').remove();
				  $('#smallGroup').append("<option value=''>해당없음</option>");
				  $('#detailGroup').find('option').remove();
				  $('#detailGroup').append("<option value=''>해당없음</option>");
				  for (i = 0; i < code.length; i++) {
		               $('#bigGroup').append("<option value='" + code[i].code + "'>" + code[i].comKor + "</option>");
			  }  
			}else{
				 $('#bigGroup').find('option').remove();
		         $('#bigGroup').append("<option value=''>선택</option>");
			}
		  }
		});
	});
	
	//항 ajax
	$('#bigGroup').click(function(){
		var big_group = $('#bigGroup').val();
		$.ajax({
			data : {category : big_group},
			url : "/account/selectCombo.do",
			type : "get",
			datatype : "JSON",
			success : function(result){
				  var code = result.codeData;
				  if(code.length > 0){  
				  $('#middleGroup').find('option').remove();
				  $('#middleGroup').append("<option value=''>전체</option>");
				  $('#smallGroup').find('option').remove();
				  $('#smallGroup').append("<option value=''>해당없음</option>");
				  $('#detailGroup').find('option').remove();
				  $('#detailGroup').append("<option value=''>해당없음</option>");
				  for (i = 0; i < code.length; i++) {
		               $('#middleGroup').append("<option value='" + code[i].code + "'>" + code[i].comKor + "</option>");
			  }  
			}else{
				 $('#middleGroup').find('option').remove();
				 $('#smallGroup').append("<option value=''>해당없음</option>");
			}
		  }
		});
	});
	
	//목 ajax
	$('#middleGroup').click(function(){
		var middle_group = $('#middleGroup').val();
		$.ajax({
			data : {category : middle_group},
			url : "/account/selectCombo.do",
			type : "get",
			datatype : "JSON",
			success : function(result){
				  var code = result.codeData;
				  if(code.length > 0){  
				  $('#smallGroup').find('option').remove();
				  $('#smallGroup').append("<option value=''>전체</option>");
				  $('#detailGroup').find('option').remove();
				  $('#detailGroup').append("<option value=''>해당없음</option>");
				  for (i = 0; i < code.length; i++) {
		               $('#smallGroup').append("<option value='" + code[i].code + "'>" + code[i].comKor + "</option>");
			  }  
			}else{
				 $('#smallGroup').find('option').remove();
		         $('#smallGroup').append("<option value=''>해당없음</option>");
			}
		  }
		});
	});
	
	//과 ajax
	$('#smallGroup').click(function(){
		var middle_group = $('#smallGroup').val();
		$.ajax({
			data : {category : middle_group},
			url : "/account/selectCombo.do",
			type : "get",
			datatype : "JSON",
			success : function(result){
				  var code = result.codeData;
				  if(code.length > 0){  
				  $('#detailGroup').find('option').remove();
				  $('#detailGroup').append("<option value=''>전체</option>");
				  for (i = 0; i < code.length; i++) {
		               $('#detailGroup').append("<option value='" + code[i].code + "'>" + code[i].comKor + "</option>");
			  }  
			}else{
				 $('#detailGroup').find('option').remove();
		         $('#detailGroup').append("<option value=''>해당없음</option>");
			}
		  }
		});
	});
	
});


</script>

<!-- 비용 START -->
<div class="container" style="margin-top: 50px">
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>
	<div class="col-sm-12"><label for="disabledInput" class="col-sm-12 control-label"></label></div>


<form name="form1" method="get">
	<div class="col-sm-11" id="costDiv">
		<div>
			<div class="col-sm-11">
			 		<div class="col-sm-12">
				      <div class="col-sm-3">
						<select class="form-control" id="profitCost" name="profit_cost" title="비용">
				        	<option value="${detail.profit_cost}">${detail.profit_cost_nm}</option>
				        	<c:forEach var="list" items="${resultMap}" varStatus="cnt">
					        	<option value="${list.code}">${list.comKor}</option>
				        	</c:forEach>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control" id="bigGroup" name="big_group" title="관">
				        	<option value="${detail.big_group}">${detail.big_group_nm}</option>
						
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="middleGroup" name="middle_group"  title="항">
					        	<option value="${detail.middle_group}">${detail.middle_group_nm}</option>
				        </select>
				      </div>

				      <div class="col-sm-3">
						<select class="form-control " id="smallGroup" name="small_group" title="목">
					        	<option value="${detail.small_group}">${detail.small_group_nm}</option>
				        </select>
				      </div>
			 		</div>

			 		<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 			  <div class="col-sm-3">
								<select class="form-control " id="detailGroup" name="detail_group" title="과">
							        	<option value="${detail.detail_group}">${detail.detail_group_nm}</option>
						        </select>
					      </div>
				      <div class="col-sm-9">
				      		<input class="form-control" name="comments" type="text" placeholder="비용 상세 입력" title="비용 상세" value="${detail.comments}">
				      </div>
			 		</div>
                    <div>
                     <input type="hidden" name="account_seq" value="${detail.account_seq}">
                    </div>
				<div class="col-sm-12">  <label for="disabledInput" class="col-sm-12 control-label"> </label></div>
			 		<div class="col-sm-12">
			 		  <label for="disabledInput" class="col-sm-1 control-label"><font size="1px">금액</font></label>
				      <div class="col-sm-3">
				        	<input class="form-control" name="transaction_money" type="text" value="${detail.transaction_money}" placeholder="숫자만 입력 가능합니다." title="금액" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
				      </div>
			 		  <label for="disabledInput" class="col-sm-1 control-label"><font size="1px">거래일자</font></label>
				      <div class="col-sm-3">
				       	 <input id="datepicker" name="transaction_date" type="date" style="width: 80%" title="거래일자" value="${detail.transaction_date}">
				      </div>
			 		</div>
			 </div>
		</div>
	</div><br><br>
	 <div>
					<div class="col-md-offset-4"><label for="disabledInput" class="col-sm-12 control-label">
					<input type="submit" class="btn btn-primary" value="회계 수정" onclick="update();">
					<input type="button" class="btn btn-warning" value="목록" onclick="cancel();">
					</label>
					</div>
	</div>
	</form>
</div>

<!-- 비용 END -->