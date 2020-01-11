package top.itlq.spring.data.access.orm;

import java.time.Instant;

public class PosEntity {
    private Integer id;
    private Instant time;
    private Float lat;
    private Float lng;

    public PosEntity(){

    }

    public PosEntity(Instant time, Float lat, Float lng) {
        this.time = time;
        this.lat = lat;
        this.lng = lng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }
}
