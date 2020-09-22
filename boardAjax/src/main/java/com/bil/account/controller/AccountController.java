package com.bil.account.controller;

import java.lang.ProcessBuilder.Redirect;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bil.account.service.AccountService;
import com.bil.account.vo.AccountVO;
import com.bil.common.service.CommonService;
import com.bil.user.service.UserService;

import com.bil.util.CommUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bil.util.Pager;



@Controller
public class AccountController {


	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="userService")
	private UserService userService;

	/**
	 *
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	
	//회계정보리스트
	@RequestMapping(value = "/account/accountList.do")
	public ModelAndView selectSampleList(HttpServletRequest request, ModelMap model,@RequestParam(defaultValue="1") int curPage) throws Exception {
		
		
		//레코드 갯수 계산
		int count=accountService.countArticle();
		
		//페이지 관련 설정
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		
		ModelAndView mav =new ModelAndView();
		List<AccountVO> accountList = accountService.accountList(start,end);
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		inOutMap.put("accountList",accountList);
		inOutMap.put("pager", pager); //페이지 네비게이션을 위한 변수
		mav.setViewName("/account/accountList");
		mav.addObject("inOutMap", inOutMap);
		return mav;
	}



	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	//회계등록 페이지 이동
	@RequestMapping(value="/account/accountInsert.do")
	public String accountInsert(HttpServletRequest request, ModelMap model) throws Exception{
		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		inOutMap.put("category","A000000");
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);
		System.out.println(resultMap);
		model.put("resultMap", resultMap);
		return "/account/accountInsert";
	}


	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	/*셀렉박스 ajax
		@RequestMapping(value="/account/selectCombo.do",method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView ajaxSelectbox(HttpServletRequest request,@RequestParam("profit_cost") String profit_cost) throws Exception{
			Map<String, Object> map = CommUtils.getFormParam(request);
			List<EgovMap> codeData = commonService.selectCode(profit_cost);		
			System.out.println(codeData);
			map.put("codeData", codeData);
			return new ModelAndView(jsonView,map);
		}*/
	
	//셀렉박스 ajax
	@RequestMapping(value="/account/selectCombo.do",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView ajaxSelectbox(HttpServletRequest request) throws Exception{
		Map<String, Object> map = CommUtils.getFormParam(request);
		List<EgovMap> codeData = commonService.selectCombo(map);
		System.out.println(codeData);
		map.put("codeData", codeData);
		return new ModelAndView(jsonView,map);
	}
	
	//회계등록(추가)
	@RequestMapping(value="/account/accountJoin.do")
	public String accountJoin(HttpServletRequest request,AccountVO vo) {
		accountService.accountJoin(vo);
		System.out.println(vo);		
		return "redirect:/account/accountDetail.do?account_seq="+vo.getAccount_seq();
	}
	
	//회계 수정 페이지,세부정보
	@RequestMapping(value="/account/accountDetail.do")
	public ModelAndView accountDetail(HttpServletRequest request,int account_seq,ModelMap model) throws Exception {
		//셀렉박스
		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		inOutMap.put("category","A000000");
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);
		System.out.println(resultMap);
		model.put("resultMap", resultMap);
		//수정기능
		AccountVO vo = accountService.accountDetail(account_seq);
		System.out.println(vo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/account/accountUpdate");
		mav.addObject("detail", vo);
		return mav;
	}
	
	//회계 수정
	@RequestMapping("/account/accountUpdate.do")
	public String accountUpdate(AccountVO vo) {
		accountService.accountUpdate(vo);
		return "redirect:/account/accountList.do";
	}
	
	//엑셀 다운로드
	@RequestMapping("/account/excelDownload.do")
	public void excelDownload(HttpServletResponse response,@RequestParam(defaultValue="1") int curPage) throws Exception{
		 
		 int count=accountService.countArticle();
		 
		 Pager pager=new Pager(count,curPage);
		 int start=pager.getPageBegin();
		 int end=pager.getPageEnd();
		   
		    //회계 목록조회
		   List<AccountVO> accountList = accountService.accountList(start,end);
		    System.out.println(accountList);
		    //워크북 생성
		    Workbook wb = new HSSFWorkbook();
		    Sheet sheet = wb.createSheet("회계정보");
		    Row row = null;
		    Cell cell = null;
		    int rowNo = 0;
		    // 테이블 헤더용 스타일
		    CellStyle headStyle = wb.createCellStyle();


		    // 헤더 생성

		    row = sheet.createRow(rowNo++);

		    cell = row.createCell(0);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("seq");

		    cell = row.createCell(1);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("수익/비용");

		    cell = row.createCell(2);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("관");
		    
		    cell = row.createCell(3);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("항");
		    
		    cell = row.createCell(4);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("목");
		    
		    cell = row.createCell(5);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("과");
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("상세입력");
		    
		    cell = row.createCell(6);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("금액");
		    
		    cell = row.createCell(7);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("거래일자");
		    
		    cell = row.createCell(8);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("등록일자");
		    
		    cell = row.createCell(9);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("작성자");
		    
		    



		    // 데이터 부분 생성

		    for(AccountVO vo : accountList) {

		        row = sheet.createRow(rowNo++);
		        
		        cell = row.createCell(0);
		        cell.setCellValue(vo.getAccount_seq());
		        
		        cell = row.createCell(1);
		        cell.setCellValue(vo.getProfit_cost_nm());
		        
		        cell = row.createCell(2);
		        cell.setCellValue(vo.getBig_group_nm());
		        
		        cell = row.createCell(3);
		        cell.setCellValue(vo.getMiddle_group_nm());
		        
		        cell = row.createCell(4);
		        cell.setCellValue(vo.getSmall_group_nm());
		        
		        cell = row.createCell(5);
		        cell.setCellValue(vo.getDetail_group_nm());
		        
		        cell = row.createCell(6);
		        cell.setCellValue(vo.getComments());
		        
		        cell = row.createCell(7);
		        cell.setCellValue(vo.getTransaction_money());
		        
		        cell = row.createCell(8);
		        cell.setCellValue(vo.getTransaction_date());
		        
		        cell = row.createCell(9);
		        cell.setCellValue(vo.getWriter());
		        
		        cell = row.createCell(10);
		        cell.setCellValue(vo.getReg_date());
		    }
		    // 컨텐츠 타입과 파일명 지정

		    response.setHeader("Content-Disposition", "ATTachment; Filename="+URLEncoder.encode("회계정보","UTF-8")+".xls");
		    // 엑셀 출력
		    wb.write(response.getOutputStream());
		    wb.close();
		}
	}// end of class


