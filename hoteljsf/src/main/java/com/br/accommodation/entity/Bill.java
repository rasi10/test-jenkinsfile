/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id")})
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "bill_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BillStatus billStatusId;
    @JoinColumn(name = "hotel_reservation_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private HotelReservation hotelReservationId;
    @Transient
    private BigDecimal totalBill;
    @Transient
    private int totalDays;

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BillStatus getBillStatusId() {
        return billStatusId;
    }

    public void setBillStatusId(BillStatus billStatusId) {
        this.billStatusId = billStatusId;
    }

    public HotelReservation getHotelReservationId() {
        return hotelReservationId;
    }

    public void setHotelReservationId(HotelReservation hotelReservationId) {
        this.hotelReservationId = hotelReservationId;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Number Bill: " + id;
    }

    /**
     * @return the totalBill
     */
    public BigDecimal getTotalBill() {
        return totalBill;
    }

    /**
     * @param totalBill the totalBill to set
     */
    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    /**
     * @return the totalDays
     */
    public int getTotalDays() {
        return totalDays;
    }

    /**
     * @param totalDays the totalDays to set
     */
    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
    
}
