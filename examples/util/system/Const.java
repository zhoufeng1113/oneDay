package com.homevip.util.system;

import com.homevip.core.util.Global;

/**
 * 全局参数
 *
 */
public final class Const {

	/**保存到session中的随机图片变量名*/
	public static final String SESSION_KEY_RAND = "_rcode";

	/**保存到session中的openid变量名*/
	public static final String SESSION_KEY_OPENID = "_openid";
	/**保存到session中的openid变量名*/
	public static final String SESSION_KEY_APP = "_app";
	/**参数列表**/
	public static final String SESSION_KEY_PARAMLIST = "_param_list";

	/**保存到session中的后台用户变量名*/
	public static final String SESSION_KEY_USER = "_user";
	/**保存到session中的后台用户菜单变量名*/
	public static final String SESSION_KEY_USER_MENU = "_user_menu";
	
	/**保存到session中的前台用户变量名*/
	public static final String SESSION_KEY_MEMBER = "_member";

	/**保存到cookies中的openid*/
	public static final String COOKIES_KEY_OPENID = "_COOKIES_1";
	
	/**word docx*/
	public static final String WORD_DOCX = "docx";
	/**word doc*/
	public static final String WORD_DOC = "doc";
		
	/**保存到cookie中变量名*/
	
	/**文件上传相关配置**/
	public final static String UPLOAD_URL_ROOT = Global.WebPath + "/uploads/";
	public final static String STATIC_URL_ROOT = Global.WebPath + "/static/";
	public final static String UPLOAD_URL_TMP = UPLOAD_URL_ROOT + "tmp/";
	
	public final static String UPLOAD_FLODER_ROOT = Global.FilePath;
	public final static String UPLOAD_FLODER_TMP = UPLOAD_FLODER_ROOT + "tmp/";
	public final static String STATIC_FLODER_ROOT = Global.FilePath + "/../static";

	public final static String APP_STATIC_IMG = Global.HttpsPath + "/app/static/images/";
	public final static String WORK_STATIC_IMG = Global.HttpsPath + "/work/static/images/";

	public final static String UPLOAD_HTTPS_ROOT = Global.HttpsPath + "/uploads/";
	public final static String STATIC_HTTPS_ROOT = Global.HttpsPath + "/static/";

	//合同上传路径
	public final static String CONTRACT_TEMPLATE_ROOT = Global.contract_path + "/templates/";
	public final static String CONTRACT_MEMBER_QR = Global.contract_path + "/qr/";
	//合同http路径
	public final static String CONTRACT_TEMPLATE_HTTP_ROOT = Global.HttpsPath + "/contracts/";
	public final static String CONTRACT_MEMBER_HTTP_ROOT = Global.HttpsPath + "/contracts/files/";
	public final static String CONTRACT_QR_HTTP_ROOT = Global.HttpsPath + "/contracts/qr/";

	/**证书上传路径*/
	public final static String CERTIFICATE_TEMPLATE = Global.FilePath + "/certificate/template/";
	public final static String CERTIFICATE_TMP = Global.FilePath + "/certificate/tmp/";
	public final static String CERTIFICATE_USER_CERT = Global.FilePath + "/certificate/user/certificate/";
	public final static String CERTIFICATE_USER_PHOTOS = Global.FilePath + "/certificate/user/photos/";

	/**证书http路径*/
	public final static String CERTIFICATE_HTTP_TEMPLATE = UPLOAD_URL_ROOT + "/certificate/template/";
	public final static String CERTIFICATE_HTTP_USER_CERT = UPLOAD_URL_ROOT + "/certificate/user/certificate/";
	public final static String CERTIFICATE_HTTP_USER_PHOTOS = UPLOAD_URL_ROOT + "/certificate/user/photos/";

	/**工牌上传路径*/
	public final static String USER_BADGE_TEMPLATE = Global.FilePath + "/badge/template/";
	public final static String USER_BADGE_TEMP = Global.FilePath + "/badge/temp/";
	public final static String USER_BADGE_PHOTOS = Global.FilePath + "/badge/photos/";
	public final static String USER_BADGE_QRCODE = Global.FilePath + "/badge/qrcode/";
	public final static String USER_BADGE_RESULT = Global.FilePath + "/badge/result/";

	/**工牌http路径*/
	public final static String USER_BADGE_HTTP_TEMPLATE = UPLOAD_URL_ROOT + "/badge/template/";
	public final static String USER_BADGE_HTTP_PHOTOS = UPLOAD_URL_ROOT + "/badge/photos/";
	public final static String USER_BADGE_HTTP_QUCODE = UPLOAD_URL_ROOT + "/badge/qrcode/";
	public final static String USER_BADGE_HTTP_RESULT = UPLOAD_URL_ROOT + "/badge/result/";
	public final static String USER_BADGE_HTTP_DOWNLOAD = UPLOAD_URL_ROOT + "/badge/download/";

	/**微信菜单小图标路径与http路径*/
	public final static String WECHAT_MENU_ICON = Global.FilePath + "/wechatMenuIcon/";
	public final static String WECHAT_MENU_ICON_HTTP = UPLOAD_URL_ROOT + "/wechatMenuIcon/";

	/**早教游戏方案路径**/
	public final static String EARLY_EDUCATION_SCHEME = Global.FilePath + "/scheme/";
	public final static String EARLY_EDUCATION_HTTP_SCHEME = UPLOAD_URL_ROOT + "/scheme/";

	/**母婴回访记录路径**/
	public final static String INFANT_MOM_RETURN_VISIT = Global.FilePath + "/record/";
	public final static String INFANT_MOM_RETURN_VISIT_HTTP = UPLOAD_URL_ROOT + "/record/";

	/**用于与密码结合MD5混淆*/
	public static final String MD5_MIX = "jw134#%pqNLVfn";
	
	/**后台管理员默认用户名密码*/
	public static final String ADMINUSER_NAME = "admin";
	public static final String ADMINUSER_PWD = "123456";
	
	/**全局变量中的变量名称*/
	public static final String CONTEXT_PATH = "path";
	public static final String CONTEXT_EXPORT_WEBPATH = "export";
	public static final String CONTEXT_PLATFORMPATH = "platform";
	public static final String PREVIEW_PDF = "PREVIEW_PDF";
	public static final String CONTRACT_QR_PATH = "CONTRACT_QR_PATH";
	public static final String CONTRACT_MEMBER_PATH = "CONTRACT_MEMBER_PATH";
	public static final String CONTEXT_API = "api";
	public static final String CONTEXT_WEIXINPATH = "weixin";
	public static final String CONTEXT_WORKPATH = "work";
	public static final String CONTEXT_APP = "app";
	public static final String CONTEXT_UPLOADPATH = "CONTEXT_UPLOADPATH";
	public static final String CONTEXT_WEBPATH = "CONTEXT_WEBPATH";
	public static final String CONTEXT_HTTPSPATH = "CONTEXT_HTTPSPATH";
	public static final String CONTEXT_COMMARRAY = "commArray";
	public static final String CONTEXT_WORDER = "worderEum";
	public static final String CONTEXT_APP_PARAMS = "_appParams";

	/**默认显示条数*/
	public static final int DEFINE_PAGE = 10;
	
	/**静态文件*/
	public static final String[] RESOURCES =  new String[] { "jpg", "png", "gif", "css","js", "swf", "ttf", "woff","mp4","webm" ,"html" };
	
	/**编辑地址前缀标识*/
	public static final String[] FLAG_EDIT =  new String[] { "ajax_"};
	
	public static final String UTF8 = "utf-8";
	
	/**时间段标识**/
	public static final int DUR_TODAY = 1 ; //今天
	public static final int DUR_THIS_WEEK = 2 ; //本周
	public static final int DUR_THIS_MONTH = 3 ; //本月
	public static final int DUR_LAST_MONTH = 4 ; //上月
	
	/**工人工单的工作状态**/
	public static final int ORDER_NOT_STARTED = 0 ; //未开始
	public static final int ORDER_WORKING = 1 ; //进行中
	public static final int ORDER_FINISHED = 2 ; //已完成

	/**广州城市代码*/
	public static final String GZ_CODE = "00200001";
	
	public static final int OP_EQ = 0x0001;
	public static final int OP_LE = 0x0002;
	public static final int OP_GE= 0x0003;
	
	public static final String GEOCODING_URL = "http://api.map.baidu.com/geocoder/v2/?ak=XzYbNmFDxUtYbgAPR8GSVUfu&output=json&pois=1&location=%s";
	
	/* 
	 * 地址关键词代号
	 * char的值不能重复 
	 * 由于mysql比较字符串时不区分大小写
	 * 例如 T 已经使用了， t 就不能再使用， 否则查找T会一并返回t的结果 
	 * 
	 */
	
	public static final char PROVINCE = 'P';
	public static final char CITY = 'C';
	public static final char TOWN = 'T';
	public static final char AREA = 'g';
	public static final char VILLAGE = 'V';
	public static final char REGION = 'D';
	public static final char STREET = 'S';
	public static final char STREETSUFFIX = 'u';
	public static final char DIRECTION = 'f';
	public static final char STREETNOSUFFIX = 'N';
	public static final char COMMUNITY = 'Q';
	public static final char COMMUNITYSUFFIX = 'x';
	public static final char ROAD = 'R';
	public static final char ROADSUFFIX = 'j';
	public static final char BUILDINGSUFFIX = 'B';
	public static final char BUILDINGNOSUFFIX = 'a';
	public static final char ELEVATORSUFFIX = 'e';
	public static final char LEVELSUFFIX = 'l';
	public static final char ROOMSUFFIX = 'm';
	public static final char BLANK = '.';
	public static final char DIGIT = '0';
	public static final char WHITESPACE = 'w';
	
	public static final char PROVINCE_CHN = '省';
	public static final char CITY_CHN = '市';
	public static final char TOWN_CHN = '镇';
	public static final char VILLAGE_CHN = '村';
	public static final char REGION_CHN = '区';
	
	/**每次地址解析的条目数量*/
	public static final int PARSING_PAGE_NUM = 1000;
	
	/**最少升级单笔钱*/
	public static final int UPGRADE_MIN = 1000;;
	/**微信企业端登录记住账号密码用*/
	public static final String WX_AUTO_ACCOUNT = "wx_auto_account";
	public static final String WX_AUTO_PASSWORD = "wx_auto_password";
	public static final String WX_AUTO_APP = "wx_auto_app";


    public static final String WX_TOKEN = "wx_token";
    public static final String JSON_PARAMS = "json_params";
    public static final Double NEAR_COMMUNITY_DISTANCES = 500d;	//附近小区默认距离范围
}
