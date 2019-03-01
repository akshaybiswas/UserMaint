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
import org.dgrf.javadev.UserAuth;

/**
 *
 * @author dgrfiv
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String userId;
    private String password;

    /**
     * Creates a new instance of LoginConctroller
     */
    public LoginController() {

    }

    public String userAuthenticate() {
        UserAuth userAuth = new UserAuth();
        int returnCode = userAuth.AuthenticateUser(userId, password);

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm;

        switch (returnCode) {
            case 0:
                fm = new FacesMessage("1st message", "Invalid User");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";
            case 1:
                fm = new FacesMessage("2nd message", "User logged in successfully. :)");
                context.addMessage(null, fm);
                System.out.println("Hello");
                return "loggedin/landing?faces-redirect=true";
            case 2:
                fm = new FacesMessage("3rd message", "Enterd wrong credentials. :(");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";
            case 3:
                fm = new FacesMessage("4th message", "Inactive User Account!");
                context.addMessage(null, fm);
                return "index?faces-redirect=true";

            case 4:
                fm = new FacesMessage("5th message", "You have exceeded your attempts.");
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

}
