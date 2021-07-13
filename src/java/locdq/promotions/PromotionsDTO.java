/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.promotions;

import java.io.Serializable;
import java.sql.Date;
import locdq.typepromotions.TypePromotionsDTO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
public class PromotionsDTO implements Serializable{
    private TypePromotionsDTO TypePromotionsDTO;
    private  UsersDTO UsersDTO;
    private int rank;
    private Date assignmentdate;    

    public PromotionsDTO() {
    }

    public PromotionsDTO(TypePromotionsDTO TypePromotionsDTO, UsersDTO UsersDTO, int rank, Date assignmentdate) {
        this.TypePromotionsDTO = TypePromotionsDTO;
        this.UsersDTO = UsersDTO;
        this.rank = rank;
        this.assignmentdate = assignmentdate;
    }

    /**
     * @return the TypePromotionsDTO
     */
    public TypePromotionsDTO getTypePromotionsDTO() {
        return TypePromotionsDTO;
    }

    /**
     * @param TypePromotionsDTO the TypePromotionsDTO to set
     */
    public void setTypePromotionsDTO(TypePromotionsDTO TypePromotionsDTO) {
        this.TypePromotionsDTO = TypePromotionsDTO;
    }

    /**
     * @return the UsersDTO
     */
    public UsersDTO getUsersDTO() {
        return UsersDTO;
    }

    /**
     * @param UsersDTO the UsersDTO to set
     */
    public void setUsersDTO(UsersDTO UsersDTO) {
        this.UsersDTO = UsersDTO;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the assignmentdate
     */
    public Date getAssignmentdate() {
        return assignmentdate;
    }

    /**
     * @param assignmentdate the assignmentdate to set
     */
    public void setAssignmentdate(Date assignmentdate) {
        this.assignmentdate = assignmentdate;
    }

    @Override
    public String toString() {
        return "PromotionsDTO{" + "TypePromotionsDTO=" + TypePromotionsDTO + ", UsersDTO=" + UsersDTO + ", rank=" + rank + ", assignmentdate=" + assignmentdate + '}';
    }

    
}
