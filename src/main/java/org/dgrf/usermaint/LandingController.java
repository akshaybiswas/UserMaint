/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
@Named(value = "landingController")
@ViewScoped
public class LandingController implements Serializable{
    
    private String userName;
    /**
     * Creates a new instance of LandingController
     */
    @Inject
    LoginController loginController;
    public LandingController() {
    }
    
    public void findUserFullName () {
        String userId = loginController.getUserId();
        
        UserDataDAO uD = new UserDataDAO();
        UserData userData = uD.findUserData(userId);
        userName = userData.getUserFirstname();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
