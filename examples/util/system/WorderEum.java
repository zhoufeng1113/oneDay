package com.homevip.util.system;


/**
 * 公共工单枚举
 * @author hehaoqun
 * @date 2015年6月5日
 */
public class WorderEum {
	/** 
	 * 私有化构造方法 
	 */
	private WorderEum() {} 
	/** 
	 * 静态初始化器，线程安全 
	 */ 
	public static final WorderEum instance = new WorderEum();

	public static WorderEum getInstance(){
		return instance;  
	}
	public EnumAcceptAdvice[] getEnumAcceptAdvice(){
		return EnumAcceptAdvice.values();
	}
	public EnumAcceptFeedback[] getEnumAcceptFeedback(){
		return EnumAcceptFeedback.values();
	}
	public EnumAcceptPlan[] getEnumAcceptPlan(){
		return EnumAcceptPlan.values();
	}
	public EnumAcceptPort[] getEnumAcceptPort(){
		return EnumAcceptPort.values();
	}
	public EnumDegreeCode[] getEnumDegreeCode(){
		return EnumDegreeCode.values();
	}
	public EnumExamineStatus[] getEnumExamineStatus(){
		return EnumExamineStatus.values();
	}
	public EnumFeedBackAdvice[] getEnumFeedBackAdvice(){
		return EnumFeedBackAdvice.values();
	}
	
	public EnumInfoFromInType[] getEnumInfoFromInType(){
		return EnumInfoFromInType.values();
	}
	public EnumMemberSex[] getEnumMemberSex(){
		return EnumMemberSex.values();
	}
	public EnumReson[] getEnumReson(){
		return EnumReson.values();
	}
	public EnumResponsibility[] getEnumResponsibility(){
		return EnumResponsibility.values();
	}
	public EnumServiceContent[] getEnumServiceContent(){
		return EnumServiceContent.values();
	}
	public EnumServiceTime[] getEnumServiceTime(){
		return EnumServiceTime.values();
	}
	public EnumTache[] getEnumTache(){
		return EnumTache.values();
	}
	public EnumTerminal[] getEnumTerminal(){
		return EnumTerminal.values();
	}
	public EnumWorderStatus[] getEnumWorderStatus(){
		return EnumWorderStatus.values();
	}
	public EnumWorderType[] getEnumWorderType(){
		return EnumWorderType.values();
	}
	public EnumCallCount[] getEnumCallCount(){
		return EnumCallCount.values();
	}
	public EnumMemberType[] getEnumMemberType(){
		return EnumMemberType.values();
	}
	public EnumAcceptStatus[] getEnumAcceptStatus(){
		return EnumAcceptStatus.values();
	}
	public EnumServiceInfoType[] getEnumServiceInfoType(){
		return EnumServiceInfoType.values();
	}
	/**
	 * 工单信息来源
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumInfoFromInType{
		_1("热线"),
		_2("官网"),
		_3("微信"),
		_4("APP"),
		_5("回访"),
		_6("其他");

		public final String value;

		EnumInfoFromInType(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 工单状态
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumWorderStatus{
		_1("待处理"),
		_2("处理中"),
		_3("有方案待跟进"),
		_4("联系不上"),
		_5("处理完成"),
		_6("待审核");

		public final String value;
		EnumWorderStatus(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	public static enum EnumAcceptStatus{
		_1("待处理"),
		_2("处理中"),
		_3("有方案待跟进"),
		_4("联系不上"),
		_5("处理完成"),
		_6("待审核");

		public final String value;
		EnumAcceptStatus(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	
	/**
	 * 工单类型
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumWorderType{
		_1("投诉工单"),
		_2("咨询工单"),
		_3("服务不良工单"),
		_4("反馈建议");

		public final String value;

		EnumWorderType(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 工单的重要程度
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumDegreeCode {
		_1("一般"),
		_2("重要"),
		_3("非常重要");

		public final String value;

		EnumDegreeCode(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	
	public static enum EnumMemberSex {
		_0("未填写"),
		_1("保密"),
		_2("男"),
		_3("女");

		public final String value;

		EnumMemberSex(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 客服受理信息的需求服务内容
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumServiceContent {
		_1("订单更改"),
		_2("退款"),
		_3("业务咨询"),
		_4("其他");

		public final String value;

		EnumServiceContent(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 客服受理信息流转终端
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumTerminal {
		_1("客服部"),
		_2("组长"),
		_3("销售部"),
		_4("产品部");

		public final String value;

		EnumTerminal(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 客服受理服务时间
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumServiceTime {
		_1("10分钟"),
		_2("30分钟"),
		_3("1个小时"),
		_4("1个小时以上");

		public final String value;

		EnumServiceTime(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 处理达成的协议
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumAcceptPort {
		_1("是"),
		_2("否");

		public final String value;

		EnumAcceptPort(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 处理方案
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumAcceptPlan {
		_1("赔偿"),
		_2("道歉认可"),
		_3("免单"),
		_4("礼品"),
		_5("积分"),
		_6("优惠券");

		public final String value;

		EnumAcceptPlan(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 处理原因
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumReson {
		_1("服务态度差"),
		_2("服务技能差"),
		_3("服务不及时"),
		_4("损坏财物"),
		_5("服务态度生硬"),
		_6("服务技能不够熟练"),
		_7("服务不够细致"),
		_8("服务形象差"),
		_9("其他");

		public final String value;

		EnumReson(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 在责任体系
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumResponsibility {
		_1("高端家政"),
		_2("呼叫中心"),
		_3("管家部"),
		_4("养老护理"),
		_5("销售部"),
		_6("线上营销"),
		_7("其他");
	

		public final String value;

		EnumResponsibility(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 内部处理建议
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumAcceptAdvice {
		_1("处罚"),
		_2("通报批评");
		public final String value;

		EnumAcceptAdvice(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 审批状态
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumExamineStatus {
		_1("审批通过"),
		_2("审批不通过");

		public final String value;

		EnumExamineStatus(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 反馈建议
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumFeedBackAdvice {
		_1("产品改善方面"),
		_2("网站反面"),
		_3("下单流程"),
		_4("服务流程"),
		_5("服务时间"),
		_6("其他");
		public final String value;

		EnumFeedBackAdvice(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 环节
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumTache {
		_1("售前"),
		_2("售中"),
		_3("售后");
		public final String value;

		EnumTache(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 内部处理反馈
	 * @author hehaoqun
	 * @date 2015年6月5日
	 */
	public static enum EnumAcceptFeedback {
		_1("无需追究"),
		_2("未追究"),
		_3("同意处罚"),
		_4("不同意处罚");

		public final String value;

		EnumAcceptFeedback(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 来电次数
	 * @author hehaoqun
	 * @date 2015年7月16日
	 */
	public static enum EnumCallCount {
		_1("1次"),
		_2("2次"),
		_3("3次"),
		_4("3次以上");
		
		public final String value;

		EnumCallCount(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	public static enum EnumMemberType {
		_1("否"),
		_2("是");
		
		public final String value;

		EnumMemberType(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	/**
	 * 各种服务信息维护内容
	 * @author hehaoqun
	 * @date 2015年7月16日
	 */
	public static enum EnumServiceInfoType {
		_1("服务内容");
		
		public final String value;

		EnumServiceInfoType(String value) {
			this.value = value;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getValue() {
			return this.value;
		}		
	}
	
	
	public static void main(String[] args) {
			System.out.println(WorderEum.EnumWorderType.valueOf("_1").value);
	}
}
