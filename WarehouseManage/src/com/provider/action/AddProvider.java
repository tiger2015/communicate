package com.provider.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProviderDAO;
import com.entity.Provider;
import com.opensymphony.xwork2.ActionSupport;

public class AddProvider extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2222547825074180955L;
	private Provider provider;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		ProviderDAO dao=(ProviderDAO) context.getBean("providerDAO");
		dao.saveProvider(provider);
		return SUCCESS;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
