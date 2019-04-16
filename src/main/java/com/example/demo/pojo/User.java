package com.example.demo.pojo;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Contact{

    @Id
    @Column(length = 40)
    private String name;
    @Column(length = 40)
    private String psw;

    private int role;
    @Transient
    private String type = "user";
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

    public User setPsw(String psw) {
        this.psw = psw;
        return this;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"psw\":\"")
                .append(psw).append('\"');
        sb.append(",\"role\":")
                .append(role);
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getRole() == user.getRole() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPsw(), user.getPsw()) &&
                Objects.equals(type, user.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPsw(), getRole(), type);
    }
}
