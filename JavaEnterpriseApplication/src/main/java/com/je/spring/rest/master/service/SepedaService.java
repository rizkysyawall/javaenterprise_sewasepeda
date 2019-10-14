/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service;

import com.je.spring.rest.master.model.Sepeda;
import java.util.List;

/**
 *
 * @author Syawal
 */
public interface SepedaService {
        public List<Sepeda> getAll();
    public Sepeda getById(int id);
    public void insert(Sepeda sepeda);
    public void update(Sepeda sepeda);
    public void delete(Sepeda sepeda);
    public long count();
}
