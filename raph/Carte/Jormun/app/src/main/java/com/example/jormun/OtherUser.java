package com.example.jormun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OtherUser {
    String sToken;

    boolean bUpdated;
    boolean bToRemove;

    Runnable rnbleUpdating;

    float fLat;
    float fLong;

    public OtherUser(String sToken, float fLat,float fLong) {
        setsToken(sToken);
        setfLat(fLat);
        setfLong(fLong);
        setbToRemove(false);
        setbUpdated(true);

        setRnbleUpdating(new Runnable() {
            @Override
            public void run() {
                do {
                    if(!isbUpdated()){
                        setbToRemove(true);
                    }else{
                        setbUpdated(false);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (!isbToRemove());
            }
        });
        ExecutorService excutor = Executors.newCachedThreadPool();
        excutor.submit(getRnbleUpdating());
    }

    public String getsToken() {
        return sToken;
    }

    public void setsToken(String sToken) {
        this.sToken = sToken;
    }

    public float getfLat() {
        return fLat;
    }

    public void setfLat(float fLat) {
        this.fLat = fLat;
    }

    public float getfLong() {
        return fLong;
    }

    public void setfLong(float fLong) {
        this.fLong = fLong;
    }

    public boolean isbUpdated() {
        return bUpdated;
    }

    public void setbUpdated(boolean bUpdated) {
        this.bUpdated = bUpdated;
    }

    public boolean isbToRemove() {
        return bToRemove;
    }

    public void setbToRemove(boolean bToRemove) {
        this.bToRemove = bToRemove;
    }

    public Runnable getRnbleUpdating() {
        return rnbleUpdating;
    }

    public void setRnbleUpdating(Runnable rnbleUpdating) {
        this.rnbleUpdating = rnbleUpdating;
    }
}
