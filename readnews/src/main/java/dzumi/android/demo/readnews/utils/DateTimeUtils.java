package dzumi.android.demo.readnews.utils;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Dzumi on 4/1/2016.
 */
public class DateTimeUtils {

    private static Date parseDate(String text)
            throws ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.parse(text);
    }

    public static String getPrettyTime(String text){
        try {
            return new PrettyTime().format(parseDate(text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return text;
    }
}
