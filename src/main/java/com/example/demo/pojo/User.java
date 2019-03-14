package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(length = 40)
    private String _id;
    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String psw;
    private String ip;
    private Integer port;
    private int role;
    public User() {
    }

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return role == user.role &&
                Objects.equals(get_id(), user.get_id()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPsw(), user.getPsw()) &&
                Objects.equals(getIp(), user.getIp()) &&
                Objects.equals(getPort(), user.getPort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getName(), getPsw(), getIp(), getPort(), role);
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", role=" + role +
                '}';
    }
}
