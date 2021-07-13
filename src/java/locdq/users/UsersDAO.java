/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locdq.roles.RolesDAO;
import locdq.roles.RolesDTO;
import locdq.status.StatusDAO;
import locdq.status.StatusDTO;
import locdq.utils.DBUtilties;

/**
 *
 * @author test
 */
public class UsersDAO implements Serializable {

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

    public UsersDTO checkLogin(String uID, String pass) throws NamingException, SQLException {
        UsersDTO dto = null;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, username, password, email, "
                        + " phone, photo, roleID, statusID  "
                        + "	FROM tblUsers "
                        + "	WHERE userID = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, uID);
                stm.setString(2, pass);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    //-------------------
                    String roleID = rs.getString("roleID");
                    RolesDAO dao = new RolesDAO();
                    List<RolesDTO> list = dao.getListRole(roleID);
                    RolesDTO role = list.get(0);
                    //----------
                    String statusID = rs.getString("statusID");
                    StatusDAO daos = new StatusDAO();
                    List<StatusDTO> lists = daos.getListStatus(statusID);
                    StatusDTO status = lists.get(0);
                    //-----------
                    dto = new UsersDTO(userID, username, password, email, phone, photo, role, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<UsersDTO> getListUser(String searchvalue, String rol) throws NamingException, SQLException {
        List<UsersDTO> list = new ArrayList<>();
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT userID,username,password,email,phone,photo,rolename,r.roleID,s.statusID,s.statusname "
                        + " FROM tblUsers u,tblRoles r,tblStatus s "
                        + " WHERE u.roleID=r.roleID AND u.statusID=s.statusID "
                        + "	AND u.username LIKE ? AND r.rolename LIKE ? AND s.statusID LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchvalue + "%");
                stm.setString(2, "%" + rol + "%");
                stm.setString(3, "%%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    //-------------------
                    String roleID = rs.getString("roleID");
                    RolesDAO dao = new RolesDAO();
                    List<RolesDTO> listRoles = dao.getListRole(roleID);
                    RolesDTO role = listRoles.get(0);
                    //----------
                    String statusID = rs.getString("statusID");
                    StatusDAO daos = new StatusDAO();
                    List<StatusDTO> lists = daos.getListStatus(statusID);
                    StatusDTO status = lists.get(0);
                    //----------
                    UsersDTO dto = new UsersDTO(userID, username, password, email, phone, photo, role, status);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean DeleteUser(String uID) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " UPDATE tblUsers "
                        + "		SET statusID='s2' "
                        + "		WHERE userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, uID);
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean UpdateUser(UsersDTO dto) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " UPDATE tblUsers "
                        + " SET username= ? ,password= ? ,email= ? ,phone= ? ,photo= ? ,roleID= ? "
                        + " WHERE userID= ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getEmail());
                stm.setString(4, dto.getPhone());
                stm.setString(5, dto.getPhoto());
                stm.setString(6, dto.getRoleDTO().getRoleID());
                stm.setString(7, dto.getUserID());
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkUserID(String uID) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT userID "
                        + " FROM tblUsers "
                        + " WHERE userID= ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, uID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean InsertUser(UsersDTO dto) throws NamingException, SQLException {
        boolean check = false;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " INSERT INTO tblUsers(userID,username,password,email,phone,photo,roleID,statusID)"
                        + " VALUES (?,?,?,?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUserID());
                stm.setString(2, dto.getUsername());
                stm.setString(3, dto.getPassword());
                stm.setString(4, dto.getEmail());
                stm.setString(5, dto.getPhone());
                stm.setString(6, dto.getPhoto());
                stm.setString(7, dto.getRoleDTO().getRoleID());
                stm.setString(8, dto.getStatusDTO().getStatusID());
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public UsersDTO getUserByUserID(String uID) throws NamingException, SQLException {
        UsersDTO dto = null;
        try {
            con = DBUtilties.makeConnection();
            if (con != null) {
                String sql = " SELECT u.userID,u.username,u.password,u.email,u.phone,u.photo,r.roleID,s.statusID,r.rolename,s.statusname "
                        + " FROM tblUsers u,tblRoles r,tblStatus s "
                        + " WHERE u.roleID=r.roleID "
                        + "	AND u.statusID=s.statusID AND userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, uID);
                rs = stm.executeQuery();
                if (rs.next()) {
                     String userID = rs.getString("userID");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    //-------------------
                    String roleID = rs.getString("roleID");
                    RolesDAO dao = new RolesDAO();
                    List<RolesDTO> listRoles = dao.getListRole(roleID);
                    RolesDTO role = listRoles.get(0);
                    //----------
                    String statusID = rs.getString("statusID");
                    StatusDAO daos = new StatusDAO();
                    List<StatusDTO> lists = daos.getListStatus(statusID);
                    StatusDTO status = lists.get(0);
                    //----------
                    dto = new UsersDTO(userID, username, password, email, phone, photo, role, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
