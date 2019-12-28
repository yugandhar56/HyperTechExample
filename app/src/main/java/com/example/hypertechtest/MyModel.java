package com.example.hypertechtest;

import com.example.hypertechtest.util.Utils;

class MyModel {

    private final String image_url;
    private String video_url;
    private final String name;
    private byte type;

    public MyModel(String video_url, String image_url, String name) {
        this.video_url = video_url;
        this.image_url = image_url;
        this.name = name;
        this.type=Utils.TYPE_VIDEO;
    }

    public MyModel(String image_url, String name) {
        this.image_url = image_url;
        this.name = name;
        this.type=Utils.TYPE_VIDEO;
    }
    public MyModel(String name) {
        this.image_url=null;
        this.name = name;
        this.type= Utils.TYPE_TEXT;
    }
    public void setType(byte type)
    {
        this.type=type;
    }
    public byte getType(){return this.type;}
    public String getImage_url() {
        return image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getName() {
        return name;
    }
}
