package com.zyu.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-13 21:32
 * Description:
 */
public class SpELDemo {
    private String str;
    private BlankDisc blankDisc;
    private Object obj;
    private boolean falg;

    public Object getObj() {
        return obj;
    }

    public SpELDemo(String str, BlankDisc blankDisc, Object obj, boolean falg) {
        this.str = str;
        this.blankDisc = blankDisc;
        this.obj = obj;
        this.falg = falg;
    }

    public boolean isFalg() {

        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public BlankDisc getBlankDisc() {
        return blankDisc;
    }

    public void setBlankDisc(BlankDisc blankDisc) {
        this.blankDisc = blankDisc;
    }

    public SpELDemo(String str, BlankDisc blankDisc, Object obj) {
        this.str = str;
        this.blankDisc = blankDisc;
        this.obj = obj;
    }

    public SpELDemo(Object obj) {

        this.obj = obj;
    }

    public SpELDemo() {
    }

    public SpELDemo(String str) {
        this.str = str;
    }

    public SpELDemo(String str, BlankDisc blankDisc) {
        this.str = str;
        this.blankDisc = blankDisc;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
