/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

/**
 *
 * @author ADMIN
 */
public class PublicEvent {

    private static PublicEvent instance;
    private EventImageView eventImageView;
    private EventMain eventMain;
    private EventChat eventChat;
    private EventLogin eventLogin;
    private EventMenuLeft eventMenuLeft;
    
    public static PublicEvent getInstance() {
        if(instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }
    
    private PublicEvent() {
        
    }    
    
    public void AddEventMain(EventMain event) {
        this.eventMain = event;
    }
    
    public void AddEventChat(EventChat event){
        this.eventChat = event;
    }
    
    public void AddEventImageView(EventImageView event) {
        this.eventImageView = event;
    }
    
    public void AddEventLogin(EventLogin event) {
        this.eventLogin = event;
    }
    
    public void AddEventMenuLeft(EventMenuLeft event) {
        this.eventMenuLeft = event;
    }
    
    public EventChat getEventChat() {
        return eventChat;
    }
    
    public EventImageView getEventImageView() {
        return eventImageView;
    }
    
    public EventLogin getEventLogin() {
        return eventLogin;
    }
    
    public EventMain getEventMain() {
        return eventMain;
    }
    
    public EventMenuLeft getEventMenuLeft() {
        return eventMenuLeft;
    }
}
