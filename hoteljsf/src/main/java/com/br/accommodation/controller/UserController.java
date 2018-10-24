package com.br.accommodation.controller;

import com.br.accommodation.entity.User;
import com.br.accommodation.controller.util.JsfUtil;
import com.br.accommodation.controller.util.PaginationHelper;
import com.br.accommodation.bean.UserFacade;
import com.br.accommodation.controller.util.Hash;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@Named("userController")
@ManagedBean
@SessionScoped
//@RequestScoped
//@ViewScoped
public class UserController implements Serializable {

    private User current;
    private DataModel items = null;
    @EJB
    private com.br.accommodation.bean.UserFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UserController() {
    }

    public User getSelected() {
        if (current == null) {
            current = new User();
            selectedItemIndex = -1;
        }
        return current;
    }

//    @PostConstruct
//    public void init() {
//        ejbFacade = new com.br.accommodation.bean.UserFacade();
//    }
    private UserFacade getFacade() {
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
                    
                    List<User> users = getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                    
                    for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
                        User userObj = (User) iterator.next();
                        typeUserToString(userObj);
                        
                    }
                    
                    return new ListDataModel(users);
                }
            };
        }
        return pagination;
    }
    
    public String List(){
        recreateModel();
        return "/user/List";
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (User) getItems().getRowData();
        typeUserToString(current);
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new User();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (current.getPassword().equals(current.getRetypingPassword())) {
                current.setPassword(Hash.sha1(current.getPassword()));
                getFacade().create(current);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
                return "View";
            } else {
                JsfUtil.addErrorMessage("Passwords Do Not Match");
                return null;
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (User) getItems().getRowData();
        current.setRetypingPassword(current.getPassword());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {

            if (current.getPassword().equals(current.getRetypingPassword())) {
                User user = getFacade().find(current.getId());

                if (user != null && !user.getPassword().equals(current.getPassword())) {
                    current.setPassword(Hash.sha256(current.getPassword()));
                }
                getFacade().edit(current);
                //43983
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
                return "View";
            } else {
                JsfUtil.addErrorMessage("Passwords Do Not Match");
                return null;
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (User) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
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

    public User getUser(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public void typeUserToString(User user){
        
        if(user.getTypeUser() == 1){
            user.setTypeUserToString("Administrator");
        }
        else{
            user.setTypeUserToString("Common");            
        }
        
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
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
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
