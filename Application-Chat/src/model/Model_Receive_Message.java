/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import app.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class Model_Receive_Message {

    /**
     * @return the messageType
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(MessageType messageType) {
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

    public Model_Receive_Message(MessageType messageType, int fromId, String text) {
        this.messageType = messageType;
        this.fromId = fromId;
        this.text = text;
    }
    
    public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            messageType=MessageType.toMessageType(obj.getInt("messageType"));
            fromId = obj.getInt("fromId");
            text = obj.getString("text");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
    private MessageType messageType;
    private int fromId;
    private String text;
    
    public JSONObject toJsonObject() {
        try {
            
            JSONObject json = new JSONObject();
            json.put("messageType", messageType.getValue());
            json.put("fromId", getFromId());
            json.put("text", getText());
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
