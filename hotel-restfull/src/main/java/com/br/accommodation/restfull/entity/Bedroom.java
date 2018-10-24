/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "bedroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bedroom.findAll", query = "SELECT b FROM Bedroom b"),
    @NamedQuery(name = "Bedroom.findById", query = "SELECT b FROM Bedroom b WHERE b.id = :id"),
    @NamedQuery(name = "Bedroom.findByDescription", query = "SELECT b FROM Bedroom b WHERE b.description = :description"),
    @NamedQuery(name = "Bedroom.findByFloor", query = "SELECT b FROM Bedroom b WHERE b.floor = :floor"),
    @NamedQuery(name = "Bedroom.findByNumber", query = "SELECT b FROM Bedroom b WHERE b.number = :number"),
    @NamedQuery(name = "Bedroom.findByPriceDaily", query = "SELECT b FROM Bedroom b WHERE b.priceDaily = :priceDaily")})
public class Bedroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "description")
    private String description;
    @Column(name = "floor")
    private Integer floor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_daily")
    private BigDecimal priceDaily;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bedroomId")
    @JsonIgnore
    private Collection<HotelReservation> hotelReservationCollection;
    @JoinColumn(name = "bedroom_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BedroomStatus bedroomStatusId;
    @JoinColumn(name = "type_bedroom_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeBedroom typeBedroomId;

    public Bedroom() {
    }

    public Bedroom(Integer id) {
        this.id = id;
    }

    public Bedroom(Integer id, String description, int number, BigDecimal priceDaily) {
        this.id = id;
        this.description = description;
        this.number = number;
        this.priceDaily = priceDaily;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPriceDaily() {
        return priceDaily;
    }

    public void setPriceDaily(BigDecimal priceDaily) {
        this.priceDaily = priceDaily;
    }

    @XmlTransient
    public Collection<HotelReservation> getHotelReservationCollection() {
        return hotelReservationCollection;
    }

    public void setHotelReservationCollection(Collection<HotelReservation> hotelReservationCollection) {
        this.hotelReservationCollection = hotelReservationCollection;
    }

    public BedroomStatus getBedroomStatusId() {
        return bedroomStatusId;
    }

    public void setBedroomStatusId(BedroomStatus bedroomStatusId) {
        this.bedroomStatusId = bedroomStatusId;
    }

    public TypeBedroom getTypeBedroomId() {
        return typeBedroomId;
    }

    public void setTypeBedroomId(TypeBedroom typeBedroomId) {
        this.typeBedroomId = typeBedroomId;
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
        if (!(object instanceof Bedroom)) {
            return false;
        }
        Bedroom other = (Bedroom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.accommodation.restfull.entity.Bedroom[ id=" + id + " ]";
    }
    
}
