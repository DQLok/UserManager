/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.roles;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class RolesDTO implements Serializable{
    private String roleID;
    private String rolename;

    public RolesDTO() {
    }

    public RolesDTO(String roleID, String rolename) {
        this.roleID = roleID;
        this.rolename = rolename;
    }

    /**
     * @return the roleID
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "RolesDTO{" + "roleID=" + roleID + ", rolename=" + rolename + '}';
    }
    
    
}
