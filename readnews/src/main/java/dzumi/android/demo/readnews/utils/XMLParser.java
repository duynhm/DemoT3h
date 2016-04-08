package dzumi.android.demo.readnews.utils;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.List;

import dzumi.android.demo.readnews.model.News;
import dzumi.android.demo.readnews.model.Rss;

/**
 * Created by Dzumi on 4/4/2016.
 */
public class XMLParser {
    public static List<News> getNewsByVnExpress(String xml){
        XmlParserCreator xmlParserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        GsonXml gsonXml = new GsonXmlBuilder().setXmlParserCreator(xmlParserCreator).setSameNameLists(true).create();
        Rss rss =  gsonXml.fromXml(xml, Rss.class);
        return rss.channel.items;

    }
}
