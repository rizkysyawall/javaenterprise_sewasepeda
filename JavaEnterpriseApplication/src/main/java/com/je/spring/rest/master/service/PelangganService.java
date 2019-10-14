/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.service;

import com.je.spring.rest.master.model.Pelanggan;
import java.util.List;

/**
 *
 * @author Syawal
 */
public interface PelangganService {
             public List<Pelanggan> getAll();
	 public Pelanggan getById(int id);
	 public void insert(Pelanggan pelanggan);
         public void update(Pelanggan pelanggan);
	 public void delete(Pelanggan pelanggan);
	 public long count();
}
