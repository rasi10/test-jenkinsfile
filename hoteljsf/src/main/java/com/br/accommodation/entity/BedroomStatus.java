/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "bedroom_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BedroomStatus.findAll", query = "SELECT b FROM BedroomStatus b"),
    @NamedQuery(name = "BedroomStatus.findById", query = "SELECT b FROM BedroomStatus b WHERE b.id = :id"),
    @NamedQuery(name = "BedroomStatus.findByCode", query = "SELECT b FROM BedroomStatus b WHERE b.code = :code"),
    @NamedQuery(name = "BedroomStatus.findByName", query = "SELECT b FROM BedroomStatus b WHERE b.name = :name")})
public class BedroomStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bedroomStatusId")
    private Collection<Bedroom> bedroomCollection;

    public BedroomStatus() {
    }

    public BedroomStatus(Integer id) {
        this.id = id;
    }

    public BedroomStatus(Integer id, int code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Bedroom> getBedroomCollection() {
        return bedroomCollection;
    }

    public void setBedroomCollection(Collection<Bedroom> bedroomCollection) {
        this.bedroomCollection = bedroomCollection;
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
        if (!(object instanceof BedroomStatus)) {
            return false;
        }
        BedroomStatus other = (BedroomStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
