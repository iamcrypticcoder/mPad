/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.Font;
import java.awt.Frame;
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
public class FontDialog extends JDialog {

    JLabel label1 = new JLabel("Font:");
    JLabel label2 = new JLabel("Font Style:");
    JLabel label3 = new JLabel("Size:");
    JLabel sampleLabel = new JLabel("AaBbYyZz");

    JTextField fontTextBox = new JTextField();
    JTextField styleTextBox = new JTextField();
    JTextField sizeTextBox = new JTextField();


    JList fontListBox = new JList();
    JList styleListBox = new JList();
    JList sizeListBox = new JList();

	JButton buttonOk = new JButton("OK");
	JButton buttonCancel = new JButton("Cancel");

    Font retFont;

    public FontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        InitComponent();
    }


    private void InitComponent()
    {
        this.setLayout(null);

        setTitle("Font");

        label1.setBounds(10, 10, 100, 20);
        label2.setBounds(200, 10, 100, 20);
        label3.setBounds(300, 10, 100, 20);

		sampleLabel.setBounds(100, 200, 100, 20);

        fontTextBox.setBounds(10, 30, 180, 20);
		styleTextBox.setBounds(200, 30, 100, 20);
		sizeTextBox.setBounds(310, 30, 100, 20);


		fontListBox.setBounds(10, 55, 180, 100);
		styleListBox.setBounds(200, 55, 100, 100);
		sizeListBox.setBounds(310, 55, 100, 100);

		buttonOk.setBounds(420, 30, 70, 23);
		buttonCancel.setBounds(420, 55, 70, 23);

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(sampleLabel);

        this.add(fontTextBox);
        this.add(styleTextBox);
   		this.add(sizeTextBox);

        this.add(fontListBox);
        this.add(styleListBox);
        this.add(sizeListBox);

		this.add(buttonOk);
		this.add(buttonCancel);

        this.setSize(510, 350);

    }


 /*
    public static void main(String args[])
    {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

    	new FontDialog(new JFrame(), true).setVisible(true);
    }
*/
}

