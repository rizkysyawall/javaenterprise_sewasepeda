/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.dao.impl;

import com.je.spring.rest.master.dao.PemilikDao;
import com.je.spring.rest.master.model.Pemilik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("pemilikDao")
public class PemilikDaoImpl implements PemilikDao{
 
    private static final String SQL_SELECT_PEMILIK_BY_ID = "SELECT Idpemilik,nmpemilik,almtpemilik,jkpemilik,umurpemilik,notlpnpemilik FROM pemilik WHERE Idpemilik = ?";
    private static final String SQL_SELECT_PEMILIK_ALL = "SELECT Idpemilik,nmpemilik,almtpemilik,jkpemilik,umurpemilik,notlpnpemilik FROM pemilik ";
    private static final String SQL_COUNT_PEMILIK= "SELECT COUNT(*) FROM pemilik ";
    
    private static final String SQL_INSERT_PEMILIK = "INSERT INTO pemilik (nmpemilik,almtpemilik,jkpemilik,umurpemilik,notlpnpemilik) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_PEMILIK = "UPDATE pemilik SET nmpemilik=? , almtpemilik=?, jkpemilik=?, umurpemilik=?, notlpnpemilik=? WHERE Idpemilik =? ";
    private static final String SQL_DELETE_PEMILIK = "DELETE FROM pemilik WHERE Idpemilik = ?";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Pemilik> getAll() {
            
        List<Pemilik> pemilikList = null;
        try{
    	pemilikList = jdbcTemplate.query(SQL_SELECT_PEMILIK_ALL, new Object[]{}, new RowMapper<Pemilik>() {
                public Pemilik mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Pemilik pemilik = new Pemilik();
                    pemilik.setId(rs.getInt("Idpemilik"));
                    pemilik.setNmpemilik(rs.getString("nmpemilik"));
                    pemilik.setAlmtpemilik(rs.getString("almtpemilik"));
                    pemilik.setJkpemilik(rs.getString("jkpemilik"));
                    pemilik.setUmurpemilik(rs.getString("umurpemilik"));
                    pemilik.setNotlpnpemilik(rs.getString("notlpnpemilik"));
                    return pemilik;
                }
            });
//        pemilikList = jdbcTemplate.query(SQL_SELECT_PEMILIK_ALL, new Object[]{}, new BeanPropertyRowMapper(Pemilik.class));      
//                       
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	      
        return pemilikList;}

    @Override
    public Pemilik getById(int id) {
             Pemilik pemilik = null;
    	try{
    		
    		  
            pemilik = (Pemilik) jdbcTemplate.queryForObject(SQL_SELECT_PEMILIK_BY_ID,new Object[]{id},new RowMapper<Pemilik>(){
                        public Pemilik mapRow(ResultSet rs, int rowNum)throws SQLException {
	        				Pemilik pemilik = new Pemilik();
	        				pemilik.setId(rs.getInt("Idpemilik"));
	        				pemilik.setNmpemilik(rs.getString("nmpemilik"));
	        				pemilik.setAlmtpemilik(rs.getString("almtpemilik"));
                                                pemilik.setJkpemilik(rs.getString("jkpemilik"));
                                                pemilik.setUmurpemilik(rs.getString("umurpemilik"));
                                                pemilik.setNotlpnpemilik(rs.getString("notlpnpemilik"));
	        				return pemilik;
	        			}
                   });
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return pemilik;
    }

    @Override
    public long count() {
                long count = 0;
            try{
                    count = jdbcTemplate.queryForObject(SQL_COUNT_PEMILIK, null,Long.class);

            }catch(Exception e){
                    e.printStackTrace();
            }
            return count;
    }

    @Override
    public void insert(Pemilik pemilik) {
             try{ 
            jdbcTemplate.update(SQL_INSERT_PEMILIK, new Object[]{pemilik.getNmpemilik(),pemilik.getAlmtpemilik(),
            pemilik.getJkpemilik(),pemilik.getUmurpemilik(),pemilik.getNotlpnpemilik()});  	
    	 }catch(Exception e){
             
    		 e.printStackTrace();
    	 }
    }

    @Override
    public void update(Pemilik pemilik) {
                try{ 
                  jdbcTemplate.update(SQL_UPDATE_PEMILIK, new Object[]{pemilik.getNmpemilik(),
                      pemilik.getAlmtpemilik(),pemilik.getJkpemilik(),pemilik.getUmurpemilik(),
                      pemilik.getNotlpnpemilik(),pemilik.getId()});  	
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    }

    @Override
    public void delete(Pemilik pemilik) {
                try{
			jdbcTemplate.update(SQL_DELETE_PEMILIK, new Object[]{pemilik.getId()});
		 }catch(Exception e){
			 e.printStackTrace();
		 }
    }
    
}
