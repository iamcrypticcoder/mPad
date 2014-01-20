package my;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author MAHBUB
 */

public class ReferencePacket implements Serializable {
/*
	public List < Book > bookList = new ArrayList < Book >();
	public List < Journal > journalList = new ArrayList < Journal >();
	public List < Conference > confList = new ArrayList < Conference >();


    private Book book1 = new Book("David A Bell", "Digital Logic and Circuit Design", "Something", "1990");
    private Book book2 = new Book("HUMAYON AZAD", "NARI", "Something", "1996");
	private Book book3 = new Book();
*/

    public List < Reference > refList = new ArrayList < Reference >();

    public ReferencePacket()
    {

    }
/*
    public void AddBook(Book newBook)
    {
        bookList.add(newBook);
    }

    public void DelBook(int index)
    {
        bookList.remove(index);
    }

    public void AddJournal(Journal newJournal)
    {
        journalList.add(newJournal);
    }

    public void DelJournal(int index)
    {
        journalList.remove(index);
    }

    public void AddConference(Conference newConf)
    {
        confList.add(newConf);
    }

    public void DelConference(int index)
    {
        confList.remove(index);
    }
*/

    public void AddReference(Reference newRef)
    {
        refList.add(newRef);
    }

    public void ClearReference()
    {
        refList.clear();
    }

    public void print()
    {
    	int i, j;

		for(i=0; i<refList.size(); i++)
            System.out.println(refList.get(i).toString());
    }
}
