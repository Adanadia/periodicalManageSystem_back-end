package top.ahcdc.periodical.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarString {
    public String CToS(Calendar c){
        String str=new String();
        if(c.get(Calendar.MONTH)+1>=10&&c.get(Calendar.DATE)>=10)
            str=Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString((c.get(Calendar.DATE)));
        else if(c.get(Calendar.MONTH)+1<10&&c.get(Calendar.DATE)<10)
            str=Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(0)+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(0)+Integer.toString((c.get(Calendar.DATE)));
        else if(c.get(Calendar.MONTH)+1>=10&&c.get(Calendar.DATE)<10)
            str=Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString(0)+Integer.toString((c.get(Calendar.DATE)));
        else if(c.get(Calendar.MONTH)+1<10&&c.get(Calendar.DATE)>=10)
            str=Integer.toString(c.get(Calendar.YEAR))+"-"+Integer.toString(0)+Integer.toString(c.get(Calendar.MONTH)+1)+"-"+Integer.toString((c.get(Calendar.DATE)));
        return str;
    }
    public Calendar SToC(String s) throws ParseException {
        Calendar ca=new GregorianCalendar();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date day=df.parse(s);
        ca.setTime(day);
        return ca;
    }
}
