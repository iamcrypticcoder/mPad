/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReferenceWizardPanel.java
 *
 * Created on Aug 3, 2010, 11:31:18 PM
 */

package my;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author MAHBUB
 */


public class ReferenceWizardPanel extends JPanel implements ItemListener {

    ReferencePacket currentRefPacket = new ReferencePacket();
    File currentRefFile;


    Object columnNames[] = { "No.", "Type", "Description"};
    Object rowData[][];
    DefaultTableModel model;

    JFrame parent;
    ButtonGroup radioGroup = new ButtonGroup();


    public ReferenceWizardPanel(JFrame parent) {
        initComponents();
        this.parent = parent;
        
        InitReferenceJTable();
    }


    public void InitReferenceJTable()
    {
        int i;

        model = new DefaultTableModel();
        for(i=0; i<3; i++)
            model.addColumn(columnNames[i]);

        referenceJTable.setModel(model);
        referenceJTable.getColumnModel().getColumn(0).setWidth(5);
    }

    public void UpdateReferenceJTable()
    {
        Reference tempRef = new Reference();
        Iterator < Reference > iterator = currentRefPacket.refList.iterator();
        int count = 0;

        rowData = new Object[currentRefPacket.refList.size()][3];
        while(iterator.hasNext()) {
            tempRef = iterator.next();
            rowData[count][0] = String.valueOf(count+1);
            rowData[count][1] = tempRef.ReferenceType();
            rowData[count][2] = tempRef.toString();
            count++;
        }
        TableModel temp = new DefaultTableModel(rowData, columnNames);

        referenceJTable.setModel(temp);
    }


    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == singleJRadio)
            referenceJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        else if(e.getSource() == multipleJRadio)
            referenceJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addBookJButton = new javax.swing.JButton();
        addJournalJButton = new javax.swing.JButton();
        addConferenceJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        referenceJTable = new javax.swing.JTable();
        clearAllJButton = new javax.swing.JButton();
        deleteJButton = new javax.swing.JButton();
        newRefPacketButton = new javax.swing.JButton();
        openRefPacketButton = new javax.swing.JButton();
        saveJButton = new javax.swing.JButton();
        saveAsJButton = new javax.swing.JButton();
        multipleJRadio = new javax.swing.JRadioButton();
        singleJRadio = new javax.swing.JRadioButton();

        addBookJButton.setText("Add Book");
        addBookJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookJButtonActionPerformed(evt);
            }
        });

        addJournalJButton.setText("Add Journal");
        addJournalJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJournalJButtonActionPerformed(evt);
            }
        });

        addConferenceJButton.setText("Add Conference");
        addConferenceJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConferenceJButtonActionPerformed(evt);
            }
        });

        referenceJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(referenceJTable);

        clearAllJButton.setText("Clear All");
        clearAllJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllJButtonActionPerformed(evt);
            }
        });

        deleteJButton.setText("Delete");
        deleteJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJButtonActionPerformed(evt);
            }
        });

        newRefPacketButton.setText("New Reference Packet");
        newRefPacketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRefPacketButtonActionPerformed(evt);
            }
        });

        openRefPacketButton.setText("Open Reference Packet");
        openRefPacketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRefPacketButtonActionPerformed(evt);
            }
        });

        saveJButton.setText("Save");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });

        saveAsJButton.setText("Save As");
        saveAsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsJButtonActionPerformed(evt);
            }
        });

        multipleJRadio.setText("Multiple Selection");
        radioGroup.add(multipleJRadio);
        multipleJRadio.addItemListener(this);

        singleJRadio.setText("Single Selection");
        radioGroup.add(singleJRadio);
        singleJRadio.addItemListener(this);
        singleJRadio.setSelected(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBookJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addJournalJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addConferenceJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addComponent(singleJRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multipleJRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearAllJButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newRefPacketButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openRefPacketButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
                        .addComponent(saveAsJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBookJButton)
                    .addComponent(addJournalJButton)
                    .addComponent(addConferenceJButton)
                    .addComponent(clearAllJButton)
                    .addComponent(deleteJButton)
                    .addComponent(multipleJRadio)
                    .addComponent(singleJRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newRefPacketButton)
                    .addComponent(openRefPacketButton)
                    .addComponent(saveJButton)
                    .addComponent(saveAsJButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBookJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookJButtonActionPerformed
        AddBookJDialog dia = new AddBookJDialog(parent, true);
        dia.setVisible(true);
        Book book = dia.getBook();

        if(book != null) {
            currentRefPacket.AddReference(new Reference(book));
            System.out.println(book.toString());
            UpdateReferenceJTable();
        }
        else
            System.out.println("Null is returned");
        
    }//GEN-LAST:event_addBookJButtonActionPerformed

    private void addJournalJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJournalJButtonActionPerformed
        AddJournalJDialog dia = new AddJournalJDialog(parent, true);
        dia.setVisible(true);
        Journal jour = dia.getJournal();

        if(jour != null) {
            currentRefPacket.AddReference(new Reference(jour));
            System.out.println(jour.toString());
            UpdateReferenceJTable();
        }
        else
            System.out.println("Null is returned");
    }//GEN-LAST:event_addJournalJButtonActionPerformed

    private void addConferenceJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addConferenceJButtonActionPerformed
        AddConfJDialog dia = new AddConfJDialog(parent, true);
        dia.setVisible(true);
        Conference conf = dia.getConference();

        if(conf != null) {
            currentRefPacket.AddReference(new Reference(conf));
            System.out.println(conf.toString());
            UpdateReferenceJTable();
        }
        else
            System.out.println("Null is returned");

        System.out.println(conf.toString());
    }//GEN-LAST:event_addConferenceJButtonActionPerformed

    private void deleteJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteJButtonActionPerformed
         int index = referenceJTable.getSelectedRow();

         currentRefPacket.refList.remove(index);
         UpdateReferenceJTable();
    }//GEN-LAST:event_deleteJButtonActionPerformed

    private void clearAllJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllJButtonActionPerformed
         currentRefPacket.refList.clear();
         UpdateReferenceJTable();
    }//GEN-LAST:event_clearAllJButtonActionPerformed

    private void newRefPacketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRefPacketButtonActionPerformed
         currentRefFile = null;
         currentRefPacket.refList.clear();
         UpdateReferenceJTable();
    }//GEN-LAST:event_newRefPacketButtonActionPerformed

    public void SaveReference(File file)
    {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file.getPath()));
            output.writeObject(currentRefPacket);
            output.flush();
            output.close();
        }
        catch ( IOException ioException )
        {
            System.out.println("IOException occured");
        }
        System.out.println("File Successfully Saved\n");
    }


    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed
        if(currentRefFile == null)
            saveAsJButtonActionPerformed(evt);
        else
            SaveReference(currentRefFile);
            
    }//GEN-LAST:event_saveJButtonActionPerformed

    private void saveAsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsJButtonActionPerformed
        JFileChooser fileChooser;
        fileChooser = new JFileChooser();

        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        if(currentRefFile == null) {
            fileChooser.setDialogTitle("Save");
        } else fileChooser.setDialogTitle("Save As");

        fileChooser.resetChoosableFileFilters();
        //    fileChooser.addChoosableFileFilter(new )

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showSaveDialog(this);
        fileChooser.setOpaque(false);

        currentRefFile = new File("");
        currentRefFile = fileChooser.getSelectedFile();
        if(currentRefFile != null) {
            System.out.println(currentRefFile.getPath());
            SaveReference(currentRefFile);
        }
    }//GEN-LAST:event_saveAsJButtonActionPerformed

    private void openRefPacketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRefPacketButtonActionPerformed
        JFileChooser fileChooser;
        fileChooser = new JFileChooser();

        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);

        currentRefFile = new File("");
        currentRefFile = fileChooser.getSelectedFile();
        System.out.println(currentRefFile.getPath());

        OpenReference(currentRefFile);
        UpdateReferenceJTable();
    }//GEN-LAST:event_openRefPacketButtonActionPerformed

    public void OpenReference(File file)
    {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getPath()));
            currentRefPacket = ( ReferencePacket ) input.readObject();
            input.close();
        }
        catch ( IOException ioException )
        {
            System.out.println("IOException occured");
        }
        catch( ClassNotFoundException classNotFoundException)
        {
            System.out.println("ClassNotFoundException occured");
        }
        System.out.println("File Successfully Opened\n");      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookJButton;
    private javax.swing.JButton addConferenceJButton;
    private javax.swing.JButton addJournalJButton;
    private javax.swing.JButton clearAllJButton;
    private javax.swing.JButton deleteJButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton multipleJRadio;
    private javax.swing.JButton newRefPacketButton;
    private javax.swing.JButton openRefPacketButton;
    private javax.swing.JTable referenceJTable;
    private javax.swing.JButton saveAsJButton;
    private javax.swing.JButton saveJButton;
    private javax.swing.JRadioButton singleJRadio;
    // End of variables declaration//GEN-END:variables


    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame();
        frame.add(new ReferenceWizardPanel(frame));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
