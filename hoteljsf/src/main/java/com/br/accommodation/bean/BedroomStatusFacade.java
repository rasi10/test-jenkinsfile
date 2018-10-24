/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.bean;

import com.br.accommodation.entity.BedroomStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MarcosAlexandrede
 */
@Stateless
public class BedroomStatusFacade extends AbstractFacade<BedroomStatus> {

    @PersistenceContext(unitName = "com.br_accommodation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em==null){
            em = Persistence.createEntityManagerFactory("com.br_accommodation_war_1.0-SNAPSHOTPU").createEntityManager();
        }
        return em;
    }
    
    public BedroomStatus findByCode(Object code){
        BedroomStatus a = (BedroomStatus) getEntityManager().createNamedQuery("BedroomStatus.findByCode")
        .setParameter("code", code)
        .getSingleResult();
        return a;
    }
    
    public List<BedroomStatus> findByName(Object code){
        List a = getEntityManager().createNamedQuery("BedroomStatus.findByName")
        .setParameter("name", code)
        .getResultList();
        return a;
    }
    public BedroomStatusFacade() {
        super(BedroomStatus.class);
    }
    
}
