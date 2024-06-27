/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import model.Model_Message;
import model.Model_Register;
import java.util.List;
import model.Model_Client;
import model.Model_Login;
import model.Model_Receive_Message;
import model.Model_Send_Message;
import model.Model_User_Account;

/**
 *
 * @author ADMIN
 */
public class Service {

    private static Service instance;
    private SocketIOServer server;
    private ServiceUser serviceUser;
    private List<Model_Client> listClient;
    private JTextArea textArea;
    private final int PORT_NUMBER = 9999;

    public static Service getInstance(JTextArea textArea) {
        if (instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }

    private Service(JTextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser();
        listClient = new ArrayList<>();
    }

    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient sioc) {
                textArea.append("One client connected\n");
            }

        });
        server.addEventListener("register", Model_Register.class, new DataListener<Model_Register>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Register t, AckRequest ar) throws Exception {
                Model_Message message = serviceUser.register(t);
                ar.sendAckData(message.isAction(), message.getMessage(), message.getData());
                if (message.isAction()) {
                    textArea.append("User has register : " + t.getFullname() + " | Email : " + t.getEmail() + " | Username : " + t.getUsername() + " | Password : " + t.getPassword() + "\n");
                    server.getBroadcastOperations().sendEvent("list_user", (Model_User_Account) message.getData());
                    addClient(sioc, (Model_User_Account) message.getData());
                }
            }

        });
        server.addEventListener("login", Model_Login.class, new DataListener<Model_Login>() {
            @Override
            public void onData(SocketIOClient sioc, Model_Login t, AckRequest ar) throws Exception {
                Model_User_Account login = serviceUser.login(t);
                if (login != null) {
                    ar.sendAckData(true, login);
                    addClient(sioc, login);
                    userConnect(login.getId());
                } else {
                    ar.sendAckData(false);
                }
            }
        });
        server.addEventListener("list_user", Integer.class, new DataListener<Integer>() {
            @Override
            public void onData(SocketIOClient sioc, Integer id, AckRequest ar) throws Exception {
                try {
                    List<Model_User_Account> list = serviceUser.getUser(id);
                    sioc.sendEvent("list_user", list.toArray());
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        server.addEventListener("send_to_user", Model_Send_Message.class, new DataListener<Model_Send_Message>(){
            @Override
            public void onData(SocketIOClient sioc, Model_Send_Message t , AckRequest ar){
                sendToClient(t);
            }
        });
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient sioc) {
                int id = removeClient(sioc);
                if(id!=0) {
                    userDisconnect(id);
                }
            }
            
        });
        server.start();
        textArea.append("Server has Start on port : " + PORT_NUMBER + "\n");
    }
    
    private void userConnect(int id) {
        server.getBroadcastOperations().sendEvent("user_status", id, true);
    }
    
    private void userDisconnect(int id) {
        server.getBroadcastOperations().sendEvent("user_status", id, false);
    }

    private void addClient(SocketIOClient client, Model_User_Account user) {
        getListClient().add(new Model_Client(client, user));
    }
    
    private void sendToClient(Model_Send_Message data){
        for(Model_Client c : listClient){
            if(c.getUser().getId() == data.getToId()){
               c.getClient().sendEvent("receive_ms", new Model_Receive_Message(data.getMessageType(), data.getFromId(), data.getText()));
            }
        }
    }
    public int removeClient(SocketIOClient client) {
        for(Model_Client d:listClient) {
            if(d.getClient()==client) {
                listClient.remove(d);
                return d.getUser().getId();
            }
        }
        return 0;
    }

    public List<Model_Client> getListClient() {
        return listClient;
    }
}
