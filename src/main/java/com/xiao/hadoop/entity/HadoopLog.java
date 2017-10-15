package com.xiao.hadoop.entity;

/**
 * Created by xiaomengning on 2017/10/15.
 */
public class HadoopLog {
    private int id;
    private String rdate;
    private String time;
    private String type;
    private String relateclass;
    private String information;

    public HadoopLog() {
    }

    public HadoopLog(String rdate, String time, String type, String relateclass, String information) {
        this.rdate = rdate;
        this.time = time;
        this.type = type;
        this.relateclass = relateclass;
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelateclass() {
        return relateclass;
    }

    public void setRelateclass(String relateclass) {
        this.relateclass = relateclass;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
