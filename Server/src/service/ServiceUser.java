/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Connection.DataBaseConnection;
import java.sql.Connection;
import model.Model_Message;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Model_Client;
import model.Model_Login;
import model.Model_Register;
import model.Model_User_Account;

/**
 *
 * @author ADMIN
 */
public class ServiceUser {

    public ServiceUser() {
        this.conn = DataBaseConnection.getInstance().getConnection();
    }

    public Model_Message register(Model_Register data) {
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = conn.prepareStatement(CHECK_USER);
            p.setString(1, data.getFullname());
            ResultSet r = p.executeQuery();
            if (r.next()) {
                message.setAction(false);
                message.setMessage("User Already Exist");
            } else {
                message.setAction(true);
            }
            r.close();
            p.close();

            if (message.isAction()) {
                //Insert user register
                conn.setAutoCommit(false);
                p = conn.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getFullname());
                p.setString(2, data.getEmail());
                p.setString(3, data.getUsername());
                p.setString(4, data.getPassword());
                p.execute();
                r = p.getGeneratedKeys();
                r.next();
                int id = r.getInt(1);
                r.close();
                p.close();

                //create user account
                p = conn.prepareStatement(INSERT_USER_ACCOUNT);
                p.setInt(1, id);
                p.setString(2, data.getUsername());
                p.execute();
                p.close();
                conn.commit();
                conn.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(new Model_User_Account(id, data.getUsername(), "", "", true));
            }
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            e.printStackTrace();
            try {
                if(conn.getAutoCommit()==false) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e1) {
            }
        }

        return message;
    }
    
    public Model_User_Account login(Model_Login login) throws SQLException {
        Model_User_Account data = null;
        PreparedStatement p = conn.prepareStatement(LOGIN);
        p.setString(1, login.getUsername());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if(r.next()) {
            int id = r.getInt(1);
            String username = r.getString(2);
            String gender = r.getString(3);
            String status = r.getString(4);
            data = new Model_User_Account(id, username, gender, status, true);
        }
        r.close();
        p.close();
        return data;
    }
    
    public List<Model_User_Account> getUser(int exitUser) throws SQLException {
        List<Model_User_Account> list = new ArrayList<>();
        PreparedStatement p = conn.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while(r.next()) {
            int id = r.getInt(1);
            String username = r.getString(2);
            String gender = r.getString(3);
            String image = r.getString(4);
            list.add(new Model_User_Account(id, username, gender, image, checkUserStatus(id)));
        }
        r.close();
        p.close();
        return list;
    }
    
    private boolean checkUserStatus(int id) {
        List<Model_Client> clients = Service.getInstance(null).getListClient();
        for (Model_Client c : clients) {
            if(c.getUser().getId() == id) {
                return true;
            }
        }
        return false;
    }

    private final String LOGIN = "select id, user_account.username, gender, imagestring from `user` join user_account using (id) where `user`.username=BINARY(?) and `user`.`password`=BINARY(?) and user_account.`status`='1'";
    private final String SELECT_USER_ACCOUNT = "select id, username, gender, imagestring from user_account where user_account.`status` = '1' and id<>?";
    private final String INSERT_USER = "insert into user (fullname, email, username, `password`) values (?,?,?,?)";
    private final String INSERT_USER_ACCOUNT = "insert into user_account (id, username) values (?,?)";
    private final String CHECK_USER = "select id from user where fullname = ?";
    private final Connection conn;
}
