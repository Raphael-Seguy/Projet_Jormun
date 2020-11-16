package com.example.jormun;

public class OtherUser {
    String sToken;
    float fPosX;
    float fPosY;

    public OtherUser(String sToken, float fPosX,float fPosY) {
        this.sToken = sToken;
        this.fPosX = fPosX;
        this.fPosY = fPosY;
    }

    public String getsToken() {
        return sToken;
    }

    public void setsToken(String sToken) {
        this.sToken = sToken;
    }

    public float getfPosX() {
        return fPosX;
    }

    public void setfPosX(float fPosX) {
        this.fPosX = fPosX;
    }

    public float getfPosY() {
        return fPosY;
    }

    public void setfPosY(float fPosY) {
        this.fPosY = fPosY;
    }
}
