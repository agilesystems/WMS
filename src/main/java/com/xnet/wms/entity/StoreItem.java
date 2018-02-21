/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramy
 */
@Entity
@Table(name = "store_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreItem.findAll", query = "SELECT s FROM StoreItem s")
    , @NamedQuery(name = "StoreItem.findById", query = "SELECT s FROM StoreItem s WHERE s.id = :id")
    , @NamedQuery(name = "StoreItem.findByQuantity", query = "SELECT s FROM StoreItem s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "StoreItem.findBySalprice", query = "SELECT s FROM StoreItem s WHERE s.salprice = :salprice")
    , @NamedQuery(name = "StoreItem.findByBuyprice", query = "SELECT s FROM StoreItem s WHERE s.buyprice = :buyprice")
    , @NamedQuery(name = "StoreItem.findByLocalcode", query = "SELECT s FROM StoreItem s WHERE s.localcode = :localcode")
    , @NamedQuery(name = "StoreItem.findByLowestprice", query = "SELECT s FROM StoreItem s WHERE s.lowestprice = :lowestprice")
    , @NamedQuery(name = "StoreItem.findByLowestquantity", query = "SELECT s FROM StoreItem s WHERE s.lowestquantity = :lowestquantity")
    , @NamedQuery(name = "StoreItem.findByIsdead", query = "SELECT s FROM StoreItem s WHERE s.isdead = :isdead")
    , @NamedQuery(name = "StoreItem.findByExtrainfo", query = "SELECT s FROM StoreItem s WHERE s.extrainfo = :extrainfo")
    , @NamedQuery(name = "StoreItem.findByPhotopath", query = "SELECT s FROM StoreItem s WHERE s.photopath = :photopath")
    , @NamedQuery(name = "StoreItem.findByExpiredate", query = "SELECT s FROM StoreItem s WHERE s.expiredate = :expiredate")
    , @NamedQuery(name = "StoreItem.findByCreatedat", query = "SELECT s FROM StoreItem s WHERE s.createdat = :createdat")
    , @NamedQuery(name = "StoreItem.findByUpdatedby", query = "SELECT s FROM StoreItem s WHERE s.updatedby = :updatedby")
    , @NamedQuery(name = "StoreItem.findByUpdatedat", query = "SELECT s FROM StoreItem s WHERE s.updatedat = :updatedat")
    , @NamedQuery(name = "StoreItem.findByDeleted", query = "SELECT s FROM StoreItem s WHERE s.deleted = :deleted")
    , @NamedQuery(name = "StoreItem.findByDeletedby", query = "SELECT s FROM StoreItem s WHERE s.deletedby = :deletedby")})
public class StoreItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salprice")
    private BigDecimal salprice;
    @Column(name = "buyprice")
    private BigDecimal buyprice;
    @Size(max = 255)
    @Column(name = "localcode")
    private String localcode;
    @Column(name = "lowestprice")
    private BigDecimal lowestprice;
    @Column(name = "lowestquantity")
    private Integer lowestquantity;
    @Column(name = "isdead")
    private Boolean isdead;
    @Size(max = 255)
    @Column(name = "extrainfo")
    private String extrainfo;
    @Size(max = 255)
    @Column(name = "photopath")
    private String photopath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiredate")
    @Temporal(TemporalType.DATE)
    private Date expiredate;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @Column(name = "updatedby")
    private Integer updatedby;
    @Column(name = "updatedat")
    @Temporal(TemporalType.DATE)
    private Date updatedat;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "deletedby")
    private Integer deletedby;
    @OneToMany(mappedBy = "storeItem")
    private Collection<InvoiceItem> invoiceItemCollection;
    @OneToMany(mappedBy = "item")
    private Collection<TransferItem> transferItemCollection;
    @JoinColumn(name = "item", referencedColumnName = "id")
    @ManyToOne
    private Item item;
    @JoinColumn(name = "branch", referencedColumnName = "id")
    @ManyToOne
    private Branch branch;
    @JoinColumn(name = "store", referencedColumnName = "id")
    @ManyToOne
    private Store store;
    @JoinColumn(name = "unit", referencedColumnName = "id")
    @ManyToOne
    private Unit unit;
    @JoinColumn(name = "group", referencedColumnName = "id")
    @ManyToOne
    private ItemGroup group1;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private User createdby;

    public StoreItem() {
    }

    public StoreItem(Integer id) {
        this.id = id;
    }

    public StoreItem(Integer id, Date expiredate) {
        this.id = id;
        this.expiredate = expiredate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalprice() {
        return salprice;
    }

    public void setSalprice(BigDecimal salprice) {
        this.salprice = salprice;
    }

    public BigDecimal getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(BigDecimal buyprice) {
        this.buyprice = buyprice;
    }

    public String getLocalcode() {
        return localcode;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public BigDecimal getLowestprice() {
        return lowestprice;
    }

    public void setLowestprice(BigDecimal lowestprice) {
        this.lowestprice = lowestprice;
    }

    public Integer getLowestquantity() {
        return lowestquantity;
    }

    public void setLowestquantity(Integer lowestquantity) {
        this.lowestquantity = lowestquantity;
    }

    public Boolean getIsdead() {
        return isdead;
    }

    public void setIsdead(Boolean isdead) {
        this.isdead = isdead;
    }

    public String getExtrainfo() {
        return extrainfo;
    }

    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
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

    @XmlTransient
    public Collection<InvoiceItem> getInvoiceItemCollection() {
        return invoiceItemCollection;
    }

    public void setInvoiceItemCollection(Collection<InvoiceItem> invoiceItemCollection) {
        this.invoiceItemCollection = invoiceItemCollection;
    }

    @XmlTransient
    public Collection<TransferItem> getTransferItemCollection() {
        return transferItemCollection;
    }

    public void setTransferItemCollection(Collection<TransferItem> transferItemCollection) {
        this.transferItemCollection = transferItemCollection;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ItemGroup getGroup1() {
        return group1;
    }

    public void setGroup1(ItemGroup group1) {
        this.group1 = group1;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
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
        if (!(object instanceof StoreItem)) {
            return false;
        }
        StoreItem other = (StoreItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.StoreItem[ id=" + id + " ]";
    }
    
}
