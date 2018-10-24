package com.br.accommodation.restfull.entity;

import com.br.accommodation.restfull.entity.Bedroom;
import com.br.accommodation.restfull.entity.Bill;
import com.br.accommodation.restfull.entity.Client;
import com.br.accommodation.restfull.entity.ReservationStatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T20:21:44")
@StaticMetamodel(HotelReservation.class)
public class HotelReservation_ { 

    public static volatile SingularAttribute<HotelReservation, Client> clientId;
    public static volatile SingularAttribute<HotelReservation, BigDecimal> priceDaily;
    public static volatile SingularAttribute<HotelReservation, Date> entryDate;
    public static volatile SingularAttribute<HotelReservation, Bedroom> bedroomId;
    public static volatile SingularAttribute<HotelReservation, ReservationStatus> reservationStatusId;
    public static volatile SingularAttribute<HotelReservation, Bill> bill;
    public static volatile SingularAttribute<HotelReservation, Integer> id;
    public static volatile SingularAttribute<HotelReservation, Date> exitDate;

}