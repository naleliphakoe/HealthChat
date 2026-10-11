
package com.example.healthchat;

public class Message {

    private String sender;
    private String text;
    private String time;
    private boolean fromUser;

    public Message(String sender, String text, String time,
                   boolean fromUser) {
        this.sender = sender;
        this.text = text;
        this.time = time;
        this.fromUser = fromUser;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public boolean isFromUser() {
        return fromUser;
    }
}
