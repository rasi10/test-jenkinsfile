/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author MarcosAlexandrede
 */
@javax.ws.rs.ApplicationPath("webresources")
    public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        // following code can be used to customize Jersey 1.x JSON provider:
        try {
            Class jacksonProvider = Class.forName("org.codehaus.jackson.jaxrs.JacksonJsonProvider");
            resources.add(jacksonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.br.accommodation.restfull.service.BedroomFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.BedroomStatusFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.BillFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.BillStatusFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.ClientFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.HotelReservationFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.ReservationStatusFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.TypeBedroomFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.UserFacadeREST.class);
        resources.add(com.br.accommodation.restfull.service.UserStatusFacadeREST.class);
    }
    
}
