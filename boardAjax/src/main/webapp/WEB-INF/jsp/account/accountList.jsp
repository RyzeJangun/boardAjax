<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
function list(page){
	location.href="/account/accountList.do?curPage="+page;
} 

function excelDownload(){
	document.sendForm.action="/account/excelDownload.do";
	document.sendForm.submit();
}

 
</script>

<form name="sendForm" id="sendForm" method="post" onsubmit="return false;">

<input type="hidden" id="situSeq" name="situSeq" value="">
<input type="hidden" id="mode" name="mode" value="Cre">

<div id="wrap"  class="col-md-offset-1 col-sm-10" >
		<div align="center"><h2>회계정보리스트</h2></div>
		<div class="form_box2 col-md-offset-7" align="right" >
			<div class="right" >
				<button class="btn btn-primary" onclick="location.href='/account/accountInsert.do'" >등록</button>
				<button class="btn btn-primary" onclick="excelDownload();">엑셀 다운</button>
			</div>
		</div>
	    <br/>
		<table class="table table-hover">
			    <thead>
			      <tr align="center">
			        <th style="text-align: center;" >수익/비용</th>
			        <th style="text-align: center;" >관</th>
			        <th style="text-align: center;" >항</th>
			        <th style="text-align: center;" >목</th>
			        <th style="text-align: center;" >과</th>
			        <th style="text-align: center;" >금액</th>
			        <th style="text-align: center;" >등록일</th>
			        <th style="text-align: center;" >작성자</th>
			      </tr>
			    </thead>
			    <tbody>
			   <c:forEach items="${inOutMap.accountList}" var="var">
			    <tr align="center">
                    <th style="text-align: center;" >${var.profit_cost_nm}</th>
			        <th style="text-align: center;" >${var.big_group_nm}</th>
			        <th style="text-align: center;" >${var.middle_group_nm}</th>
			        <th style="text-align: center;" >${var.small_group_nm}</th>
			        <th style="text-align: center;" >${var.detail_group_nm}</th>
			        <th style="text-align: center;" >${var.transaction_money} 원</th>
			        <th style="text-align: center;" >${var.transaction_date}</th>
			        <th style="text-align: center;" ><a style="color: black;" href="/account/accountDetail.do?account_seq=${var.account_seq}">${var.writer}</a></th>
                </tr>
                </c:forEach>
			    </tbody>
			</table>
			<div align="center">
		<c:if test="${inOutMap.pager.curPage > 1}">

			<a style="color:black;" href="#" onclick="list('1')">[처음]</a>

		</c:if>

		<c:if test="${inOutMap.pager.curPage > 1}">

			<a style="color:black;" href="#" onclick="list('${inOutMap.pager.prevPage}')"> [이전]</a>

		</c:if>

		<c:forEach var="num" begin="${inOutMap.pager.blockBegin}"
			end="${inOutMap.pager.blockEnd}">

			<c:choose>

				<c:when test="${num == inOutMap.pager.curPage}">

					<!-- 현재 페이지인 경우 하이퍼링크 제거 -->

					<span style="color: red;">${num}</span>

				</c:when>

				<c:otherwise>

					<a style="color:black;" href="#" onclick="list('${num}')">${num}</a>

				</c:otherwise>

			</c:choose>

		</c:forEach>

		<c:if test="${inOutMap.pager.curBlock < inOutMap.pager.totBlock}">

			<a style="color:black;" href="#" onclick="list('${inOutMap.pager.nextPage}')">[다음]</a>

		</c:if>

		<c:if test="${inOutMap.pager.curPage < inOutMap.pager.totPage}">

			<a style="color:black;" href="#" onclick="list('${inOutMap.pager.totPage}')">[끝]</a>

		</c:if>

	</div>

</div>
</form>


