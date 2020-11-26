package com.tengfei.fairy.eventbus;

/**
 * @ Description :自定义事件，程序内部使用该对象作为通信的信息
 * @ Author 李腾飞
 * @ Time 2020-11-24   11:20
 * @ Version :
 */
public class MessageEvent {
    private String message;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    private String messageType;

    public MessageEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
