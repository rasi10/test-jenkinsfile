package com.br.accommodation.entity;

import com.br.accommodation.entity.Client;
import com.br.accommodation.entity.UserStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-02T21:13:57")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Client> clientId;
    public static volatile SingularAttribute<User, Integer> typeUser;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, UserStatus> userStatusId;
    public static volatile SingularAttribute<User, String> login;

}