package com.br.accommodation.restfull.entity;

import com.br.accommodation.restfull.entity.HotelReservation;
import com.br.accommodation.restfull.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T20:21:44")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> gender;
    public static volatile SingularAttribute<Client, String> socialSecurityNumber;
    public static volatile CollectionAttribute<Client, HotelReservation> hotelReservationCollection;
    public static volatile SingularAttribute<Client, String> name;
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile SingularAttribute<Client, User> user;
    public static volatile SingularAttribute<Client, String> email;
    public static volatile SingularAttribute<Client, Date> createDate;

}