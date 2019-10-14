/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.dao.impl;

import com.je.spring.rest.master.dao.PelangganDao;
import com.je.spring.rest.master.model.Pelanggan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("pelangganDao")
public class PelangganDaoImpl implements PelangganDao{
  
    private static final String SQL_INSERT_PELANGGAN = "INSERT INTO pelanggan (nmplgn,almtplgn,umurplgn,jkplgn,nohpplgn) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_PELANGGAN_BY_ID = "SELECT Idplgn,nmplgn,almtplgn,umurplgn,jkplgn,nohpplgn FROM pelanggan WHERE Idplgn = ?";
    private static final String SQL_SELECT_PELANGGAN_ALL = "SELECT Idplgn,nmplgn,almtplgn,umurplgn,jkplgn,nohpplgn FROM pelanggan ";
    private static final String SQL_UPDATE_PELANGGAN = "UPDATE pelanggan SET nmplgn=? , almtplgn=?, umurplgn=?, jkplgn=?,nohpplgn=? WHERE Idplgn =? ";
    private static final String SQL_DELETE_PELANGGAN = "DELETE FROM pelanggan WHERE Idplgn = ?";
    private static final String SQL_COUNT_PELANGGAN = "SELECT COUNT(*) FROM pelanggan ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  

    
    @Override
    public List<Pelanggan> getAll() {
        
            List<Pelanggan> pelangganList = null;
        try{
    	    	pelangganList = jdbcTemplate.query(SQL_SELECT_PELANGGAN_ALL, new Object[]{}, new RowMapper<Pelanggan>() {
                public Pelanggan mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Pelanggan pelanggan = new Pelanggan();
                    pelanggan.setId(rs.getInt("Idplgn"));
                    pelanggan.setNmplgn(rs.getString("nmplgn"));
                    pelanggan.setAlmtplgn(rs.getString("almtplgn"));
                    pelanggan.setUmurplgn(rs.getString("umurplgn"));
                    pelanggan.setJkplgn(rs.getString("jkplgn"));
                    pelanggan.setNohpplgn(rs.getString("nohpplgn"));
                    return pelanggan;
                }
            });
//        pelangganList = jdbcTemplate.query(SQL_SELECT_PELANGGAN_ALL, new Object[]{}, new BeanPropertyRowMapper(Pelanggan.class));      
//                       
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	      
        return pelangganList;
    }

    @Override
    public Pelanggan getById(int id) {
    Pelanggan pelanggan = null;
    	try{
    		
    		  
            pelanggan = (Pelanggan) jdbcTemplate.queryForObject(SQL_SELECT_PELANGGAN_BY_ID,new Object[]{id},new RowMapper<Pelanggan>(){
                        public Pelanggan mapRow(ResultSet rs, int rowNum)throws SQLException {
	        				Pelanggan pelanggan = new Pelanggan();
	        				pelanggan.setId(rs.getInt("Idplgn"));
	        				pelanggan.setNmplgn(rs.getString("nmplgn"));
	        				pelanggan.setAlmtplgn(rs.getString("almtplgn"));
                                                pelanggan.setUmurplgn(rs.getString("umurplgn"));
                                                pelanggan.setJkplgn(rs.getString("jkplgn"));
                                                pelanggan.setNohpplgn(rs.getString("nohpplgn"));
	        				return pelanggan;
	        			}
                   });
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return pelanggan;
    }

    @Override
    public void insert(Pelanggan pelanggan) {
            
         try{ 
            jdbcTemplate.update(SQL_INSERT_PELANGGAN, new Object[]{pelanggan.getNmplgn(),pelanggan.getAlmtplgn(),
                pelanggan.getUmurplgn(),pelanggan.getJkplgn(),pelanggan.getNohpplgn()});  	
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    }

    @Override
    public void update(Pelanggan pelanggan) {
             try{ 
                  jdbcTemplate.update(SQL_UPDATE_PELANGGAN, new Object[]{pelanggan.getNmplgn(),pelanggan.getAlmtplgn(),
                pelanggan.getUmurplgn(),pelanggan.getJkplgn(),pelanggan.getNohpplgn(),pelanggan.getId()});  	
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    }

    @Override
    public void delete(Pelanggan pelanggan) {
            try{
		jdbcTemplate.update(SQL_DELETE_PELANGGAN, new Object[]{pelanggan.getId()});
            }catch(Exception e){
		e.printStackTrace();
	}
    }

    @Override
    public long count() {
               long count = 0;
            try{
                    count = jdbcTemplate.queryForObject(SQL_COUNT_PELANGGAN, null,Long.class);

            }catch(Exception e){
                    e.printStackTrace();
            }
            return count;
    }
    
}
