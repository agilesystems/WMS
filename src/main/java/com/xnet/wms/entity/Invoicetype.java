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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramy
 */
@Entity
@Table(name = "invoicetype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoicetype.findAll", query = "SELECT i FROM Invoicetype i")
    , @NamedQuery(name = "Invoicetype.findById", query = "SELECT i FROM Invoicetype i WHERE i.id = :id")
    , @NamedQuery(name = "Invoicetype.findByName", query = "SELECT i FROM Invoicetype i WHERE i.name = :name")
    , @NamedQuery(name = "Invoicetype.findByNotes", query = "SELECT i FROM Invoicetype i WHERE i.notes = :notes")})
public class Invoicetype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "notes")
    private String notes;

    public Invoicetype() {
    }

    public Invoicetype(Integer id) {
        this.id = id;
    }

    public Invoicetype(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoicetype)) {
            return false;
        }
        Invoicetype other = (Invoicetype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.Invoicetype[ id=" + id + " ]";
    }
    
}
