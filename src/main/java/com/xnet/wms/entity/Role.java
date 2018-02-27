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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Muhammad
 */
@Entity
@Table(name = "role", catalog = "inventory", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
    , @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")
    , @NamedQuery(name = "Role.findByCreatedat", query = "SELECT r FROM Role r WHERE r.createdat = :createdat")
    , @NamedQuery(name = "Role.findByUpdatedby", query = "SELECT r FROM Role r WHERE r.updatedby = :updatedby")
    , @NamedQuery(name = "Role.findByUpdatedat", query = "SELECT r FROM Role r WHERE r.updatedat = :updatedat")
    , @NamedQuery(name = "Role.findByDeleted", query = "SELECT r FROM Role r WHERE r.deleted = :deleted")
    , @NamedQuery(name = "Role.findByDeletedat", query = "SELECT r FROM Role r WHERE r.deletedat = :deletedat")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdat;
    @Column(name = "updatedby")
    private Integer updatedby;
    @Column(name = "updatedat")
    @Temporal(TemporalType.DATE)
    private Date updatedat;
    @Column(name = "deleted")
    private Integer deleted;
    @Column(name = "deletedat")
    @Temporal(TemporalType.DATE)
    private Date deletedat;
    @JoinTable(name = "role_menu", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "menu_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Menu> menuCollection;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private User createdby;

    public Role() {
    }

    public Role(Integer id) {
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(Date deletedat) {
        this.deletedat = deletedat;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.Role[ id=" + id + " ]";
    }
    
}
