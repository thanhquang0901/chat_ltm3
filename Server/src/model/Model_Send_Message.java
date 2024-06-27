/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Model_Send_Message {

    /**
     * @return the messageType
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the fromId
     */
    public int getFromId() {
        return fromId;
    }

    /**
     * @param fromId the fromId to set
     */
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    /**
     * @return the toId
     */
    public int getToId() {
        return toId;
    }

    /**
     * @param toId the toId to set
     */
    public void setToId(int toId) {
        this.toId = toId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public Model_Send_Message(int messageType, int fromId, int toId, String text) {
        this.messageType = messageType;
        this.fromId = fromId;
        this.toId = toId;
        this.text = text;
    }

    public Model_Send_Message() {
    }

    private int messageType;
    private int fromId;
    private int toId;
    private String text;

 
}
