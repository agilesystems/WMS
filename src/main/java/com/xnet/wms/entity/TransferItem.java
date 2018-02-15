/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Muhammad
 */
@Entity
@Table(catalog = "inventory", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransferItem.findAll", query = "SELECT t FROM TransferItem t")
    , @NamedQuery(name = "TransferItem.findById", query = "SELECT t FROM TransferItem t WHERE t.id = :id")
    , @NamedQuery(name = "TransferItem.findByQuantity", query = "SELECT t FROM TransferItem t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "TransferItem.findByPrice", query = "SELECT t FROM TransferItem t WHERE t.price = :price")})
public class TransferItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Float price;
    @JoinColumn(name = "transferid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Transfer transferid;
    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item itemid;

    public TransferItem() {
    }

    public TransferItem(Integer id) {
        this.id = id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Transfer getTransferid() {
        return transferid;
    }

    public void setTransferid(Transfer transferid) {
        this.transferid = transferid;
    }

    public Item getItemid() {
        return itemid;
    }

    public void setItemid(Item itemid) {
        this.itemid = itemid;
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
        if (!(object instanceof TransferItem)) {
            return false;
        }
        TransferItem other = (TransferItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.TransferItem[ id=" + id + " ]";
    }

}
