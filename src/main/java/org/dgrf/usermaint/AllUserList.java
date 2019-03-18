package org.dgrf.usermaint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.dgrf.javadev.DAO.UserDataDAO;
import org.dgrf.javadev.JPA.exceptions.NonexistentEntityException;
import org.dgrf.javadev.UserMaintanance;
import org.dgrf.javadev.entities.UserData;
import org.dgrf.javadev.dto.UserDTO;

/**
 *
 * @author dgrfiv
 */
@Named(value = "usersList")
@ViewScoped
public class AllUserList implements Serializable {

    
    @Inject
    LoginController loginController;
    
    private List<UserDTO> userDTOList;
    private UserDTO selectedUser;
    private boolean renderUser;

    /**
     * Creates a new instance of UsersList
     */
    public void allUserList() {
        UserDataDAO userDataDAO = new UserDataDAO();
        List<UserData> users = userDataDAO.findUserDataEntities();
        userDTOList = new ArrayList<>();
        for (int i =0; i<users.size();i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(users.get(i).getUserEmail());
            userDTO.setFirstName(users.get(i).getUserFirstname());
            userDTO.setLastname(users.get(i).getUserLastname());
            userDTO.setActiveFlag(users.get(i).getUserStatus());
            boolean loggedInUser = isLoggedInUser(users.get(i).getUserEmail());
            userDTO.setLoggedInUser(loggedInUser);
            userDTOList.add(userDTO);
        }
    }


    public String editUser() {
        System.out.println(selectedUser.getEmail());
        return "/loggedin/EditUser?faces-redirect=true&userid=" + selectedUser.getEmail();
    }

    public String deleteUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        try {
            UserDataDAO dataDAO = new UserDataDAO();
            dataDAO.destroy(selectedUser.getEmail());

            fm = new FacesMessage("User delete alert:", "User deleted Successfully.");
            context.addMessage(null, fm);

            return "UserMaintList?faces-redirect=true";
            
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AllUserList.class.getName()).log(Level.SEVERE, null, ex);
            
            fm = new FacesMessage("User changes alert:", "Either user not found or something went wrong.");
            context.addMessage(null, fm);
            
            return "/loggedin/UserMaintList?faces-redirect=true";
        }
    }
    
    private boolean isLoggedInUser(String userId){
        
        return !userId.equals(loginController.getUserId());
    }

    public UserDTO getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public boolean isRenderUser() {
        return renderUser;
    }

    public void setRenderUser(boolean renderUser) {
        this.renderUser = renderUser;
    }

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }
  
    

}
