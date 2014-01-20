/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mahbub
 */
public class ReplaceDialog extends JDialog {

    JLabel label1 = new JLabel("Find What :");
    JLabel label2 = new JLabel("Replace With :");

    JTextField findField = new JTextField();
    JTextField replaceField = new JTextField();

    JButton findNextButton = new JButton("Find Next");
    JButton replaceButton = new JButton("Replace");
    JButton replaceAllButton = new JButton("Replace All");
    JButton cancelButton = new JButton("Cancel");

    String findString, replaceString;

    public final static int FIND_NEXT_BUTTON = 1;
    public final static int REPLACE_BUTTON = 2;
    public final static int REPLACE_ALL_BUTTON = 3;

    boolean IS_CANCELLED = false;



    public ReplaceDialog(Frame owner, boolean modal) {
    super(owner, modal);
    InitComponent();
    }

    private void InitComponent() {
        this.setTitle("Replace");
        this.setLayout(null);

        label1.setBounds(10, 10, 80, 10);
        label2.setBounds(10, 40, 80, 10);
        findField.setBounds(100, 5, 180, 20);
        replaceField.setBounds(100, 35, 180, 20);

        findNextButton.setBounds(300, 5, 100, 25);
        replaceButton.setBounds(300, 35, 100, 25);
        replaceAllButton.setBounds(300, 65, 100, 25);
        cancelButton.setBounds(300, 95, 100, 25);

/*
        findNextButton.addActionListener(this);
        replaceButton.addActionListener(this);
        replaceAllButton.addActionListener(this);
        cancelButton.addActionListener(this);
*/


        this.add(label1);
        this.add(findField);
        this.add(label2);
        this.add(replaceField);
        this.add(findNextButton);
        this.add(replaceButton);
        this.add(replaceAllButton);
        this.add(cancelButton);
    }

    /*

    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == replaceButton) {
            findString = findField.getText();
            replaceString = replaceField.getText();
            this.setVisible(false);

        }
        else if(evt.getSource() == replaceAllButton) {
            findString = findField.getText();
            replaceString = replaceField.getText();
        }
        else if(evt.getSource() == replaceButton) {

        }


    }
     */

    public void Show()
    {
        this.setVisible(true);
    }

    public String getFindString()
    {
        return findString;
    }

    public String getReplaceString()
    {
        return replaceString;
    }



    public JButton getFindNextButton()
    {
        return this.findNextButton;
    }
    public JButton getReplaceButton()
    {
        return this.replaceButton;
    }

    public JButton getReplaceAllButton()
    {
        return this.replaceAllButton;
    }

    public JButton getCancelButton()
    {
        return this.cancelButton;
    }

}
