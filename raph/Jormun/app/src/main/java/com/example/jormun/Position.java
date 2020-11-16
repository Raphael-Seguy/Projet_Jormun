package com.example.jormun;

public class Position<X,Y> {
    X Lat;
    Y Long;
    public Position(X Lat,Y Long) {
        setLat(Lat);
        setLong(Long);
    }
    public X getLat() {
        return Lat;
    }
    public void setLat(X lat) {
        this.Lat = lat;
    }
    public Y getLong() {
        return Long;
    }
    public void setLong(Y Long) {
        this.Long = Long;
    }
}
