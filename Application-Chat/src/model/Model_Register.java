/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class Model_Register {

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Model_Register() {
    }

    public Model_Register(String fullname, String email, String username, String password) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    private String fullname;
    private String email;
    private String username;
    private String password;


    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fullname", fullname);
            json.put("email", email);
            json.put("username", username);
            json.put("password", password);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
}
