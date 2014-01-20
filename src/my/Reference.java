package my;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import my.Book;

/**
 *
 * @author MAHBUB
 */


public class Reference implements Serializable {
    public
            Book book;
            Journal journal;
            Conference conf;

            int refType;

            final static int BOOK = 1;
            final static int JOURNAL = 2;
            final static int CONFERENCE = 3;

    public Reference()
    {
        
    }
    public Reference(Book newBook)
    {
        journal = null;
        conf = null;

        refType = 1;
        book = newBook;
    }
    public Reference(Journal newJournal)
    {
        book = null;
        conf = null;

        refType = 2;
        journal = newJournal;
    }
    public Reference(Conference newConf)
    {
        book = null;
        journal = null;

        refType = 3;
        conf = newConf;
    }

    public Book RetBook()
    {
        return book;
    }

    public Journal RetJournal()
    {
        return journal;
    }

    public Conference RetConference()
    {
        return conf;
    }

    public String ReferenceType()
    {
        if(refType == 1)
            return "Book";
        else if(refType == 2)
            return "Journal";
        else if(refType == 3)
            return "Conference";
        return null;
    }

    public String toString()
    {
        String retStr = new String();
        if(book != null)
            retStr = book.toString();
        else if(journal != null)
            retStr = journal.toString();
        else if(conf != null)
            retStr = conf.toString();
        return retStr;
    }

}
