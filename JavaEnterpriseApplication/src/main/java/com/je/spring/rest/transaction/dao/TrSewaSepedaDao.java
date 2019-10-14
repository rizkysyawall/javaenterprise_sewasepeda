/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.transaction.dao;

import com.je.spring.rest.transaction.model.TrSewaSepeda;
import java.util.List;

/**
 *
 * @author Syawal
 */
public interface TrSewaSepedaDao {
    public List<TrSewaSepeda> getAll();
    public TrSewaSepeda getById(int id);  
    public void insert(TrSewaSepeda trSewaSepeda);
    public void update(TrSewaSepeda trSewaSepeda);
    public void delete(TrSewaSepeda trSewaSepeda);
    public long count();
}
