package com.br.accommodation.restfull.entity;

import com.br.accommodation.restfull.entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-20T20:21:44")
@StaticMetamodel(UserStatus.class)
public class UserStatus_ { 

    public static volatile SingularAttribute<UserStatus, Integer> code;
    public static volatile CollectionAttribute<UserStatus, User> userCollection;
    public static volatile SingularAttribute<UserStatus, String> name;
    public static volatile SingularAttribute<UserStatus, Integer> id;

}