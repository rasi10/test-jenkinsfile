/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.bean;

import com.br.accommodation.entity.HotelReservation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MarcosAlexandrede
 */
@Stateless
public class HotelReservationFacade extends AbstractFacade<HotelReservation> {

    @PersistenceContext(unitName = "com.br_accommodation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em==null){
            em = Persistence.createEntityManagerFactory("com.br_accommodation_war_1.0-SNAPSHOTPU").createEntityManager();
        }
        return em;
    }
    
    public Long countHotelReservationOfCodeStatus(int code){
        String qlString  =  "SELECT count(h) FROM HotelReservation as h left join h.reservationStatusId r WHERE r.code = :code";
        Object a = getEntityManager()
                .createQuery(qlString)
                .setParameter("code", code)
                .getSingleResult();
        return (Long) a;
    }

    public HotelReservationFacade() {
        super(HotelReservation.class);
    }
    
}
