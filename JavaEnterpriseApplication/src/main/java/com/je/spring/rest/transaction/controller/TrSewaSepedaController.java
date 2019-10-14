/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.transaction.controller;

import com.google.gson.Gson;
import com.je.spring.rest.transaction.service.TrSewaSepedaService;
import com.je.spring.rest.master.service.PelangganService;
import com.je.spring.rest.master.service.SepedaService;
import com.je.spring.rest.transaction.model.TrSewaSepeda;
import com.je.spring.rest.master.model.Pelanggan;
import com.je.spring.rest.master.model.Sepeda;
import com.je.spring.rest.util.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrSewaSepedaController {

    @Autowired
    private TrSewaSepedaService trSewaSepedaService;

    @Autowired
    private PelangganService pelangganService;

    @Autowired
    private SepedaService sepedaService;

    Gson gson = new Gson();

    @RequestMapping(value = "/transaksi/trsewasepeda", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map<String, Object> insert(@RequestBody final Map<String, Object> request) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> trsewasepedaMap = (Map<String, Object>) request.get(Constants.TRSEPEDA_KEY);

        try {
            System.out.println("test trsewasepeda");

            TrSewaSepeda trSewaSepeda = gson.fromJson(trsewasepedaMap.toString(), TrSewaSepeda.class);

            Integer Idpelanggan = trSewaSepeda.getPelanggan().getId();

            if (pelangganService.getById(Idpelanggan) == null) {

                response.put(Constants.STATUS, "Pelanggan Id Not Found");

                return response;
            }

            Integer sepedaId = trSewaSepeda.getSepeda().getId();

            if (sepedaService.getById(sepedaId) == null) {

                response.put(Constants.STATUS, "Sepeda Id Not Found");

                return response;
            }

            trSewaSepedaService.insert(trSewaSepeda);

            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;
    }

    @RequestMapping(value = "/transaksi/trsewasepeda/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    Map<String, Object> update(@PathVariable("id") final int id,
            @RequestBody final Map<String, Object> request) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> trsewasepedaMap = (Map<String, Object>) request.get(Constants.TRSEPEDA_KEY);

        try {
            String jsonString = new ObjectMapper().writeValueAsString(trsewasepedaMap);
            TrSewaSepeda trSewaSepeda = gson.fromJson(jsonString, TrSewaSepeda.class);
            trSewaSepeda.setId(id);
            Integer Idpelanggan = trSewaSepeda.getPelanggan().getId();

            if (pelangganService.getById(Idpelanggan) == null) {

                response.put(Constants.STATUS, "Pelanggan Id Not Found");

                return response;
            }

            Integer sepedaId = trSewaSepeda.getSepeda().getId();

            if (sepedaService.getById(sepedaId) == null) {

                response.put(Constants.STATUS, "Sepeda Id Not Found");

                return response;
            }

            trSewaSepedaService.update(trSewaSepeda);

            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;
    }

    @RequestMapping(value = "/transaksi/trsewasepeda", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getAll() {

        Map<String, Object> response = new HashMap<String, Object>();

        try {

            List<TrSewaSepeda> trsewasepedaList = trSewaSepedaService.getAll();
            long count = trSewaSepedaService.count();

            response.put(Constants.LIST, trsewasepedaList);
            response.put(Constants.TOTAL, count);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    @RequestMapping(value = "/transaksi/trsewasepeda/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getById(@PathVariable("id") final int id) {

        Map<String, Object> response = new HashMap<String, Object>();

        try {

            TrSewaSepeda trSewaSepeda = trSewaSepedaService.getById(id);

            response.put(Constants.TRSEPEDA_KEY, trSewaSepeda);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/transaksi/trsewasepeda/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id") final int id) {

        Map<String, Object> response = new HashMap<String, Object>();
        TrSewaSepeda trSewaSepeda = new TrSewaSepeda();
        try {

            trSewaSepeda.setId(id);

            trSewaSepedaService.delete(trSewaSepeda);

            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;

    }
}
