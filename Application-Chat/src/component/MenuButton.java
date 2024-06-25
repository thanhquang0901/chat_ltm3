/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.Icon;

/**
 *
 * @author ADMIN
 */


//Day la component cua thanh button o cua left menu
public class MenuButton extends JButton {

    /**
     * @return the iconSimple
     */
    public Icon getIconSimple() {
        return iconSimple;
    }

    /**
     * @param iconSimple the iconSimple to set
     */
    public void setIconSimple(Icon iconSimple) {
        this.iconSimple = iconSimple;
    }

    /**
     * @return the iconSelected
     */
    public Icon getIconSelected() {
        return iconSelected;
    }

    /**
     * @param iconSelected the iconSelected to set
     */
    public void setIconSelected(Icon iconSelected) {
        this.iconSelected = iconSelected;
    }
    
    private Icon iconSimple;
    private Icon iconSelected;
    
    
    public MenuButton() {
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        if(b){
            setIcon(iconSelected);
        }else{
            setIcon(iconSimple);
        }
    } 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()) {
            g.setColor(new Color(99, 152, 219));
            g.fillRect(0, getHeight() - 2, getWidth(), getHeight());
        }
    }
    
    
}
