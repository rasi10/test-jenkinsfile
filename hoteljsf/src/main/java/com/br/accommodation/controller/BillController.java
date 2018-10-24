package com.br.accommodation.controller;

import com.br.accommodation.entity.Bill;
import com.br.accommodation.controller.util.JsfUtil;
import com.br.accommodation.controller.util.PaginationHelper;
import com.br.accommodation.bean.BillFacade;
import com.br.accommodation.controller.util.SessionContext;
import com.br.accommodation.entity.Bedroom;
import com.br.accommodation.entity.BedroomStatus;
import com.br.accommodation.entity.BillStatus;
import com.br.accommodation.entity.HotelReservation;
import com.br.accommodation.entity.ReservationStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named("billController")
@ManagedBean
@SessionScoped
//@RequestScoped
//@ViewScoped
public class BillController implements Serializable {

    private Bill current;
    private DataModel items = null;
    @EJB
    private com.br.accommodation.bean.BillFacade ejbFacade;
    @EJB
    private com.br.accommodation.bean.HotelReservationFacade ejbFacadeHotel;
    @EJB
    private com.br.accommodation.bean.BedroomFacade ejbFacadeBedroom;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @ManagedProperty("#{hotelReservationController.selected.id}")
    private String hotel_reservation_id;

    public BillController() {
    }

    public Bill getSelected() {
        if (current == null) {
            current = new Bill();
            selectedItemIndex = -1;
        }
        return current;
    }

//    @PostConstruct
//    public void init() {
//        ejbFacade = new com.br.accommodation.bean.BillFacade();
//        ejbFacadeHotel = new com.br.accommodation.bean.HotelReservationFacade();
//        ejbFacadeBedroom = new com.br.accommodation.bean.BedroomFacade();
//    }
    public String createBillByReservation(String idReservation) {

        try {
            String idHotel = "";
            idHotel = idReservation;
            ejbFacadeHotel = new com.br.accommodation.bean.HotelReservationFacade();
            HotelReservation hotel = ejbFacadeHotel.find(Integer.valueOf(idHotel));
            BillStatus status = new BillStatus();
            status.setCode(0);
            status.setId(3);
            status.setName("OPEN");
            Bill bill = new Bill();
            bill.setHotelReservationId(hotel);
            bill.setBillStatusId(status);
            current = bill;
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BillCreated"));
            return "/bill/Edit";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    private BillFacade getFacade() {
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
                    List<Bill> bill = (List<Bill>) getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                    for (Iterator iterator = bill.iterator(); iterator.hasNext();) {
                        Bill billObj = (Bill) iterator.next();
                        BigDecimal[] bigDecimals = calculateBill(billObj.getHotelReservationId().getEntryDate(), billObj.getHotelReservationId().getExitDate(), billObj.getHotelReservationId().getPriceDaily());
                        billObj.setTotalDays(bigDecimals[0].intValue());
                        billObj.setTotalBill(bigDecimals[1]);

                    }
                    return new ListDataModel(bill);
                }

            };
        }
        return pagination;
    }

    public String List(){
        recreateModel();
        return "/bill/List";
    }
    
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Bill) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Bill();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BillCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public Long getCountOfStatus(int code) {
        return getFacade().countBillOfCodeStatus(code);
    }

    public String prepareEdit() {
        LoginController loginBean = (LoginController) SessionContext.getInstance().getAttribute("loginController");
        current = (Bill) getItems().getRowData();
        if (loginBean.getUser().getTypeUser() == 1 || current.getBillStatusId().getCode() == 0) {
            selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
            return "Edit";
        }
        else{
            JsfUtil.addErrorMessage("User not allowed to edit a "+ current.getBillStatusId().getName() +" Bill");
            return null;
        }
    }

    public String update() {
        try {

            if (current.getBillStatusId().getId() == 1 || current.getBillStatusId().getId() == 2) {
                Bedroom bedroom = ejbFacadeBedroom.find(current.getHotelReservationId().getBedroomId().getId());
                bedroom.setBedroomStatusId(new BedroomStatus(1, 0, "VACANT"));
                ejbFacadeBedroom.edit(bedroom);
            }

            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BillUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Bill) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BillDeleted"));
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

    public Bill getBill(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public BigDecimal[] calculateBill(Date entryDate, Date exitDate, BigDecimal priceDaily) {
        BigDecimal[] bigDecimals = new BigDecimal[2];
        long diff = exitDate.getTime() - entryDate.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        bigDecimals[0] = new BigDecimal(days);
        bigDecimals[1] = priceDaily.multiply(bigDecimals[0]);
        return bigDecimals;
    }

    /**
     * @return the hotel_reservation_id
     */
    public String getHotel_reservation_id() {
        return hotel_reservation_id;
    }

    /**
     * @param hotel_reservation_id the hotel_reservation_id to set
     */
    public void setHotel_reservation_id(String hotel_reservation_id) {
        this.hotel_reservation_id = hotel_reservation_id;
    }

    @FacesConverter(forClass = Bill.class)
    public static class BillControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BillController controller = (BillController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "billController");
            return controller.getBill(getKey(value));
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
            if (object instanceof Bill) {
                Bill o = (Bill) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Bill.class.getName());
            }
        }

    }

}
