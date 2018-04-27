package com.homevip.util.system;

import com.homevip.core.util.Global;
import com.homevip.util.StringUtil;
import com.homevip.util.http.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 链接模板
 * Created by Pigy-PC on 2017/4/12.
 */
public class UrlTemplate {
    public static Map<Integer,String> app_templates = new HashMap<Integer,String>(); //链接

    static {
        app_templates.put(1, Global.HttpsPath + "/app/web/order/introduction?type=%s&id=%s");
        app_templates.put(2, Global.HttpsPath + "/app/web/member/member_center");
        app_templates.put(3, Global.HttpsPath + "/weixin/main/member/address_add?id=%s&addresstype=1");
        app_templates.put(4, Global.HttpsPath + "/weixin/main/member/invoice_edit");
        app_templates.put(5, Global.HttpsPath + "/weixin//main/member/appointment/%s");
        app_templates.put(6, Global.HttpsPath + "/app/web/member/month/index?itemid=%s");
        app_templates.put(8, Global.HttpsPath + "/weixin/main/member/order/detail?orderno=%s&catacode=%s");
        app_templates.put(9, Global.HttpsPath + "/weixin/main/member/order/month/sub_detail?orderno=%s");
        app_templates.put(10, Global.HttpsPath + "/app/web/home");
        app_templates.put(11, Global.HttpsPath + "/weixin/main/member/recharge/index?paymoney=%s");
        app_templates.put(12, Global.HttpsPath + "/weixin/main/member/good/detail?id=%s&catacode=%s");
        app_templates.put(13, Global.HttpsPath + "/weixin/main/member/package/packagedetail?id=%s&catacode=%s");
        app_templates.put(14, Global.HttpsPath + "/weixin/main/publics/ordersucc?orderno=%s");
        app_templates.put(15, Global.HttpsPath + "/weixin/main/member/order/workerinfo?code=%s&orderno=%s");
        app_templates.put(16, Global.HttpsPath + "/app/web/member/vmanager");
        app_templates.put(17, Global.HttpsPath + "/weixin/main/member/my_arrearage");
        app_templates.put(18, Global.HttpsPath + "/weixin/main/member/month/weekdate_list?orderid==%s");
        app_templates.put(19, Global.HttpsPath + "/weixin/main/member/month/log?id=%s");
        app_templates.put(20, Global.HttpsPath + "/app/web/find/detail?id=%s");
        app_templates.put(21, Global.HttpsPath + "/weixin/main/more/join");
        app_templates.put(22, Global.HttpsPath + "/weixin/main/more/about");
        app_templates.put(23, Global.HttpsPath + "/weixin/main/shareview/invite/%s");
        app_templates.put(24, Global.HttpsPath + "/weixin/main/member/appointment/plan?planid=%s&orderno=%s");
        app_templates.put(25, Global.HttpsPath + "/weixin/main/cookbook");
        app_templates.put(26, Global.HttpsPath + "/weixin/main/member/month/log_list?monthtype=%s");
        app_templates.put(27, Global.HttpsPath + "/weixin/main/member/appointment/list?catacode=%s");
        app_templates.put(28, Global.HttpsPath + "/weixin/main/member/be_member");
        app_templates.put(29, Global.HttpsPath + "/weixin/usercenter/login");
        app_templates.put(30, Global.HttpsPath + "/weixin/leader/order/detail?app=%s&id=%s&act=view");
        app_templates.put(31, Global.HttpsPath + "/work/web/worker/order/detail?app=%s&id=%s&act=view&targetOrderno=%s");
        app_templates.put(32, Global.HttpsPath + "/weixin/monthleader/order/detail?app=%s&id=%s&act=view");
        app_templates.put(33, Global.HttpsPath + "/weixin/monthworker/order/detail?app=%s&id=%s&act=view");
        app_templates.put(34, Global.HttpsPath + "/weixin/main/member/contract/list");
        app_templates.put(35, Global.HttpsPath + "/weixin/main/member/month/month_list?cataid=%s");
        app_templates.put(36, Global.HttpsPath + "/weixin/%s/home");
        app_templates.put(37, Global.HttpsPath + "/weixin/main/publics/view?id=%s&type=%s");
        app_templates.put(38, Global.HttpsPath + "/weixin/monthworker/order/view?id=%s");
        app_templates.put(39, Global.HttpsPath + "/weixin/usercenter/salary");
        app_templates.put(40, Global.HttpsPath + "/weixin/main/member/calendar/index");
        app_templates.put(41, Global.HttpsPath + "/weixin/usercenter/personal_info");
        app_templates.put(42, Global.HttpsPath + "/weixin/worker/commission_his?r=%s");
        app_templates.put(43, Global.HttpsPath + "/work/web/worker/order/list?app=%s&act=view");
        app_templates.put(44, Global.HttpsPath + "/work/web/worker/service_calendar?act=view");
        app_templates.put(45, Global.HttpsPath + "/work/web/worker/reward_explain");
    }

    public static enum EnumUrlTemplate {
        _01("服务介绍链接"),_02("app个人中心链接"),_03("个人地址添加/编辑"),_04("发票编辑"),_05("无忧管家介绍页")
        ,_06("商品预约"),_08("普通订单详情"),_09("包月订单详情"),_10("首页"),_11("充值")
        ,_12("商品订单详情"),_13("套餐订单详情"),_14("订单是否成功下单确认页面"),_15("服务师详情页")
        ,_16("小V管家窗口"),_17("欠费明细"),_18("无忧评价"),_19("无忧日志"),_20("发现详情"),_21("加入我们"),_22("关于我们")
        ,_23("邀请注册"),_24("包月方案"),_25("无忧菜谱"),_26("整体解决方案"),_27("预约订单列表"),_28("会员权益"),_29("企业端登录")
        ,_30("组长订单详情"),_31("服务师订单详情"),_32("组长包月订单详情"),_33("服务师包月订单详情"),_34("我的合同"),_35("包月订单列表")
        ,_36("终端首页"),_37("方案记录显示"),_38("服务师早教方案详情"),_39("工资查看详情"),_40("用户端服务日历"),_41("个人资料"),_42("佣金历史"),_43("服务师端订单列表")
        ,_44("服务师日程"),_45("冲单奖说明");
        public final String value;

        EnumUrlTemplate(String value) {
            this.value = app_templates.get(getCode());
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

    /**
     * 获取格式化后的链接
     * @param enumUrlTemplate
     * @param str
     * @return
     */
    public static String getLink(EnumUrlTemplate enumUrlTemplate, HttpServletRequest request, Object...str){
        String url = String.format(enumUrlTemplate.getValue(), str);
        // FIXME: 特殊处理，由于Android在https环境下有session丢失问题，暂时改回http
        if(null != request && CommArray.EnumMobileType._android.getCode().equals(HttpUtil.getMobileType(request))){
            url = url.replace("https://","http://");
        }
        return url;
    }

    /**
     * 获取格式化后的链接
     * @param url
     * @return
     */
    public static String getLink(String url, HttpServletRequest request){
        // FIXME: 特殊处理，由于Android在https环境下有session丢失问题，暂时改回http
        if(null != request && CommArray.EnumMobileType._android.getCode().equals(HttpUtil.getMobileType(request))){
            url = url.replace("https://","http://");
        }
        return url;
    }

    /**
     * 获取格式化后的链接
     * @param enumUrlTemplate
     * @param extParam 扩展参数（直接拼接）
     * @param str
     * @return
     */
    public static String getLinkWithExt(EnumUrlTemplate enumUrlTemplate, HttpServletRequest request, String extParam, Object...str){
        String url = String.format(enumUrlTemplate.getValue() + extParam, str);
        // FIXME: 特殊处理，由于Android在https环境下有session丢失问题，暂时改回http
        if(null != request && CommArray.EnumMobileType._android.getCode().equals(HttpUtil.getMobileType(request))){
            url = url.replace("https://","http://");
        }
        return url;
    }

    public static void main(String[] sa){
        System.out.println(getLink(EnumUrlTemplate._01,null,"service",1));
    }
}
