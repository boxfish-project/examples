package com.lenicliu.jboot.websocket;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MinimunWebSocketHandler implements WebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(MinimunWebSocketHandler.class);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("connection established.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info("message:" + message.getPayload().toString());
		session.sendMessage(new TextMessage("I received a message[" + message.getPayload().toString() + "]. Server Time:" + new Date()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.error("websocket error", exception);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("connection closed.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}