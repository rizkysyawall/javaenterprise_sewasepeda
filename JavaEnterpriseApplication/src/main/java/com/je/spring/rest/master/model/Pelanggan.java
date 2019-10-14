/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.model;

/**
 *
 * @author Syawal
 */
import java.io.Serializable;
public class Pelanggan {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmplgn() {
        return nmplgn;
    }

    public void setNmplgn(String nmplgn) {
        this.nmplgn = nmplgn;
    }

    public String getAlmtplgn() {
        return almtplgn;
    }

    public void setAlmtplgn(String almtplgn) {
        this.almtplgn = almtplgn;
    }

    public String getUmurplgn() {
        return umurplgn;
    }

    public void setUmurplgn(String umurplgn) {
        this.umurplgn = umurplgn;
    }

    public String getJkplgn() {
        return jkplgn;
    }

    public void setJkplgn(String jkplgn) {
        this.jkplgn = jkplgn;
    }

    public String getNohpplgn() {
        return nohpplgn;
    }

    public void setNohpplgn(String nohpplgn) {
        this.nohpplgn = nohpplgn;
    }

    private int id;  
    private String nmplgn; 
    private String almtplgn;
    private String umurplgn;
    private String jkplgn;
    private String nohpplgn;
}
