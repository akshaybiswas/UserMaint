/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.dgrf.javadev.dto.ResponseCode;
import javax.faces.view.ViewScoped;
import org.dgrf.javadev.UserMaintanance;

/**
 *
 * @author dgrfiv
 */
@Named(value = "addNewUser")
@ViewScoped
public class AddNewUser implements Serializable {

    /**
     * Creates a new instance of AddNewUser
     */
    public AddNewUser() {
    }

    private String email;
    private String firstName;
    private String lastName;

    public String createUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        UserMaintanance um = new UserMaintanance();
        responseCode = um.AddUser(email, firstName, lastName);

        if (responseCode == ResponseCode.SUCCESS) {
            fm = new FacesMessage("User creation alert:", "User created Successfully.");
            context.addMessage(null, fm);
            return "UserMaintList?faces-redirect=true";
        } else {
            if (responseCode == ResponseCode.ALREADY_EXISISTS) {
                fm = new FacesMessage("User creation alert:", "User already exsists.");
                context.addMessage(null, fm);
            } else {
                fm = new FacesMessage("User creation alert:", "duh! Something went wrong :(");
                context.addMessage(null, fm);
            }

            return "addNewUser?faces-redirect=true";
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
