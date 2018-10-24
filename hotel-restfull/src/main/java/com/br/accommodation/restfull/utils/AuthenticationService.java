/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.utils;

/**
 *
 * @author MarcosAlexandrede
 */
import com.br.accommodation.restfull.entity.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AuthenticationService {

    private User current = null;
    private EntityManager em;

    public AuthenticationService() {

    }
    public User getUser(){
        return current;
    }
    protected EntityManager getEntityManager() {
        if(em==null){
            em = Persistence.createEntityManagerFactory("com.br_accommodation-restfull_war_1.0-SNAPSHOTPU").createEntityManager();
        }
        return em;
    }
    
    public User findByForLoginPassword(String login, String password) {
            Query query = getEntityManager().createNamedQuery("User.findByForLoginPassword",User.class);
            query.setParameter("password", password);
            query.setParameter("login", login);
        User  a = (User) query.getSingleResult();
        return a;
    }

    public boolean authenticate(String authCredentials) {

        if (null == authCredentials) {
            return false;
        }
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        try {
            // we have fixed the userid and password as admin
            // call some UserService/LDAP here
            current = (User) findByForLoginPassword(username, Hash.sha256(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception e){
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        boolean authenticationStatus = (current != null);
        return authenticationStatus;
    }
}
