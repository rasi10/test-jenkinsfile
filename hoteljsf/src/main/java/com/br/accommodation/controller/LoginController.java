/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.controller;

/**
 *
 * @author JoaoPaulo
 */
import com.br.accommodation.bean.UserFacade;
import com.br.accommodation.controller.util.Hash;
import static com.br.accommodation.controller.util.JsfUtil.addErrorMessage;
import com.br.accommodation.controller.util.PaginationHelper;
import com.br.accommodation.controller.util.SessionContext;
import com.br.accommodation.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.inject.Named;

/**
 * Controla o LOGIN e LOGOUT do Usuário
 *
 */
@Named("loginController")
@ManagedBean
@SessionScoped
//@RequestScoped
//@ViewScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<User> current = null;
    private DataModel items = null;
    @EJB
    private com.br.accommodation.bean.UserFacade ejbFacade;

    private String email = "";
    private String login = "";
    private String senha = "";
    private PaginationHelper pagination;
    private boolean loggedIn = false;

    /**
     * Retorna usuario logado
     *
     */
    public User getUser() {
//        return (User) SessionContext.getInstance().getUsuarioLogado();
        return current.get(0);
    }

//    @PostConstruct
//    public void init() {
//        ejbFacade = new com.br.accommodation.bean.UserFacade();
//    }

    private UserFacade getFacade() {
        return ejbFacade;
    }
    
    public String doLogout(){
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return "/login/login.xhtml";
    }

    public String doLogin() {
        try {

//            current = (List<User>) getItems().getRowData();
//
//            if (current == null) {
//                addErrorMessage("Login ou Senha errado, tente novamente !");
//                FacesContext.getCurrentInstance().validationFailed();
//                return "";
//            }
            current = (List<User>) ejbFacade.findByForLoginPassword(login, Hash.sha256(senha));//new User(1, "sda", "dasfaf");

            if (current != null && current.size() > 0) {
                loggedIn = true;
                SessionContext.getInstance().setAttribute("loginController", this);
                return "/faces/index.xhtml?faces-redirect=true";
            }
            else{
                addErrorMessage("Login and Password Wrong");
            }
            return "";
        } catch (Exception e) {

            addErrorMessage(e.getMessage());
            FacesContext.getCurrentInstance().validationFailed();
            e.printStackTrace();
            return "";
        }

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
