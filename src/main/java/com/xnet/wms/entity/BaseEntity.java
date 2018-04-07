/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ramy
 */
@MappedSuperclass
@Where(clause = "is_deleted=0")
public class BaseEntity {

    @Id
    @GeneratedValue
    private int id;
    private boolean isDeleted = false;
    @ManyToOne
    private User deletedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
    @ManyToOne
    @Basic(optional = false)
    private User createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date createdDate = new Date();
    @ManyToOne
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
