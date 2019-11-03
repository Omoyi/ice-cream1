package com.myicecream.ice_cream1;

public class WishedIcecream {

    private String iceId;
    private String type;
    private String size;

    public WishedIcecream(){

    }

    public WishedIcecream(String iceId, String type, String size) {
        this.iceId = iceId;
        this.type = type;
        this.size = size;
    }

    public String getIceId() {
        return iceId;
    }

    public void setIceId(String iceId) {
        this.iceId = iceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
