package com.fortech.bookstore.scopebean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("counterBean")
@ViewScoped
public class CounterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationScopeBean applicationScopeBean;

	@Inject
	private SessionScopeBean sessionScopeBean;

	@Inject
	private ConversationScopeBean conversationScopeBean;

	@Inject
	private RequestScopeBean requestScopeBean;

	public CounterBean() {
		super();
	}

	public void refresh() {
		applicationScopeBean.increment();
		sessionScopeBean.increment();
		conversationScopeBean.increment();
		requestScopeBean.increment();
	}

	public void startConversation() {
		conversationScopeBean.begin();
	}

	public void endConversation() {
		conversationScopeBean.end();
	}

	public void endSession() {
		sessionScopeBean.setCounter(0);
	}

	public int applicationCounter() {
		return applicationScopeBean.getCounter();
	}

	public int sessionCounter() {
		return sessionScopeBean.getCounter();
	}

	public int conversationCounter() {
		return conversationScopeBean.getCounter();
	}

	public int requestCounter() {
		return requestScopeBean.getCounter();
	}

}
