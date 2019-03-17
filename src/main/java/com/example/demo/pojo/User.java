package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
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
        this.role=0;
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
        return getRole() == user.getRole() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPsw(), user.getPsw()) &&
                Objects.equals(getIp(), user.getIp()) &&
                Objects.equals(getPort(), user.getPort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPsw(), getIp(), getPort(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", role=" + role +
                '}';
    }
}
