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
import javax.swing.UIManager;
import javax.swing.JTextField;


/**
 *
 * @author Mahbub
 */
public class AddBookJDialog extends JDialog implements ActionListener {

    JLabel label1 = new JLabel("Author Name: ");
    JLabel label2 = new JLabel("Book Title : ");
    JLabel label3 = new JLabel("Edition : ");
    JLabel label4 = new JLabel("Volume : ");
    JLabel label5 = new JLabel("Place of Publication: ");
    JLabel label6 = new JLabel("Publisher : ");
    JLabel label7 = new JLabel("Publication Year : ");
    JLabel label8 = new JLabel("Total Page : ");

    JTextField authorJTextBox = new JTextField();
    JTextField titleJTextBox = new JTextField();
    JTextField editionJTextBox = new JTextField();
    JTextField volumeJTextBox = new JTextField();
    JTextField placeOfPubJTextBox = new JTextField();
    JTextField publisherJTextBox = new JTextField();
    JTextField pubYearJTextBox = new JTextField();
    JTextField totalPageJTextBox = new JTextField();

	JButton buttonOk = new JButton("OK");
	JButton buttonCancel = new JButton("Cancel");

    Book book;

    public AddBookJDialog(Frame owner, boolean modal) {
        super(owner, modal);
        InitComponent();
    }

    private void InitComponent()
    {
        setTitle("New Book");

        this.setLayout(new GridLayout(9,2));
        add(label1);
        add(authorJTextBox);
        add(label2);
        add(titleJTextBox);
        add(label3);
        add(editionJTextBox);
        add(label4);
        add(volumeJTextBox);
        add(label5);
        add(placeOfPubJTextBox);
        add(label6);
        add(publisherJTextBox);
        add(label7);
        add(pubYearJTextBox);
        add(label8);
        add(totalPageJTextBox);

		this.add(buttonOk);
		this.add(buttonCancel);

        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);

        this.setSize(400, 300);

    }

    public Book getBook()
    {
        return book;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == buttonOk) {
            book = new Book(  authorJTextBox.getText(), titleJTextBox.getText(), editionJTextBox.getText(),
                                 volumeJTextBox.getText(), placeOfPubJTextBox.getText(), publisherJTextBox.getText(),
                                 pubYearJTextBox.getText(), totalPageJTextBox.getText() );
            System.out.println(book.toString());
            this.dispose();
        }
        else if(evt.getSource() == buttonCancel) {
            book = null;
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
    	new AddBookJDialog(new JFrame(), true).setVisible(true);
    }
}
