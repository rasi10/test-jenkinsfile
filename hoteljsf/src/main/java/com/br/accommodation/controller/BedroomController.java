
package com.br.accommodation.controller;

import com.br.accommodation.entity.Bedroom;
import com.br.accommodation.controller.util.JsfUtil;
import com.br.accommodation.controller.util.PaginationHelper;
import com.br.accommodation.bean.BedroomFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named("bedroomController")
@ManagedBean
@SessionScoped
//@RequestScoped
//@ViewScoped
public class BedroomController implements Serializable {

    private Bedroom current;
    private DataModel items = null;
    @EJB
    private com.br.accommodation.bean.BedroomFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int count;
    private static Date startDate;
    
    private static Date endDate;

    public BedroomController() {
    }

    public Bedroom getSelected() {
        if (current == null) {
            current = new Bedroom();
            selectedItemIndex = -1;
        }
        return current;
    }
    
//    @PostConstruct
//    public void init() {
//        ejbFacade = new com.br.accommodation.bean.BedroomFacade();
//    }
    
    
    public int getCount(){
        count = getFacade().count();
        return count;
    }
    
    public Long getCountOfStatus(int code){
        return getFacade().countBedromOfCodeStatus(code);
    }
    
    private BedroomFacade getFacade() {
        return ejbFacade;
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
    
    public String List(){
        recreateModel();
        return "/bedroom/List";
    }

    public String prepareView() {
        current = (Bedroom) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Bedroom();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BedroomCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Bedroom) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BedroomUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Bedroom) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BedroomDeleted"));
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
    //Verificar se outras classes chamam esse metodo
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findByReservation(startDate, endDate), true);
    }

    public Bedroom getBedroom(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    /**
     * @return the startDate
     */
    public static Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public static void setStartDate(Date startDate) {
        BedroomController.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public static Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public static void setEndDate(Date endDate) {
        BedroomController.endDate = endDate;
    }

    @FacesConverter(forClass = Bedroom.class)
    public static class BedroomControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BedroomController controller = (BedroomController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bedroomController");
            return controller.getBedroom(getKey(value));
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
            if (object instanceof Bedroom) {
                Bedroom o = (Bedroom) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Bedroom.class.getName());
            }
        }

    }

}
