package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "_group")
public class Group implements Contact{
    @Id
    private int _id;
    private String name;
    private int count_p;
    @Transient
    private String type = "group";
    public Group() {
    }

    public Group(int _id, String name, int count_p) {
        this._id = _id;
        this.name = name;
        this.count_p = count_p;
    }

    public int get_id() {
        return _id;
    }

    public Group set_id(int _id) {
        this._id = _id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public int getCount_p() {
        return count_p;
    }

    public Group setCount_p(int count_p) {
        this.count_p = count_p;
        return this;
    }
}
