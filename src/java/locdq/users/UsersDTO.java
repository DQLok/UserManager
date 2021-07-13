/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import java.io.Serializable;
import locdq.roles.RolesDTO;
import locdq.status.StatusDTO;

/**
 *
 * @author test
 */
public class UsersDTO implements Serializable{
    private String userID;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String photo;
    private RolesDTO RoleDTO;
    private StatusDTO StatusDTO;

    public UsersDTO() {
    }

    public UsersDTO(String userID, String username, String password, String email, String phone, String photo, RolesDTO RoleDTO, StatusDTO StatusDTO) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.RoleDTO = RoleDTO;
        this.StatusDTO = StatusDTO;
    }


    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the RoleDTO
     */
    public RolesDTO getRoleDTO() {
        return RoleDTO;
    }

    /**
     * @param RoleDTO the RoleDTO to set
     */
    public void setRoleDTO(RolesDTO RoleDTO) {
        this.RoleDTO = RoleDTO;
    }

    /**
     * @return the StatusDTO
     */
    public StatusDTO getStatusDTO() {
        return StatusDTO;
    }

    /**
     * @param StatusDTO the StatusDTO to set
     */
    public void setStatusDTO(StatusDTO StatusDTO) {
        this.StatusDTO = StatusDTO;
    }

    @Override
    public String toString() {
        return "UsersDTO{" + "userID=" + userID + ", username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone + ", photo=" + photo + ", RoleDTO=" + RoleDTO + ", StatusDTO=" + StatusDTO + '}';
    }    
    
}
