/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.status;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class StatusDTO implements Serializable{
    private String statusID;
    private String statusname;

    public StatusDTO() {
    }

    public StatusDTO(String statusID, String statusname) {
        this.statusID = statusID;
        this.statusname = statusname;
    }

    /**
     * @return the statusID
     */
    public String getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the statusname
     */
    public String getStatusname() {
        return statusname;
    }

    /**
     * @param statusname the statusname to set
     */
    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    @Override
    public String toString() {
        return "StatusDTO{" + "statusID=" + statusID + ", statusname=" + statusname + '}';
    }
    
    
}
