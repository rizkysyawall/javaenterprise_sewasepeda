/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.transaction.service.impl;

import com.je.spring.rest.transaction.dao.TrSewaSepedaDao;
import com.je.spring.rest.transaction.model.TrSewaSepeda;
import com.je.spring.rest.transaction.service.TrSewaSepedaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("trsewasepedaService")
public class TrSewaSepedaServiceImpl implements TrSewaSepedaService{
    
    @Autowired
    TrSewaSepedaDao trSewaSepedaDao;

    @Override
    public List<TrSewaSepeda> getAll() {
//                List<TrSewaSepeda> trsewasepedaList = new ArrayList<>);
        List<TrSewaSepeda> trsewasepedaList = new ArrayList();
        trsewasepedaList = trSewaSepedaDao.getAll();
        return trsewasepedaList;
    }

    @Override
    public TrSewaSepeda getById(int id) {
        TrSewaSepeda trSewaSepeda = new TrSewaSepeda();
        trSewaSepeda = trSewaSepedaDao.getById(id);

        return trSewaSepeda;
    }

    @Override
    public void insert(TrSewaSepeda trSewaSepeda) {        
        trSewaSepedaDao.insert(trSewaSepeda);
    }

    @Override
    public void update(TrSewaSepeda trSewaSepeda) {
        trSewaSepedaDao.update(trSewaSepeda);
    }

    @Override
    public void delete(TrSewaSepeda trSewaSepeda) {
        trSewaSepedaDao.delete(trSewaSepeda);
    }

    @Override
    public long count() {
        return trSewaSepedaDao.count();
    }
    
}
