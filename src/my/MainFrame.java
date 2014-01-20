/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Jul 16, 2010, 11:23:24 PM
 */

package my;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 *
 * @author MAHBUB
 */
public class MainFrame extends javax.swing.JFrame implements ActionListener {

    MainPanel mainPanel;
    UndoableTextArea textArea;

    Dimension dim;
    int frameWidth, frameHeight;

    MenuBar menuBar;
    Menu fileMenu, editMenu, formatMenu, viewMenu, referenceMenu, helpMenu;
    Menu addReferenceMenu;
    Menu exportMenu;
    MenuItem exportDocxFile;
    MenuItem newItem, openItem, saveItem, saveAsItem, exitItem;
    MenuItem undoItem, redoItem, cutItem, copyItem, pasteItem, deleteItem, findItem, findNextItem,
              replaceItem, selectAllItem, timeAndDateItem;
    MenuItem fontItem;
    MenuItem addReferenceItem, createReferenceItem, currentReferenceItem, viewReferenceItem, fixReferenceItem,
             referenceWizardItem;
    MenuItem addBookItem, addJournalItem, addConferenceItem, addFromExisting;


    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();


        textArea = new UndoableTextArea();

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frameWidth = dim.width - 200;
        frameHeight = dim.height - 60;

        this.setBounds((dim.width - frameWidth)/2, (dim.height - frameHeight)/2 - 20, frameWidth, frameHeight);
        mainPanel = new MainPanel(this);
        mainPanel.setSize(500, 400);
        menuBar = new MenuBar();

// --------------------------------------File Menu -----------------------------
        fileMenu = new Menu("File");

        newItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_N));
        openItem = new MenuItem("Open", new MenuShortcut(KeyEvent.VK_O));
        saveItem = new MenuItem("Save", new MenuShortcut(KeyEvent.VK_N));
        saveAsItem = new MenuItem("SaveAs", new MenuShortcut(KeyEvent.VK_N));
        exitItem = new MenuItem("Exit");

        exportMenu = new Menu("Export");

        exportDocxFile = new MenuItem("DOCX File");
        exportMenu.add(exportDocxFile);

        exportDocxFile.addActionListener(this);
        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        openItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exportMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

// ------------------------------------- Edit Menu -----------------------------
        editMenu = new Menu("Edit");

        undoItem = new MenuItem("Undo", new MenuShortcut(KeyEvent.VK_Z));
        redoItem = new MenuItem("Redo", new MenuShortcut(KeyEvent.VK_Y));
        cutItem = new MenuItem("Cut", new MenuShortcut(KeyEvent.VK_X));
        copyItem = new MenuItem("Copy", new MenuShortcut(KeyEvent.VK_C));
        pasteItem = new MenuItem("Paste", new MenuShortcut(KeyEvent.VK_V));
        deleteItem = new MenuItem("Delete");
        findItem = new MenuItem("Find");
        findNextItem = new MenuItem("Find Next");
        replaceItem = new MenuItem("Replace");
        selectAllItem = new MenuItem("Select All", new MenuShortcut(KeyEvent.VK_A));
        timeAndDateItem = new MenuItem("Time/Date");


        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        replaceItem.addActionListener(this);
        

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.addSeparator();
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(deleteItem);
        editMenu.addSeparator();
        editMenu.add(findItem);
        editMenu.add(findNextItem);
        editMenu.add(replaceItem);
        editMenu.addSeparator();
        editMenu.add(selectAllItem);
        editMenu.add(timeAndDateItem);

        menuBar.add(editMenu);

// ------------------------------------ Format Menu ----------------------------
        formatMenu = new Menu("Format");

        fontItem = new MenuItem("Font");
        formatMenu.add(fontItem);

        menuBar.add(formatMenu);


// ------------------------------------ Reference Menu -------------------------

        referenceMenu = new Menu("Reference");

        addReferenceMenu = new Menu("Add Reference");
            addBookItem = new MenuItem("Add Book");
            addJournalItem = new MenuItem("Add Journal");
            addConferenceItem = new MenuItem("Add Conference");
            addFromExisting = new MenuItem("Add From Existing Reference");

            addReferenceMenu.add(addBookItem);
            addReferenceMenu.add(addJournalItem);
            addReferenceMenu.add(addConferenceItem);
            addReferenceMenu.add(addFromExisting);

        createReferenceItem = new MenuItem("Create Reference");
        currentReferenceItem = new MenuItem("Current Reference");
        viewReferenceItem = new MenuItem("View Reference");
        fixReferenceItem = new MenuItem("Fix Reference", new MenuShortcut(KeyEvent.VK_F));
        referenceWizardItem = new MenuItem("Reference Wizard");
        
        addBookItem.addActionListener(this);
        addJournalItem.addActionListener(this);
        addConferenceItem.addActionListener(this);
        addFromExisting.addActionListener(this);
        fixReferenceItem.addActionListener(this);
        referenceWizardItem.addActionListener(this);

        referenceMenu.add(addReferenceMenu);
//        referenceMenu.add(createReferenceItem);
//        referenceMenu.add(currentReferenceItem);
//        referenceMenu.add(viewReferenceItem);
        referenceMenu.add(fixReferenceItem);
        referenceMenu.add(referenceWizardItem);

        menuBar.add(referenceMenu);

// -----------------------------------------------------------------------------

        setMenuBar(menuBar);
        setLayout(new GridLayout(1, 1));
        add(mainPanel);
        
        this.setVisible( true );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent evt)
    {
        JWindow window;
        JPanel panel;


        if(evt.getSource() == saveItem) 
            mainPanel.SaveAction();

        else if(evt.getSource() == openItem) 
            mainPanel.OpenAction();

        else if(evt.getSource() == exportDocxFile)
            mainPanel.ExportDocxAction();
        
        else if(evt.getSource() == exitItem) {
            System.exit(0);
        }

// ---------------------------------------------- Edit Menu---------------------
        else if(evt.getSource() == undoItem) {
            mainPanel.UndoAction();
        }
        else if(evt.getSource() == redoItem) {
            mainPanel.RedoAction();
        }
        else if(evt.getSource() == cutItem) {
            mainPanel.CutAction();
        }
        else if(evt.getSource() == copyItem) {
            mainPanel.CopyAction();
        }
        else if(evt.getSource() == pasteItem) {
            mainPanel.PasteAction();
        }
        else if(evt.getSource() == replaceItem) {
            mainPanel.ReplaceAction();
        }

        else if(evt.getSource() == addBookItem)
            mainPanel.AddBookAction();
        else if(evt.getSource() == addJournalItem)
            mainPanel.AddJournalAction();
        else if(evt.getSource() == addConferenceItem)
            mainPanel.AddConferenceAction();
        else if(evt.getSource() == addFromExisting)
            mainPanel.addFromExistingJDialog.setVisible(true);
        else if(evt.getSource() == fixReferenceItem)
            mainPanel.FixReferenceAction();
        else if(evt.getSource() == referenceWizardItem) 
            mainPanel.ShowReferenceWizard();
        
        else if(evt.getSource() == addReferenceItem) {
     //       mainPanel.referenceJDialog.setVisible(true);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        referenceJDialog = new javax.swing.JDialog();
        saveJButton = new javax.swing.JButton();
        openJButton = new javax.swing.JButton();
        saveAsJButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        addBookJButton = new javax.swing.JButton();
        deleteBookJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        referenceJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        referenceJList = new javax.swing.JList();
        newJButton = new javax.swing.JButton();
        attachReferenceJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addNewBookJDialog = new javax.swing.JDialog();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        totalPageJTextField = new javax.swing.JTextField();
        yearJTextField = new javax.swing.JTextField();
        publisherJTextField = new javax.swing.JTextField();
        placePubJTextField = new javax.swing.JTextField();
        volumeJTextField = new javax.swing.JTextField();
        editionTextField = new javax.swing.JTextField();
        titleJTextField = new javax.swing.JTextField();
        authorJTextFeild = new javax.swing.JTextField();
        cancelJButton = new javax.swing.JButton();
        addJButton = new javax.swing.JButton();

        saveJButton.setText("Save");

        openJButton.setText("Open Reference");

        saveAsJButton.setText("Save As");

        clearButton.setText("Clear All");

        addBookJButton.setText("Add New Book");

        deleteBookJButton.setText("Delete Book");

        jScrollPane1.setViewportView(referenceJTable);

        jScrollPane2.setViewportView(referenceJList);

        newJButton.setText("New Reference");

        attachReferenceJButton.setText("Attach");

        jLabel1.setText("Currents Books in your List :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Book's Title :");

        javax.swing.GroupLayout referenceJDialogLayout = new javax.swing.GroupLayout(referenceJDialog.getContentPane());
        referenceJDialog.getContentPane().setLayout(referenceJDialogLayout);
        referenceJDialogLayout.setHorizontalGroup(
            referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(referenceJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(referenceJDialogLayout.createSequentialGroup()
                        .addComponent(addBookJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBookJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referenceJDialogLayout.createSequentialGroup()
                        .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addGroup(referenceJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 625, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referenceJDialogLayout.createSequentialGroup()
                                .addComponent(newJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(openJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                                .addComponent(attachReferenceJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveAsJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saveJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        referenceJDialogLayout.setVerticalGroup(
            referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
            .addGroup(referenceJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBookJButton)
                    .addComponent(deleteBookJButton)
                    .addComponent(clearButton))
                .addGap(15, 15, 15)
                .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                    .addGroup(referenceJDialogLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(referenceJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openJButton)
                    .addComponent(newJButton)
                    .addComponent(saveJButton)
                    .addComponent(saveAsJButton)
                    .addComponent(attachReferenceJButton))
                .addContainerGap())
        );

        jLabel21.setText("Author :");

        jLabel22.setText("Title :");

        jLabel23.setText("Edition :");

        jLabel24.setText("Volume :");

        jLabel25.setText("Place of Publication :");

        jLabel26.setText("Publisher :");

        jLabel27.setText("Year :");

        jLabel28.setText("Total Page :");

        cancelJButton.setText("Cancel");

        addJButton.setText("Add");

        javax.swing.GroupLayout addNewBookJDialogLayout = new javax.swing.GroupLayout(addNewBookJDialog.getContentPane());
        addNewBookJDialog.getContentPane().setLayout(addNewBookJDialogLayout);
        addNewBookJDialogLayout.setHorizontalGroup(
            addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(addNewBookJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalPageJTextField)
                    .addComponent(yearJTextField)
                    .addComponent(publisherJTextField)
                    .addComponent(placePubJTextField)
                    .addComponent(volumeJTextField)
                    .addComponent(editionTextField)
                    .addComponent(titleJTextField)
                    .addComponent(authorJTextFeild, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewBookJDialogLayout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(cancelJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        addNewBookJDialogLayout.setVerticalGroup(
            addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
            .addGap(0, 308, Short.MAX_VALUE)
            .addGroup(addNewBookJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(authorJTextFeild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(titleJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(editionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(volumeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(placePubJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(publisherJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(yearJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(totalPageJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(addNewBookJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelJButton)
                    .addComponent(addJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
  */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookJButton;
    private javax.swing.JButton addJButton;
    private javax.swing.JDialog addNewBookJDialog;
    private javax.swing.JButton attachReferenceJButton;
    private javax.swing.JTextField authorJTextFeild;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteBookJButton;
    private javax.swing.JTextField editionTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton newJButton;
    private javax.swing.JButton openJButton;
    private javax.swing.JTextField placePubJTextField;
    private javax.swing.JTextField publisherJTextField;
    private javax.swing.JDialog referenceJDialog;
    private javax.swing.JList referenceJList;
    private javax.swing.JTable referenceJTable;
    private javax.swing.JButton saveAsJButton;
    private javax.swing.JButton saveJButton;
    private javax.swing.JTextField titleJTextField;
    private javax.swing.JTextField totalPageJTextField;
    private javax.swing.JTextField volumeJTextField;
    private javax.swing.JTextField yearJTextField;
    // End of variables declaration//GEN-END:variables

}
