/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MarcosAlexandrede
 */
@Entity
@Table(name = "user_")
@XmlRootElement 
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByForLoginPassword", query = "SELECT u FROM User u WHERE u.password = :password and u.login = :login")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Client clientId;
    @JoinColumn(name = "user_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserStatus userStatusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type_user")
    private int typeUser;
    @Transient
    private String retypingPassword;
    @Transient
    private String typeUserToString;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String login, String password, Integer typeUser) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.typeUser = typeUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public UserStatus getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(UserStatus userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login;
    }

    /**
     * @return the typeUser
     */
    public int getTypeUser() {
        return typeUser;
    }

    /**
     * @param typeUser the typeUser to set
     */
    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    /**
     * @return the retypingPassword
     */
    public String getRetypingPassword() {
        return retypingPassword;
    }

    /**
     * @param retypingPassword the retypingPassword to set
     */
    public void setRetypingPassword(String retypingPassword) {
        this.retypingPassword = retypingPassword;
    }

    /**
     * @return the typeUserToString
     */
    public String getTypeUserToString() {
        return typeUserToString;
    }

    /**
     * @param typeUserToString the typeUserToString to set
     */
    public void setTypeUserToString(String typeUserToString) {
        this.typeUserToString = typeUserToString;
    }
    
}
