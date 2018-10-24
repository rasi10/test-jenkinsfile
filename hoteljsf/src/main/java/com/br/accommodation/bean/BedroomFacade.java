/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.bean;

import com.br.accommodation.entity.Bedroom;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MarcosAlexandrede
 */
@Stateless
public class BedroomFacade extends AbstractFacade<Bedroom> {

    @PersistenceContext(unitName = "com.br_accommodation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em==null){
            em = Persistence.createEntityManagerFactory("com.br_accommodation_war_1.0-SNAPSHOTPU").createEntityManager();
        }
        return em;
    }
    
    public List<Bedroom> findByReservation(Date startDate, Date endDate){
        
        String qlString  =  "SELECT b FROM Bedroom as b left join b.hotelReservationCollection h WHERE (" +
                            "not( (h.exitDate < :endDate and h.exitDate > :startDate) or " +
                            "(h.entryDate < :endDate and h.entryDate > :startDate) or " +
                            "(h.entryDate < :startDate and h.exitDate > :endDate ))) or " +
                            "(h.entryDate is null and h.exitDate is null)";
        List a = getEntityManager()
        .createQuery(qlString)
        .setParameter("startDate", startDate)
        .setParameter("endDate", endDate)
        .getResultList();
        return a;
    }
    
    public Long countBedromOfCodeStatus(int code){
        String qlString  =  "SELECT count(b) FROM Bedroom as b left join b.bedroomStatusId s WHERE s.code = :code";
        Object a = getEntityManager()
                .createQuery(qlString)
                .setParameter("code", code)
                .getSingleResult();
        return (Long) a;
    }

    public BedroomFacade() {
        super(Bedroom.class);
    }
    
}
