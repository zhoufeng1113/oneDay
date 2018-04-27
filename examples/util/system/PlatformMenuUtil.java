package com.homevip.util.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homevip.user.entity.User;
import com.homevip.user.entity.User_role;
import com.homevip.util.file.FileRwUtil;

import net.sf.json.JSONObject;

public class PlatformMenuUtil {
	
	public static List<String> defaultUrls = new ArrayList<String>();
	
	static{
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/home");
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/loadmenu");
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/logout");
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/json_warninginfo");
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/profile/*");
		defaultUrls.add("/" + Const.CONTEXT_PLATFORMPATH + "/public/*");
	}

	/**
	 * 获取相关用户的菜单
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*public static JsonObject getMenu(User user, HttpServletRequest request) throws Exception{
		String path= request.getSession().getServletContext().getRealPath("/");
		String menu =FileRwUtil.readFileReader(path + "/WEB-INF/res/modules.json");
		JsonObject root = new JsonParser().parse(menu).getAsJsonObject();
		if(Const.ADMINUSER_NAME.equals(user.getAccount())){//初始管理员不鉴权
			return new JSONObject().fromObject(root.toString());
		}
		Set<String> rolecodes = new HashSet<String>();
		List<User_role> roles = user.getRoles();
		if(roles!=null){
			for (User_role user_role : roles) {
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
		for (JsonElement je : ja) {
			String url = "";
			JsonObject cmenu1 = je.getAsJsonObject();
			JsonArray menus2 = cmenu1.get("item").getAsJsonArray();
			JsonArray newmenus2 = new JsonArray();
			for (JsonElement cje2 : menus2) {
				JsonObject cmenu2 = cje2.getAsJsonObject();
				JsonArray menus3 = cmenu2.get("item").getAsJsonArray();
				JsonArray newmenus3 = new JsonArray();
				for (JsonElement cje3 : menus3) {
					JsonObject cmenu3 = cje3.getAsJsonObject();
					String code = cmenu3.get("code").getAsString();
					for (String str : rolecodes) {
						if(str.equals(code)){
							if("".equals(url))url = cmenu3.get("url").getAsString();
							newmenus3.add(cmenu3);
							break;
						}
					}
				}
				cmenu2.add("item", newmenus3);
				if(newmenus3.size()!=0){
					newmenus2.add(cmenu2);
				}
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

	public static JSONObject getSessionMenu(User user, HttpServletRequest request) throws Exception{
		JSONObject sessionmenu = (JSONObject)request.getSession().getAttribute(Const.SESSION_KEY_USER_MENU);
		if(sessionmenu==null){
			sessionmenu = getMenu(user, request);
			request.getSession().setAttribute(Const.SESSION_KEY_USER_MENU,sessionmenu);
		}
		return sessionmenu;
	}
	*/
	public static boolean isDefaultUrl(String url){
		for(String e : defaultUrls){
			if(e.indexOf("/*") > -1){
				e = e.replace("/*", "/");
			}
			if(url.indexOf(e) > -1) return true;
		}
		return false;
	}
}
