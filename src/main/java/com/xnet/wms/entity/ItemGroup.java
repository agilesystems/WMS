/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramy
 */
@Entity
@Table(name = "item_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemGroup.findAll", query = "SELECT i FROM ItemGroup i")
    , @NamedQuery(name = "ItemGroup.findById", query = "SELECT i FROM ItemGroup i WHERE i.id = :id")
    , @NamedQuery(name = "ItemGroup.findByName", query = "SELECT i FROM ItemGroup i WHERE i.name = :name")
    , @NamedQuery(name = "ItemGroup.findByNotes", query = "SELECT i FROM ItemGroup i WHERE i.notes = :notes")})
public class ItemGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "notes")
    private String notes;
    @OneToMany(mappedBy = "group1")
    private Collection<StoreItem> storeItemCollection;

    public ItemGroup() {
    }

    public ItemGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public Collection<StoreItem> getStoreItemCollection() {
        return storeItemCollection;
    }

    public void setStoreItemCollection(Collection<StoreItem> storeItemCollection) {
        this.storeItemCollection = storeItemCollection;
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
        if (!(object instanceof ItemGroup)) {
            return false;
        }
        ItemGroup other = (ItemGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.ItemGroup[ id=" + id + " ]";
    }
    
}
