package com.volkruss.BookOhFoo.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO move package
public class DateUtil {

    public static String getCurrentStr(){
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS")
                .format(new Date());
    }
}
