package com.example.myapplication;

public class team {
    String name,tit;
    Boolean select;
    Float point;

    public String getName() {
        return name;
    }

    public String getTit() {
        return tit;
    }

    public Float getPoint() {
        return point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public team(String name, String tit, Float point, Boolean sele) {
        this.name = name;
        this.tit = tit;
        this.point = point;
        select=sele;
    }


}
