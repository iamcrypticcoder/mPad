/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

/**
 *
 * @author MAHBUB
 */

import java.io.Serializable;

public class Book implements Serializable {
    public String authorName;                       
    public String bookName;                         // Italic
    public String edition;
    public String volume;
    public String placeOfPub;                       // after :
    public String publisher;
    public String publicationYear;
    public String totalPage;

    public Book()
    {
        this("", "", "", "", "", "", "", "");
    }

    public Book(String aName, String bName, String ed, String vol, String pubPlace, String pub, String pubYear, String tPage)
    {
        authorName = aName;
        bookName = bName;
        edition = ed;
        volume = vol;
        placeOfPub = pubPlace;
        publisher  = pub;
        publicationYear  = pubYear;
        totalPage  = tPage;
    }

    public String toString()
    {
    	String ret = new String();

        if(!authorName.equals(""))
            ret = authorName;
        if(!bookName.equals(""))
            ret = ret.concat(", " + "\"" + bookName + "\"");
        if(!edition.equals(""))
            ret = ret.concat(", " + edition);
        if(!volume.equals(""))
            ret = ret.concat(", vol. " + volume);
        if(!placeOfPub.equals(""))
            ret = ret.concat(", " + placeOfPub);
        if(!publisher.equals(""))
            ret = ret.concat(", " + publisher);
        if(!publicationYear.equals(""))
            ret = ret.concat(", " + publicationYear);
        if(!totalPage.equals(""))
            ret = ret.concat(", " + totalPage);

//    	ret = authorName + ", " + bookName + ", " + edition + ", " + volume + ", " + placeOfPub + ", " + publisher + ", " +
  //  			publicationYear + ", " + totalPage;

        return ret;
        
    }

    public void printBook()
    {
    	System.out.println(authorName);
    	System.out.println(bookName);
    	System.out.println(edition);
    	System.out.println(volume);

    	System.out.println(placeOfPub);
    	System.out.println(publisher);
    	System.out.println(publicationYear);
    	System.out.println(totalPage);

    }
    
    public static void main(String args[])
    {
        System.out.println(new Book("MAHBUB", "Programming in C", "", "", "", "", "", "").toString());
    }
}
