package com.example.demo.pojo;

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

    public void setPsw(String psw) {
        this.psw = psw;
    }



    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", role=" + role +
                ", type='" + type + '\'' +
                '}';
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
