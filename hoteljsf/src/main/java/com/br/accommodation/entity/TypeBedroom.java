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
//import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "type_bedroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeBedroom.findAll", query = "SELECT t FROM TypeBedroom t"),
    @NamedQuery(name = "TypeBedroom.findById", query = "SELECT t FROM TypeBedroom t WHERE t.id = :id"),
    @NamedQuery(name = "TypeBedroom.findByName", query = "SELECT t FROM TypeBedroom t WHERE t.name = :name")})
public class TypeBedroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeBedroomId")
    private Collection<Bedroom> bedroomCollection;

    public TypeBedroom() {
    }

    public TypeBedroom(Integer id) {
        this.id = id;
    }

    public TypeBedroom(Integer id, String name) {
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

    @XmlTransient
//    @JsonIgnore
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
        if (!(object instanceof TypeBedroom)) {
            return false;
        }
        TypeBedroom other = (TypeBedroom) object;
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
