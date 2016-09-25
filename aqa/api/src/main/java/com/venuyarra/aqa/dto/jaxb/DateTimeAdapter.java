package com.venuyarra.aqa.dto.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NIKOLAI on 25.09.2016.
 */
public class DateTimeAdapter extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public String marshal(Date date) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(date);
        }
    }

    @Override
    public Date unmarshal(String dateAsString) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.parse(dateAsString);
        }
    }
}
