/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.je.spring.rest.master.controller;

import com.google.gson.Gson;
import com.je.spring.rest.master.service.SepedaService;
import com.je.spring.rest.master.service.PemilikService;
import com.je.spring.rest.master.model.Sepeda;
import com.je.spring.rest.util.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SepedaController {

    @Autowired
    private SepedaService sepedaService;

    @Autowired
    private PemilikService pemilikService;

    Gson gson = new Gson();

    @RequestMapping(value = "/master/sepeda", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getAll() {

        Map<String, Object> response = new HashMap<String, Object>();

        try {

            List<Sepeda> sepedaList = sepedaService.getAll();
            long count = sepedaService.count();

            response.put(Constants.LIST, sepedaList);
            response.put(Constants.TOTAL, count);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    @RequestMapping(value = "/master/sepeda/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Object> getById(@PathVariable("id") final int id) {

        Map<String, Object> response = new HashMap<String, Object>();

        try {
            Sepeda sepeda = sepedaService.getById(id);

            response.put(Constants.SEPEDA_KEY, sepeda);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/master/sepeda", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map<String, Object> insert(@RequestBody final Map<String, Object> request) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> sepedaMap = (Map<String, Object>) request.get(Constants.SEPEDA_KEY);

        try {
            System.out.println("test sepeda");
            Sepeda sepeda = gson.fromJson(sepedaMap.toString(), Sepeda.class);

            Integer Idpemilik = sepeda.getPemilik().getId();
            if (pemilikService.getById(Idpemilik) == null) {

                response.put(Constants.STATUS, "Pemilik Id Not Found");

                return response;
            };
            sepedaService.insert(sepeda);
            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;
    }

    @RequestMapping(value = "/master/sepeda/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    Map<String, Object> update(@PathVariable("id") final int id,
            @RequestBody final Map<String, Object> request) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> sepedaMap = (Map<String, Object>) request.get(Constants.SEPEDA_KEY);

        try {

            Sepeda sepeda = gson.fromJson(sepedaMap.toString(), Sepeda.class);
            sepeda.setId(id);

            Integer Idpemilik = sepeda.getPemilik().getId();

            if (pemilikService.getById(Idpemilik) == null) {

                response.put(Constants.STATUS, "Pemilik Id Not Found");

                return response;
            }

            sepedaService.update(sepeda);

            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;
    }

    @RequestMapping(value = "/master/sepeda/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id") final int id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Sepeda sepeda = new Sepeda();
        try {

            sepeda.setId(id);

            sepedaService.delete(sepeda);

            response.put(Constants.STATUS, Constants.OK);

        } catch (Exception e) {

            response.put(Constants.STATUS, Constants.ERROR);
            e.printStackTrace();

        }

        return response;

    }
}
