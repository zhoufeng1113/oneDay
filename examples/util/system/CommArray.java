package com.homevip.util.system;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共数组 - 用单例模式
 *
 * @author PyePye 2014.12.18
 */
public class CommArray {
    /**
     * 私有化构造方法
     */
    private CommArray() {
    }

    /**
     * 静态初始化器，线程安全
     */
    public static final CommArray instance = new CommArray();

    public static CommArray getInstance() {
        return instance;
    }

    public String[] arr_YesNo = {"", "否", "是"};
    public String[] arr_UserType = {"", "客户", "员工", "其他"};
    public String[] arr_FromType = {"", "注册终端", "下单终端", "渠道来源", "企业渠道", "成为会员渠道"};
    public String[] arr_Goods_Status = {"下架", "上架"};
    public String[] arr_Isforbid = {"", "禁止", "正常"};
    public String[] arr_IsAudit = {"未审核", "未审核", "已审核"};
    public String[] arr_Gender = {"-", "保密", "男", "女"};
    public String[] arr_Maritalstatus = {"", "未婚", "已婚", "离异", "丧偶"};
    public String[] arr_Recommendedtype = {"", "客服", "销售"};
    public String[] arr_HelpInfoCtype = {"", "帮助", "公共页面"};
    public String[] arr_ServiceCata = {"", "咨询", "建议", "投诉"};
    public String[] arr_Marketting = {"", "大转盘", "摇钱树", "投票", "摇一摇"};
    public String[] arr_BDUserType = {"", "经理", "员工", "大妈"};
    public String[] arr_ComplaintStatus = {"", "未处理", "处理中", "处理完成"};
    public String[] arr_Lang = {"", "国语", "粤语", "英语", "潮州话"};
    public String[] arr_BillTransferStatus = {"", "未确认", "已确认"}; // 转账单状态
    public String[] arr_InviteSource = {"", "微信", "网页"};// 邀请来源
    public String[] arr_Educational = {"", "小学及以下", "初中", "中专", "高中", "大专", "本科及以上"}; // 文化程度
    public String[] arr_Payment = {"", "支付宝", "财付通", "微信", "银联", "京东支付"};// 支付方式
    public String[] arr_RechargeType = {"", "在线支付", "刷卡", "银行转帐"};// 充值方式
    public String[] arr_Age = {"", "20-30", "30-40", "50-55"}; // 年龄段
    public String[] arr_Relationship = {"本人", "亲人", "同学", "好友", "其他"}; // 关系
    public String[] arr_InvoiceStatus = {"", "未处理", "已打印", "已派送"}; // 发票状态
    public String[] arr_BillConfigTypes = {"", "单值定额", "单值比例", "多值定额", "多值比例", "复合多值定额"}; // 结算配置类型
    public String[] arr_Unit = {"", "小时", "平方", "台起", "人起", "半小时", "次", "月", "人", "人次", "套房", "份", "元起","个", "台", "件"}; // 服务价钱单位
    public String[] arr_AddressType = {"", "个人", "公司"};
    public String[] arr_ReportPayType = {"", "上门收款", "线下转帐", "线上支付"};
    @Deprecated
    public String[] arr_OrderPayType = {"", "余额支付", "上门刷卡", "网上支付", "家庭账户","微信","支付宝","银联"};
    public String[] arr_ServiceItemType = {"", "单项", "团体", "套餐"};
    public String[] arr_IsExport = {"", "未汇出", "已汇出"};
    public String[] arr_Demands = {"", "保洁", "保健", "养老"};
    public String[] arr_HotLevel = {"", "爆款", "新品", "促销", "推荐", "应季", "精品"};

    public static enum EnumReportMemberConsumerProductType{
        _1("钟点(家政产品不包含家电和抛光)"),_2("年卡(包含家政和保健)"),
        _3("无忧"),_4("月子"),_5("育婴"),_6("养老"),_7("保健"),_8("工程(甲醛、家电、抛光、小帮手)"),_9("其他");
        public final String value;
        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }
        public int getCode() {
            return Integer.parseInt(this.toString());
        }
        EnumReportMemberConsumerProductType(String value) {
            this.value = value;
        }
    }

    //客户沟通难易度
    public static enum EnumMemberGtnyd{
        _1("容易"),_2("困难");
        public final String value;
        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }
        public int getCode() {
            return Integer.parseInt(this.toString());
        }
        EnumMemberGtnyd(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }


    public static enum EnumRetrackRecordType{
        _1("旧回访类型(包含客户经理回访、包月回访、工单回访)"),_2("预约订单回访");
        public final String value;
        @Override
        public String toString() {
            return super.toString().replace("_", "");
        }
        public int getCode() {
            return Integer.parseInt(this.toString());
        }
        EnumRetrackRecordType(String value) {
            this.value = value;
        }
    }

    public static enum EnumWorkTimeType{
        _1("全天"), _2("上午"), _3("下午 + 晚上")
        , _4("下午"), _5("晚上"), _6("上午+下午"), _7("加班时段 19点-21点 （保健用）");
        public final String value;
        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }
        EnumWorkTimeType(String value) {
            this.value = value;
        }
    }

    //新版订单列表二级分类
    public static enum EnumOrderListSecondCata {
        _1("标准保洁"), _2("包年套餐"), _3("专项清洁"), _28("除螨包年")
        , _4("住家24小时"), _5("全天10小时"),_26("半天6小时"), _6("半天4小时")
        , _7("51月子"), _8("51育婴"), _9("养老护理")
        , _10("专业陪诊"), _11("甲醛治理"), _12("大理石保养")
        , _13("家电保洁"), _27("抛光打蜡"), _14("家庭小帮手"), _15("中医推拿")
        , _16("小儿推拿"), _17("健康家套餐"), _18("健儿家套餐")
        , _19("管家体验"), _20("养老体验"), _21("管家预约")
        , _22("月子预约"), _23("育婴预约"), _24("养老预约")
        , _25("其他商品");
        public final String value;

        EnumOrderListSecondCata(String value) {
            this.value = value;
        }

        @Override
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



    //预约订单环节
    public static enum EnumAppointmentProcess {
        _1("客服回访"), _2("主管分配 "), _3("管家传方案"), _4("客服确认且下单"), _5("预约结束");
        public final String value;

        EnumAppointmentProcess(String value) {
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


    //潜在客户需求
    public static enum EnumDemand {
        _1("保洁"), _2("保健"), _3("养老"), _4("月子师"), _5("育儿师"), _6("无忧");
        public final String value;

        EnumDemand(String value) {
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
    //客户绑定平台类型
    public static enum EnumPlatFormType {
        _1("公众号","gzh"), _2("微信","weixin"),_20("设备","device");
        public final String value;
        public final String type;

        EnumPlatFormType(String value,String type) {
            this.value = value;
            this.type = type;
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
        public String getType() {
            return this.type;
        }
    }


    //报表运行类型
    public static enum EnumReportRunType {
        _1("手动"), _2("自动");
        public final String value;

        EnumReportRunType(String value) {
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
    //报表运行状态
    public static enum EnumReportRunStatus {
        _1("停止"), _2("运行");
        public final String value;

        EnumReportRunStatus(String value) {
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


    //报表运行频率
    public static enum EnumReportRunRate {
        _1("天"), _2("周"), _3("月");
        public final String value;

        EnumReportRunRate(String value) {
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

    //订单生成失败原因
    public static enum EnumFailReason {
        _1("库存不足"), _2("券已被使用"),_3("积分不足"),_4("余额不足");
        public final String value;

        EnumFailReason(String value) {
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

    //套餐订单生成失败原因
    public static enum EnumPackageReason {
        _1("下单失败");
        public final String value;

        EnumPackageReason(String value) {
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

    //订单类型
    public static enum EnumZorderRefType {
        _1("包月主单"), _2("包月体验单"), _3("赠送单"), _4("套餐订单"), _5("加时订单"), _6("普通订单"), _7("活动订单");
        public final String value;

        EnumZorderRefType(String value) {
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


    //无忧管家预约订单状态
    public static enum EnumAppointmentStatus {
        //1、待回访 2、评估中  3、体验中 4、待支付 5、服务中
        _1("待回访"), _2("评估中"), _3("体验中"), _4("方案已确认"), _9("已完成"), _20("已取消");
        public final String value;

        EnumAppointmentStatus(String value) {
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

    //无忧管家类型
    public static enum EnumAppointmentHometype {
        _1("不住家"), _2("住家");
        public final String value;

        EnumAppointmentHometype(String value) {
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

    //无忧管家类型
    public static enum EnumAppointmentTimetype {
        _4("4小时"), _10("10小时"), _24("24小时");
        public final String value;

        EnumAppointmentTimetype(String value) {
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


    public static enum EnumYN {
        _Y(2, "是"), _N(1, "否");
        public final int code;
        public final String value;

        EnumYN(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return this.code;
        }

        public String getValue() {
            return this.value;
        }

    }

    public static enum EnumOrderPayType {
        _0("", "", false), _1("balancepay", "余额支付", false), _2("cashpay", "上门刷卡", false), _3("onlinepay", "网上支付", true), _4("familypay", "家庭账户", false),_5("wechatpay", "微信", true),_6("alipay", "支付宝", true),_7("unionpay", "银联", false);
        public final String value;
        public final String name;
        public final boolean isNetPay;

        EnumOrderPayType(String name, String value, boolean isNetPay) {
            this.value = value;
            this.name = name;
            this.isNetPay = isNetPay;
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

        public String getName() {
            return name;
        }

        public boolean isNetPay() { return isNetPay; }
    }

    public static enum EnumGoodsStatus {
        _1("待处理"), _2("配送中"), _3("订单完成"), _20("废单"), _22("临时订单"), _30("逻辑删除");
        public final String value;

        EnumGoodsStatus(String value) {
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

    public static enum EnumGoodsItemType {
        _1("自营"), _2("非自营");
        public final String value;

        EnumGoodsItemType(String value) {
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

    public static enum EnumGoodsType {
        _1("商品","good"), _2("优惠券",""), _3("活动",""), _4("服务",""), _5("包月",""), _6("甲醛","kongqi"), _7("月嫂","yuesao"), _8("养老","yanglao"), _9("课程","course"),_10("维修","fix");
        public final String value;
        public final String typecode;  //对应商品code

        EnumGoodsType(String value,String typecode) {
            this.value = value;
            this.typecode = typecode;
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

        public String getTypecode() {
            return this.typecode;
        }
    }

    public static enum EnumVisitFromType {
        _1("微信"), _2("短信"), _3("客服回访"), _4("App");
        public final String value;

        EnumVisitFromType(String value) {
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

    public static enum EnumCouponSubType {
        _1("直接减"), _2("满额度减"), _3("额定支付");
        public final String value;

        EnumCouponSubType(String value) {
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

    public static enum EnumZorderMonthStatus {
        _1("待处理"), _2("评估中"), _3("体验中"), _4("已下单"), _5("已结束"), _6("已取消");
        public final String value;

        EnumZorderMonthStatus(String value) {
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

    public static enum EnumZorderRegisterStatus {
        _1("待处理"), _2("已处理");
        public final String value;

        EnumZorderRegisterStatus(String value) {
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
     * 消费记录的类型
     *
     * @author hehaoqun
     * @date 2015年10月14日
     */
    public static enum EnumConsumerHisType {
        _1("消费记录主体"), _2("红冲消费记录"), _3("红冲补充消费记录"), _4("退套餐消费记录"), _5("退礼品卡消费记录"), _6("违约扣费"), _7("退包月订单消费记录"),_8("积分补扣"),_9("退商品");
        public final String value;

        EnumConsumerHisType(String value) {
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
     * 礼品卡状态
     */
    public static enum EnumCardStatus {
        _1("未兑换"), _2("已兑换"), _3("作废");
        public final String value;

        EnumCardStatus(String value) {
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
     * 退款方式
     */
    public static enum EnumRefundType {
        _1("银行转账"), _2("支付宝转账"), _3("内部转账"), _4("现金"), _5("测试");
        public final String value;

        EnumRefundType(String value) {
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
     * 优惠类型
     *
     * @author hehaoqun
     * @date 2015年10月10日
     */
    public static enum EnumCouponSaleType {
        _1("单品现金优惠"), _2("单品服务优惠"), _3("套餐券"), _4("商家礼品券");
        public final String value;

        EnumCouponSaleType(String value) {
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

    public static enum EnumUserType {
        _1("潜在用户"), _2("正式用户");
        public final String value;

        EnumUserType(String value) {
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

    // 标记订单联系方式是属于哪种订单
    public static enum EnumContactType {
        _1("普通订单"), _2("套餐订单"), _3("意向登记"), _4("包月订单");
        public final String value;

        EnumContactType(String value) {
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
     * 会员下单量等级
     */
    public static enum EnumMemberOrderLevel {
        _3("C级"), _7("B级"), _8("A级");
        public final String value;

        EnumMemberOrderLevel(String value) {
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
     * 套餐类型
     *
     * @author pigy
     */
    public static enum EnumPackageOrderType {
        _1("无忧"), _2("至尊");
        public final String value;

        EnumPackageOrderType(String value) {
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
     * 包月订单类型
     *
     * @author pigy
     */
    public static enum EnumMonthsOrderType {
        _1("服务"), _2("商品");
        public final String value;

        EnumMonthsOrderType(String value) {
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
     * 套餐服务类型
     *
     * @author pigy
     */
    public static enum EnumHotType {
        _1("保洁"), _2("保健");
        public final String value;

        EnumHotType(String value) {
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
     * 套餐服务类型
     *
     * @author pigy
     */
    public static enum EnumSubHotType {
        _1("标准保洁"), _2("标准保健"), _3("小儿保健"),_4("极致除螨");
        public final String value;

        EnumSubHotType(String value) {
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
     * 套餐周期
     *
     * @author pigy
     */
    public static enum EnumCycle {
        _1("月度"), _2("季度"), _3("半年"), _4("一年");
        public final String value;

        EnumCycle(String value) {
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
     * 对账结果
     *
     * @author hehaoqun
     * @date 2015年11月30日
     */
    public static enum EnumContractResult {
        _1("当天pos记录机有此记录，系统没有此记录"), _2("当天pos机没有此记录，系统有此记录"), _3("流水号相同，金额数不对"), _4("流水号相同，金额数相同,但是系统有重复"), _5(
                "流水号相同，金额数不相同，"), _6("当天微信记录没有此记录，系统有此记录"), _7("当天微信记录有此记录，系统没有此记录");
        public final String value;

        EnumContractResult(String value) {
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
     * 充值方式
     *
     * @author hehaoqun
     * @date 2015年8月7日
     */

    public static enum EnumPayType {
        _1("刷卡"), _2("余额"), _3("网上支付"), _4("银行转账"), _5("空充"), _6("测试"), _7("红冲");
        public final String value;

        EnumPayType(String value) {
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
     * 优惠类别
     *
     * @author hehaoqun
     * @date 2015年9月6日
     */
    public static enum EnumOrderDiscountType {
        _1("会员折扣"), _2("销售派券"), _3("客户经理派券"), _4("客服派券"), _5("下单改价"), _6("其他优惠券"), _7("首单免费"), _8("礼品卡"),_9("积分抵扣");
        public final String value;

        EnumOrderDiscountType(String value) {
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
     * 服务站配置优惠方式
     */
    public static enum EnumDiscountType {
        _0("无优惠"), _1("首单优惠"), _2("现金券优惠");

        public final String value;

        EnumDiscountType(String value) {
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
     * 营销礼包优惠类型id
     */
    public static enum EnumPackageType {
        _1("套餐"), _2("会员等级"), _3("服务站"), _4("会员推荐(注册人获券)"), _5("会员推荐(推荐人获券)"), _6("其他"), _7("充值送券"), _8("客户经理送券"), _9("商家特惠");

        public final String value;

        EnumPackageType(String value) {
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
     * 短信业务逻辑编码
     *
     * @author ken
     */
    public static enum EnumSmsServiceCode {

        _101("Y,N,"), // 客户在家确认信息: Y.是 N.否
        _102("1,2,3,"), // 服务评价邀请: 1.满意 2.一般 3.不满意
        _103("B"), // 服务计时开始
        _104("E");// 服务计时结束

        public final String value;

        EnumSmsServiceCode(String value) {
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
     * 订单评论类型
     *
     * @author panpan
     */
    public static enum EnumSatisfaction {
        _0("未评价"), _1("满意"), _2("一般"), _3("不满意");

        public final String value;

        EnumSatisfaction(String value) {
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
     * @hhq
     * 评价状态
     */
    public static enum EnumCommentStatus {
        _1("未评"), _2("已评");

        public final String value;

        EnumCommentStatus(String value) {
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
     * 分组类型
     *
     * @author panpan
     */
    public static enum EnumGroupType {

        _1("服务师组"), _2("客户经理组"), _3("线上销售"), _4("客服组"), _5("站长组"), _6("运营组"), _7("培训组"), _8("产品经理组"), _9(
                "小V管家组"),_10("新人组");

        public final String value;

        EnumGroupType(String value) {
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
     * 订单预派方式
     */
    public static enum EnumOrderPreAssignType{
        _1("手动预派"),_2("自动预派");
        private String value;
        private  int code;
        EnumOrderPreAssignType(String value) {
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
     * 公共页面类型
     *
     * @author pigy
     */
    public static enum EnumPublicPageType {
        _1("关于我们", "about"), _2("加入我们", "joinus"), _3("招聘信息", "join"), _4("服务保障", "service"), _5("下单需知", "order"), _6("服务协议", "agreement"), _7("市场营销", "market"), _8("分享有礼", "share"), _9("优惠券使用规则", "coupon"), _10("家庭账户说明", "family"),
        _11("注册协议", "agreement"),_12("发票说明", "invoice"),_13("优惠券使用说明", "coupon"),_14("积分使用规则", "point"),_15("分享规则说明", "share_point"),_16("服务师冲单奖说明", "reward");

        public final String value;
        public final String codeStr;

        EnumPublicPageType(String value, String codeStr) {
            this.value = value;
            this.codeStr = codeStr;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getCodeStr() {
            return codeStr;
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 员工类型
     *
     * @author panpan
     */
    public static enum EnumStaffType {

        _1("服务师"), _2("客户经理"), _3("线上销售"), _4("客服"), _5("站长"), _6("运营"), _7("培训"), _8("产品经理"), _9("小V管家"),_10("新人组");

        public final String value;

        EnumStaffType(String value) {
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
     * 终端类型
     *
     * @author pigy
     */
    public static enum EnumDataType {

        _1("PC端"), _2("手机端");

        public final String value;

        EnumDataType(String value) {
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
     * 订单状态类型
     *
     * @author panpan
     */
    public static enum EnumOrderStatus {
        _1("待处理"), _3("派工中"), _4("待确认"), _5("待服务"), _6("服务中"), _7("待评价"), _8("暂停"), _9("订单完成"), _10("恢复中"),_20("已取消"), _21("违约扣费"), _22("临时订单"), _30("逻辑删除");

        public final String vaule;

        EnumOrderStatus(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 配置类型
     *
     * @author Ken
     */
    public static enum EnumConfigType {

        _1("系统整体配置"), _2("积分配置");

        public final String value;

        EnumConfigType(String value) {
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
     * 组用户类型
     *
     * @author panpan
     */
    public static enum EnumGroupUserType {

        _1("一般员工"), _2("负责人"), _3("副负责人"), _4("小组负责人");

        public final String value;

        EnumGroupUserType(String value) {
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
     * 国内国外
     *
     * @author panpan
     */
    public static enum EnumCountryType {

        _1("国内"), _2("国外");

        public final String value;

        EnumCountryType(String value) {
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
     * 价钱等级
     *
     * @author panpan
     */
    public static enum EnumPriceLevel {

        _1("普通"), _2("高级");

        public final String value;

        EnumPriceLevel(String value) {
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
     * 新老会员标记
     *
     * @author pigy
     */
    public static enum EnumIsOnline {
        _1("第一版"), _2("第二版"),_3("第三版");

        public final String value;

        EnumIsOnline(String value) {
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
     * 服务子项类型等级
     *
     * @author panpan
     */
    public static enum EnumSubItemType {

        _1("服务参数"), _2("服务师能力");

        public final String value;

        EnumSubItemType(String value) {
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
     * 服务子项能力要求
     *
     * @author panpan
     */
    public static enum EnumForOrder {

        _1("普通", "pricelevel=1"), _2("高级", "pricelevel=2");

        public final String name;
        public final String value;

        EnumForOrder(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 派单(工单)状态类型
     *
     * @author ll
     */
    public static enum EnumOrderAssignmentStatus {

        _1("派工中"), _2("待确认"), _3("已确认"), _4("拒绝"), _5("服务中"), _6("订单完成"),_7("撤派"),_8("暂停");

        public final String vaule;

        EnumOrderAssignmentStatus(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 微信版本
     *
     * @author panpan
     */
    public static enum EnumApps {
        /**
         * 微信版本
         */
        _operative("operative","运营管理"), _workstation(
                "workstation","站长"), _crm("crm","客户管理"), _leader("leader","服务师组长"), _worker("worker","服务师"),_monthleader("monthleader","包月组长"),_monthworker("monthworker","包月服务师"), _vmanager("vmanager","会员服务部"), _product("product","产品经理"),_main("main","公众号"), _bd("bd","bd"), _manager("manager","管家"), _training("training","培训"), _usercenter("usercenter","用户中心"), _formaldehyde("formaldehyde","甲醛");
        public final String vaule;
        public final String cname;

        EnumApps(String vaule,String cname) {
            this.vaule = vaule;
            this.cname = cname;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCname() {
            return cname;
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 订单查询状态类型
     *
     * @author panpan
     */
    public static enum EnumQueryOrderStatus {
        _1("未派工"), _2("待确定"),_3("未开始"), _4("进行中"), _5("已完成"), _6("暂停");

        public final String vaule;

        EnumQueryOrderStatus(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 优惠券活动分类
     *
     * @author ken
     */
    public static enum EnumCouponCata {
        _1("营销礼包"), _2("新人礼包"), _3("活动礼包"), _4("积分换券"), _5("一次领取券");

        public final String vaule;

        EnumCouponCata(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 优惠券种类
     *
     * @author Administrator
     */
    public static enum EnumCouponType {
        _1("服务优惠券"), _2("首单券"), _3("商品优惠券"), _4("商家礼品券");

        public final String value;

        EnumCouponType(String value) {
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
     * 发送短信返回状态
     *
     * @author pyepye
     */
    public String SmsRetStatus(int code) {
        String msg = "";

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "提交成功");
        map.put(1, "含有敏感词汇");
        map.put(2, "余额不足");
        map.put(3, "没有号码");
        map.put(4, "包含sql语句");
        map.put(10, "账号不存在");
        map.put(11, "账号注销");
        map.put(12, "账号停用");
        map.put(13, "IP鉴权失败");
        map.put(14, "格式错误");
        map.put(-1, "系统异常");
        map.put(-2, "已发送");

        msg = map.get(code);

        return msg;
    }

    /**
     * 积分编码
     *
     * @author panpan
     */
    public static enum EnumPointCode {
        _register("用户注册"), _profile("完善个人资料"), _renewals("会员续费"), _inviterecharge("邀请并充值成功"), _invitereg(
                "邀请并注册成功"), _complaint("投诉"), _share("分享成功"), _shareview("点击分享"), _firstfinish("首单完成"), _upgrade(
                "成为会员"), _feedback("反馈"), _good("好评"), _comment("评价"), _businessorder("商家推荐订单"), _pointchange("积分换券", 1), _system("系统操作", 1),_invitepay("消费邀请人送积分")
        , _consumer("消费"),_use("使用积分"),_return("积分返还"),_weekcomment("周评价");

        public final String vaule;
        public final int op; // 0 标准积分配置 1 特殊，不需要积分配置

        EnumPointCode(String vaule) {
            this.vaule = vaule;
            this.op = 0;
        }

        EnumPointCode(String vaule, int op) {
            this.vaule = vaule;
            this.op = op;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.vaule;
        }

        public int getOp() {
            return this.op;
        }
    }

    /**
     * 产品分类编码
     *
     * @author panpan
     */
    public static enum EnumServiceCataCode {
        _jz("家政", 1), _bj("保健", 2), _by("包月服务", 3), _yl("养老", 4);

        public final String value;
        public final int timeType; // 库存用

        EnumServiceCataCode(String value, int timeType) {
            this.value = value;
            this.timeType = timeType;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }

        public int getTimeType() {
            return this.timeType;
        }
    }

    /**
     * 产品编码
     *
     * @author panpan jz_101 : jz类型 _1单项 _2团体 _3单项套餐 _4团体套餐
     */
    public static enum EnumServiceItemCode {
        _jz_101("家洁净","标"), _jz_102("家电保洁","电"), _jz_103("地板抛光打蜡","抛")
        , _jz_104("360°墙壁掸扫","墙"), _jz_105("家居除螨","螨"), _jz_106("大扫除","扫"),
        _jz_107("深度保洁","深"), _jz_108("厨房专项","厨"), _jz_109("开荒保洁","荒"),_jz_110("家用中央空调","中"),
        _jz_111("简约保洁","简"),_jz_112("尊享保洁","尊"), _bj_101("标准保健","健"), _bj_201("团体保健",""),
        _bj_301("保健套餐",""), _bj_401("团体保健套餐",""), _yl_501("养老护理","老"),
        _bj_101_xe("小儿保健","儿"), _by_101("家政包月","月");

        public final String value;
        public final String abbr;


        EnumServiceItemCode(String value,String abbr) {
            this.value = value;
            this.abbr = abbr;
        }

        public String toString() {
            String ret = super.toString();
            ret = ret.substring(1, ret.length());
            return ret;
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }
        public String getAbbr() {
            return this.abbr;
        }
    }

    /**
     * 套餐券服务项代码
     *
     * @author ken
     */
    public static enum EnumServicePackageItemCode {
        _jz_101("标准保洁", 0, 0), _jz_101_4("标准保洁(4小时)", 4, 1), _jz_101_2("标准保洁(2小时)", 2, 1),
        _jz_111("简约保洁(4小时)", 4, 1),_jz_112("尊享保洁(4小时)", 4, 1), _jz_107("深度保洁", 4, 1),
        _bj_101("标准保健", 1, 1), _bj_101_xe("小儿保健", 0.75, 1), _jz_105("极致除螨", 1, 1);

        public final String value;
        public final double servicetime;
        public final int people;

        EnumServicePackageItemCode(String value, double servicetime, int people) {
            this.value = value;
            this.servicetime = servicetime;
            this.people = people;
        }

        public String toString() {
            String ret = super.toString();
            ret = ret.substring(1, ret.length());
            return ret;
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }

        public double getServicetime() {
            return this.servicetime;
        }
    }

    /**
     * 下单面积范围
     *
     * @author Ken
     */
    public static enum EnumOrderAcreageCode {
        _1("120平方以下", 1), // 0平方以上
        _2("120-240平方", 121), // 120平方以上
        _3("240平方以上", 241);// 240平方以上

        public final String value;
        public final int acreage;

        EnumOrderAcreageCode(String value, int acreage) {
            this.value = value;
            this.acreage = acreage;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }

        public String getShortValue() {
            return (this.acreage == 1 ? "<" : "") + (this.acreage == 241 ? ">" : "")
                    + this.value.substring(0, this.value.indexOf("平方")) + "m²";
        }

        public int getAcreage() {
            return this.acreage;
        }

    }

    public static enum EnumOrderAcreagejz106Code {
        _1("120平方以下", 1,3), // 0平方以上
        _2("120-240平方", 121,4), // 121平方以上
        _3("240-360平方", 241,5);// 241平方以上

        public final String value;
        public final int acreage;
        public final int peplenum;

        EnumOrderAcreagejz106Code(String value, int acreage,int peplenum) {
            this.value = value;
            this.acreage = acreage;
            this.peplenum = peplenum;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }

        public String getShortValue() {
            return (this.acreage == 1 ? "<" : "") + (this.acreage == 241 ? ">" : "")
                    + this.value.substring(0, this.value.indexOf("平方")) + "m²";
        }

        public int getAcreage() {
            return this.acreage;
        }
        public int getPeplenum() {
            return this.peplenum;
        }

    }

    /**
     * 套餐类型
     *
     * @return
     */
    public static enum EnumServicePackageType {
        _1("个人"), _2("团体保健");

        public final String value;

        EnumServicePackageType(String value) {
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 消费类型
     *
     * @return
     */
    public static enum EnumConsumerType {
        _1("服务消费"), _2("套餐"), _3("礼品卡"), _4("普通订单违约扣费"), _5("商品消费"), _6("套餐订单违约扣费");

        public final String value;

        EnumConsumerType(String value) {
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

    public EnumConsumerType[] getEnumConsumerType() {
        return EnumConsumerType.values();
    }

    public EnumServicePackageType[] getEnumServicePackageType() {
        return EnumServicePackageType.values();
    }

    /**
     * 优惠券服务项代码
     * 服务优惠券代码
     *
     * @author panpan
     */
    @Deprecated
    public static enum EnumCouponItemCode {
        A("标准保洁2小时", "jz_101", 1, 2), // 保洁2时
        B("标准保洁4小时", "jz_101", 1, 4), // 保洁4时
        C("专业保健1小时", "bj_101", 1, 1), // 专业保健1小时
        D("专业保健半小时", "bj_101", 1, 0.5); // 专业保健半小时

        public final String name;
        public final String item;
        public final int level;
        public final double num;

        EnumCouponItemCode(String name, String item, int level, double num) {
            this.name = name;
            this.item = item;
            this.level = level;
            this.num = num;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getItem() {
            return this.item;
        }

        public String getName() {
            return this.name;
        }

        public int getLevel() {
            return this.level;
        }

        public double getNum() {
            return this.num;
        }
    }



    /**
     * 号码类型
     *
     * @return
     */
    public static enum EnumNumberType {
        _1("手机"), _2("固话");

        public final String value;

        EnumNumberType(String value) {
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
     * 订单类型
     *
     * @return
     */
    public static enum EnumOrderType {
        //        _1("活动首单"), _2("活动订单"), _3("套餐订单");
        _1("普通订单"), _2("套餐订单"), _3("包月订单"), _4("包月体验订单");

        public final String value;

        EnumOrderType(String value) {
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
     * 消费订单类型
     *
     * @return
     */
    public static enum EnumConsumerOrderType {
        _1("订单"), _2("套餐"), _3("礼品卡"), _4("订单违约退款"), _5("商品"), _6("套餐订单违约退款");

        public final String value;

        EnumConsumerOrderType(String value) {
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
     * 员工级别
     *
     * @author panpan
     */
//    public static enum EnumUserLevel {
//        _11("新人"), _1("初级"), _2("专业"), _3("高级"), _4("资深");
//
//        public final String value;
//
//        EnumUserLevel(String value) {
//            this.value = value;
//        }
//
//        public String toString() {
//            return super.toString().replace("_", "");
//        }
//
//        public int getCode() {
//            return Integer.parseInt(this.toString());
//        }
//
//        public String getValue() {
//            return this.value;
//        }
//    }

    /**
     * 回访单的类型
     *
     * @author hehaoqun
     * @date 2015年7月2日
     */
    public static enum EnumRetrackType {
        _1("未评价"), _2("已评非满意"), _3("投诉满意度"), _4("包月订单");

        public final String value;

        EnumRetrackType(String value) {
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
     * 回访单状态
     *
     * @author hehaoqun
     * @date 2015年7月2日
     */
    public static enum EnumRetrackStatus {
        _1("未访"), _2("联系无果"), _3("有方案待跟进"), _4("回访完成");

        public final String value;

        EnumRetrackStatus(String value) {
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
     * 回访单状态
     *
     * @author hehaoqun
     * @date 2015年7月2日
     */
    public static enum EnumMonthOrderStatus {
        _1("未下单"), _2("已下单"), _3("废单");

        public final String value;

        EnumMonthOrderStatus(String value) {
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
     * 改单原因
     *
     * @author panpan
     */
    public static enum EnumReasonType {
        _0(""), _1("客户没空"), _2("服务站资源不足"), _3("指定工作组资源不足"),
        _4("指定人员资源不足"), _5("空充"), _6("红冲"), _7("违约扣费"), _8("预派")
        , _9("改派"), _10("撤销"), _11("换人"), _12("订单暂停"), _13("订单恢复"),_14("更换优惠券"),_15("跟单改派"),_16("预派方式变动"),_17("退单"),_18("修改服务日期"),_19("确认派工")
        ,_20("修改总工时及节假日"),_21("订单提前结束"),_22("订单加时原因");

        public final String value;

        EnumReasonType(String value) {
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

    public static enum EnumCouponLogType {
        _1("直接领取"), _2("输入兑换码"), _3("后台派送"), _4("积分换券"), _5("分享有礼"), _6("销售派券"), _7("客户经理派券"), _8("商家扫码派券");

        public final String value;

        EnumCouponLogType(String value) {
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
     * 回访类型
     *
     * @author fate
     */
    public static enum EnumVisitType {
        _1("电话"), _2("短信"), _3("微信"), _4("面谈");

        public final String value;

        EnumVisitType(String value) {
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
     * 正式客户标签
     *
     * @author zzj
     */
    public static enum EnumMemberLable {
        _A("8次/月以上"), _B("4-7次/月"), _C("1-3次/月");

        public final String value;

        EnumMemberLable(String value) {
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
     * 非会员客户标签
     *
     * @author zzj
     */
    public static enum EnumMemberPotentialLable {
        _A("优质，有意向"), _B("优质，无意向"), _C("有意向无能力"), _D("无意向无能力");

        public final String value;

        EnumMemberPotentialLable(String value) {
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
     * 会员类型
     *
     * @author panpan
     */
    public static enum EnumMemberType {
        _0("普通"), _1("折扣会员"), _2("充值会员"),_3("积分会员");
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
     * 会员家庭账号类型
     *
     * @author panpan
     */
    public static enum EnumFamilyMemberType {
        _0("普通"), _1("家庭账号"), _2("家庭成员账号");
        public final String value;

        EnumFamilyMemberType(String value) {
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
     * 家庭账号关系
     *
     * @author panpan
     */
    public static enum EnumRelationshipcode {
        _1("丈夫"), _2("妻子"), _3("儿子"), _4("儿媳"), _5("女儿"), _6("女婿"), _7("父亲"), _8("母亲"), _9("爷爷"), _10("奶奶"), _11(
                "外公"), _12("外婆"), _13("孙子"), _14("孙女"), _15("其他");
        public final String value;

        EnumRelationshipcode(String value) {
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
     * 员工编制类型
     *
     * @return
     */
    public static enum EnumSignType {
        _1("正式"), _2("签约(无底薪)"), _3("签约(带底薪)");

        public final String value;

        EnumSignType(String value) {
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
     * 广告位置
     *
     * @author fate
     */
    public static enum EnumAdArea {
        _1("上"), _2("中");

        public final String value;

        EnumAdArea(String value) {
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
     * 浏览历史类型
     *
     * @author fate
     */
    public static enum EnumMemberVisitType {
        _1("高端家政"), _2("中医保健"), _3("养老护理");

        public final String value;

        EnumMemberVisitType(String value) {
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

    public static enum EnumMemberDimensionType {
        _1("余额范围"), _2("积分范围"), _3("消费金额范围"), _4("消费次数范围"), _5("1周内生日"), _6("1周内服务"), _7("回访次数范围");

        public final String value;

        EnumMemberDimensionType(String value) {
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
     * 购买商品的类型
     *
     * @return
     */
    public static enum EnumGoodByType {
        _1("服务"), _2("套餐"), _3("商品");

        public final String value;

        EnumGoodByType(String value) {
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
     * 优惠劵所属使用者
     *
     * @author panpan
     */
    public static enum EnumCouponOwner {

        _1(""), _2("客户经理"), _3("销售"), _4("客服"), _5("服务站"), _6("市场活动"), _7(""), _8(""), _9("小V管家");

        public final String value;

        EnumCouponOwner(String value) {
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
     * 38活动
     *
     * @author jerry
     */
    public static enum Enum38Active {

        _1("我想要放松", 36, "womenpackage_a"), _2("我想要休息", 52, "womenpackage_b"), _3("我不想做家务", 62, "womenpackage_c"), _4("我想彻底解放", 78, "womenpackage_d");
        public final String name;
        public final int value;
        public final String tcode;

        Enum38Active(String name, int value, String tcode) {
            this.value = value;
            this.name = name;
            this.tcode = tcode;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return name;
        }
    }

    public static enum EnumCouponServiceItemType {
        _1("服务"), _2("商品");
        public final String value;

        EnumCouponServiceItemType(String value) {
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

    public static enum EnumBusinessType {
        _1("连锁"), _2("个体店");
        public final String value;

        EnumBusinessType(String value) {
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

    public static enum EnumHomeItemType {
        _1("单品"), _2("套餐"), _3("商品");
        public final String value;

        EnumHomeItemType(String value) {
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
     * 护理等级（养老护理）
     */
    public static enum EnumNursingLevel {
        _1("1级护理"), _2("2级护理"), _3("3级护理");

        public final String value;

        EnumNursingLevel(String value) {
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
     * 包月产品类型
     */
    public static enum EnumMonthType {
        _1("家庭服务师", "wy"), _2("月子师", "ys"), _3("照护师", "yl"),_4("育婴师", "yy");

        public final String value;
        public final String servicecode;

        EnumMonthType(String value, String servicecode) {
            this.value = value;
            this.servicecode = servicecode;
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

        public String getServicecode() {
            return this.servicecode;
        }
    }

    /**
     * 包月人员类型
     */
    public static enum EnumMonthUserType {
        _1("无忧管家", "wy"), _2("母婴护理", "my"), _3("养老护理", "yl");

        public final String vaule;
        public final String servicecode;

        EnumMonthUserType(String vaule, String servicecode) {
            this.vaule = vaule;
            this.servicecode = servicecode;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }

        public String getServicecode() {
            return this.servicecode;
        }
    }



    /**
     * 培训学校新闻分类
     */
    public static enum EnumInfoCode {
        _1("培训官网", "school");

        public final String vaule;
        public final String infocode;

        EnumInfoCode(String vaule, String infocode) {
            this.vaule = vaule;
            this.infocode = infocode;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }

        public String getInfocode() {
            return this.infocode;
        }
    }

    /**
     * 中秋活动
     */
    public static enum EnumMidAutumn {

        _1("4小时标准保洁", 5, "moon_a"), _2("1小时中医保健", 5, "moon_b");
        public final String name;
        public final int value;
        public final String tcode;

        EnumMidAutumn(String name, int value, String tcode) {
            this.value = value;
            this.name = name;
            this.tcode = tcode;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 计价类型
     *
     * @return
     */
    public static enum EnumCalcType {
        _1("时长*单价*人数"), _2("数量*单价"), _3("面积*单价"), _4("人数*时长*单价");

        public final String value;

        EnumCalcType(String value) {
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
     * 服务站类型
     */
    public static enum EnumWorkstationType {
        _1("实体"), _2("虚拟");

        public final String value;

        EnumWorkstationType(String value) {
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

    public static enum EnumAreaType {
        _1("小区"), _2("片区"), _3("市");

        public final String value;

        EnumAreaType(String value) {
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

    public static enum EnumPointHisType {
        _1("分享"), _2("单品"), _3("好友"),_4("后台操作"),_7("消费"),_8("退单扣除赠送积分"),_9("包月单");

        public final String value;

        EnumPointHisType(String value) {
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

    public static enum EnumPointCatacode {
        _1("注册"), _2("消费"), _3("续费"),_4("分享"),_5("其他");

        public final String value;

        EnumPointCatacode(String value) {
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
     * 员工级别类型
     *
     * @author panpan
     */
    public static enum EnumUserTypeLevel {
        _1("运营"), _2("区"),_3("站"), _4("组"), _5("小组"), _6("组员");

        public final String value;

        EnumUserTypeLevel(String value) {
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
     * 会员等级分类
     * @author fate
     */
    public static enum EnumLevelType {
        _1("(Ⅰ)"), _2("(Ⅱ)"), _3("(Ⅲ)");
        public final String value;

        EnumLevelType(String value) {
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
     * 工作日志状态
     * @author fate
     */
    public static enum EnumWorkerLogStatus {
        _1("待审核"), _2("已审核"),_3("已推送"),_4("已阅"),_20("撤销");
        public final String value;

        EnumWorkerLogStatus(String value) {
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
     * 套餐状态
     * @author fate
     */
    public static enum EnumPackageStatus {
        _1("服务中"), _2("已完成"),_20("退套餐"),_22("临时订单");
        public final String value;

        EnumPackageStatus(String value) {
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
     * 菜式类型
     */
    public static enum EnumDishesType {
        _1("早餐"), _2("中餐");

        public final String value;

        EnumDishesType(String value) {
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
     * 服务系列
     *
     * @author hehaoqun
     * @date 2015年7月2日
     */
    public static enum EnumServiceSeries {
        _1("标准保洁系列"), _2("标准保健系列"), _3("家电保洁系列");

        public final String value;

        EnumServiceSeries(String value) {
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
     * 营销礼包优惠类型id
     */
    public static enum EnumCataLog_Type {
        _service("service"), _package("package"), _goods("goods"), _month("month"), _card("card");

        public final String value;

        EnumCataLog_Type(String value) {
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 终端
     * @author fate
     */
    public static enum EnumClient {
        _weixin("微信"), _iphone("苹果"),_android("安卓");
        public final String value;

        EnumClient(String value) {
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }
    }



    /**
     * 服务师员工类型
     *
     * @author panpan
     */
    public static enum EnumEmployeeType {

        _1("新员工"), _2("回炉员工"), _3("正式员工"), _4("深造员工");

        public final String value;

        EnumEmployeeType(String value) {
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
     * app消息推送
     */
    public static enum EnumPushType{
        _asset("asset","资产通知"),_order("order","订单通知"),_coupon("coupon","优惠券通知"),_point("point","积分通知"),_sys("sys","系统通知"),_salary("salary","工资通知");

        public final String vaule;
        public final String name;

        EnumPushType(String vaule, String name) {
            this.vaule = vaule;
            this.name = name;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getValue() {
            return this.vaule;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * 51周年庆活动2
     */
    public static enum EnumTwoWeekYear{
        _1("包月服务大礼包", 5, "twoweekyear_a"), _2("家居保养大礼包", 5, "twoweekyear_b");
        public final String name;
        public final int value;
        public final String tcode;

        EnumTwoWeekYear(String name, int value, String tcode) {
            this.value = value;
            this.name = name;
            this.tcode = tcode;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 终端
     * @author fate
     */
    public static enum EnumFunctionSignStatus {
        _1("待确认"), _2("已确认"),_3("已取消");
        public final String value;

        EnumFunctionSignStatus(String value) {
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
     * 浏览器终端
     * @author pan
     */
    public static enum EnumBrowserType {
        _weixin("微信"), _uc("UC浏览器"),_qq("QQ浏览器");
        public final String value;

        EnumBrowserType(String value) {
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 手机系统类型
     * @author pan
     */
    public static enum EnumMobileType {
        _iphone("苹果"), _android("安卓");
        public final String value;

        EnumMobileType(String value) {
            this.value = value;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public String getCode() {
            return this.toString();
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     * 客户大数据特殊规则
     */
    public static enum EnumOmTypeKeyRule {
        _1("指定服务师"), _2("性别"),_3("指定不要服务师"),_4("沟通难易度");
        public final String value;

        EnumOmTypeKeyRule(String value) {
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
     * 系列Code
     */
    public static enum EnumSeriesCode {
        _1("专项清洁"), _2("甲醛治理"),_3("中医推拿"),_4("小儿推拿"),_5("健康家套餐"),_6("健儿家套餐"),_7("包年套餐"),_8("除螨包年");
        public final String value;

        EnumSeriesCode(String value) {
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
     * 活动验证类型
     */
    public static enum EnumFunctionValidateType{
        _1("只验证ID"),_2("验证姓名与ID"),_3("显示但不验证姓名且验证ID");
        public final String value;

        EnumFunctionValidateType(String value) {
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
     * 包月服务师当天的系统状态
     */
    public static enum EnumSystemStatus {

        _1("出单"), _2("跟单"), _3("预约"), _4("空闲");

        public final String value;

        EnumSystemStatus(String value) {
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
     * 包月服务师手工状态
     */
    public static enum EnumManualStatus {

        _1("出单"), _2("预约"), _3("培训"), _4("空闲");

        public final String value;

        EnumManualStatus(String value) {
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
     * 服务师和组长的人员类型
     */
    public static enum EnumWorkerType {

        _1("服务师"),_2("服务师组长");

        public final String value;

        EnumWorkerType(String value) {
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
     * 帐号类型
     * @author liuxj
     */
    public static enum EnumAccountType {
        _1("正式"), _2("临时");

        public final String vaule;

        EnumAccountType(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 考核结果
     * @author liuxj
     */
    public static enum EnumExaminationResult {
        _1("不通过"), _2("通过"),_3("未考核"),_4("已流失");

        public final String vaule;

        EnumExaminationResult(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 开拓分类
     */
    public static enum EnumOpenupType {
        _1("线上"), _2("线下");

        public final String vaule;

        EnumOpenupType(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 管家面试状态
     */
    public static enum EnumUserInterViewStatus {
        _1("服务时项不明确"), _2("价格不明确 "), _3("非实时需求"), _4("完成"), _5("非服务区域");
        public final String value;

        EnumUserInterViewStatus(String value) {
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
     * 日志撤销原因分类
     */
    public static enum EnumZorderWorkerLogCancel {
        _1("填错日期"), _2("没有服务"), _3("误操作"), _4("其他原因");

        public final String vaule;

        EnumZorderWorkerLogCancel(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 日志撤销原因分类
     */
    public static enum EnumAbnormalMonthOrderType {
        _1("超订单开始日期仍未派工"), _2("超订单恢复日期仍未派工"), _3("超订单结束日期仍未结束"), _4("超期仍未按服务开始(已派工)");

        public final String vaule;

        EnumAbnormalMonthOrderType(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 报单状态
     */
    public static enum EnumZorderReportStatus {
        _1("未处理"), _2("已处理"), _3("已取消");

        public final String vaule;

        EnumZorderReportStatus(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 报单取消原因
     */
    public static enum EnumReportCancelReason {
        _1("库存不足"), _2("信息错误"), _3("延迟下单"), _4("客户要求"), _5("测试数据"), _6("其他原因");

        public final String vaule;

        EnumReportCancelReason(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 报单状态
     */
    public static enum EnumPayStatus {
        _1("待扣费"), _2("已支付"), _3("预支付");

        public final String vaule;

        EnumPayStatus(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }

    /**
     * 报单类型
     */
    public static enum EnumReserveType {
        _1("新增报单"), _2("追加人数"), _3("改约"),_4("新客注册"),_5("取消报单");

        public final String vaule;

        EnumReserveType(String vaule) {
            this.vaule = vaule;
        }

        public String toString() {
            return super.toString().replace("_", "");
        }

        public int getCode() {
            return Integer.parseInt(this.toString());
        }

        public String getValue() {
            return this.vaule;
        }
    }


    public EnumReserveType [] getEnumReserveType(){
        return EnumReserveType.values();
    }

    public EnumPayStatus [] getEnumPayStatus(){
        return EnumPayStatus.values();
    }

    public EnumReportCancelReason [] getEnumReportCancelReason(){
        return EnumReportCancelReason.values();
    }

    public EnumZorderReportStatus [] getEnumZorderReportStatus(){
        return EnumZorderReportStatus.values();
    }

    public EnumZorderWorkerLogCancel[] getEnumZorderWorkerLogCancel() {
        return EnumZorderWorkerLogCancel.values();
    }

    public EnumOpenupType[] getEnumOpenupType() {
        return EnumOpenupType.values();
    }

    public EnumExaminationResult[] getEnumExaminationResult() {
        return EnumExaminationResult.values();
    }

    public EnumAccountType[] getEnumAccountType() {
        return EnumAccountType.values();
    }

    public EnumManualStatus[] getEnumManualStatus() {
        return EnumManualStatus.values();
    }

    public EnumWorkerType[] getEnumWorkerType() {
        return EnumWorkerType.values();
    }

    public EnumSystemStatus[] getEnumSystemStatus() {
        return EnumSystemStatus.values();
    }

    public EnumSeriesCode[] getEnumSeriesType() {
        return EnumSeriesCode.values();
    }

    public EnumOmTypeKeyRule[] getEnumOmTypeKeyRule() {
        return EnumOmTypeKeyRule.values();
    }

    public EnumFunctionSignStatus[] getEnumFunctionSignStatus() {
        return EnumFunctionSignStatus.values();
    }

    public EnumTwoWeekYear[] getEnumTwoWeekYear() {
        return EnumTwoWeekYear.values();
    }

    public EnumPushType[] getEnumPushType() {
        return EnumPushType.values();
    }

    public EnumClient[] getEnumClient() {
        return EnumClient.values();
    }

    public EnumWorkerLogStatus[] getEnumWorkerLogStatus() {
        return EnumWorkerLogStatus.values();
    }

    public EnumLevelType[] getEnumLevelType() {
        return EnumLevelType.values();
    }

    public EnumPointCatacode[] getEnumPointCatacode() {
        return EnumPointCatacode.values();
    }

    public EnumPointHisType[] getEnumPointHisType() {
        return EnumPointHisType.values();
    }

    public EnumAreaType[] getEnumareaType() {
        return EnumAreaType.values();
    }

    public EnumMidAutumn[] getEnumMidAutumn() {
        return EnumMidAutumn.values();
    }

    public EnumHomeItemType[] getEnumHomeItemType() {
        return EnumHomeItemType.values();
    }

    public EnumYN[] getEnumYN() {
        return EnumYN.values();
    }

    public EnumCouponServiceItemType[] getEnumCouponServiceItemType() {
        return EnumCouponServiceItemType.values();
    }

    public EnumMemberDimensionType[] getEnumMemberDimensionType() {
        return EnumMemberDimensionType.values();
    }

    public EnumMemberVisitType[] getEnumMemberVisitType() {
        return EnumMemberVisitType.values();
    }

    public EnumAdArea[] getEnumAdArea() {
        return EnumAdArea.values();
    }

    public EnumGroupType[] getEnumGroupType() {
        return EnumGroupType.values();
    }

    public EnumContactType[] getEnumContactType() {
        return EnumContactType.values();
    }

    public EnumHotType[] getEnumHotType() {
        return EnumHotType.values();
    }

    public EnumPackageOrderType[] getEnumPackageOrderType() {
        return EnumPackageOrderType.values();
    }

    public EnumCycle[] getEnumCycle() {
        return EnumCycle.values();
    }

    public EnumRetrackType[] getEnumRetrackType() {
        return EnumRetrackType.values();
    }

    public EnumPackageType[] getEnumPackageType() {
        return EnumPackageType.values();
    }

    public EnumIsOnline[] getEnumIsOnline() {
        return EnumIsOnline.values();
    }

    public EnumRetrackStatus[] getEnumRetrackStatus() {
        return EnumRetrackStatus.values();
    }

    public EnumNumberType[] getEnumNumberType() {
        return EnumNumberType.values();
    }

    public EnumOrderType[] getEnumOrderType() {
        return EnumOrderType.values();
    }

    public EnumOrderAcreageCode[] getEnumOrderAcreageCode() {
        return EnumOrderAcreageCode.values();
    }

    public EnumOrderAcreagejz106Code[] getEnumOrderAcreagejz106Code() {
        return EnumOrderAcreagejz106Code.values();
    }

    public EnumCouponCata[] getEnumCouponCata() {
        return EnumCouponCata.values();
    }

    public EnumDishesType[] getEnumDishesType() {
        return EnumDishesType.values();
    }

    public EnumCouponType[] getEnumCouponType() {
        return EnumCouponType.values();
    }

    public EnumCouponSubType[] getEnumCouponSubType() {
        return EnumCouponSubType.values();
    }

    public EnumVisitFromType[] getEnumVisitFromType() {
        return EnumVisitFromType.values();
    }

    public EnumOrderStatus[] getEnumOrderStatus() {
        return EnumOrderStatus.values();
    }

    public EnumCountryType[] getEnumCountryType() {
        return EnumCountryType.values();
    }

    public EnumStaffType[] getEnumStaffType() {
        return EnumStaffType.values();
    }

    public EnumPriceLevel[] getEnumPriceLevel() {
        return EnumPriceLevel.values();
    }

    public EnumSubItemType[] getEnumSubItemType() {
        return EnumSubItemType.values();
    }

    public EnumForOrder[] getEnumForOrder() {
        return EnumForOrder.values();
    }

    public EnumOrderAssignmentStatus[] getEnumOrderAssignmentStatus() {
        return EnumOrderAssignmentStatus.values();
    }

    public EnumApps[] getEnumApps() {
        return EnumApps.values();
    }

    public EnumQueryOrderStatus[] getEnumQueryOrderStatus() {
        return EnumQueryOrderStatus.values();
    }

    public EnumSatisfaction[] getEnumSatisfaction() {
        return EnumSatisfaction.values();
    }

    public EnumDiscountType[] getEnumDiscountType() {
        return EnumDiscountType.values();
    }

    public EnumPublicPageType[] getEnumPublicPageType() {
        return EnumPublicPageType.values();
    }

    public EnumPointCode[] getEnumPointCode() {
        return EnumPointCode.values();
    }

    public EnumServiceCataCode[] getEnumServiceCataCode() {
        return EnumServiceCataCode.values();
    }

    public EnumServiceItemCode[] getEnumServiceItemCode() {
        return EnumServiceItemCode.values();
    }

    public EnumCouponItemCode[] getEnumCouponItemCode() {
        return EnumCouponItemCode.values();
    }

    public EnumServicePackageItemCode[] getEnumServicePackageItemCode() {
        return EnumServicePackageItemCode.values();
    }

//    public EnumUserLevel[] getEnumUserLevel() {
//        return EnumUserLevel.values();
//    }

    public EnumReasonType[] getEnumReasonType() {
        return EnumReasonType.values();
    }

    public EnumCardStatus[] getEnumCardStatus() {
        return EnumCardStatus.values();
    }

    public EnumCouponLogType[] getEnumCouponLogType() {
        return EnumCouponLogType.values();
    }

    public EnumVisitType[] getEnumVisitType() {
        return EnumVisitType.values();
    }

    public EnumOrderDiscountType[] getEnumOrderDiscountType() {
        return EnumOrderDiscountType.values();
    }

    public EnumMemberType[] getEnumMemberType() {
        return EnumMemberType.values();
    }

    public EnumSignType[] getEnumSignType() {
        return EnumSignType.values();
    }

    public EnumGoodsStatus[] getEnumGoodsStatus() {
        return EnumGoodsStatus.values();
    }

    public EnumCouponOwner[] getEnumCouponOwner() {
        return EnumCouponOwner.values();
    }

    public String[] getArr_YesNo() {
        return arr_YesNo;
    }

    public String[] getArr_UserType() {
        return arr_UserType;
    }

    public String[] getArr_FromType() {
        return arr_FromType;
    }

    public String[] getArr_Isforbid() {
        return arr_Isforbid;
    }

    public String[] getArr_IsAudit() {
        return arr_IsAudit;
    }

    public String[] getArr_Gender() {
        return arr_Gender;
    }

    public String[] getArr_Maritalstatus() {
        return arr_Maritalstatus;
    }

    public String[] getArr_Recommendedtype() {
        return arr_Recommendedtype;
    }

    public String[] getArr_ServiceCata() {
        return arr_ServiceCata;
    }

    public String[] getArr_Marketting() {
        return arr_Marketting;
    }

    public String[] getArr_HelpInfoCtype() {
        return arr_HelpInfoCtype;
    }

    public String[] getArr_Goods_Status() {
        return arr_Goods_Status;
    }

    public String[] getArr_Educational() {
        return arr_Educational;
    }

    public String[] getArr_BDUserType() {
        return arr_BDUserType;
    }

    public String[] getArr_ComplaintStatus() {
        return arr_ComplaintStatus;
    }

    public String[] getArr_Lang() {
        return arr_Lang;
    }

    public String[] getArr_BillTransferStatus() {
        return arr_BillTransferStatus;
    }

    public void setArr_BillTransferStatus(String[] arr_BillTransferStatus) {
        this.arr_BillTransferStatus = arr_BillTransferStatus;
    }

    public String[] getArr_InviteSource() {
        return arr_InviteSource;
    }

    public String[] getArr_Payment() {
        return arr_Payment;
    }

    public String[] getArr_RechargeType() {
        return arr_RechargeType;
    }

    public void setArr_RechargeType(String[] arr_RechargeType) {
        this.arr_RechargeType = arr_RechargeType;
    }

    public String[] getArr_Age() {
        return arr_Age;
    }

    public String[] getArr_Relationship() {
        return arr_Relationship;
    }

    public String[] getArr_InvoiceStatus() {
        return arr_InvoiceStatus;
    }

    public String[] getArr_BillConfigTypes() {
        return arr_BillConfigTypes;
    }

    public String[] getArr_Unit() {
        return arr_Unit;
    }

    public String[] getArr_AddressType() {
        return arr_AddressType;
    }

    public String[] getArr_OrderPayType() {
        return arr_OrderPayType;
    }

    public String[] getArr_ReportPayType() {
        return arr_ReportPayType;
    }

    public String[] getArr_ServiceItemType() {
        return arr_ServiceItemType;
    }

    public String[] getArr_IsExport() {
        return arr_IsExport;
    }

    public void setArr_IsExport(String[] arr_IsExport) {
        this.arr_IsExport = arr_IsExport;
    }

    public String[] getArr_Demands() {
        return arr_Demands;
    }

    public EnumPayType[] getEnumPayType() {
        return EnumPayType.values();
    }

    public EnumRefundType[] getEnumRefundType() {
        return EnumRefundType.values();
    }

    public EnumConsumerHisType[] getEnumConsumerHisType() {
        return EnumConsumerHisType.values();
    }

    public EnumConsumerOrderType[] getEnumConsumerOrderType() {
        return EnumConsumerOrderType.values();
    }

    public EnumContractResult[] getEnumContractResult() {
        return EnumContractResult.values();
    }

    public EnumCouponSaleType[] getEnumCouponSaleType() {
        return EnumCouponSaleType.values();
    }

    public EnumGoodsType[] getEnumGoodsType() {
        return EnumGoodsType.values();
    }

    public String[] getArr_HotLevel() {
        return arr_HotLevel;
    }

    public void setArr_HotLevel(String[] arr_HotLevel) {
        this.arr_HotLevel = arr_HotLevel;
    }

    public EnumFamilyMemberType[] getEnumFamilyMemberType() {
        return EnumFamilyMemberType.values();
    }

    public EnumRelationshipcode[] getEnumRelationshipcode() {
        return EnumRelationshipcode.values();
    }

    public EnumGoodsItemType[] getEnumGoodsItemType() {
        return EnumGoodsItemType.values();
    }

    public EnumOrderPayType[] getEnumOrderPayType() {
        return EnumOrderPayType.values();
    }

    public EnumSubHotType[] getEnumSubHotType() {
        return EnumSubHotType.values();
    }

    public EnumBusinessType[] getEnumBusinessType() {
        return EnumBusinessType.values();
    }

    public EnumZorderMonthStatus[] getEnumZorderMonthStatus() {
        return EnumZorderMonthStatus.values();
    }

    public EnumZorderRegisterStatus[] getEnumZorderRegisterStatus() {
        return EnumZorderRegisterStatus.values();
    }

    public EnumFailReason[] getEnumFailReason() {
        return EnumFailReason.values();
    }

    public EnumZorderRefType[] getEnumZorderRefType() {
        return EnumZorderRefType.values();
    }

    public EnumAppointmentHometype[] getEnumAppointmentHometype() {
        return EnumAppointmentHometype.values();
    }

    public EnumAppointmentTimetype[] getEnumAppointmentTimetype() {
        return EnumAppointmentTimetype.values();
    }

    public EnumAppointmentStatus[] getEnumAppointmentStatus() {
        return EnumAppointmentStatus.values();
    }

    public EnumMonthsOrderType[] getEnumMonthsOrderType() {
        return EnumMonthsOrderType.values();
    }

    public EnumMonthOrderStatus[] getEnumMonthOrderStatus() {
        return EnumMonthOrderStatus.values();
    }

    public EnumMonthType[] getEnumMonthType() {
        return EnumMonthType.values();
    }
    public EnumMonthUserType[] getEnumMonthUserType() {
        return EnumMonthUserType.values();
    }

    public EnumInfoCode[] getEnumInfoCode() {
        return EnumInfoCode.values();
    }

    public EnumCalcType[] getEnumCalcType() {
        return EnumCalcType.values();
    }
    public EnumDataType[] getEnumDataType() {
        return EnumDataType.values();
    }

    public EnumWorkstationType[] getEnumWorkstationType() {
        return EnumWorkstationType.values();
    }
    public EnumReportRunRate[] getEnumReportRunRate() {
        return EnumReportRunRate.values();
    }
    public EnumReportRunType[] getEnumReportRunType() {
        return EnumReportRunType.values();
    }
    public EnumPlatFormType[] getEnumPlatFormType() {
        return EnumPlatFormType.values();
    }
    public EnumDemand[] getEnumDemand() {
        return EnumDemand.values();
    }
    public EnumReportRunStatus[] getEnumReportRunStatus() {
        return EnumReportRunStatus.values();
    }

    public EnumCommentStatus[] getEnumCommentStatus(){ return EnumCommentStatus.values();}
    public EnumPackageStatus[] getEnumPackageStatus(){
        return EnumPackageStatus.values();
    }

    public EnumServiceSeries[] getEnumServiceSeries(){
        return EnumServiceSeries.values();
    }

    public EnumCataLog_Type[] getEnumCataLog_Type(){ return EnumCataLog_Type.values();}

    public EnumEmployeeType[] getEnumEmployeeType() {
        return EnumEmployeeType.values();
    }
    public EnumNursingLevel[] getEnumNursingLevel() {
        return EnumNursingLevel.values();
    }

    public static int getOrderPaytypeCode(String paytypestr){
       EnumOrderPayType[] enumOrderPayTypes=CommArray.getInstance().getEnumOrderPayType();
       for(EnumOrderPayType e:enumOrderPayTypes){
            if(e.getName().equals(paytypestr)){
                return e.getCode();
            }
        }
        return 0;
    }

    public static int getGoodsTypeCode(String paytypestr){
        EnumGoodsType[] enumGoodsTypes=CommArray.getInstance().getEnumGoodsType();
        for(EnumGoodsType e:enumGoodsTypes){
            if(e.getTypecode().equals(paytypestr)){
                return e.getCode();
            }
        }
        return 0;
    }

    public static int getPublicPageTypeCode(String code){
        EnumPublicPageType[] enumPublicPageType=CommArray.getInstance().getEnumPublicPageType();
        for(EnumPublicPageType e:enumPublicPageType){
            if(e.getCodeStr().equals(code)){
                return e.getCode();
            }
        }
        return 0;
    }

    public EnumOrderPreAssignType[] getEnumOrderPreAssignType() {
        return EnumOrderPreAssignType.values();
    }
    public EnumAppointmentProcess[] getEnumAppointmentProcess() {
        return EnumAppointmentProcess.values();
    }
    public EnumOrderListSecondCata[] getEnumOrderListSecondCata() {
        return EnumOrderListSecondCata.values();
    }
    public EnumGroupUserType[] getEnumGroupUserType() {
        return EnumGroupUserType.values();
    }
    public EnumUserInterViewStatus[] getEnumUserInterViewStatus() {
        return EnumUserInterViewStatus.values();
    }
    public EnumMemberGtnyd[] getEnumMemberGtnyd() {
        return EnumMemberGtnyd.values();
    }
    public EnumPackageReason[] getEnumPackageReason() {
        return EnumPackageReason.values();
    }
    public EnumFunctionValidateType[] getEnumFunctionValidateType() {
        return EnumFunctionValidateType.values();
    }

}
