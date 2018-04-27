package com.homevip.util.system;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homevip.user.entity.User;
import com.homevip.user.entity.User_role;
import com.homevip.user.service.UserService;
import com.homevip.util.CollectionUtil;
import com.homevip.util.StringUtil;
import com.homevip.util.UserSessionUtil;
import com.homevip.util.file.FileRwUtil;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeixinMenuUtil {

	/**
	 * 获取相关用户的菜单
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
/*	public static JSONObject getMenu(User user, UserService userService, HttpServletRequest request) throws Exception{
		String path= request.getSession().getServletContext().getRealPath("/");
		String menu =FileRwUtil.readFileReader(path + "/WEB-INF/res/mobile_modules.json");
		JsonObject root = new JsonParser().parse(menu).getAsJsonObject();

		String sessionApp = UserSessionUtil.getApp(request);

		if(Const.ADMINUSER_NAME.equals(user.getAccount())){//初始管理员不鉴权
			return new JSONObject().fromObject(root.toString());
		}
		Set<String> rolecodes = new HashSet<String>();
		List<User_role> roles = user.getRoles();
		int rolesnums= 0;
		if(roles!=null){
			for (User_role user_role : roles) {
				//过滤PC端角色
				if(user_role.getDatatype() != CommArray.EnumDataType._2.getCode()){
					continue;
				}
				rolesnums++;
				if(user_role.getApp()==null || !user_role.getApp().equals(sessionApp)){
					continue;
				}
				String[] codes = StringUtils.defaultString(user_role.getAcls()).split(",");
				for (String str : codes) {
					if(!"".equals(StringUtils.defaultString(str))){
						rolecodes.add(str);
					}
				}
			}
		}
		JsonArray ja = root.get("item").getAsJsonArray();
		JsonArray newmenus1 = new JsonArray();
		//固化菜单列表
		JsonArray menuOther_two = new JsonArray();
		//固化菜单1
		//获取账号列表
		List<User> userList = userService.findUserByCode(user.getCode(),user.getId());
		//有其他账号的时候才固化这个菜单
		if(!CollectionUtil.isEmpty(userList)){

			JsonObject cmenu_temp1 = new JsonObject();
			cmenu_temp1.addProperty("code","9999999");
			cmenu_temp1.addProperty("item","");
			cmenu_temp1.addProperty("url","/weixin/usercenter/change_account?t="+Math.random()+1000);
			cmenu_temp1.addProperty("title","切换账号");
			menuOther_two.add(cmenu_temp1);
		}
		//固化菜单2
		//存在多角色才需要切换终端
		if(rolesnums > 1){
			JsonObject cmenu_temp2 = new JsonObject();
			cmenu_temp2.addProperty("code","9999999");
			cmenu_temp2.addProperty("item","");
			cmenu_temp2.addProperty("url","/weixin/usercenter/change_end?t="+Math.random()+1000);
			cmenu_temp2.addProperty("title","切换角色");
			menuOther_two.add(cmenu_temp2);
		}
		//固化菜单3
		JsonObject cmenu_temp3 = new JsonObject();
		cmenu_temp3.addProperty("code","9999999");
		cmenu_temp3.addProperty("item","");
		cmenu_temp3.addProperty("url","/weixin/usercenter/login?app="+UserSessionUtil.getApp(request));
		cmenu_temp3.addProperty("title","重新登陆");
		menuOther_two.add(cmenu_temp3);
		//固化菜单3
		JsonObject cmenu_temp4 = new JsonObject();
		cmenu_temp4.addProperty("code","9999999");
		cmenu_temp4.addProperty("item","");
		cmenu_temp4.addProperty("url","/weixin/usercenter/personal_info?_t"+Math.random());
		cmenu_temp4.addProperty("title","个人资料");
		menuOther_two.add(cmenu_temp4);

		//固化菜单4
		JsonObject cmenu_temp5 = new JsonObject();
		cmenu_temp5.addProperty("code","9999999");
		cmenu_temp5.addProperty("item","");
		cmenu_temp5.addProperty("url","/weixin/usercenter/changpwd?_t"+Math.random());
		cmenu_temp5.addProperty("title","修改密码");
		menuOther_two.add(cmenu_temp5);
		//固化一层菜单
		JsonObject menuOther_one = new JsonObject();
		menuOther_one.addProperty("code","9999999");
		menuOther_one.add("item",menuOther_two);
		menuOther_one.addProperty("title","个人");
		newmenus1.add(menuOther_one);
		for (JsonElement je : ja) {
			String url = "";
			JsonObject cmenu1 = je.getAsJsonObject();
			JsonArray menus2 = cmenu1.get("item").getAsJsonArray();
			JsonArray newmenus2 = new JsonArray();
			for (JsonElement cje2 : menus2) {
				JsonObject cmenu2 = cje2.getAsJsonObject();
				String code = cmenu2.get("code").getAsString();
				JsonElement cmenu3Element = cmenu2.get("item");
				JsonArray newmenus3 = new JsonArray();
				if(null != cmenu3Element){
					JsonArray menus3 = cmenu3Element.getAsJsonArray();
					for (JsonElement cje3 : menus3) {
						JsonObject cmenu3 = cje3.getAsJsonObject();
						code = cmenu3.get("code").getAsString();
						String title =cmenu3.get("title").getAsString();
						if(title.indexOf("-") > -1){
							title = title.substring(title.indexOf("-")+1);
							cmenu3.remove("title");
							cmenu3.addProperty("title",title);
						}
						for (String str : rolecodes) {
							if(str.equals(code)){
								if("".equals(url))url = cmenu3.get("url").getAsString();
								newmenus3.add(cmenu3);
								break;
							}
						}
					}
				}
				cmenu2.add("item", newmenus3);
				String title =cmenu2.get("title").getAsString();
				if(title.indexOf("-") > -1){
					title = title.substring(title.indexOf("-")+1);
					cmenu2.remove("title");
					cmenu2.addProperty("title",title);
				}
				for (String str : rolecodes) {
					if(str.equals(code)){
						if("".equals(url))url = cmenu2.get("url").getAsString();
						newmenus2.add(cmenu2);
						break;
					}
				}
//				if(newmenus3.size()!=0){
//
//					newmenus2.add(cmenu2);
//				}
			}
			String title =cmenu1.get("title").getAsString();
			if(title.indexOf("-") > -1){
				title = title.substring(title.indexOf("-")+1);
				cmenu1.remove("title");
				cmenu1.addProperty("title",title);
			}
			cmenu1.add("item", newmenus2);
			cmenu1.addProperty("url", url);
			if(newmenus2.size()!=0){
				newmenus1.add(cmenu1);
			}
		}

		root.add("item", newmenus1);
		JSONObject jo = new JSONObject().fromObject(root.toString());
		
		return jo;
	}

	public static JSONObject getSessionMenu(User user,UserService userService, HttpServletRequest request) throws Exception{
		JSONObject sessionmenu = (JSONObject)request.getSession().getAttribute(Const.SESSION_KEY_USER_MENU);
		if(sessionmenu==null){
		sessionmenu = getMenu(user,userService, request);
			request.getSession().setAttribute(Const.SESSION_KEY_USER_MENU,sessionmenu);
		}
		return sessionmenu;
	}*/

	public static void clearSessionMenu(HttpServletRequest request){
		request.getSession().removeAttribute(Const.SESSION_KEY_USER_MENU);
	}

}
