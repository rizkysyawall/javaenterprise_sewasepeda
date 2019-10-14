/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service.impl;

import com.je.spring.rest.master.dao.PelangganDao;
import com.je.spring.rest.master.model.Pelanggan;
import com.je.spring.rest.master.service.PelangganService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pelangganService")
public class PelangganServiceImpl implements PelangganService{

    @Autowired
    PelangganDao pelangganDao;
    
    @Override
    public List<Pelanggan> getAll() {
            List<Pelanggan> pelangganList = new ArrayList<Pelanggan>();
            pelangganList = pelangganDao.getAll();
            return pelangganList;
    }

    @Override
    public Pelanggan getById(int id) {
        Pelanggan pelanggan = new Pelanggan();
        pelanggan = pelangganDao.getById(id);
        
        return pelanggan;
    }

    @Override
    public void insert(Pelanggan pelanggan) {
        pelangganDao.insert(pelanggan);
    }

    @Override
    public void update(Pelanggan pelanggan) {
        pelangganDao.update(pelanggan);
    }

    @Override
    public void delete(Pelanggan pelanggan) {
        pelangganDao.delete(pelanggan);
    }

    @Override
    public long count() {
    return pelangganDao.count();
    }
    
}
