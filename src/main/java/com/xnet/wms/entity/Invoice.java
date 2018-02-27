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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Muhammad
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
    , @NamedQuery(name = "Invoice.findByRefrence", query = "SELECT i FROM Invoice i WHERE i.refrence = :refrence")
    , @NamedQuery(name = "Invoice.findByTransactionDate", query = "SELECT i FROM Invoice i WHERE i.transactionDate = :transactionDate")
    , @NamedQuery(name = "Invoice.findByDiscountper", query = "SELECT i FROM Invoice i WHERE i.discountper = :discountper")
    , @NamedQuery(name = "Invoice.findByDiscountValue", query = "SELECT i FROM Invoice i WHERE i.discountValue = :discountValue")
    , @NamedQuery(name = "Invoice.findByTax1per", query = "SELECT i FROM Invoice i WHERE i.tax1per = :tax1per")
    , @NamedQuery(name = "Invoice.findByTax1val", query = "SELECT i FROM Invoice i WHERE i.tax1val = :tax1val")
    , @NamedQuery(name = "Invoice.findByTax2per", query = "SELECT i FROM Invoice i WHERE i.tax2per = :tax2per")
    , @NamedQuery(name = "Invoice.findByTax2val", query = "SELECT i FROM Invoice i WHERE i.tax2val = :tax2val")
    , @NamedQuery(name = "Invoice.findByTax3per", query = "SELECT i FROM Invoice i WHERE i.tax3per = :tax3per")
    , @NamedQuery(name = "Invoice.findByTax3val", query = "SELECT i FROM Invoice i WHERE i.tax3val = :tax3val")
    , @NamedQuery(name = "Invoice.findByTotal", query = "SELECT i FROM Invoice i WHERE i.total = :total")
    , @NamedQuery(name = "Invoice.findByPaied", query = "SELECT i FROM Invoice i WHERE i.paied = :paied")
    , @NamedQuery(name = "Invoice.findByNet", query = "SELECT i FROM Invoice i WHERE i.net = :net")
    , @NamedQuery(name = "Invoice.findByOutstanding", query = "SELECT i FROM Invoice i WHERE i.outstanding = :outstanding")
    , @NamedQuery(name = "Invoice.findByCreatedat", query = "SELECT i FROM Invoice i WHERE i.createdat = :createdat")
    , @NamedQuery(name = "Invoice.findByUpdatedby", query = "SELECT i FROM Invoice i WHERE i.updatedby = :updatedby")
    , @NamedQuery(name = "Invoice.findByUpdatedat", query = "SELECT i FROM Invoice i WHERE i.updatedat = :updatedat")
    , @NamedQuery(name = "Invoice.findByDeleted", query = "SELECT i FROM Invoice i WHERE i.deleted = :deleted")
    , @NamedQuery(name = "Invoice.findByDeletedby", query = "SELECT i FROM Invoice i WHERE i.deletedby = :deletedby")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "refrence")
    private String refrence;
    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "discountper")
    private Integer discountper;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount_value")
    private Float discountValue;
    @Column(name = "tax1per")
    private Integer tax1per;
    @Column(name = "tax1val")
    private Float tax1val;
    @Column(name = "tax2per")
    private Integer tax2per;
    @Column(name = "tax2val")
    private Float tax2val;
    @Column(name = "tax3per")
    private Integer tax3per;
    @Column(name = "tax3val")
    private Float tax3val;
    @Column(name = "total")
    private Float total;
    @Column(name = "paied")
    private Float paied;
    @Column(name = "net")
    private Float net;
    @Column(name = "outstanding")
    private Float outstanding;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
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
    @OneToMany(mappedBy = "invoice")
    private Collection<InvoiceItem> invoiceItemCollection;
    @JoinColumn(name = "store", referencedColumnName = "id")
    @ManyToOne
    private Store store;
    @JoinColumn(name = "account", referencedColumnName = "id")
    @ManyToOne
    private Account account;
    @JoinColumn(name = "invoice_type", referencedColumnName = "id")
    @ManyToOne
    private InvoiceType invoiceType;
    @JoinColumn(name = "cach_type", referencedColumnName = "id")
    @ManyToOne
    private CashType cachType;
    @JoinColumn(name = "createdby", referencedColumnName = "id")
    @ManyToOne
    private User createdby;
    @JoinColumn(name = "branch", referencedColumnName = "id")
    @ManyToOne
    private Branch branch;

    public Invoice() {
    }

    public Invoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefrence() {
        return refrence;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getDiscountper() {
        return discountper;
    }

    public void setDiscountper(Integer discountper) {
        this.discountper = discountper;
    }

    public Float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Float discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getTax1per() {
        return tax1per;
    }

    public void setTax1per(Integer tax1per) {
        this.tax1per = tax1per;
    }

    public Float getTax1val() {
        return tax1val;
    }

    public void setTax1val(Float tax1val) {
        this.tax1val = tax1val;
    }

    public Integer getTax2per() {
        return tax2per;
    }

    public void setTax2per(Integer tax2per) {
        this.tax2per = tax2per;
    }

    public Float getTax2val() {
        return tax2val;
    }

    public void setTax2val(Float tax2val) {
        this.tax2val = tax2val;
    }

    public Integer getTax3per() {
        return tax3per;
    }

    public void setTax3per(Integer tax3per) {
        this.tax3per = tax3per;
    }

    public Float getTax3val() {
        return tax3val;
    }

    public void setTax3val(Float tax3val) {
        this.tax3val = tax3val;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getPaied() {
        return paied;
    }

    public void setPaied(Float paied) {
        this.paied = paied;
    }

    public Float getNet() {
        return net;
    }

    public void setNet(Float net) {
        this.net = net;
    }

    public Float getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Float outstanding) {
        this.outstanding = outstanding;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public CashType getCachType() {
        return cachType;
    }

    public void setCachType(CashType cachType) {
        this.cachType = cachType;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xnet.wms.entity.Invoice[ id=" + id + " ]";
    }
    
}
