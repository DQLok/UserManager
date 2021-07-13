/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import locdq.roles.RolesDTO;
import locdq.status.StatusDTO;

/**
 *
 * @author test
 */
public class UserErrorDTO {
    private String userIDER;
    private String usernameER;
    private String passwordER;
    private String emailER;
    private String phoneER;
    private String photoER;
    private RolesDTO RoleDTOER;
    private StatusDTO StatusDTOER;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String userIDER, String usernameER, String passwordER, String emailER, String phoneER, String photoER, RolesDTO RoleDTOER, StatusDTO StatusDTOER) {
        this.userIDER = userIDER;
        this.usernameER = usernameER;
        this.passwordER = passwordER;
        this.emailER = emailER;
        this.phoneER = phoneER;
        this.photoER = photoER;
        this.RoleDTOER = RoleDTOER;
        this.StatusDTOER = StatusDTOER;
    }

    /**
     * @return the userIDER
     */
    public String getUserIDER() {
        return userIDER;
    }

    /**
     * @param userIDER the userIDER to set
     */
    public void setUserIDER(String userIDER) {
        this.userIDER = userIDER;
    }

    /**
     * @return the usernameER
     */
    public String getUsernameER() {
        return usernameER;
    }

    /**
     * @param usernameER the usernameER to set
     */
    public void setUsernameER(String usernameER) {
        this.usernameER = usernameER;
    }

    /**
     * @return the passwordER
     */
    public String getPasswordER() {
        return passwordER;
    }

    /**
     * @param passwordER the passwordER to set
     */
    public void setPasswordER(String passwordER) {
        this.passwordER = passwordER;
    }

    /**
     * @return the emailER
     */
    public String getEmailER() {
        return emailER;
    }

    /**
     * @param emailER the emailER to set
     */
    public void setEmailER(String emailER) {
        this.emailER = emailER;
    }

    /**
     * @return the phoneER
     */
    public String getPhoneER() {
        return phoneER;
    }

    /**
     * @param phoneER the phoneER to set
     */
    public void setPhoneER(String phoneER) {
        this.phoneER = phoneER;
    }

    /**
     * @return the photoER
     */
    public String getPhotoER() {
        return photoER;
    }

    /**
     * @param photoER the photoER to set
     */
    public void setPhotoER(String photoER) {
        this.photoER = photoER;
    }

    /**
     * @return the RoleDTOER
     */
    public RolesDTO getRoleDTOER() {
        return RoleDTOER;
    }

    /**
     * @param RoleDTOER the RoleDTOER to set
     */
    public void setRoleDTOER(RolesDTO RoleDTOER) {
        this.RoleDTOER = RoleDTOER;
    }

    /**
     * @return the StatusDTOER
     */
    public StatusDTO getStatusDTOER() {
        return StatusDTOER;
    }

    /**
     * @param StatusDTOER the StatusDTOER to set
     */
    public void setStatusDTOER(StatusDTO StatusDTOER) {
        this.StatusDTOER = StatusDTOER;
    }

    @Override
    public String toString() {
        return "UserErrorDTO{" + "userIDER=" + userIDER + ", usernameER=" + usernameER + ", passwordER=" + passwordER + ", emailER=" + emailER + ", phoneER=" + phoneER + ", photoER=" + photoER + ", RoleDTOER=" + RoleDTOER + ", StatusDTOER=" + StatusDTOER + '}';
    }
    
    
}
