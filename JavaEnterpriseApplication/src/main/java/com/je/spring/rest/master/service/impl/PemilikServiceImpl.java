/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service.impl;

import com.je.spring.rest.master.dao.PemilikDao;
import com.je.spring.rest.master.model.Pemilik;
import com.je.spring.rest.master.service.PemilikService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pemilikService")
public class PemilikServiceImpl implements PemilikService{

    @Autowired
    PemilikDao pemilikDao;
    
    @Override
    public List<Pemilik> getAll() {
            List<Pemilik> pemilikList = new ArrayList<Pemilik>();
            pemilikList = pemilikDao.getAll();
            return pemilikList;
    }

    @Override
    public Pemilik getById(int id) {
    
        Pemilik pemilik = new Pemilik();
        pemilik = pemilikDao.getById(id);
        
        return pemilik;
    }

    @Override
    public long count() {
    
        return pemilikDao.count();
    }

    @Override
    public void insert(Pemilik pemilik) {
    
        pemilikDao.insert(pemilik);
    }

    @Override
    public void update(Pemilik pemilik) {
    
        pemilikDao.update(pemilik);
    }

    @Override
    public void delete(Pemilik pemilik) {
       
        pemilikDao.delete(pemilik);
    }
    
}
