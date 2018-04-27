package com.homevip.util.system;


import java.util.HashMap;
import java.util.Map;

public class MsgTemplate {

	public static String TYPE_MESSAGE_WX = "wx";
	public static String TYPE_MESSAGE_QY = "qy";
	public static String TYPE_MESSAGE_SMS = "sms";
	public static String TYPE_MESSAGE_APP = "app";

	public static Map<Integer,String> templates = new HashMap<Integer,String>(); //短信
	public static Map<Integer,String> wxTemplateIds = new HashMap<Integer,String>(); //微信
	public static Map<Integer,String> wxTestTemplateIds = new HashMap<Integer,String>(); //测试微信

	/*
	 * 姓名后面要带上（先生/女士）
	 * */
	static{
		templates.put(1, "尊敬的%s，非常感谢您对51家庭管家的关注，更多高端家政、中医保健和养老护理服务请关注微信服务号“51家庭管家”——开启您的无忧生活。");
		
		templates.put(2, "尊敬的客户，您注册的账号验证码是：%s，如非本人操作请致电：4006-518051——开启您的无忧生活。");
		
		templates.put(3, "尊敬的客户，欢迎您成为51家庭管家的注册用户，我们将为您提供高品质的高端家政、中医保健和养老护理服务，服务热线：4006-518051——开启您的无忧生活。");
		
		templates.put(4, "亲爱的%s，欢迎您成为51家庭管家的尊贵会员，我们将竭诚为您提供高品质的高端家政、中医保健和养老护理服务，服务热线：4006-518051——开启您的无忧生活。");
		
		//第二个参数是时间:XXXX年XX月XX日XX时XX分
		templates.put(5, "尊敬的客户，%s，充值金额%s元已经成功充值到您的帐户上，感谢您的支持！");
		
		templates.put(6, "尊贵的VIP客户%s，您的专属管家是%s，为您零距离贴心服务，开启您的无忧生活！如您有任何需求请拨打管家热线4006-518051-XXX。");
		
		//第二个参数是时间:XX月XX日
		templates.put(7, "%s号管家服务提醒：%s月%s日开通会员账号：%s，姓名：%s，请登录系统进行服务。");
		
		//第一个参数是时间:XX月XX日 XX:XX
		templates.put(8, "（签约信息）服务时间%s，客户姓名%s，客户电话%s，客户地址：%s，请提前做好准备工作。");
		
		//第二个参数是时间:XX月XX日 XX:XX
		templates.put(9, "尊敬的%s，您已成功预约服务，服务时间：%s，如需修改，请提前24小时致电客服热线4006-518051——开启您的无忧生活。");
		
		//第二个参数是时间:XX月XX日 XX:XX
		templates.put(10, "首单号%s，%s，%s，%s，%s，请提前做好服务和签约准备工作。");
		
		//第三个参数是时间:XX月XX日 XX:XX
//		templates.put(11, "尊敬的客户，您已成功预约%s服务项目，订单号%s，服务时间%s，服务时长%s小时，如需修改请提前24小时致电客服热线4006-518051。");
		
		//第二个参数是时间:XX月XX日 XX:XX
		templates.put(12, "订单号%s，%s，%s，%s，%s，请提前做好准备工作。");
		
		//第三个参数是时间:XX月XX日 XX:XX
		templates.put(13, "尊敬的客户，您已成功预约%s服务项目，订单号%s，服务时间%s，服务时长%s小时，如需修改请提前24小时致电客服热线4006-518051。");
		
		//第三个参数是时间:XX月XX日 XX:XX
		templates.put(14, "尊敬的客户，您预约%s的%s，服务人员%s，请您保持手机畅通及家中留人，感谢您的支持！");
		
		//第二个参数是时间:XX月XX日 XX:XX
//		templates.put(15, "（服务信息）您有一张服务单号%s，服务时间%s，服务时长%s小时，服务项目%s，客户姓名%s，客户电话%s，客户地址：%s，请按时到达。");
		
		//第三个参数是时间:XX月XX日 XX:XX
		templates.put(16, "尊敬的客户，您的订单%s(%s)，%s，如有疑问请致电4006-518051。");
		
		//第三个参数是时间:XX月XX日 XX:XX
		templates.put(17, "尊敬的客户，您的订单%s已完成，实际服务%s小时，金额%s元，如有疑问请致电4006518051。");
		
		templates.put(18, "尊敬的客户，请您对本次订单号%s进行评价，满意请回复数字1，一般请回复数字2，不满意请回复数字3，您的真切评价对我们服务提升以及保持一贯良好质量非常重要，感谢您的支持！——开启您的无忧生活。");

		//第三个参数是时间:2015.03.15
		templates.put(19, "尊敬的客户，您有%s张优惠券将于5天内过期，请您尽快使用，详情请登录微官网，如有疑问请致电客服热线4006-518051。");
		
		//第五个参数是时间:2015.03.15
		templates.put(20, "尊敬的客户，您已成功订购%s套餐（%s），套餐金额%s元，如有疑问请致电客服热线4006-518051。");
		
		templates.put(21, "取消订单:%s，%s,%s,%s:%s,%s。");

		templates.put(22, "尊敬的%s,恭喜您获得了%s。如有疑问请致电客服热线4006-518051");

		templates.put(23, "尊敬的%s组长您好，%s服务订单剩余%s张尚未派工，请及时派工，谢谢。");

		templates.put(24, "尊敬的%s站长您好，%s订单剩余%s张尚未派工，请及时处理，谢谢。");
		templates.put(25, "（改单)订单%s，%s，%s，%s，%s。");
		
		templates.put(26, "尊敬的%s，%s，充值金额%s元已经退回到您的账户上，感谢您的支持！");
		templates.put(27, "尊敬的%s，%s，您的订单实际消费金额为%s元，已从您的账户上扣除%s元，感谢您的支持！");
		templates.put(28, "尊敬的%s，%s，您的订单实际消费金额为%s元，已返回%s元到您的账户上，感谢您的支持！");
		templates.put(30, "尊敬的%s，%s，礼品卡金额%s元已经退回到您的账户上，感谢您的支持！");
		templates.put(31, "尊敬的%s，%s，套餐金额%s元已经退回到您的账户上，感谢您的支持！");
		templates.put(32, "您已获得总额为[%s元]的优惠券礼包！");
		templates.put(33, "%s，%s%s，%s，服务人数、时间或地址等已发生变更，请及时登入系统查看订单详情！");
		templates.put(34, "尊敬的%s，因您未按规定的时间内取消服务，给服务师造成了无法工作的损失，本次仅作温馨提醒，以后如有类似情况将会直接进行扣费，请提前做好计划安排，感谢您的配合！取消服务要求规则：在服务开始前3小时内取消的扣除一半费用，在服务开始前2小时内取消的会全额扣除。建议提前24小时联系修改处理，如有疑问请致电客服热线4006-518051——开启您的无忧生活。");
		templates.put(35, "尊敬的%s，很遗憾地告诉您，因您已经是%s次未按规定的时间内取消服务，给服务师造成了无法工作的损失，将会直接对您本次的服务进行扣费！取消服务要求规则：在服务开始前3小时内取消的扣除一半费用，在服务开始前2小时内取消的会全额扣除。建议提前24小时联系修改处理，如有疑问请致电客服热线4006-518051——开启您的无忧生活。");

		templates.put(36, "您有一张订单号%s，%s，%s，时长%s小时，%s，%s，%s，请按时到达。");
		
		templates.put(37, "您有一张订单号%s已被拒绝，请及时处理。");
		//第五个参数是时间:2015.03.15
		templates.put(38, "尊敬的客户，您已成功订购%s服务(%s)，服务金额%s元，如有疑问请致电客服热线4006-518051。");
		//第三个参数是时间:XX月XX日 XX:XX
		templates.put(39, "尊敬的客户，您已成功预约%s服务项目，订单号%s，如需修改请提前24小时致电客服热线4006-518051。");
		templates.put(40, "尊敬的客户，您在51家庭管家的新年活动抽奖次数还有%s次。");
		templates.put(41, "尊敬的%s，%s，商品金额%s元已经退回到您的账户上，感谢您的支持！");
		templates.put(42, "尊敬的%s，抱歉，由于%s无法生成订单，充值金额已退还您的账户。如有疑问请致电4006-518051。");
		templates.put(43, "尊敬的%s，您已成功购买%s服务项目，服务时长%s小时，订单号%s，服务日期%s，如有疑问请致电4006-518051。");
        templates.put(44,"尊敬的%s，%s，服务订单金额%s元已经退回到您的账户上，感谢您的支持！");
		templates.put(45,"您有一张订单号%s，%s，%s，时长%s小时，%s，%s，%s，请按时到达。");
		templates.put(46,"%s，%s:%s, %s，时间已改为%s，地址已改为%s，服务人数已改为%s");
		templates.put(47,"取消订单%s，%s，%s,%s:%s，%s");
		templates.put(48,"尊敬的%s，请您查看服务日志，您当天的服务已完成。服务人员：:%s，服务项目:%s，如有疑问请致电：4006518051。");
		templates.put(49,"尊敬的%s，51家庭管家邀请您为本周服务点评，评价完成即送100积分。服务人员:%s，服务项目:%s，如有疑问请致电：4006518051。");
		templates.put(50,"尊敬的客户，%s积分已到账，如有疑问请致电：4006518051。");
		templates.put(51,"尊敬的客户，您刚扣除%s积分,如有疑问请致电：4006518051。");
        templates.put(52,"尊敬的客户，您的账户余额%s元已经退回，感谢您的支持！");
        templates.put(53,"跟单服务通知：订单号%s，%s，%s，时长%s小时，%s， %s，%s，请按时到达。");
        templates.put(54,"取消跟单通知：订单号%s，%s，%s，时长%s小时，%s， %s，%s");
		templates.put(55,"尊敬的客户，您的订单%s已经服务完成，请您对本次服务进行评价，您的真切评价对我们服务提升非常重要，感谢您的支持！——开启您的无忧生活。");
		templates.put(56,"您有一张包月订单%s，%s，服务开始时间：%s %s，若有疑问，请及时联系主管：%s");
		templates.put(57,"您的包月订单%s，%s，已经暂停服务，最后服务时间：%s，若有疑问，请及时联系主管：%s");
		templates.put(58,"预约单%s客服已经回访完成；用户：%s；客户电话：%s；请及时分配管家。");
		templates.put(59," 尊敬的客户，您好，您的专属管家已为您的 %s项目 配置方案，请及时查看。");
		templates.put(60," 主管您好，截止至%s止尚剩余%s张%s预约订单已超过24小时未回访，请及时处理。");
		templates.put(61," 主管您好，截止至%s止尚剩余%s张无忧、%s张母婴、%s张养老预约订单已超过48小时未上传方案，请及时处理。");
		templates.put(101, "包月正式订单号%s，%s，%s，%s号即将服务到期，请提前联系客户做好续费工作，谢谢！");
		templates.put(102, "尊敬的客户，您的动态码是：%s，如非本人操作请致电：4006-518051——开启您的无忧生活。");
		templates.put(103," 尊敬的客户，您的专属 %s已更新，请您及时查看，如有疑问请联系您的专属管家！");
		templates.put(104," 尊敬的客户，您有一份 %s已更新，点击“详情”可查看，如有疑问请联系您的专属管家！");
		templates.put(105," 您的客户%s，专属早教方案已更新，请您及时查看，如有疑问请联系早教老师");
		templates.put(106," %s<职位：%s  工号：%s>  您%s共收获%s元！一定是你打开的方式不对！明明你很优秀！要多提醒自己：你可以！多挖掘多提升，下月工资压不住蹭蹭往上升!");
		templates.put(107," %s<职位：%s  工号：%s>  您%s共收获%s元！别以为大家不知道，你还没有展示自己的真功夫哦！你的技能不赖，隐藏着强大业绩可能，发挥出来一定超出色!");
		templates.put(108," %s<职位：%s  工号：%s>  您%s共收获%s元！值得好好吃顿饭，犒劳一下努力拼搏的自己！业绩惊人的你，是大家学习的好榜样！顺应好势头，期待下个月创新高!");
		templates.put(109," 现在快来核实自己的工资吧！ 打开员工端→个人→工资→查询工资表，核对工资明细，如有疑惑请于%s号前（3日内）联系你的上级领导申诉核对，千万不要错过最后期限哦！希望你领取准确无误的工资，开心收到劳动成果。");

	}
	
	static{
		wxTemplateIds.put(5, "WUgNTB5CeatJYgViVd5GgDUHgMjsUP5bvgI6jdXLJuk");
		wxTemplateIds.put(10, "1MxsR5m19G9PAvdpYnfxJ2rlGv2u5CYbkY320_Gt7k0");
		wxTemplateIds.put(11, "1MxsR5m19G9PAvdpYnfxJ2rlGv2u5CYbkY320_Gt7k0");
		wxTemplateIds.put(14, "Jd7ZuK1t0Bb-Fv1ZHybJLpDVnpQBEcs84XJJOXXEwo4");
		wxTemplateIds.put(17, "LCNEF49MC0ckeyHecIPqS-OkD-TFdp0LDl0SWbJ2ySI");
		wxTemplateIds.put(19, "nbyg3PuHnZzfAFK9XJVKPGSWU-GheT86BfKfM39rTgo");
		wxTemplateIds.put(20, "7XqH1DiwS28NLft4Lti2dkM_rKwS74aiuHGbSqVkHdE");
		wxTemplateIds.put(22, "nBkGuYXy5th6O5ex9Q1FGX3q35jV3JETcKAWn_WuQ7g");
		wxTemplateIds.put(38, "IDadBIObR_ztkSipN12HnW0DRWds2PybfURLpgpeg0ReY");
		wxTemplateIds.put(32, "9lhwbkr4BscCebldT3gQpD3h3RbU9kbHpTY9mopR0MU");
		wxTemplateIds.put(42, "LSXQbKOPBIUSw5yGw2GS9o3VGAZoujAD9On-_0Vc5FM");
		wxTemplateIds.put(48, "b1KHrS-j5Z3ByhQIkYjC30oxtuI9rvwArdLe72KW7WI");
		wxTemplateIds.put(49, "8Pzi0TlPuLYV_dU1b5juatM7wo9EMrY6YXGfF6nKMLc");
		wxTemplateIds.put(59, "9ftb1MAuWSIbm8BFn0xgVMDctAB6eKqhf4WSkl6jnug");
		wxTemplateIds.put(103, "9ftb1MAuWSIbm8BFn0xgVMDctAB6eKqhf4WSkl6jnug");
		wxTemplateIds.put(104, "9ftb1MAuWSIbm8BFn0xgVMDctAB6eKqhf4WSkl6jnug");
		wxTemplateIds.put(105, "9ftb1MAuWSIbm8BFn0xgVMDctAB6eKqhf4WSkl6jnug");

		//测试
		wxTestTemplateIds.put(19, "9UOGpAdDqzWXeeLR54hhL3LTW4PhDtBavGcHlAXDVHk");
	}
	
	public static enum EnumMsgTemplate {
		_01("没注册意向客户导引信息", CommArray.EnumPushType._sys.getValue()),
		_02("用户版绑定时发送的验证码短信", CommArray.EnumPushType._sys.getValue()),
		_03("会员注册成功消息（微信和短信）", CommArray.EnumPushType._sys.getValue()),
		_04("会员开通消息（微信和短信）", CommArray.EnumPushType._sys.getValue()),
		_05("充值成功通知", CommArray.EnumPushType._asset.getValue()),
		_06("管家信息(vip)", CommArray.EnumPushType._sys.getValue()),
		_07("Vip会员信息（管家）", CommArray.EnumPushType._sys.getValue()),
		_08("首次预约上门签约信息", CommArray.EnumPushType._order.getValue()),
		_09("首次预约上门签约信息", CommArray.EnumPushType._order.getValue()),
		_10("首单确认消息（含体验）", CommArray.EnumPushType._order.getValue()),
		@Deprecated
		_11("首单确认消息（含体验）(客户)", CommArray.EnumPushType._order.getValue()),
		_12("订单确认消息", CommArray.EnumPushType._order.getValue()),
		_13("服务确认通知", CommArray.EnumPushType._order.getValue()),
		_14("服务即将开始通知", CommArray.EnumPushType._order.getValue()),
		@Deprecated
		_15("N小时前，准备进行服务通知家政员及组长", CommArray.EnumPushType._order.getValue()),
		_16("服务变更通知", CommArray.EnumPushType._order.getValue()),
		_17("服务完成提醒", CommArray.EnumPushType._order.getValue()),
		_18("服务评价邀请", CommArray.EnumPushType._order.getValue()),
		_19("优惠券到期提醒", CommArray.EnumPushType._coupon.getValue()),
		_20("套餐购买成功通知", CommArray.EnumPushType._order.getValue()),
		_21("订单取消消息", CommArray.EnumPushType._order.getValue()),
		_22("领取优惠券通知", CommArray.EnumPushType._coupon.getValue()),
		_23("未派工通知组长", CommArray.EnumPushType._sys.getValue()),
		_24("未派工通知站长", CommArray.EnumPushType._sys.getValue()),
		_25("订单改单通知（管家）", CommArray.EnumPushType._sys.getValue()),
		_26("充值红冲通知", CommArray.EnumPushType._asset.getValue()),
		_27("消费红冲扣款通知", CommArray.EnumPushType._asset.getValue()),
		_28("消费红冲退款通知", CommArray.EnumPushType._asset.getValue()),
		_30("退礼品卡通知", CommArray.EnumPushType._order.getValue()),
		_31("退套餐通知", CommArray.EnumPushType._order.getValue()),
		_32("优惠券礼包派发通知", CommArray.EnumPushType._coupon.getValue()),
		_33("订单改单通知", CommArray.EnumPushType._order.getValue()),
		_34("客户首次违约通知", CommArray.EnumPushType._order.getValue()),
		_35("客户违约扣费通知", CommArray.EnumPushType._order.getValue()),
		_36("派工通知服务师", CommArray.EnumPushType._sys.getValue()),
		_37("转单被退回", CommArray.EnumPushType._sys.getValue()),
		_38("确认服务订单消息", CommArray.EnumPushType._sys.getValue()),
		_40("抽奖剩余次数消息", CommArray.EnumPushType._sys.getValue()),
		_41("退商品通知", CommArray.EnumPushType._order.getValue()),
		_42("下单失败通知", CommArray.EnumPushType._order.getValue()),
		_43("服务通知", CommArray.EnumPushType._order.getValue()),
        _44("退订单通知", CommArray.EnumPushType._order.getValue()),
		_45("组长确认派工", CommArray.EnumPushType._sys.getValue()),
		_46("订单变更", CommArray.EnumPushType._order.getValue()),
		_47("订单取消", CommArray.EnumPushType._order.getValue()),
		_48("包月工作日志推送", CommArray.EnumPushType._sys.getValue()),
		_49("包月评价邀请", CommArray.EnumPushType._order.getValue()),
		_50("积分到账通知", CommArray.EnumPushType._point.getValue()),
		_51("积分使用通知", CommArray.EnumPushType._point.getValue()),
        _52("余额退回通知", CommArray.EnumPushType._asset.getValue()),
        _53("跟单服务通知", CommArray.EnumPushType._order.getValue()),
        _54("取消跟单通知", CommArray.EnumPushType._order.getValue()),
		_55("邀请评价通知", CommArray.EnumPushType._order.getValue()),
		_56("包月派工企业通知", CommArray.EnumPushType._order.getValue()),
		_57("包月暂停企业通知", CommArray.EnumPushType._order.getValue()),
		_58("包月分配管家企业通知", CommArray.EnumPushType._order.getValue()),
		_59("包月订单方案上传通知", CommArray.EnumPushType._order.getValue()),
		_60("包月预约订单管家面试回访提醒", CommArray.EnumPushType._order.getValue()),
		_61("包月预约订单管家上传方案提醒", CommArray.EnumPushType._order.getValue()),
		_101("企业端通知无忧到期", CommArray.EnumPushType._sys.getValue()),
		_102("登录动态码", CommArray.EnumPushType._sys.getValue()),
		_103("早教方案更新通知！", CommArray.EnumPushType._order.getValue()),
		_104("专家回访记录更新通知！", CommArray.EnumPushType._order.getValue()),
		_105("早教方案更新通知！", CommArray.EnumPushType._order.getValue()),//服务师
		_106("发放工资通知！", CommArray.EnumPushType._salary.getValue()),
		_107("发放工资通知！", CommArray.EnumPushType._salary.getValue()),
		_108("发放工资通知！", CommArray.EnumPushType._salary.getValue()),
		_109("公示工资通知！", CommArray.EnumPushType._salary.getValue());

		public final String title;
		public final String value;
		public final String temlpateId;
		public final String catalogCode;

		EnumMsgTemplate(String value, String catalogCode) {
			this.title = value;
			this.value = templates.get(getCode());
			this.temlpateId = wxTemplateIds.get(getCode());
			this.catalogCode = catalogCode;
		}

		public String toString() {
			return super.toString().replace("_", "");
		}
		
		public int getCode() {
			return Integer.parseInt(this.toString());
		}

		public String getTitle() {
			return this.title;
		}
		
		public String getValue() {
			return this.value;
		}

		public String get4App() {
			return this.value;
		}

		public String getTemplateId() {
			return this.temlpateId;
		}

		public String getCcatalogCode() {
			return this.catalogCode;
		}
	}

}
