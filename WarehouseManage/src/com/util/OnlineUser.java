package com.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.dao.AdminDAO;

public class OnlineUser implements HttpSessionBindingListener {

	private String username;
	private String sessionId;

	public OnlineUser(String username, String sessionId) {
		this.sessionId = sessionId;
		this.username = username;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		Map sessionIdList = (Map) application.getAttribute("sessionIdList");
		if (sessionIdList == null) {
			sessionIdList = new HashMap();
			application.setAttribute("sessionIdList", sessionIdList);
		}
		if (!(sessionIdList.containsValue(this.username) && sessionIdList
				.containsKey(this.sessionId))) {
			sessionIdList.put(this.sessionId, this.username);
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		Map sessionIdList = (Map) application.getAttribute("sessionIdList");
		new AdminDAO().setAdminIsOnline(this.username, 0);
		sessionIdList.remove(this.sessionId);
		System.out.println(this.username + "退出"); 
	}

}
