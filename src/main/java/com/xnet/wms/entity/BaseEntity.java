/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Where;

/**
 *
 * @author ramy
 */
@MappedSuperclass
@Where(clause = "isDeleted=0")
public class BaseEntity {

    @Id
    @GeneratedValue
    private int id;
    private boolean isDeleted = false;
    @ManyToOne(cascade = CascadeType.ALL)
    private User deletedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @Basic(optional = false)
    private User createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date createdDate = new Date();
    @ManyToOne(cascade = CascadeType.ALL)
    private User updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
