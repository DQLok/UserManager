/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.status;

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
public class StatusDAO implements Serializable {

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

    public List<StatusDTO> getListStatus(String sta) throws NamingException, SQLException {
        List<StatusDTO> list = new ArrayList<>();
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT  statusID,statusname "
                        + "	FROM tblStatus "
                        + "	WHERE statusID LIKE ? ";
                stm=con.prepareStatement(sql);
                stm.setString(1, "%"+sta+"%");
                rs=stm.executeQuery();
                while(rs.next()){
                    String statusID=rs.getString("statusID");
                    String statusname=rs.getString("statusname");
                    StatusDTO dto=new StatusDTO(statusID, statusname);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
