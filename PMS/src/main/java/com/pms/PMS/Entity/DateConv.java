package com.pms.PMS.Entity;

import org.springframework.format.annotation.DateTimeFormat;

//import java.util.Date;
import java.sql.Date;

public class DateConv {

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date start;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date end;

    public DateConv(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
