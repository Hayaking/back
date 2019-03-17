package com.example.demo.pojo;

import javax.persistence.Transient;

public interface Contact {
    String name = null;
    @Transient
    String type = null;

}
