/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package event;

import java.util.List;
import model.Model_User_Account;

/**
 *
 * @author ADMIN
 */
public interface EventMenuLeft {

    public void newUser(List<Model_User_Account> users);

    public void userConnect(int id);

    public void userDisconnect(int id);
}
