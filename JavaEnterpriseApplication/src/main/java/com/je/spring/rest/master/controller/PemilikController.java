/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.controller;

import com.je.spring.rest.master.service.PemilikService;
import com.je.spring.rest.util.Constants;
import com.je.spring.rest.master.model.Pemilik;

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
public class PemilikController {
    
    @Autowired
    private PemilikService pemilikService;
    
    
    @RequestMapping(value = "/master/pemilik", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getAll() {
            
         Map<String, Object> response = new HashMap<String, Object>();
         
        try{
            
         List<Pemilik> pemilikList = pemilikService.getAll();
         long count = pemilikService.count();
             
         response.put(Constants.LIST, pemilikList);
         response.put(Constants.TOTAL, count);
         
            
        }catch(Exception e){
            e.printStackTrace();
        }
    	
        return response;
    	
    }
    
    
    @RequestMapping(value = "/master/pemilik/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getById(@PathVariable("id") final int id) {

         Map<String, Object> response = new HashMap<String, Object>();
         
         try{
             
             Pemilik pemilik = pemilikService.getById(id);
             
              response.put(Constants.PEMILIK_KEY, pemilik);
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
         return response;
    }
    
    @RequestMapping(value = "/master/pemilik", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> insert(@RequestBody final Map<String, Object> request) {
        
        Map<String, Object> response = new HashMap<String, Object>();
    	Map<String, Object> pemilikMap = (Map<String, Object>) request.get(Constants.PEMILIK_KEY);

        Pemilik pemilik = new Pemilik();
        try{
        System.out.println("test pemilik");
        
        pemilik.setNmpemilik((String) pemilikMap.get("nmpemilik"));
        pemilik.setAlmtpemilik((String) pemilikMap.get("almtpemilik"));
        pemilik.setJkpemilik((String) pemilikMap.get("jkpemilik"));
        pemilik.setUmurpemilik((String) pemilikMap.get("umurpemilik"));
        pemilik.setNotlpnpemilik((String) pemilikMap.get("notlpnpemilik"));
        
        pemilikService.insert(pemilik);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/pemilik/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody Map<String, Object> update(@PathVariable("id") final int id,
           @RequestBody final Map<String, Object> request) {

    	Map<String, Object> response = new HashMap<String, Object>();
    	Map<String, Object> pemilikMap = (Map<String, Object>) request.get(Constants.PEMILIK_KEY);
        
        Pemilik pemilik = new Pemilik();
        
        try{
        
        pemilik.setId(id);
        pemilik.setNmpemilik((String) pemilikMap.get("nmpemilik"));
        pemilik.setAlmtpemilik((String) pemilikMap.get("almtpemilik"));
        pemilik.setJkpemilik((String) pemilikMap.get("jkpemilik"));
        pemilik.setUmurpemilik((String) pemilikMap.get("umurpemilik"));
        pemilik.setNotlpnpemilik((String) pemilikMap.get("notlpnpemilik"));
        
        pemilikService.update(pemilik);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;
    }
    
    @RequestMapping(value = "/master/pemilik/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id") final int id) {
    	
    	
    	Map<String, Object> response = new HashMap<String, Object>();
        Pemilik pemilik = new Pemilik();
        try{
        
        pemilik.setId(id);
        
        pemilikService.delete(pemilik);
        
        response.put(Constants.STATUS, Constants.OK);
            
        }catch(Exception e){
            
            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();
            
        }
        
        return response;

    }
}
