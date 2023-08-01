package com.example.chattingfirebase.chatlist;

public class ChatRoom {
    private String lastMessage;
    private String otherUserName;
    private String otherUserId;

    private String chatRoomId;

    public ChatRoom() {
    }

    public ChatRoom(String name, String description) {
        this.lastMessage = name;
        this.otherUserName = description;
    }

    public ChatRoom(String name, String description, String UID) {
        this.lastMessage = name;
        this.otherUserName = description;
        this.chatRoomId = UID;
    }

    public ChatRoom(String lastMessage, String otherUserName, String otherUserId, String chatRoomId) {
        this.lastMessage = lastMessage;
        this.otherUserName = otherUserName;
        this.otherUserId = otherUserId;
        this.chatRoomId = chatRoomId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }
}

