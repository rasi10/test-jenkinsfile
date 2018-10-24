/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "hotel_reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelReservation.findAll", query = "SELECT h FROM HotelReservation h"),
    @NamedQuery(name = "HotelReservation.findById", query = "SELECT h FROM HotelReservation h WHERE h.id = :id"),
    @NamedQuery(name = "HotelReservation.findByEntryDate", query = "SELECT h FROM HotelReservation h WHERE h.entryDate = :entryDate"),
    @NamedQuery(name = "HotelReservation.findByExitDate", query = "SELECT h FROM HotelReservation h WHERE h.exitDate = :exitDate"),
    @NamedQuery(name = "HotelReservation.findByPriceDaily", query = "SELECT h FROM HotelReservation h WHERE h.priceDaily = :priceDaily")})
public class HotelReservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry_date")
    @Temporal(TemporalType.DATE)
    private Date entryDate;
    @Column(name = "exit_date")
    @Temporal(TemporalType.DATE)
    private Date exitDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_daily")
    private BigDecimal priceDaily;
    @JoinColumn(name = "bedroom_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bedroom bedroomId;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Client clientId;
    @JoinColumn(name = "reservation_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ReservationStatus reservationStatusId;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hotelReservationId")
    private Bill bill;

    public HotelReservation() {
    }

    public HotelReservation(Integer id) {
        this.id = id;
    }

    public HotelReservation(Integer id, Date entryDate, BigDecimal priceDaily) {
        this.id = id;
        this.entryDate = entryDate;
        this.priceDaily = priceDaily;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public BigDecimal getPriceDaily() {
        return priceDaily;
    }

    public void setPriceDaily(BigDecimal priceDaily) {
        this.priceDaily = priceDaily;
    }

    public Bedroom getBedroomId() {
        return bedroomId;
    }

    public void setBedroomId(Bedroom bedroomId) {
        this.bedroomId = bedroomId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public ReservationStatus getReservationStatusId() {
        return reservationStatusId;
    }

    public void setReservationStatusId(ReservationStatus reservationStatusId) {
        this.reservationStatusId = reservationStatusId;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
        if (!(object instanceof HotelReservation)) {
            return false;
        }
        HotelReservation other = (HotelReservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.accommodation.restfull.entity.HotelReservation[ id=" + id + " ]";
    }
    
}
