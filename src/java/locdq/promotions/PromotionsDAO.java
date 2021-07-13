/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.promotions;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locdq.typepromotions.TypePromotionsDTO;
import locdq.users.UsersDTO;
import locdq.utils.DBUtilties;

/**
 *
 * @author test
 */
public class PromotionsDAO implements Serializable {

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

    public void saveDatabase(List<PromotionsDTO> listpro, String typePID) throws SQLException, NamingException {
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                for (PromotionsDTO pro : listpro) {
                    String sql = "INSERT INTO tblPromotions(typeID,userID,rank,assignmentdate) "
                            + "	VALUES(?,?,?,?) ";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, typePID);
                    stm.setString(2, pro.getUsersDTO().getUserID());
                    stm.setInt(3, pro.getRank());
                    stm.setDate(4, pro.getAssignmentdate());
                    stm.executeUpdate();
                }
            }
        } finally {
            closeConnection();
        }
    }

//    public String saveDatabase2(List<PromotionsDTO> listpro, String typePID) throws SQLException, NamingException {
//        String name="";
//        try {
//            con = DBUtilties.makeConnection();
//            if (con != null) {
//                for (PromotionsDTO pro : listpro) {
//                    String sql = " IF NOT EXISTS (SELECT 1 "
//                            + "	FROM tblPromotions p,tblUsers u "
//                            + "	WHERE p.userID=u.userID AND u.userID=? AND "
//                            + "	p.assignmentdate LIKE ? AND p.rank = ? )"
//                            + "	BEGIN "
//                            + "	INSERT INTO tblPromotions(typeID,userID,rank,assignmentdate) "
//                            + "	VALUES( ? , ? ,? , ?) "
//                            + "	END "
//                            + "	GO ";
//                    stm = con.prepareStatement(sql);
//                    stm.setString(1, pro.getUsersDTO().getUserID());
//                    stm.setDate(2, pro.getAssignmentdate());
//                    stm.setInt(3, pro.getRank());
//                    stm.setString(4, typePID);
//                    stm.setString(5, pro.getUsersDTO().getUserID());
//                    stm.setInt(6, pro.getRank());
//                    stm.setDate(7, pro.getAssignmentdate());
//                    name=stm.executeUpdate()==0?pro.getUsersDTO().getUsername():"";
//                }
//            }
//        } finally {
//            closeConnection();
//        }
//        return name;
//    }

    public List<PromotionsDTO> getListByDate(UsersDTO user, String day) throws SQLException, NamingException {
        List<PromotionsDTO> list = new ArrayList<>();
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = "	SELECT u.userID,p.rank,p.assignmentdate,t.typeID ,t.typename "
                        + "	FROM tblPromotions p,tblUsers u,tblTypePromotions t "
                        + "	WHERE u.userID=p.userID AND p.typeID=t.typeID AND u.userID = ? AND p.assignmentdate LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, "%" + day + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String rank = rs.getString("rank");
                    Date assignmentdate = rs.getDate("assignmentdate");
                    String typeID = rs.getString("typeID");
                    String typename = rs.getString("typename");
                    TypePromotionsDTO tp = new TypePromotionsDTO(typeID, typename);
                    PromotionsDTO pro = new PromotionsDTO(tp, user, Integer.parseInt(rank), assignmentdate);
                    list.add(pro);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
