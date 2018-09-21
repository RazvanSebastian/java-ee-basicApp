package com.fortech.bookstore.chat;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import com.fortech.bookstore.util.annotation.Loggable;

@ServerEndpoint("/chat")
@Loggable(debug = false)
public class ChatEndpoint {

	@OnOpen
	public void onOpen(Session session) {
	}

	@OnMessage
	public void message(String message, Session client) throws IOException {
		for (Session peer : client.getOpenSessions()) {
			peer.getBasicRemote().sendText(message);
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "close"));
	}

}
