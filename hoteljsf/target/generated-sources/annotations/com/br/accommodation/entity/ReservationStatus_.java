package com.br.accommodation.entity;

import com.br.accommodation.entity.HotelReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-02T21:13:57")
@StaticMetamodel(ReservationStatus.class)
public class ReservationStatus_ { 

    public static volatile SingularAttribute<ReservationStatus, Integer> code;
    public static volatile CollectionAttribute<ReservationStatus, HotelReservation> hotelReservationCollection;
    public static volatile SingularAttribute<ReservationStatus, String> name;
    public static volatile SingularAttribute<ReservationStatus, Integer> id;

}