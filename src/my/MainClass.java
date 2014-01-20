/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import javax.swing.UIManager;

/**
 *
 * @author MAHBUB
 */
public class MainClass {

    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainFrame();
    }
}
