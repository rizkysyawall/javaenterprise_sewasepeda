/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.dao.impl;

import com.je.spring.rest.master.dao.SepedaDao;
import com.je.spring.rest.master.model.Pemilik;
import com.je.spring.rest.master.model.Sepeda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sepedaDao")
public class SepedaDaoImpl implements SepedaDao {

    private static final String SQL_INSERT_SEPEDA = "INSERT INTO sepeda (Idpemilik,merk,jenis,warna,thnproduksi,kategori) VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT_SEPEDA_BY_ID = "SELECT sepeda.Idsepeda AS Idsepeda, pemilik.Idpemilik AS Idpemilik, pemilik.nmpemilik AS nmpemilik, sepeda.merk AS merk, sepeda.jenis AS jenis, sepeda.warna AS warna, sepeda.thnproduksi AS thnproduksi, sepeda.kategori AS kategori FROM sepeda INNER JOIN pemilik ON pemilik.Idpemilik=sepeda.Idpemilik WHERE sepeda.Idsepeda =? ";
    private static final String SQL_SELECT_SEPEDA_ALL = "SELECT sepeda.Idsepeda AS Idsepeda, pemilik.Idpemilik AS Idpemilik, pemilik.nmpemilik AS nmpemilik, sepeda.merk AS merk, sepeda.jenis AS jenis, sepeda.warna AS warna, sepeda.thnproduksi AS thnproduksi, sepeda.kategori AS kategori FROM sepeda INNER JOIN pemilik ON pemilik.Idpemilik=sepeda.Idpemilik ";
    private static final String SQL_UPDATE_SEPEDA = "UPDATE sepeda SET Idpemilik=?, merk=? , jenis=?, warna=?, thnproduksi=?, kategori=? WHERE Idsepeda =? ";
    private static final String SQL_DELETE_SEPEDA = "DELETE FROM sepeda WHERE Idsepeda = ?";
    private static final String SQL_COUNT_SEPEDA = "SELECT COUNT(*) FROM sepeda ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Sepeda> getAll() {
        List<Sepeda> sepedaList = null;
        try {

            sepedaList = jdbcTemplate.query(SQL_SELECT_SEPEDA_ALL, new Object[]{}, new RowMapper<Sepeda>() {
                public Sepeda mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Sepeda sepeda = new Sepeda();
                    Pemilik pemilik = new Pemilik();
                    sepeda.setId(rs.getInt("Idsepeda"));
                    pemilik.setId(rs.getInt("Idpemilik"));
                    pemilik.setNmpemilik(rs.getString("nmpemilik"));
                    sepeda.setPemilik(pemilik);
                    sepeda.setMerek(rs.getString("merk"));
                    sepeda.setTipe(rs.getString("jenis"));
                    sepeda.setWarna(rs.getString("warna"));
                    sepeda.setThnproduksi(rs.getString("thnproduksi"));
                    sepeda.setKategori(rs.getString("kategori"));
                    return sepeda;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sepedaList;
    }

    @Override
    public Sepeda getById(int id) {
        Sepeda sepeda = null;
        try {

            sepeda = (Sepeda) jdbcTemplate.queryForObject(SQL_SELECT_SEPEDA_BY_ID, new Object[]{id}, new RowMapper<Sepeda>() {
                public Sepeda mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Sepeda sepeda = new Sepeda();
                    Pemilik pemilik = new Pemilik();
                    sepeda.setId(rs.getInt("Idsepeda"));
                    pemilik.setId(rs.getInt("Idpemilik"));
                    pemilik.setNmpemilik(rs.getString("nmpemilik"));
                    sepeda.setPemilik(pemilik);
                    sepeda.setMerek(rs.getString("merk"));
                    sepeda.setTipe(rs.getString("jenis"));
                    sepeda.setWarna(rs.getString("warna"));
                    sepeda.setThnproduksi(rs.getString("thnproduksi"));
                    sepeda.setKategori(rs.getString("kategori"));

                    return sepeda;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sepeda;
    }

    @Override
    public void insert(Sepeda sepeda) {
        try {
            jdbcTemplate.update(SQL_INSERT_SEPEDA, new Object[]{
                sepeda.getPemilik().getId(),
                sepeda.getMerek(),
                sepeda.getTipe(),
                sepeda.getWarna(),
                sepeda.getThnproduksi(),
                sepeda.getKategori()});
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void update(Sepeda sepeda) {
        try {
            jdbcTemplate.update(SQL_UPDATE_SEPEDA, new Object[]{
                sepeda.getPemilik().getId(),
                sepeda.getMerek(),
                sepeda.getTipe(),
                sepeda.getWarna(),
                sepeda.getThnproduksi(),
                sepeda.getKategori(),
                sepeda.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Sepeda sepeda) {

        try {
            jdbcTemplate.update(SQL_DELETE_SEPEDA, new Object[]{
                sepeda.getId()});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long count() {

        long count = 0;
        try {
            count = jdbcTemplate.queryForObject(SQL_COUNT_SEPEDA, null, Long.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
