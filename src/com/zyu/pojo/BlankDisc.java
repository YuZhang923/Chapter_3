package com.zyu.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-13 19:08
 * Description:
 */
public class BlankDisc {
    private String title;
    private String artist;

    public BlankDisc() {
    }

    public BlankDisc(String title,String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String hello(){
        return "BlankDisc.hello()";
    }

    @Override
    public String toString() {
        return "BlankDisc{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

