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


public class Conference implements Serializable {
    public
            String conferenceSpeaker;
            String conferenceName;              // Italic 
            String conferenceTopic;             // "fgfdg"
            String place;
            String date;

    public Conference()
    {
        this("", "", "", "", "");
    }

    public Conference(String sp, String confName, String confTopic, String p, String d)
    {
        conferenceSpeaker =sp;
		conferenceName = confName; 
		conferenceTopic = confTopic;
		place = p;
		date = d;
    }
    
    public String toString()
    {
    	String ret = new String();

        if(!conferenceSpeaker.equals(""))
            ret = conferenceSpeaker;
        if(!conferenceName.equals(""))
            ret = ret.concat(", " + "\"" + conferenceName + "\"");
        if(!conferenceTopic.equals(""))
            ret = ret.concat(", " + conferenceTopic);
        if(!place.equals(""))
            ret = ret.concat(", " + place);
        if(!date.equals(""))
            ret = ret.concat(", " + date);

//    	ret = conferenceName + ", " + conferenceTopic + ", " + place + ", " + date;

        return ret;
    }    

    public void printConference()
    {
    	System.out.println(conferenceName);
    	System.out.println(conferenceTopic);
    	System.out.println(place);
    	System.out.println(date);
        
    }

}
