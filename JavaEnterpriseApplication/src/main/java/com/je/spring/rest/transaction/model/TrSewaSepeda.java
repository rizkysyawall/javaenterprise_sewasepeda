/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.transaction.model;

import com.je.spring.rest.master.model.Pelanggan;
import com.je.spring.rest.master.model.Sepeda;

/**
 *
 * @author Syawal
 */
public class TrSewaSepeda {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Sepeda getSepeda() {
        return sepeda;
    }

    public void setSepeda(Sepeda sepeda) {
        this.sepeda = sepeda;
    }

    public String getTglsewa() {
        return tglsewa;
    }

    public void setTglsewa(String tglsewa) {
        this.tglsewa = tglsewa;
    }

    public String getTglkembali() {
        return tglkembali;
    }

    public void setTglkembali(String tglkembali) {
        this.tglkembali = tglkembali;
    }

    public String getHargasewa() {
        return hargasewa;
    }

    public void setHargasewa(String hargasewa) {
        this.hargasewa = hargasewa;
    }
    private int id; 
    private Pelanggan pelanggan ;
    private Sepeda sepeda;
    private String tglsewa;
    private String tglkembali;
    private String hargasewa;
}
