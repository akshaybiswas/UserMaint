/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.dto.ResponseCode;
import org.dgrf.javadev.UserAuth;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String userId;
    private String password;
    private String userName;

    /**
     * Creates a new instance of LoginConctroller
     */
    public LoginController() {

    }

    public String userAuthenticate() {
        UserAuth userAuth = new UserAuth();
        int returnCode = userAuth.AuthenticateUser(userId, password);

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;

        switch (returnCode) {
            case ResponseCode.SUCCESS:
                UserDataDAO uD = new UserDataDAO();
                UserData userData = uD.findUserData(userId);
                userName = userData.getUserFirstname();
                return "loggedin/landing?faces-redirect=true";
            case ResponseCode.INVALID_USER:
                fm = new FacesMessage("Invalid User", "User not found.");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";
            case ResponseCode.WRONG_CREDENTIALS:
                fm = new FacesMessage("Wrong Input", "Enterd wrong credentials. :(");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";
            case ResponseCode.INACTIVE_USER:
                fm = new FacesMessage("Account Disabled", "Inactive User Account!");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";

            case ResponseCode.LOGIN_ATTEMPTS_EXCEEDED:
                fm = new FacesMessage("Tried Enough", "You have exceeded your attempts.");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";

            default:
                fm = new FacesMessage("Default", "Oh Snap! Something went wrong.");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";

        }

    }

    public String userLogOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Loged Out", "User logged out successfully.");
        context.addMessage(null, fm);
        userId = null;
        password = null;
        return "/index?faces-redirect=true";
    }
    
    public String userRedirect() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage("Session Expired", "Your session has expired.");
        context.addMessage(null, fm);
        return "/index?faces-redirect=true";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
