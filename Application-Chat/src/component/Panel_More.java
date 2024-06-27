/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import app.MessageType;
import com.sun.tools.javac.Main;
import emoji.Emogi;
import emoji.Model_Emoji;
import event.PublicEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.Model_Send_Message;
import model.Model_User_Account;
import service.Service;
import swing.ScrollBar;
import swing.WrapLayout;


public class Panel_More extends javax.swing.JPanel {

    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    
    private Model_User_Account user;
  
    public Panel_More() {
        initComponents();
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx"));
        panelHeader=new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonFile());
        panelHeader.add(getEmojiStyle1());
        panelHeader.add(getEmojiStyle2());
        add(panelHeader,"w 100%, h 30!, wrap");
        panelDetail=new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new ScrollBar());
        panelDetail.setBackground(Color.yellow);
        add(ch,"w 100%, h 100%");
        
    }
    
    private JButton getButtonFile(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/icon/link.png")));
        cmd.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               JFileChooser ch = new JFileChooser();
               ch.showOpenDialog(Panel_More.this);
           }
        });
        return cmd;
    }
    
    private JButton getEmojiStyle1(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Emogi.getInstance().getImoji(21).toSize(25, 25).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for(Model_Emoji d:Emogi.getInstance().getStyle1()){
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }
    
    private JButton getEmojiStyle2(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Emogi.getInstance().getImoji(21).toSize(25, 25).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for(Model_Emoji d:Emogi.getInstance().getStyle2()){                 
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }
    
        private JButton getButton(Model_Emoji data){
            JButton cmd=new JButton(data.getIcon());
                    cmd.setName(data.getId() +"" );
                    cmd.setBorder(new EmptyBorder(3,3,3,3));
                    cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    cmd.setContentAreaFilled(false);
                    cmd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   Model_Send_Message message=new Model_Send_Message(MessageType.EMOJI, Service.getInstance().getUser().getId(), user.getId(), data.getId()+""); 
                    sendMassge(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                }
                    });
                    
                    return cmd;
            
        }
        
            private void sendMassge(Model_Send_Message data){
                Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
            }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 61, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearSelected(){
        for(Component c:panelHeader.getComponents()){
            if(c instanceof OptionButton ){
                ((OptionButton)c).setSelected(false);
            }
        }
    }
    private JPanel panelHeader;
    private JPanel panelDetail;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
