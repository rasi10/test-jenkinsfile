package com.br.accommodation.restfull.entity;

import com.br.accommodation.restfull.entity.HotelReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T20:21:44")
@StaticMetamodel(ReservationStatus.class)
public class ReservationStatus_ { 

    public static volatile SingularAttribute<ReservationStatus, Integer> code;
    public static volatile CollectionAttribute<ReservationStatus, HotelReservation> hotelReservationCollection;
    public static volatile SingularAttribute<ReservationStatus, String> name;
    public static volatile SingularAttribute<ReservationStatus, Integer> id;

}