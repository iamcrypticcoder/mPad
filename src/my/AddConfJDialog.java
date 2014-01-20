/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;


/**
 *
 * @author Mahbub
 */
public class AddConfJDialog extends JDialog implements ActionListener {

    JLabel label0 = new JLabel("Conference Speaker: ");
    JLabel label1 = new JLabel("Conference Name: ");
    JLabel label2 = new JLabel("Conference Topic : ");
    JLabel label3 = new JLabel("place : ");
    JLabel label4 = new JLabel("date : ");

    JTextField confSpeakerJTextBox = new JTextField();
    JTextField confNameJTextBox = new JTextField();
    JTextField confTopicJTextBox = new JTextField();
    JTextField placeJTextBox = new JTextField();
    JTextField dateJTextBox = new JTextField();

	JButton buttonOk = new JButton("OK");
	JButton buttonCancel = new JButton("Cancel");

    Conference conf;

    public AddConfJDialog(Frame owner, boolean modal) {
        super(owner, modal);
        InitComponent();
    }

    private void InitComponent()
    {
        setTitle("New Conference");

        this.setLayout(new GridLayout(6,2));
        add(label0);
        add(confSpeakerJTextBox);
        add(label1);
        add(confNameJTextBox);
        add(label2);
        add(confTopicJTextBox);
        add(label3);
        add(placeJTextBox);
        add(label4);
        add(dateJTextBox);

		this.add(buttonOk);
		this.add(buttonCancel);

        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);

        this.setSize(400, 300);
    }

    public Conference getConference()
    {
        return conf;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == buttonOk) {
            conf = new Conference(  confSpeakerJTextBox.getText(), confNameJTextBox.getText(),
                                    confTopicJTextBox.getText(), placeJTextBox.getText(),
                                    dateJTextBox.getText() );
            System.out.println(conf.toString());
            this.dispose();
        }

        else if(evt.getSource() == buttonCancel) {
            conf = null;
            this.dispose();
        }

    }


    public static void main(String args[])
    {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    	new AddConfJDialog(new JFrame(), true).setVisible(true);
    }
}
