/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.roles;

import java.io.Serializable;
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
public class RolesDAO implements Serializable {

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

    public List<RolesDTO> getListRole(String ID) throws NamingException, SQLException {
        List<RolesDTO> list = new ArrayList<>();
   //     List<RolesDTO> listtmp = new ArrayList<>();
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT rolename,roleID "
                        + "	FROM tblRoles "
                        + "	WHERE roleID LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + ID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roleID = rs.getString("roleID");
                    String rolename = rs.getString("rolename");
                    RolesDTO dto = new RolesDTO(roleID, rolename);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
//        for (int i=list.size();i>=0;i--){
//            listtmp.add(list.get(i));
//        }
        return list;
    }
}
