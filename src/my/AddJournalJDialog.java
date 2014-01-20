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
public class AddJournalJDialog extends JDialog implements ActionListener {

    JLabel label1 = new JLabel("Author Name: ");
    JLabel label2 = new JLabel("Journal Topic : ");
    JLabel label3 = new JLabel("Journal Name : ");
    JLabel label4 = new JLabel("Volume : ");
    JLabel label5 = new JLabel("Issue No : ");
    JLabel label6 = new JLabel("Page No: ");
    JLabel label7 = new JLabel("Publication Year : ");


    JTextField authorJTextBox = new JTextField();
    JTextField jtopicJTextBox = new JTextField();
    JTextField jnameJTextBox = new JTextField();
    JTextField volumeJTextBox = new JTextField();
    JTextField issueJTextBox = new JTextField();
    JTextField pageJTextBox = new JTextField();
    JTextField pubYearJTextBox = new JTextField();

	JButton buttonOk = new JButton("OK");
	JButton buttonCancel = new JButton("Cancel");


    Journal journal;


    public AddJournalJDialog(Frame owner, boolean modal) {
        super(owner, modal);
        InitComponent();
    }

    private void InitComponent()
    {
        setTitle("New Journal");

        this.setLayout(new GridLayout(8,2));
        add(label1);
        add(authorJTextBox);
        add(label2);
        add(jtopicJTextBox);
        add(label3);
        add(jnameJTextBox);
        add(label4);
        add(volumeJTextBox);
        add(label5);
        add(issueJTextBox);
        add(label6);
        add(pageJTextBox);
        add(label7);
        add(pubYearJTextBox);

		this.add(buttonOk);
		this.add(buttonCancel);

        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);

        this.setSize(400, 300);

    }

    public Journal getJournal()
    {
        return journal;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == buttonOk) {
            journal = new Journal(  authorJTextBox.getText(), jtopicJTextBox.getText(), jnameJTextBox.getText(),
                                    volumeJTextBox.getText(), issueJTextBox.getText(), pageJTextBox.getText(),
                                    pubYearJTextBox.getText() );
            System.out.println(journal.toString());
            this.dispose();
        }

        else if(evt.getSource() == buttonCancel) {
            journal = null;
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

    	new AddJournalJDialog(new JFrame(), true).setVisible(true);
    }

}
