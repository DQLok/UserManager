/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.typepromotions;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class TypePromotionsDTO implements Serializable{
    private String typeID;
    private String typename;

    public TypePromotionsDTO() {
    }

    public TypePromotionsDTO(String typeID, String typename) {
        this.typeID = typeID;
        this.typename = typename;
    }

    /**
     * @return the typeID
     */
    public String getTypeID() {
        return typeID;
    }

    /**
     * @param typeID the typeID to set
     */
    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename the typename to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "TypePromotionsDTO{" + "typeID=" + typeID + ", typename=" + typename + '}';
    }
    
    
}
