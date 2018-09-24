package com.fortech.bookstore.scopebean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@ConversationScoped
public class ConversationScopeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversation;

	private int counter;

	public void begin() {
		if (conversation.isTransient())
			conversation.begin();
	}

	public int increment() {
		if (!conversation.isTransient())
			counter++;
		return counter;
	}

	public void end() {
		if (!conversation.isTransient())
			conversation.end();
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
