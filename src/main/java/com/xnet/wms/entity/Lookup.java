/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramy
 */
@Entity
@Table(name = "lookup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lookup.findAll", query = "SELECT l FROM Lookup l")
    , @NamedQuery(name = "Lookup.findById", query = "SELECT l FROM Lookup l WHERE l.id = :id")
    , @NamedQuery(name = "Lookup.findByParent", query = "SELECT l FROM Lookup l WHERE l.parent = :parent")
    , @NamedQuery(name = "Lookup.findByName", query = "SELECT l FROM Lookup l WHERE l.name = :name")})
public class Lookup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "parent")
    private Integer parent;
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    public Lookup() {
    }

    public Lookup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Lookup)) {
            return false;
        }
        Lookup other = (Lookup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.Lookup[ id=" + id + " ]";
    }
    
}
