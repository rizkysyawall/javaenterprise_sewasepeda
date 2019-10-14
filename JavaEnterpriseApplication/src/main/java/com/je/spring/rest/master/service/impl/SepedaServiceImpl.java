/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service.impl;

import com.je.spring.rest.master.dao.SepedaDao;
import com.je.spring.rest.master.model.Sepeda;
import com.je.spring.rest.master.service.SepedaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sepedaService")
public class SepedaServiceImpl implements SepedaService{
    
    @Autowired
    SepedaDao sepedaDao;

    @Override
    public List<Sepeda> getAll() {
            List<Sepeda> sepedaList = new ArrayList<Sepeda>();
            sepedaList = sepedaDao.getAll();
            return sepedaList;
    }

    @Override
    public Sepeda getById(int id) {
            Sepeda sepeda = new Sepeda();
            sepeda = sepedaDao.getById(id);

            return sepeda;
    }

    @Override
    public void insert(Sepeda sepeda) {
        sepedaDao.insert(sepeda);
    }

    @Override
    public void update(Sepeda sepeda) {
        sepedaDao.update(sepeda);
    }

    @Override
    public void delete(Sepeda sepeda) {
        sepedaDao.delete(sepeda);
    }

    @Override
    public long count() {
        return sepedaDao.count();
    }
}
