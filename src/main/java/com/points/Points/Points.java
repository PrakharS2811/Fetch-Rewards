package com.points.Points;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;

    @Column(name = "payer")
    private String payer;

    @Column(name = "points")
    private int points;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "timestamp")
    private Date timestamp;

    public Points(String payer, int points, Date timestamp) {
        this.payer = payer;
        this.points = points;
        this.timestamp = timestamp;
    }
    public Points(){};
    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int timestamp) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", payer='" + payer + '\'' +
                ", points=" + points +
                ", timestamp=" + timestamp +
                '}';
    }
}
