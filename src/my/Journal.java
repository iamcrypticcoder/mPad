package my;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author MAHBUB
 */


public class Journal implements Serializable {
    public
            String authorName;
            String journalTopic;                    // "ssdsg"
            String journalName;                     // It must be Italic
            String volume;
            String issueNo;
            String pageNo;
            String publicationYear;                 // Total Field = 7

    public Journal()
    {
        this("", "", "", "", "", "", "");
    }

    public Journal(String aName, String jTopic, String jName, String vol, String iNo, String pNo, String pubYear)
    {
        authorName = aName;
        journalTopic = jTopic;
        journalName = jName;
        volume = vol;
        issueNo = iNo;
        pageNo  = pNo;
        publicationYear  = pubYear;
    }

    public String toString()
    {
    	String ret = new String();

        if(!authorName.equals(""))
            ret = authorName;
        if(!journalTopic.equals(""))
            ret = ret.concat(", " + "\"" + journalTopic + "\"");
        if(!journalName.equals(""))
            ret = ret.concat(", " + journalName);
        if(!volume.equals(""))
            ret = ret.concat(", vol. " + volume);
        if(!issueNo.equals(""))
            ret = ret.concat(", no. " + issueNo);
        if(!pageNo.equals(""))
            ret = ret.concat(", pp. " + pageNo);
        if(!publicationYear.equals(""))
            ret = ret.concat(", " + publicationYear);

//    	ret = authorName + ", " + journalTopic + ", " + journalName + ", " + volume + ", " + issueNo + ", " + pageNo + ", " +
//    			publicationYear;

        return ret;
    }

    public void printBook()
    {
    	System.out.println(authorName);
    	System.out.println(journalTopic);
    	System.out.println(journalName);
    	System.out.println(volume);

    	System.out.println(issueNo);
    	System.out.println(pageNo);
    	System.out.println(publicationYear);   
    }

}
