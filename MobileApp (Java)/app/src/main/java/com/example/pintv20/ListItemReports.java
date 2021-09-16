package com.example.pintv20;

public class ListItemReports {
    private String date;
    private String time;
    private Integer level;
    private String local;

    public ListItemReports(String date, String time, Integer level, String local) {
        if(date == null)
            this.date = "---";
        else
            this.date = date;
        if(time == null)
            this.time = "---";
        else
            this.time = time;
        this.level = level;
        this.local = local;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
