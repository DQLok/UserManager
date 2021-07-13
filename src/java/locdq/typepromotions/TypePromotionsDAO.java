/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.typepromotions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locdq.utils.DBUtilties;

/**
 *
 * @author test
 */
public class TypePromotionsDAO {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public List<TypePromotionsDTO> getTypePromotions() throws SQLException, NamingException {
        List<TypePromotionsDTO> list = new ArrayList<>();
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT t.typeID,t.typename "
                        + "	FROM tblTypePromotions t ";
                stm=con.prepareStatement(sql);
                rs=stm.executeQuery();
                while(rs.next()){
                    String typeID=rs.getString("typeID");
                    String typename=rs.getString("typename");
                    TypePromotionsDTO dto=new TypePromotionsDTO(typeID, typename);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
