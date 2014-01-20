/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.undo.*;
import java.util.Hashtable;
/**
 *
 * @author MAHBUB
 */
class UndoableTextArea extends TextArea implements StateEditable
 {
  private final static String KEY_STATE="UndoableTextAreaKey";
  
  private boolean textChanged=false;

  private UndoManager undoManager;

  private StateEdit currentEdit;

  public UndoableTextArea()
     {
      super();
      initUndoable();
     }

  public UndoableTextArea(String string)
      {
       super(string);
       initUndoable();
      }

  public UndoableTextArea(int rows,int columns)
      {
       super(rows,columns);
       initUndoable();
      }

  public UndoableTextArea(String string,int rows,int columns)
         {
          super(string,rows,columns);
          initUndoable();
         }

  public UndoableTextArea(String string,int rows,int columns,int scrollbars)
      {
       super(string,rows,columns,scrollbars);
       initUndoable();
      }

   public boolean undo(){
    try{
        undoManager.undo();
        return true;
       }
     catch(CannotUndoException e)
        {
        System.out.println("cannot undo");
        return false;
        }
     }
    public boolean redo()
       {
       try{
         undoManager.redo();
         return true;
         }
      catch(CannotRedoException e)
          {
            System.out.println("cannot redo");
            return false;
         }
       }


    public void storeState(Hashtable state)
       {
        state.put(KEY_STATE,getText());
       }

    public void restoreState(Hashtable state)
        {
         Object data=state.get(KEY_STATE);
         if(data!=null){
          setText((String)data);
          }
        }

   private void takeSnapshot()
       {
        if(textChanged)
        {
         currentEdit.end();
         undoManager.addEdit(currentEdit);
         textChanged=false;
         currentEdit=new StateEdit(this);
         }
        }

        private void initUndoable()
        {
            undoManager =new UndoManager();
            currentEdit=new StateEdit(this);
            addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent event){
                    if(event.isActionKey()){
                    takeSnapshot();
                }
             }
        });

        addFocusListener(new FocusAdapter(){
        public void focusLost(FocusEvent fe){
          takeSnapshot();
          }
          });

        addTextListener(new TextListener(){
           public void textValueChanged(TextEvent e){
               textChanged=true;
               takeSnapshot();
            }
         });

      }
   }
