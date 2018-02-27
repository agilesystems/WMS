/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Muhammad
 */
@Entity
@Table(name = "transfer", catalog = "inventory", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")
    , @NamedQuery(name = "Transfer.findByTransferdate", query = "SELECT t FROM Transfer t WHERE t.transferdate = :transferdate")
    , @NamedQuery(name = "Transfer.findByCreatedat", query = "SELECT t FROM Transfer t WHERE t.createdat = :createdat")
    , @NamedQuery(name = "Transfer.findByUpdatedby", query = "SELECT t FROM Transfer t WHERE t.updatedby = :updatedby")
    , @NamedQuery(name = "Transfer.findByUpdatedat", query = "SELECT t FROM Transfer t WHERE t.updatedat = :updatedat")
    , @NamedQuery(name = "Transfer.findByDeleted", query = "SELECT t FROM Transfer t WHERE t.deleted = :deleted")
    , @NamedQuery(name = "Transfer.findByDeletedby", query = "SELECT t FROM Transfer t WHERE t.deletedby = :deletedby")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "transferdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferdate;
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Column(name = "updatedby")
    private Integer updatedby;
    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedat;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "deletedby")
    private Integer deletedby;
    @JoinColumn(name = "store_from", referencedColumnName = "id")
    @ManyToOne
    private Store storeFrom;
    @JoinColumn(name = "store_to", referencedColumnName = "id")
    @ManyToOne
    private Store storeTo;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private User createdby;
    @JoinColumn(name = "branch", referencedColumnName = "id")
    @ManyToOne
    private Branch branch;
    @OneToMany(mappedBy = "transfer")
    private Collection<TransferItem> transferItemCollection;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDeletedby() {
        return deletedby;
    }

    public void setDeletedby(Integer deletedby) {
        this.deletedby = deletedby;
    }

    public Store getStoreFrom() {
        return storeFrom;
    }

    public void setStoreFrom(Store storeFrom) {
        this.storeFrom = storeFrom;
    }

    public Store getStoreTo() {
        return storeTo;
    }

    public void setStoreTo(Store storeTo) {
        this.storeTo = storeTo;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @XmlTransient
    public Collection<TransferItem> getTransferItemCollection() {
        return transferItemCollection;
    }

    public void setTransferItemCollection(Collection<TransferItem> transferItemCollection) {
        this.transferItemCollection = transferItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.Transfer[ id=" + id + " ]";
    }
    
}
