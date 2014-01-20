/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPanel.java
 *
 * Created on Jul 16, 2010, 2:20:25 PM
 */

package my;

import com.aspose.words.DocumentBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


import my.Book;
/**
 *
 * @author MAHBUB
 */
public class MainPanel extends JPanel implements ActionListener, ItemListener, KeyListener {

// MainPanel Fields are here

    ReferencePacket currentRefPacket = new ReferencePacket();
    File currentRefFile;

    Object columnNames[] = { "No.", "Type", "Description"};

    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3"},
                   { "Row2-Column1", "Row2-Column2", "Row2-Column3"} };

    DefaultTableModel model;
    int currentRefNo = 0;
    int totalRef = 0;


    UndoableTextArea textArea;

    JFrame parent;
    String currentTextFile;
    String windowTitle;

    Clipboard clip;

    ReplaceDialog replaceDialog;
    JButton findNextButton, replaceButton, replaceAllButton, cancelButton;
//    AutoReferenceRobot autoRefThread;

//  InitExistingReferenceJDialog variables
    ReferencePacket existRefPacket;
    ButtonGroup radioGroup = new ButtonGroup();

// -----------------------------------------------------------------------------


///    File databaseFile = new File("E:\\complete.ref");           // This is the database

    /** Creates new form MainPanel */
    public MainPanel(JFrame parent) {
        initComponents();
        this.parent = parent;
        setSize(parent.getWidth(), parent.getHeight());
        parent.setTitle("Untitled - mPad v1.0");
        clip = getToolkit().getSystemClipboard();

        model = new DefaultTableModel();

        for(int i = 0; i<columnNames.length; i++)
            model.addColumn(columnNames[i]);

        autoReferenceJTable.setModel(model);

        InitTextArea();
        InitAutoReferenceJTable();

 //       autoRefThread = new AutoReferenceRobot("AutoReferenceRobot");

    }


    public void InitExistingReferenceJDialog()
    {
    }

    private void InitTextArea()
    {
        textArea = new UndoableTextArea();
        textArea.setVisible(true);
        textArea.setSize(parent.getWidth()-10, 450);
        panel1.add(textArea);
        textArea.setFont(new Font("Calibri", Font.PLAIN, 20));
    }

    private void InitAutoReferenceJTable()
    {
        autoReferenceJTable.setFont(new Font("Calibri", Font.PLAIN, 14));
        
    }


    private void InitReplaceDialog()
    {
        replaceDialog = new ReplaceDialog(parent, false);
        replaceDialog.setSize(420, 200);

        findNextButton = replaceDialog.getFindNextButton();
        replaceButton = replaceDialog.getReplaceButton();
        replaceAllButton = replaceDialog.getReplaceAllButton();
        cancelButton = replaceDialog.getCancelButton();

        findNextButton.addActionListener(this);
        replaceButton.addActionListener(this);
        replaceAllButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void addBook()
    {
 //      addNewBookJDialog.setVisible(true);
    }

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

    public void OpenReference(File file)
    {
      
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getPath()));
            existRefPacket = ( ReferencePacket ) input.readObject();
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

/*
    public void UpdateList()
    {
        Iterator< Book > iterator = currentRef.mainList.iterator();
        Book tempBook = new Book();

        Vector < String > bookList = new Vector < String >();

        while(iterator.hasNext()) {
            tempBook = iterator.next();
            bookList.add(tempBook.bookName);
        }
        referenceJList.setListData(bookList);
    }
*/

    public void UpdateTable()
    {
/*
        Book tempBook = new Book();
        Iterator < Book > iterator = currentRef.mainList.iterator();
        int count = 0;

        rowData = new Object[currentRef.mainList.size()][8];
        while(iterator.hasNext()) {
            tempBook = iterator.next();
            rowData[count][0] = tempBook.authorName;
            rowData[count][1] = tempBook.bookName;
            rowData[count][2] = tempBook.edition;
            rowData[count][3] = tempBook.volume;
            rowData[count][4] = tempBook.placeOfPub;
            rowData[count][5] = tempBook.publisher;
            rowData[count][6] = tempBook.publicationYear;
            rowData[count][7] = tempBook.totalPage;
            count++;
        }
   //     System.out.println(rowData[0][0]);

        TableModel model = new DefaultTableModel(rowData, columnNames);

        referenceJTable.setModel(model);
 */
    }


    public void UpdateAutoReferenceJTable()
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

        autoReferenceJTable.setModel(temp);
    }

    public void UpdateExistingReferenceJTable()
    {
        Reference tempRef = new Reference();
        Iterator < Reference > iterator = existRefPacket.refList.iterator();
        int count = 0;

        rowData = new Object[existRefPacket.refList.size()][3];
        while(iterator.hasNext()) {
            tempRef = iterator.next();
            rowData[count][0] = String.valueOf(count+1);
            rowData[count][1] = tempRef.ReferenceType();
            rowData[count][2] = tempRef.toString();
            count++;
        }
        TableModel temp = new DefaultTableModel(rowData, columnNames);

        existingReferenceJTable.setModel(temp);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addFromExistingJDialog = new javax.swing.JDialog(parent, true);
        jScrollPane2 = new javax.swing.JScrollPane();
        existingReferenceJTable = new javax.swing.JTable();
        openRefJButton = new javax.swing.JButton();
        addRefJButton = new javax.swing.JButton();
        singleJRadio = new javax.swing.JRadioButton();
        multipleJRadio = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        autoReferenceJTable = new javax.swing.JTable();
        panel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        exportDocxJButton = new javax.swing.JButton();

        addFromExistingJDialog.setTitle("Add Reference From Existing File");
        addFromExistingJDialog.setSize(800, 450);

        existingReferenceJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] { "No.",
                "Type",
                "Description"

            }
        ));
        jScrollPane2.setViewportView(existingReferenceJTable);

        openRefJButton.setText("Open Ref. Packet");
        openRefJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRefJButtonActionPerformed(evt);
            }
        });

        addRefJButton.setText("Add To Document");
        addRefJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRefJButtonActionPerformed(evt);
            }
        });

        singleJRadio.setText("Single Selection");
        radioGroup.add(singleJRadio);
        singleJRadio.addItemListener(this);
        singleJRadio.setSelected(true);

        multipleJRadio.setText("Multiple Selection");
        radioGroup.add(multipleJRadio);
        multipleJRadio.addItemListener(this);

        javax.swing.GroupLayout addFromExistingJDialogLayout = new javax.swing.GroupLayout(addFromExistingJDialog.getContentPane());
        addFromExistingJDialog.getContentPane().setLayout(addFromExistingJDialogLayout);
        addFromExistingJDialogLayout.setHorizontalGroup(
            addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFromExistingJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                    .addGroup(addFromExistingJDialogLayout.createSequentialGroup()
                        .addComponent(singleJRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(multipleJRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                        .addComponent(addRefJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openRefJButton)))
                .addContainerGap())
        );
        addFromExistingJDialogLayout.setVerticalGroup(
            addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFromExistingJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addFromExistingJDialogLayout.createSequentialGroup()
                        .addGroup(addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(openRefJButton)
                            .addComponent(addRefJButton))
                        .addGap(11, 11, 11))
                    .addGroup(addFromExistingJDialogLayout.createSequentialGroup()
                        .addGroup(addFromExistingJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singleJRadio)
                            .addComponent(multipleJRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        autoReferenceJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(autoReferenceJTable);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jLabel3.setText("Current Book in Document :");

        exportDocxJButton.setText("Export Word File");
        exportDocxJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDocxJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                .addComponent(exportDocxJButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(exportDocxJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportDocxJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDocxJButtonActionPerformed
        ExportDocx();
        /*        int i, j, k;

//        thread.mysuspend();
        DefaultTableModel tempModel = (DefaultTableModel) autoReferenceJTable.getModel();
        Book tempBook = new Book();
        int bookCount = 1;

        textArea.append("\n\n\n\n\n\nBook Reference : \n");

        for(i=0; i<tempModel.getRowCount(); i++) {
            textArea.append(Integer.toString(bookCount++) + ". ");
            textArea.append(tempBook.authorName = (String) tempModel.getValueAt(i, 0) + ", ");
            textArea.append(tempBook.bookName = (String) tempModel.getValueAt(i, 1) + ", ");
            textArea.append(tempBook.edition = (String) tempModel.getValueAt(i, 2) + ", ");
            textArea.append(tempBook.volume = (String) tempModel.getValueAt(i, 3) + ", ");
            textArea.append(tempBook.placeOfPub = (String) tempModel.getValueAt(i, 4) + ", ");
            textArea.append(tempBook.publisher = (String) tempModel.getValueAt(i, 5) + ", ");
            textArea.append(tempBook.publicationYear = (String) tempModel.getValueAt(i, 6) + ", ");
            textArea.append(tempBook.totalPage = (String) tempModel.getValueAt(i, 7));
            textArea.append("\n");
        }
//        thread.myresume();
 */
}//GEN-LAST:event_exportDocxJButtonActionPerformed

    private void openRefJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRefJButtonActionPerformed
        JFileChooser fileChooser;
        fileChooser = new JFileChooser();

        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);

        File tempFile = new File("");
        tempFile = fileChooser.getSelectedFile();
        System.out.println(tempFile.getPath());

        OpenReference(tempFile);
        UpdateExistingReferenceJTable();
    }//GEN-LAST:event_openRefJButtonActionPerformed

    private void addRefJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRefJButtonActionPerformed
/*        int index = existingReferenceJTable.getSelectedRow();
        Reference tempRef = existRefPacket.refList.get(index);

        String firstHalf = new String();
        String secondHalf = new String();

        int i, j;
        int pos, added;

            pos = textArea.getCaretPosition();
            firstHalf = textArea.getText().substring(0, pos);
            secondHalf = textArea.getText().substring(pos);

            for(i=1; i<=totalRef; i++) {
                if(firstHalf.indexOf("[" + String.valueOf(i) + "]") == -1)
                    break;
            }
            firstHalf = firstHalf.concat("[" + String.valueOf(i) + "]");
            added = i;
            for( i = totalRef; i>=added; i--) {
                secondHalf = secondHalf.replace("[" + String.valueOf(i) + "]", "[" + String.valueOf(i+1) + "]");
                System.out.println(i);
            }
            totalRef++;

            textArea.setText(firstHalf + secondHalf);
            textArea.setCaretPosition(pos + 2 + String.valueOf(added).length());

            currentRefPacket.refList.add(added-1, tempRef);
            UpdateAutoReferenceJTable();
            System.out.println(tempRef.toString());
 */
        int index[] = existingReferenceJTable.getSelectedRows();
        Reference tempRef = new Reference();

        String firstHalf = new String();
        String secondHalf = new String();

        int i, j, k ;
        int pos, added;
        for(k = 0; k < index.length; k++) {
            tempRef = existRefPacket.refList.get(index[k]);
 
            pos = textArea.getCaretPosition();
            if(k != 0) {
                textArea.insert(",", pos);
                pos++;
                textArea.setCaretPosition(pos);
            }

            firstHalf = textArea.getText().substring(0, pos);
            secondHalf = textArea.getText().substring(pos);

            for(i=1; i<=totalRef; i++) {
                if(firstHalf.indexOf("[" + String.valueOf(i) + "]") == -1)
                    break;
            }
            firstHalf = firstHalf.concat("[" + String.valueOf(i) + "]");
            added = i;
            for( i = totalRef; i>=added; i--) {
                secondHalf = secondHalf.replace("[" + String.valueOf(i) + "]", "[" + String.valueOf(i+1) + "]");
                System.out.println(i);
            }
            totalRef++;

            textArea.setText(firstHalf + secondHalf);
            textArea.setCaretPosition(pos + 2 + String.valueOf(added).length());

            currentRefPacket.refList.add(added-1, tempRef);
            UpdateAutoReferenceJTable();
            System.out.println(tempRef.toString());
        }
    }//GEN-LAST:event_addRefJButtonActionPerformed


    public void NewAction()
    {
        textArea.setText("");
        windowTitle = "Untitled";
        parent.setTitle("Untitled");
    }


    public void readFile()
    {
        BufferedReader d;
        StringBuffer sb=new StringBuffer();
        try {
            d = new BufferedReader(new FileReader(currentTextFile));
            String line;
        while((line=d.readLine())!=null)
            sb.append(line+"");
            textArea.setText(sb.toString());
            d.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){ }
    }


    public void OpenAction()
    {

        FileDialog fd = new FileDialog(new Frame(),"Select File",FileDialog.LOAD);
        fd.setVisible(true);
        if((fd.getFile())!=null)
        {
            currentTextFile=fd.getDirectory()+fd.getFile();
//            dn=fd.getDirectory();
            parent.setTitle(currentTextFile);
            readFile();
        }
        textArea.requestFocus();


    }

    public void writeFile()
    {
        try {
            DataOutputStream d=new DataOutputStream(new FileOutputStream(currentTextFile));
            String line=textArea.getText();
            BufferedReader br=new BufferedReader(new StringReader(line));
            while((line=br.readLine())!=null)
            {
                d.writeBytes(line+"");
            }
            d.close();
            }
            catch(Exception e){
                System.out.println("File not found");
            }
    }


    public void SaveAction()
    {
            FileDialog fd =new FileDialog(new Frame(),"Save File",FileDialog.SAVE);
//            fd.setFile(fn);
//            fd.setDirectory(dn);
            fd.setVisible(true);

            if(fd.getFile()!=null)
            {
                currentTextFile = fd.getDirectory()+fd.getFile();
                parent.setTitle(currentTextFile);
                writeFile();
                textArea.requestFocus();
            }

    }


    public void ExportDocx()
    {
            FileDialog fd =new FileDialog(new Frame(), "Export DocX", FileDialog.SAVE);
//            fd.setFile(fn);
//            fd.setDirectory(dn);
            fd.setVisible(true);
            String tempFile;

        if(fd.getFile() != null)
        {
            tempFile = fd.getDirectory() + fd.getFile();

            int i;
            Book tempBook = new Book();
            Journal tempJournal = new Journal();
            Conference tempConf = new Conference();

            try {
                DocumentBuilder builder = new DocumentBuilder();
                com.aspose.words.Font font = builder.getFont();

                font.setSize(14);
                font.setBold(false);
                font.setName("Calibri");

                builder.writeln(textArea.getText() + "\n\n\n");

                font.setSize(20);
                font.setBold(true);
                font.setColor(Color.GREEN);

                builder.writeln("REFERENCE\n");

                font.setSize(14);
                font.setBold(false);
                font.setColor(Color.BLACK);

                for(i=0; i < currentRefPacket.refList.size(); i++) {
                    if(currentRefPacket.refList.get(i).refType == Reference.BOOK) {
                        tempBook = currentRefPacket.refList.get(i).RetBook();
                        builder.write("[" + String.valueOf(i+1) + "]  ");
                        if(!tempBook.authorName.equals(""))
                            builder.write(tempBook.authorName);
                        if(!tempBook.bookName.equals("")) {
                            font.setItalic(true);
                            builder.write(", \"" + tempBook.bookName + "\"");
                            font.setItalic(false);
                        }
                        if(!tempBook.edition.equals(""))
                            builder.write(", " + tempBook.edition);
                        if(!tempBook.volume.equals(""))
                            builder.write(", vol. " + tempBook.volume);
                        if(!tempBook.placeOfPub.equals(""))
                            builder.write(", " + tempBook.placeOfPub + ": ");
                        if(!tempBook.publisher.equals(""))
                            builder.write( tempBook.publisher);
                        if(!tempBook.publicationYear.equals(""))
                            builder.write(", " + tempBook.publicationYear + ".");
                        builder.writeln();
                    }
                    else if(currentRefPacket.refList.get(i).refType == Reference.JOURNAL) {
                        tempJournal = currentRefPacket.refList.get(i).RetJournal();
                        builder.write("[" + String.valueOf(i+1) + "]  ");

                        if(!tempJournal.authorName.equals(""))
                            builder.write(tempJournal.authorName);
                        if(!tempJournal.journalTopic.equals("")) {
                            builder.write(", \"" + tempJournal.journalTopic + "\"");
                            font.setItalic(false);
                        }
                        if(!tempJournal.journalName.equals("")) {
                            font.setItalic(true);
                            builder.write(", " + tempJournal.journalName);
                            font.setItalic(false);
                        }
                        if(!tempJournal.volume.equals(""))
                            builder.write(", vol. " + tempBook.volume);
                        if(!tempJournal.issueNo.equals(""))
                            builder.write(", no. " + tempJournal.issueNo);
                        if(!tempJournal.pageNo.equals(""))
                            builder.write(", pp. " + tempJournal.pageNo);
                        if(!tempJournal.publicationYear.equals(""))
                            builder.write(", " + tempJournal.publicationYear + ".");
                        builder.writeln();
                    }
                    else if(currentRefPacket.refList.get(i).refType == Reference.CONFERENCE) {
                        tempConf = currentRefPacket.refList.get(i).RetConference();
                        builder.write("[" + String.valueOf(i+1) + "]  ");

                        if(!tempConf.conferenceName.equals(""))
                            builder.write(tempConf.conferenceSpeaker);
                        if(!tempConf.conferenceName.equals(""))
                            builder.write(", \"" + tempConf.conferenceName + "\"");
                        if(!tempConf.conferenceTopic.equals("")) {
                            font.setItalic(true);
                            builder.write(", " + tempConf.conferenceTopic);
                            font.setItalic(false);
                        }
                        if(!tempConf.place.equals(""))
                            builder.write(", " + tempConf.place);
                        if(!tempConf.date.equals(""))
                            builder.write(", " + tempConf.date + ".");
                        builder.writeln();
                    }
                }

                builder.getDocument().save(tempFile + ".docx");
                System.out.println("Docx SAved");
                JOptionPane.showMessageDialog(parent, "Docx File Saved", "Exported Successfully", JOptionPane.INFORMATION_MESSAGE);

            }
            catch(Exception e)
            {

            }
        }
        
            
    }

    public void ExportDocxAction()
    {
        ExportDocx();
        
    }

    public void UndoAction()
    {
        textArea.undo();
    }

    public void RedoAction()
    {
        textArea.redo();
    }

    public void CopyAction()
    {
           String sel = textArea.getSelectedText();
           StringSelection clipstring = new StringSelection(sel);
           clip.setContents(clipstring,clipstring);
    }

    public void CutAction()
    {
        String sel=textArea.getSelectedText();
        StringSelection ss = new StringSelection(sel);
        clip.setContents(ss,ss);
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
    }

    public void PasteAction()
    {
        Transferable cliptran=clip.getContents(this);
        
        try {
            String sel=(String)cliptran.getTransferData(DataFlavor.stringFlavor);

            textArea.replaceRange(sel,textArea.getSelectionStart(),textArea.getSelectionEnd());
            }
            catch(Exception e){
                System.out.println("Exception in Paste Action");
            }
    }

    public void ReplaceAction()
    {

        String find, replace, mainString;


        replaceDialog.setVisible(true);

      
    }

    public void TimeAndDateAction()
    {
//        Calendar cal = new Calendar();
//        textArea.append(cal.toString());
    }

    public void FontAction()
    {
        
    }


    public void AddBookAction()
    {
        AddBookJDialog dia = new AddBookJDialog(parent, true);
        dia.setVisible(true);
        Book book = dia.getBook();

        String firstHalf = new String();
        String secondHalf = new String();

        int i, j;
        int pos, added;

        if(book != null) {
            pos = textArea.getCaretPosition();
            firstHalf = textArea.getText().substring(0, pos);
            secondHalf = textArea.getText().substring(pos);
//            System.out.println(firstHalf);
//            System.out.println(secondHalf);

            for(i=1; i<=totalRef; i++) {
                if(firstHalf.indexOf("[" + String.valueOf(i) + "]") == -1)
                    break;
            }
            firstHalf = firstHalf.concat("[" + String.valueOf(i) + "]");
            added = i;
            for( i = totalRef; i>=added; i--) {
                secondHalf = secondHalf.replace("[" + String.valueOf(i) + "]", "[" + String.valueOf(i+1) + "]");
                System.out.println(i);
            }
            totalRef++;

            textArea.setText(firstHalf + secondHalf);
            textArea.setCaretPosition(pos + 2 + String.valueOf(added).length());

            currentRefPacket.refList.add(added-1, new Reference(book));
            UpdateAutoReferenceJTable();
            System.out.println(book.toString());
 
        }
        else
            System.out.println("Null is returned");

        
    }

    public void AddJournalAction()
    {
        AddJournalJDialog dia = new AddJournalJDialog(parent, true);
        dia.setVisible(true);
        Journal jour = dia.getJournal();

        String firstHalf = new String();
        String secondHalf = new String();

        int i, j;
        int pos, added;

        if(jour != null) {
            pos = textArea.getCaretPosition();
            firstHalf = textArea.getText().substring(0, pos);
            secondHalf = textArea.getText().substring(pos);
//            System.out.println(firstHalf);
//            System.out.println(secondHalf);

            for(i=1; i<=totalRef; i++) {
                if(firstHalf.indexOf("[" + String.valueOf(i) + "]") == -1)
                    break;
            }
            firstHalf = firstHalf.concat("[" + String.valueOf(i) + "]");
            added = i;
            for( i = totalRef; i>=added; i--) {
                secondHalf = secondHalf.replace("[" + String.valueOf(i) + "]", "[" + String.valueOf(i+1) + "]");
                System.out.println(i);
            }
            totalRef++;

            textArea.setText(firstHalf + secondHalf);
            textArea.setCaretPosition(pos + 2 + String.valueOf(added).length());

            currentRefPacket.refList.add(added-1, new Reference(jour));
            UpdateAutoReferenceJTable();
            System.out.println(jour.toString());

        }
        else
            System.out.println("Null is returned");

    }

    public void AddConferenceAction()
    {
        AddConfJDialog dia = new AddConfJDialog(parent, true);
        dia.setVisible(true);
        Conference conf = dia.getConference();

        String firstHalf = new String();
        String secondHalf = new String();

        int i, j;
        int pos, added;

        if(conf != null) {
            pos = textArea.getCaretPosition();
            firstHalf = textArea.getText().substring(0, pos);
            secondHalf = textArea.getText().substring(pos);
//            System.out.println(firstHalf);
//            System.out.println(secondHalf);

            for(i=1; i<=totalRef; i++) {
                if(firstHalf.indexOf("[" + String.valueOf(i) + "]") == -1)
                    break;
            }
            firstHalf = firstHalf.concat("[" + String.valueOf(i) + "]");
            added = i;
            for( i = totalRef; i>=added; i--) {
                secondHalf = secondHalf.replace("[" + String.valueOf(i) + "]", "[" + String.valueOf(i+1) + "]");
                System.out.println(i);
            }
            totalRef++;

            textArea.setText(firstHalf + secondHalf);
            textArea.setCaretPosition(pos + 2 + String.valueOf(added).length());

            currentRefPacket.refList.add(added-1, new Reference(conf));
            UpdateAutoReferenceJTable();
            System.out.println(conf.toString());

        }
        else
            System.out.println("Null is returned");
    }


    public void FixReferenceAction()
    {
        FixReferenceNumber();
    }

    public void ShowReferenceWizard()
    {
        JFrame frame = new JFrame();
        frame.add(new ReferenceWizardPanel(frame));
        frame.pack();
        frame.setTitle("Reference Wizard");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent evt)
    {

    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == singleJRadio)
            existingReferenceJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        else if(e.getSource() == multipleJRadio)
            existingReferenceJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }


    public void FixReferenceNumber()
    {
        int i, j;
        int deleted;
        
        deleted = 0;
        for(i = totalRef; i>=1; i--)
            if((" " + textArea.getText() + " ").indexOf("[" + i + "]") == -1) {
                currentRefPacket.refList.remove(i-1);
                deleted++;
            }

        UpdateAutoReferenceJTable();

        for(i=1, j=1; i<=totalRef; i++)
            if((" " + textArea.getText() + " ").indexOf("[" + i + "]") != -1) {
                textArea.setText(textArea.getText().replace("[" + i + "]", "[" + (j++) + "]"));
            }
        totalRef -= deleted;
    }

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
  /*      if(e.get == KeyEvent.VK_F5)) {
            FixReferenceNumber();
        }
   */
    }

    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class AutoReferenceRobot implements Runnable {
        String threadName;
        boolean suspendFlag;
        Thread t;

        String mainString;
        ReferencePacket bookRef;

        AutoReferenceRobot(String ThreadName)
        {
            threadName = ThreadName;
            t = new Thread(this, threadName);
            System.out.println("New Thread Started...");
            suspendFlag = false;
            t.start();
        }


        public void run()
        {
            int i, j;
            int deleted = 0;
            String replaced = new String();
            int curCurPoint;

            while( true ) {
                try {
                    Thread.sleep(2000);
                    deleted = 0;

                    
                    replaced = textArea.getText();
                    for(i = 1; i<=currentRefNo; i++)
                        if((" " + replaced + " ").indexOf("[" + i + "]") == -1) {
                            currentRefPacket.refList.remove(i-1);
                            deleted++;
                        }

                    UpdateAutoReferenceJTable();
                    currentRefNo -= deleted;
/*
                    for(i=1, j=1; i<=currentRefNo; i++)
                        if((" " + replaced + " ").indexOf("[" + i + "]") != -1) {
                            replaced = replaced.replace("[" + i + "]", "[" + (j++) + "]");
                        }
                    currentRefNo -= deleted;

                    textArea.setText(replaced);
                    textArea.setSelectionStart(replaced.length());
*/
                    synchronized(this) {
                        while(suspendFlag) {
                            wait();
                        }
                    }
                }
/*                catch ( IOException ioException )
                {
                    System.out.println("IOException occured");
                }
                catch( ClassNotFoundException classNotFoundException)
                {
                    System.out.println("ClassNotFoundException occured");
                }
*/                catch ( InterruptedException e)
                {
                    System.out.println("InterruptedException occured");
                }
            }
   
        }
/*
        public void UpdateTable()
        {
            Book tempBook = new Book();
            Iterator < Book > iterator = bookRef.mainList.iterator();
            int count = 0;

            rowData = new Object[bookRef.mainList.size()][8];
            while(iterator.hasNext()) {
                tempBook = iterator.next();
                rowData[count][0] = tempBook.authorName;
                rowData[count][1] = tempBook.bookName;
                rowData[count][2] = tempBook.edition;
                rowData[count][3] = tempBook.volume;
                rowData[count][4] = tempBook.placeOfPub;
                rowData[count][5] = tempBook.publisher;
                rowData[count][6] = tempBook.publicationYear;
                rowData[count][7] = tempBook.totalPage;
                count++;
            }
       //     System.out.println(rowData[0][0]);

            DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

            autoReferenceJTable.setModel(model);
        }
*/
        public void mysuspend()
        {
            suspendFlag = true;
        }

        synchronized void myresume()
        {
            suspendFlag = false;
            notify();
        }
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog addFromExistingJDialog;
    private javax.swing.JButton addRefJButton;
    private javax.swing.JTable autoReferenceJTable;
    private javax.swing.JTable existingReferenceJTable;
    private javax.swing.JButton exportDocxJButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton multipleJRadio;
    private javax.swing.JButton openRefJButton;
    private javax.swing.JPanel panel1;
    private javax.swing.JRadioButton singleJRadio;
    // End of variables declaration//GEN-END:variables




}
