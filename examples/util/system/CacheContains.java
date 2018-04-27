package com.homevip.util.system;

import org.apache.commons.lang.StringUtils;

import com.homevip.user.entity.User_token;

public class CacheContains {

	/**
	 * 10分钟
	 */
	public final static int EXPIRE_ONE_MINUTE = 10*60;
	
	/**
	 * 半小时
	 */
	public final static int EXPIRE_HALF_HOUR = 30*60;
	/**
	 * 一小时
	 */
	public final static int EXPIRE_ONE_HOUR = 60*60;
	/**
	 * 两小时
	 */
	public final static int EXPIRE_TWO_HOUR = 2*60*60;
	/**
	 * 半天
	 */
	public final static int EXPIRE_HALF_DAY = 12*60*60;
	/**
	 * 一天
	 */
	public final static int EXPIRE_ONE_DAY = 24*60*60;
	/**
	 * 一周
	 */
	public final static int EXPIRE_ONE_WEEK = 7*24*60*60;
	/**
	 * 半个月
	 */
	public final static int EXPIRE_HALF_MONTH = 15*24*60*60;
	/**
	 * 一个月
	 */
	public final static int EXPIRE_ONE_MONTH = 30*24*60*60;
	
	/**
	 * user_group 实体 key
	 */
	private static String USER_GROUP_ID_KEY = "USER_GROUP_ID_KEY_";
	
	/**
	 * service_item 实体 key
	 */
	private static String SERVICE_ITEM_ID_KEY = "SERVICE_ITEM_ID_KEY_";
	
	/**
	 * user 实体 id key
	 */
	private static String USER_ID_KEY = "USER_ID_KEY_";
	/**
	 * user 实体 account key
	 */
	private static String USER_ACCOUNT_KEY = "USER_ACCOUNT_KEY_";
	
	/**
	 * Zorder_package 实体 key
	 */
	private static String ZORDER_PACKAGE_ID_KEY = "ZORDER_PACKAGE_ID_KEY_";
	
	/**
	 * Zorder_package 实体 key(orderNo)
	 */
	private static String ZORDER_PACKAGE_ORDERNO_KEY = "ZORDER_PACKAGE_ORDERNO_KEY_";
	
	/**
	 * Member_address 实体 key
	 * 
	 */
	private static String MEMBER_ADDRESS_ID_KEY = "MEMBER_ADDRESS_ID_KEY_";
	
	/**
	 * Member 实体 key
	 * 
	 */
	private static String MEMBER_ID_KEY = "MEMBER_ID_KEY_";
	/**
	 * Member 实体 key
	 * 
	 */
	private static String MEMBER_ACCOUNT_KEY = "MEMBER_ACCOUNT_KEY_";
	
	/**
	 * Service_device
	 */
	private static String SERVICE_DEVICE_ID_KEY = "SERVICE_DEVICE_ID_KEY_";
	
	
	/**
	 * zorder
	 */
	private static String ZORDER_ID_KEY = "ZORDER_ID_KEY_";
	
	/**
	 * zorder orderNum
	 */
	private static String ZORDER_ORDERNUM_KEY = "ZORDER_ORDERNUM_KEY_";

	
	/**
	 * Zorder_assignment_orderId
	 */
	private static String ZORDER_ASSIGNMENT_OID_KEY = "ZORDER_ASSIGNMENT_OID_KEY_";
	
	/**
	 * base_region
	 */
	private static String BASE_REGION_KEY = "BASE_REGION_KEY_";
	/**
	 * base_region_code
	 */
	private static String BASE_REGION_CODE_KEY = "BASE_REGION_CODE_KEY_";
	/**
	 * Base_community
	 */
	private static String BASE_COMMUNITY_KEY = "BASE_COMMUNITY_KEY_";
	/**
	 * Base_workstation
	 */
	private static String BASE_WORKSTATION_KEY = "BASE_WORKSTATION_KEY_";

	/**
	 * base_member_level
	 */
	private static String BASE_MEMBER_LEVEL = "BASE_MEMBER_LEVEL_";
	
	/**
	 * zorder_appointment
	 */
	private static String ZORDER_APPOINTMENT = "ZORDER_APPOINTMENT_";
	
	/**
	 * Zorder_goods
	 */
	private static String ZORDER_GOODS = "ZORDER_GOODS_";
	
	/**
	 * Goods
	 */
	private static String GOODS = "GOODS_";
	
	/**
	 * Service_subitem
	 */
	private static String SERVICE_SUBITEM = "SERVICE_SUBITEM_";
	
	/**
	 * service_package
	 */
	private static String SERVICE_PACKAGE = "SERVICE_PACKAGE_";
	/**
	 * Member_token
	 */
	private static String MEMBER_TOKEN_ID = "MEMBER_TOKEN_ID_";
	
	private static String MEMBER_TOKEN = "MEMBER_TOKEN_";

	/**
	 * member_binding
	 */
	private static String MEMBER_BINDING = "MEMBER_BINDING_MEMBER_ID_TYPE_";
	
	/**
	 * member_binding
	 */
	private static String MEMBER_BINDING_OPEN_ID = "MEMBER_BINDING_MEMBER_ID_OPEN_";
	/**
	 * Member_coupon
	 */
	private static String MEMBER_COUPON = "MEMBER_COUPON_ID_";
	/**
	 * Ad
	 */
	private static String AD_ID = "AD_ID_";
	private static String AD = "AD_AREA_CLIENT_";
	/**
	 * Api_cache
	 */
	private static String API_CACHE = "API_CACHE_";
	/**
	 * App_config
	 */
	private static String APP_CONFIG_ID = "APP_CONFIG_ID_";
	
	private static String APP_CONFIG_UA = "APP_CONFIG_UA_";
	
	/**
	 * user_member_mapping
	 */
	private static String USER_MEMBER_MAPPING = "USER_MEMBER_MAPPING_";
	
	private static String USER_TOKEN = "USER_TOKEN_";
	
	private static String USER_TOKEN_ID = "USER_TOKEN_ID";
	
	public static String MENU_USER_ALL = "MENU_USER_ID_*";
	
	private static String MENU_USERID = "MENU_USER_ID_";
	
	public static String getUserMenuById(int id) {
		return MENU_USERID+id;
	}
	
	public static String getUserGroupIDKey(int id) {
		return USER_GROUP_ID_KEY+id;
	}
	
	public static String getServiceItemIDKey(int id) {
		return SERVICE_ITEM_ID_KEY+id;
	}
	
	public static String getUserIDKey(int id) {
		return USER_ID_KEY+id;
	}
	public static String getUserAccountKey(String account) {
		return USER_ACCOUNT_KEY+account;
	}
	public static String getZorderPackageIDKey(int id) {
		return ZORDER_PACKAGE_ID_KEY+id;
	}
	public static String getZorderPackageOrderNoKey(String orderNo) {
		return ZORDER_PACKAGE_ORDERNO_KEY+orderNo;
	}
	public static String getMemberAddressIDKey(int id) {
		return MEMBER_ADDRESS_ID_KEY+id;
	}
	public static String getMemberIDKey(int id) {
		return MEMBER_ID_KEY+id;
	}
	public static String getMemberAccountKey(String account) {
		return MEMBER_ACCOUNT_KEY+account;
	}
	public static String getServiceDeviceIDKey(int id) {
		return SERVICE_DEVICE_ID_KEY+id;
	}
	public static String getZorderIDKey(int id) {
		return ZORDER_ID_KEY+id;
	}
	public static String getZorderOrderNumKey(String orderNum) {
		return ZORDER_ORDERNUM_KEY+orderNum;
	}
	public static String getZorderAssignmentOidKey(int id) {
		return ZORDER_ASSIGNMENT_OID_KEY+id;
	}
	public static String getBaseRegionKey(int id) {
		return BASE_REGION_KEY+id;
	}
	public static String getBaseRegionCodeKey(String code) {
		return BASE_REGION_CODE_KEY+code;
	}
	public static String getBaseCommunityKey(int id) {
		return BASE_COMMUNITY_KEY+id;
	}
	public static String getBaseWorkstationKey(int id) {
		return BASE_WORKSTATION_KEY+id;
	}
	public static String getBaseMemberLevelKey(int id) {
		return BASE_MEMBER_LEVEL+id;
	}
	public static String getZorderAppointmentKey(int id) {
		return ZORDER_APPOINTMENT+id;
	}
	public static String getZorderGoodsKey(int id) {
		return ZORDER_GOODS+id;
	}
	public static String getGoodsKey(int id) {
		return GOODS+id;
	}
	public static String getServiceSubitemKey(int id) {
		return SERVICE_SUBITEM+id;
	}
	public static String getServicePackageKey(int id) {
		return SERVICE_PACKAGE+id;
	}
	public static String getMemberTokenIdKey(int id) {
		return MEMBER_TOKEN_ID+id;
	}
	public static String getMemberTokenKey(String token) {
		return MEMBER_TOKEN+token;
	}
	public static String getMemberBindingKey(int memberId, int platformType) {
		return MEMBER_BINDING+memberId+"_"+platformType;
	}
	public static String getMemberBindingOpenIdKey(String openId, int platformType) {
		return MEMBER_BINDING_OPEN_ID+openId+"_"+platformType;
	}
	public static String getMemberCouponKey(int id) {
		return MEMBER_COUPON+id;
	}
	public static String getAdIDKey(int id) {
		return AD_ID+id;
	}
	public static String getAdKey(Integer area, String client) {
		return AD+area+"_"+client;
	}
	public static String getApiCacheKey(String code) {
		return API_CACHE+code;
	}
	public static String getAppConfigIDKey(int id) {
		return APP_CONFIG_ID+id;
	}
	public static String getAppConfigUAKey(String os,String resolution,Integer type) {
		
		resolution = resolution == null?"":resolution;
		if(StringUtils.isNotBlank(resolution)) {
			return APP_CONFIG_UA+os+"_"+resolution+"_"+type;
		}else {
			return APP_CONFIG_UA+os+"_"+type;
		}
	}
	
	public static String getUserMemberMappingKey(Integer memberId,Integer userType) {
		return USER_MEMBER_MAPPING+memberId+"_"+userType;
	}
	
	public static String getUserTokenKey(String token) {
		return USER_TOKEN + token;
	}
	
	public static String getUserTokenIdKey(int userId,String sessionId) {
		return USER_TOKEN_ID + userId+"_"+sessionId;
	}
}
