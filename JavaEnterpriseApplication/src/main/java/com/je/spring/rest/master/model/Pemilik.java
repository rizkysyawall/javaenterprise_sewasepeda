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
public class Pemilik {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmpemilik() {
        return nmpemilik;
    }

    public void setNmpemilik(String nmpemilik) {
        this.nmpemilik = nmpemilik;
    }

    public String getAlmtpemilik() {
        return almtpemilik;
    }

    public void setAlmtpemilik(String almtpemilik) {
        this.almtpemilik = almtpemilik;
    }


    public String getJkpemilik() {
        return jkpemilik;
    }

    public void setJkpemilik(String jkpemilik) {
        this.jkpemilik = jkpemilik;
    }

    public String getUmurpemilik() {
        return umurpemilik;
    }

    public void setUmurpemilik(String umurpemilik) {
        this.umurpemilik = umurpemilik;
    }

    public String getNotlpnpemilik() {
        return notlpnpemilik;
    }

    public void setNotlpnpemilik(String notlpnpemilik) {
        this.notlpnpemilik = notlpnpemilik;
    }
    private int id;  
    private String nmpemilik; 
    private String almtpemilik;
    private String jkpemilik;
    private String umurpemilik;
    private String notlpnpemilik;
}
