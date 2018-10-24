package com.br.accommodation.entity;

import com.br.accommodation.entity.HotelReservation;
import com.br.accommodation.entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-02T21:13:57")
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