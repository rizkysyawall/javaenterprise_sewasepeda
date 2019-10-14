/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.controller;

import com.je.spring.rest.master.service.PelangganService;
import com.je.spring.rest.master.model.Pelanggan;
import com.je.spring.rest.util.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PelangganController {
        
    @Autowired
    private PelangganService pelangganService;
    
    
    @RequestMapping(value = "/master/pelanggan", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getAll() {
            
         Map<String, Object> response = new HashMap<String, Object>();
         
        try{
            
         List<Pelanggan> pelangganList = pelangganService.getAll();
         long count = pelangganService.count();
             
         response.put(Constants.LIST, pelangganList);
         response.put(Constants.TOTAL, count);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    	
        return response;
    	
    }
    
    @RequestMapping(value = "/master/pelanggan/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getById(@PathVariable("id") final int id) {

         Map<String, Object> response = new HashMap<String, Object>();
         
         try{
              Pelanggan pelanggan = pelangganService.getById(id);
              response.put(Constants.PELANGGAN_KEY, pelanggan);
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
         return response;
    }
    
    @RequestMapping(value = "/master/pelanggan", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> insert(@RequestBody final Map<String, Object> request) {
        
        Map<String, Object> response = new HashMap<String, Object>();
    	Map<String, Object> pelangganMap = (Map<String, Object>) request.get(Constants.PELANGGAN_KEY);

        Pelanggan pelanggan = new Pelanggan();
        try{
        System.out.println("test pelanggan");
            
        pelanggan.setNmplgn((String) pelangganMap.get("nmplgn"));
        pelanggan.setAlmtplgn((String) pelangganMap.get("almtplgn"));
        pelanggan.setUmurplgn((String) pelangganMap.get("umurplgn"));
        pelanggan.setJkplgn((String) pelangganMap.get("jkplgn"));
        pelanggan.setNohpplgn((String) pelangganMap.get("nohpplgn"));
     
        pelangganService.insert(pelanggan);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;
    }
    
    
    @RequestMapping(value = "/master/pelanggan/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody Map<String, Object> update(@PathVariable("id") final int id,
           @RequestBody final Map<String, Object> request) {

    	Map<String, Object> response = new HashMap<String, Object>();
    	Map<String, Object> pelangganMap = (Map<String, Object>) request.get(Constants.PELANGGAN_KEY);

        Pelanggan pelanggan = new Pelanggan();
        try{
        
        pelanggan.setId(id);
        pelanggan.setNmplgn((String) pelangganMap.get("nmplgn"));
        pelanggan.setAlmtplgn((String) pelangganMap.get("almtplgn"));
        pelanggan.setUmurplgn((String) pelangganMap.get("umurplgn"));
        pelanggan.setJkplgn((String) pelangganMap.get("jkplgn"));
        pelanggan.setNohpplgn((String) pelangganMap.get("nohpplgn"));
        
        pelangganService.update(pelanggan);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/pelanggan/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id") final int id) {
    	
    	
    	Map<String, Object> response = new HashMap<String, Object>();

        Pelanggan pelanggan = new Pelanggan();
        try{
        
        pelanggan.setId(id);
        
        pelangganService.delete(pelanggan);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;

    }
}
