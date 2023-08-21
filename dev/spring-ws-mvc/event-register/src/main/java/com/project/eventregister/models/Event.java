package com.project.eventregister.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private String local;
    private String date;
    private String time;

    public Event() {
    }

    public Event(String name, String local, String date, String time) {
        this.name = name;
        this.local = local;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
