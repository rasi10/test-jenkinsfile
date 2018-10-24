package com.br.accommodation.entity;

import com.br.accommodation.entity.Bill;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-02T21:13:57")
@StaticMetamodel(BillStatus.class)
public class BillStatus_ { 

    public static volatile SingularAttribute<BillStatus, Integer> code;
    public static volatile SingularAttribute<BillStatus, String> name;
    public static volatile SingularAttribute<BillStatus, Integer> id;
    public static volatile CollectionAttribute<BillStatus, Bill> billCollection;

}