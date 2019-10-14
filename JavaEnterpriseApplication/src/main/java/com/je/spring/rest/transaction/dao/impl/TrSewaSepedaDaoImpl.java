/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.transaction.dao.impl;

import com.je.spring.rest.transaction.dao.TrSewaSepedaDao;
import com.je.spring.rest.master.model.Pelanggan;
import com.je.spring.rest.master.model.Sepeda;
import com.je.spring.rest.transaction.model.TrSewaSepeda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("trsewasepedaDao")
public class TrSewaSepedaDaoImpl implements TrSewaSepedaDao {

    private static final String SQL_INSERT_TRSEWASEPEDA = "INSERT INTO trsepeda (Idplgn,Idsepeda,tglsewa,tglkembali,hargasewa) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_TRSEWASEPEDA_BY_ID = "SELECT trSepeda.Idtransaksi AS Idtransaksi, pelanggan.Idplgn AS Idplgn, pelanggan.nmplgn AS nmplgn, pelanggan.almtplgn AS almtplgn, pelanggan.umurplgn AS umurplgn, pelanggan.jkplgn AS jkplgn, pelanggan.nohpplgn AS nohpplgn, sepeda.Idsepeda AS Idsepeda, sepeda.merk AS merk, sepeda.jenis AS jenis, sepeda.warna AS warna, sepeda.thnproduksi AS thnproduksi, sepeda.kategori AS kategori, trSepeda.tglsewa AS tglsewa, trsepeda.tglkembali AS tglkembali, trsepeda.hargasewa AS hargasewa FROM trsepeda INNER JOIN pelanggan ON pelanggan.Idplgn=trsepeda.Idplgn INNER JOIN sepeda ON sepeda.Idsepeda = trsepeda.Idsepeda WHERE trsepeda.Idtransaksi = ?";
    private static final String SQL_SELECT_TRSEWASEPEDA_ALL = "SELECT trSepeda.Idtransaksi AS Idtransaksi, pelanggan.Idplgn AS Idplgn, pelanggan.nmplgn AS nmplgn, pelanggan.almtplgn AS almtplgn, pelanggan.umurplgn AS umurplgn, pelanggan.jkplgn AS jkplgn, pelanggan.nohpplgn AS nohpplgn, sepeda.Idsepeda AS Idsepeda, sepeda.merk AS merk, sepeda.jenis AS jenis, sepeda.warna AS warna, sepeda.thnproduksi AS thnproduksi, sepeda.kategori AS kategori, trSepeda.tglsewa AS tglsewa, trsepeda.tglkembali AS tglkembali, trsepeda.hargasewa AS hargasewa FROM trsepeda INNER JOIN pelanggan ON pelanggan.Idplgn=trsepeda.Idplgn INNER JOIN sepeda ON sepeda.Idsepeda = trsepeda.Idsepeda";
    private static final String SQL_UPDATE_TRSEWASEPEDA = "UPDATE trsepeda SET Idplgn=?, Idsepeda=? , tglsewa=?, tglkembali=?, hargasewa=? WHERE Idtransaksi =? ";
    private static final String SQL_DELETE_TRSEWASEPEDA = "DELETE FROM trsepeda WHERE Idtransaksi = ?";
    private static final String SQL_COUNT_TRSEWASEPEDA = "SELECT COUNT(*) FROM trsepeda ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TrSewaSepeda> getAll() {
        List<TrSewaSepeda> trsewasepedaList = null;
        try {
            trsewasepedaList = jdbcTemplate.query(SQL_SELECT_TRSEWASEPEDA_ALL, new Object[]{}, new RowMapper<TrSewaSepeda>() {
                public TrSewaSepeda mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TrSewaSepeda trSewaSepeda = new TrSewaSepeda();
                    Pelanggan pelanggan = new Pelanggan();
                    Sepeda sepeda = new Sepeda();

                    trSewaSepeda.setId(rs.getInt("Idtransaksi"));
                    pelanggan.setId(rs.getInt("Idplgn"));
                    pelanggan.setNmplgn(rs.getString("nmplgn"));
                    pelanggan.setAlmtplgn(rs.getString("almtplgn"));
                    pelanggan.setUmurplgn(rs.getString("umurplgn"));
                    pelanggan.setJkplgn(rs.getString("jkplgn"));
                    pelanggan.setNohpplgn(rs.getString("nohpplgn"));
                    trSewaSepeda.setPelanggan(pelanggan);
                    sepeda.setId(rs.getInt("Idsepeda"));
                    sepeda.setMerek(rs.getString("merk"));
                    sepeda.setTipe(rs.getString("jenis"));
                    sepeda.setWarna(rs.getString("warna"));
                    sepeda.setThnproduksi(rs.getString("thnproduksi"));
                    sepeda.setKategori(rs.getString("kategori"));
                    trSewaSepeda.setSepeda(sepeda);
                    trSewaSepeda.setTglsewa(rs.getString("tglsewa"));
                    trSewaSepeda.setTglkembali(rs.getString("tglkembali"));
                    trSewaSepeda.setHargasewa(rs.getString("hargasewa"));
                    return trSewaSepeda;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trsewasepedaList;

    }

    @Override
    public TrSewaSepeda getById(int id) {
        TrSewaSepeda trSewaSepeda = null;
        try {
            trSewaSepeda = (TrSewaSepeda) jdbcTemplate.queryForObject(SQL_SELECT_TRSEWASEPEDA_BY_ID, new Object[]{id}, new RowMapper<TrSewaSepeda>() {
                public TrSewaSepeda mapRow(ResultSet rs, int rowNum) throws SQLException {
                    TrSewaSepeda trSewaSepeda = new TrSewaSepeda();
                    Pelanggan pelanggan = new Pelanggan();
                    Sepeda sepeda = new Sepeda();

                    trSewaSepeda.setId(rs.getInt("Idtransaksi"));
                    pelanggan.setId(rs.getInt("Idplgn"));
                    pelanggan.setNmplgn(rs.getString("nmplgn"));
                    pelanggan.setAlmtplgn(rs.getString("almtplgn"));
                    pelanggan.setUmurplgn(rs.getString("umurplgn"));
                    pelanggan.setJkplgn(rs.getString("jkplgn"));
                    pelanggan.setNohpplgn(rs.getString("nohpplgn"));
                    trSewaSepeda.setPelanggan(pelanggan);
                    sepeda.setId(rs.getInt("Idsepeda"));
                    sepeda.setMerek(rs.getString("merk"));
                    sepeda.setTipe(rs.getString("jenis"));
                    sepeda.setWarna(rs.getString("warna"));
                    sepeda.setThnproduksi(rs.getString("thnproduksi"));
                    sepeda.setKategori(rs.getString("kategori"));
                    trSewaSepeda.setSepeda(sepeda);
                    trSewaSepeda.setTglsewa(rs.getString("tglsewa"));
                    trSewaSepeda.setTglkembali(rs.getString("tglkembali"));
                    trSewaSepeda.setHargasewa(rs.getString("hargasewa"));
                    return trSewaSepeda;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trSewaSepeda;
    }

    @Override
    public void insert(TrSewaSepeda trSewaSepeda) {
        try {
            jdbcTemplate.update(SQL_INSERT_TRSEWASEPEDA, new Object[]{
                trSewaSepeda.getPelanggan().getId(),
                trSewaSepeda.getSepeda().getId(),
                trSewaSepeda.getTglsewa(),
                trSewaSepeda.getTglkembali(),
                trSewaSepeda.getHargasewa()});
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(TrSewaSepeda trSewaSepeda) {
        try {
            jdbcTemplate.update(SQL_UPDATE_TRSEWASEPEDA, new Object[]{
                trSewaSepeda.getPelanggan().getId(),
                trSewaSepeda.getSepeda().getId(),
                trSewaSepeda.getTglsewa(),
                trSewaSepeda.getTglkembali(),
                trSewaSepeda.getHargasewa(),
                trSewaSepeda.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TrSewaSepeda trSewaSepeda) {

        try {
            jdbcTemplate.update(SQL_DELETE_TRSEWASEPEDA, new Object[]{
                trSewaSepeda.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long count() {
        long count = 0;
        try {
            count = jdbcTemplate.queryForObject(SQL_COUNT_TRSEWASEPEDA, null, Long.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
