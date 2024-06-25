/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import com.sun.tools.javac.Main;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
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
import swing.WrapLayout;


public class Panel_More extends javax.swing.JPanel {

  
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
        ch.setVerticalScrollBar(new JScrollBar());
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
        cmd.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/emoji/icon/1.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        
        return cmd;
    }
    
    private JButton getEmojiStyle2(){
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/emoji/icon/8.png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        
        return cmd;
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
    
    private JPanel panelHeader;
    private JPanel panelDetail;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
