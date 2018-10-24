package com.br.accommodation.controller;

import com.br.accommodation.entity.HotelReservation;
import com.br.accommodation.controller.util.JsfUtil;
import com.br.accommodation.controller.util.PaginationHelper;
import com.br.accommodation.bean.HotelReservationFacade;
import com.br.accommodation.entity.Bedroom;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named("hotelReservationController")
@ManagedBean
@SessionScoped
//@RequestScoped
//@ViewScoped
public class HotelReservationController implements Serializable {

    /**
     * @return the itemsAvailablesBedroom
     */
    public static SelectItem[] getItemsAvailablesBedroom() {
        return itemsAvailablesBedroom;
    }

    /**
     * @param aItemsAvailablesBedroom the itemsAvailablesBedroom to set
     */
    public static void setItemsAvailablesBedroom(SelectItem[] aItemsAvailablesBedroom) {
        itemsAvailablesBedroom = aItemsAvailablesBedroom;
    }

    private HotelReservation current;
    private DataModel items = null;
    @EJB
    private com.br.accommodation.bean.HotelReservationFacade ejbFacade;
    @EJB
    private com.br.accommodation.bean.BedroomStatusFacade ejbBedroomStatusFacade;
    @EJB
    private com.br.accommodation.bean.BedroomFacade ejbBedroomFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private static SelectItem[] itemsAvailablesBedroom;
//    private UIInput entrDT = null;
    public HotelReservationController() {
    }

    public HotelReservation getSelected() {
        if (current == null) {
            current = new HotelReservation();
            selectedItemIndex = -1;
        }
        return current;
    }

    @PostConstruct
    public void init() {
//        ejbFacade = new com.br.accommodation.bean.HotelReservationFacade();
        ejbBedroomStatusFacade = new com.br.accommodation.bean.BedroomStatusFacade();
        ejbBedroomFacade = new com.br.accommodation.bean.BedroomFacade();
    }
    
    private HotelReservationFacade getFacade() {
        return ejbFacade;
    }
    
    public SelectItem[] getItemsAvailableSelectOneBedroom() {
        if (current != null && current.getEntryDate() != null && current.getExitDate() != null) 
            HotelReservationController.setItemsAvailablesBedroom(JsfUtil.getSelectItems(getEjbBedroomFacade().findByReservation(current.getEntryDate(), current.getExitDate()), true));
        
        return HotelReservationController.getItemsAvailablesBedroom();
    }
    public void onPriceDailyChange(){
        if(current !=null && !current.equals(""))
            current.setPriceDaily(current.getBedroomId().getPriceDaily());
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (HotelReservation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new HotelReservation();
        selectedItemIndex = -1;
        Date today=new Date();
        long ltime=today.getTime()+8*24*60*60*1000;
        Date today8=new Date(ltime);
        current.setEntryDate(today);
        current.setExitDate(today8);
        return "Create";
    }
    private void alterStatusBedroom() throws Exception
    {
        if (current != null && current.getReservationStatusId().getCode()==5) {
            current.getBedroomId().setBedroomStatusId(getEjbBedroomStatusFacade().findByCode(1));
            getEjbBedroomFacade().edit(current.getBedroomId());
        }
    }
    
    public String List(){
        recreateModel();
        return "/hotelReservation/List";
    }
    
    public Long getCountOfStatus(int code){
        return getFacade().countHotelReservationOfCodeStatus(code);
    }

    public String create() {
        try {
            alterStatusBedroom();
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HotelReservationCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (HotelReservation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            alterStatusBedroom();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HotelReservationUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (HotelReservation) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("HotelReservationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public HotelReservation getHotelReservation(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    /**
     * @return the ejbBedroomStatusFacade
     */
    public com.br.accommodation.bean.BedroomStatusFacade getEjbBedroomStatusFacade() {
        return ejbBedroomStatusFacade;
    }

    /**
     * @param ejbBedroomStatusFacade the ejbBedroomStatusFacade to set
     */
    public void setEjbBedroomStatusFacade(com.br.accommodation.bean.BedroomStatusFacade ejbBedroomStatusFacade) {
        this.ejbBedroomStatusFacade = ejbBedroomStatusFacade;
    }

    /**
     * @return the ejbBedroomFacade
     */
    public com.br.accommodation.bean.BedroomFacade getEjbBedroomFacade() {
        return ejbBedroomFacade;
    }

    /**
     * @param ejbBedroomFacade the ejbBedroomFacade to set
     */
    public void setEjbBedroomFacade(com.br.accommodation.bean.BedroomFacade ejbBedroomFacade) {
        this.ejbBedroomFacade = ejbBedroomFacade;
    }



    @FacesConverter(forClass = HotelReservation.class)
    public static class HotelReservationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HotelReservationController controller = (HotelReservationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "hotelReservationController");
            return controller.getHotelReservation(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof HotelReservation) {
                HotelReservation o = (HotelReservation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + HotelReservation.class.getName());
            }
        }

    }

}
