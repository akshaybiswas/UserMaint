/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.usermaint;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dgrf.javadev.DAO.RoleDAO;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.dto.ResponseCode;
import org.dgrf.javadev.UserMaintanance;
import org.dgrf.javadev.entities.Roles;
import org.dgrf.javadev.entities.UserData;

/**
 *
 * @author dgrfiv
 */
@Named(value = "editUser")
@ViewScoped
public class EditUser implements Serializable {

    @Inject
    LoginController loginController;

    /**
     * Creates a new instance of EditUser
     */
    public EditUser() {
    }

    private String userId;
    private String firstName;
    private String lastName;
    private String oldPass;
    private String newPass;
    private Integer roles;

    private boolean userStatus;

    private Map<String, Boolean> statusMap;
    private Map<String, Integer> roleMap;

    

    @PostConstruct
    public void fillDropdownValues() {
        RoleDAO roleDAO = new RoleDAO();
        List<Roles> uRoles = roleDAO.findRolesEntities();
        roleMap = new HashMap<>();

        for (int i = 0; i < uRoles.size(); i++) {
            roleMap.put(uRoles.get(i).getName(), uRoles.get(i).getId());
        }

    }

    public void loadUserData() {
        UserDataDAO uDAO = new UserDataDAO();
        UserData ud = uDAO.findUserData(userId);

        userId = ud.getUserEmail();
        firstName = ud.getUserFirstname();
        lastName = ud.getUserLastname();
        userStatus = ud.getUserStatus();
        roles = ud.getRolesId();
    }

    public String editSelectedUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        UserMaintanance um = new UserMaintanance();
        responseCode = um.UpdateUser(userId, firstName, lastName, userStatus, roles);

        if (responseCode == 0) {
            fm = new FacesMessage("User changes alert:", "User data changed Successfully.");
            context.addMessage(null, fm);
            return "UserMaintList?faces-redirect=true";
        } else {
            fm = new FacesMessage("User changes alert:", "Either user not found or something went wrong.");
            context.addMessage(null, fm);
            return "/loggedin/EditUser?faces-redirect=true&userid=" + userId;
        }

    }

    public String changeUserPassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        UserMaintanance um = new UserMaintanance();
        responseCode = um.UpdatePassword(userId, oldPass, newPass);

        if (responseCode == ResponseCode.LOGIN_ATTEMPTS_EXCEEDED) {
            fm = new FacesMessage("Password changes alert:", "You have exceeded your attempts.");
            context.addMessage(null, fm);
            return "/loggedin/landing?faces-redirect=true";

        } else if (responseCode == ResponseCode.SUCCESS) {
            fm = new FacesMessage("Password changes alert:", "User password updated successfully.");
            context.addMessage(null, fm);
            return "/loggedin/ChangeUserPassword?faces-redirect=true&userid=" + userId;

        } else if (responseCode == ResponseCode.EXCEPTION_CAUGHT) {
            fm = new FacesMessage("Password changes alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "/loggedin/ChangeUserPassword?faces-redirect=true&userid=" + userId;

        } else if (responseCode == ResponseCode.WRONG_CREDENTIALS) {
            fm = new FacesMessage("Password changes alert:", "You have entered wrong credentials.");
            context.addMessage(null, fm);
            return "/loggedin/ChangeUserPassword?faces-redirect=true&userid=" + userId;

        } else {
            fm = new FacesMessage("Password changes alert:", "Please contact Admin.");
            context.addMessage(null, fm);
            return "/loggedin/landing?faces-redirect=true";
        }

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Map<String, Boolean> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<String, Boolean> statusMap) {
        this.statusMap = statusMap;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public Map<String, Integer> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<String, Integer> roleMap) {
        this.roleMap = roleMap;
    }
    
}
