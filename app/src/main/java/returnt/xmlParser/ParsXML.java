/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package returnt.xmlParser;

import android.os.StrictMode;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 *
 * @author proj
 */
public class ParsXML {

    protected URL url ;
    private final ArrayList<String> title = new ArrayList();
    private final ArrayList<String> links = new ArrayList();
    private final ArrayList<String> description = new ArrayList();
    private final ArrayList<String> media = new ArrayList();
    private final ArrayList<String> published = new ArrayList();

    public ParsXML() {
        ParsXml();
    } 
    
    /**
     * method parsing xml
     */
    private void ParsXml() {

        //pars

    }

    private InputStream OpenStream(URL url) {
        try {
            
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.d("IOException", e.toString());
            return null;
        }
    }

    public ArrayList<String> getTitle()
    {
        //Log.d( "qq1", title+"");
        return title;
    }
    
    public ArrayList<String> getLinks()
    {
        //Log.d( "qq2", links+"");
        return links;
    }
    
    public ArrayList<String> getDescription()
    {
        //Log.d( "qq3", description+"");
        return description;
    }
    
    public ArrayList<String> getMedia()
    {
        //Log.d( "qq4", media.isEmpty()+"");
        return media;
    }

    public ArrayList<String> getPublished()
    {
        //Log.d( "qq4", media.isEmpty()+"");
        return published;
    }

}