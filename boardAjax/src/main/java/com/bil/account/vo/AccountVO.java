package com.bil.account.vo;

public class AccountVO {
	
	private int account_seq;
	private String profit_cost;
	private String big_group;
	private String middle_group;
	private String small_group;
	private String detail_group;
	private String comments;
	private String transaction_money;
	private String transaction_date;
	private String writer;
	private String reg_date;
	
	//추가 변수
	private String profit_cost_nm;
	private String big_group_nm;
	private String middle_group_nm;
	private String small_group_nm;
	private String detail_group_nm;
	
	public int getAccount_seq() {
		return account_seq;
	}
	public void setAccount_seq(int account_seq) {
		this.account_seq = account_seq;
	}
	public String getProfit_cost_nm() {
		return profit_cost_nm;
	}
	public void setProfit_cost_nm(String profit_cost_nm) {
		this.profit_cost_nm = profit_cost_nm;
	}
	public String getBig_group_nm() {
		return big_group_nm;
	}
	public void setBig_group_nm(String big_group_nm) {
		this.big_group_nm = big_group_nm;
	}
	public String getMiddle_group_nm() {
		return middle_group_nm;
	}
	public void setMiddle_group_nm(String middle_group_nm) {
		this.middle_group_nm = middle_group_nm;
	}
	public String getSmall_group_nm() {
		return small_group_nm;
	}
	public void setSmall_group_nm(String small_group_nm) {
		this.small_group_nm = small_group_nm;
	}
	public String getDetail_group_nm() {
		return detail_group_nm;
	}
	public void setDetail_group_nm(String detail_group_nm) {
		this.detail_group_nm = detail_group_nm;
	}
	public String getProfit_cost() {
		return profit_cost;
	}
	public void setProfit_cost(String profit_cost) {
		this.profit_cost = profit_cost;
	}
	public String getBig_group() {
		return big_group;
	}
	public void setBig_group(String big_group) {
		this.big_group = big_group;
	}
	public String getMiddle_group() {
		return middle_group;
	}
	public void setMiddle_group(String middle_group) {
		this.middle_group = middle_group;
	}
	public String getSmall_group() {
		return small_group;
	}
	public void setSmall_group(String small_group) {
		this.small_group = small_group;
	}
	public String getDetail_group() {
		return detail_group;
	}
	public void setDetail_group(String detail_group) {
		this.detail_group = detail_group;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTransaction_money() {
		return transaction_money;
	}
	public void setTransaction_money(String transaction_money) {
		this.transaction_money = transaction_money;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "AccountVO [account_seq=" + account_seq + ", profit_cost=" + profit_cost + ", big_group=" + big_group + ", middle_group="
				+ middle_group + ", small_group=" + small_group + ", detail_group=" + detail_group + ", comments="
				+ comments + ", transaction_money=" + transaction_money + ", transaction_date=" + transaction_date
				+ ", writer=" + writer + ", reg_date=" + reg_date + "]";
	}

	
	
	

	
}
