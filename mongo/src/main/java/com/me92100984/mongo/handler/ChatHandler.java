package com.me92100984.mongo.handler;

import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ChatHandler extends TextWebSocketHandler {


  private CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.info(session.getId() + "사용자의 접속");
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    for(WebSocketSession ws : sessions) {
      ws.sendMessage(new TextMessage(message.getPayload()));
    }
  }
  
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    log.info(session.getId() + "사용자의 종료");

  }


}
