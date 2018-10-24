package com.br.accommodation.entity;

import com.br.accommodation.entity.BillStatus;
import com.br.accommodation.entity.HotelReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-02T21:13:57")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, BillStatus> billStatusId;
    public static volatile SingularAttribute<Bill, Integer> id;
    public static volatile SingularAttribute<Bill, HotelReservation> hotelReservationId;

}