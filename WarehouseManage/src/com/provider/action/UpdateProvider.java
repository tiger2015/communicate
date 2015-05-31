package com.provider.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.ProviderDAO;
import com.entity.Provider;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateProvider extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7234805578011037305L;
	private Provider provider;
	private String choice;
	private String condition;
	private String currentPage;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
		ProviderDAO dao=(ProviderDAO) context.getBean("providerDAO");
		dao.updateProvider(provider);
		return SUCCESS;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
}
