/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.controller.util;


import com.br.accommodation.controller.HotelReservationController;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
/**
 *
 * @author MarcosAlexandrede
 */
public class BedroomPriceValueListener implements ValueChangeListener{
    @Override
    public void processValueChange(ValueChangeEvent event)
                    throws AbortProcessingException {
        HotelReservationController hotelReservation = (HotelReservationController) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("hotelReservationController");
        hotelReservation.getSelected().setPriceDaily(hotelReservation.getSelected().getBedroomId().getPriceDaily());

    }
}
