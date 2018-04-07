/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ramy
 */
@Entity(name = "system_exception")
public class SystemException implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Lob
    private String message;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date exceptionDate;
    private String request;

    public SystemException(String message, String request) {
        this.message = message;
        this.exceptionDate = new Date();
        this.request = request;
    }

    public SystemException() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getExceptionDate() {
        return exceptionDate;
    }

    public void setExceptionDate(Date exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

}
