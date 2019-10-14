/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service;

import com.je.spring.rest.master.model.Pemilik;
import java.util.List;

/**
 *
 * @author Syawal
 */
public interface PemilikService {
        public List<Pemilik> getAll();
    public Pemilik getById(int id);
    public void insert(Pemilik pemilik);
    public void update(Pemilik pemilik);
    public void delete(Pemilik pemilik);
    public long count();
}
