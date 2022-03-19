/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author urvax
 */
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {

    //NU,Nunavut,4.8
    //MB,Manitoba,7.00
    public Tax testTax1;
    public Tax testTax2;

    FlooringMasteryTaxDaoStubImpl() {
        this.testTax1 = new Tax("NU", "Nunavat", new BigDecimal("4.8"));
        this.testTax2 = new Tax("MB", "Manitoba", new BigDecimal("7.00"));
    }

    @Override
    public Tax getTax(String stateAbb) throws FlooringMasteryPersistenceException {
        
        if(stateAbb.equals("NU")){
            return testTax1;
        }
        if(stateAbb.equals("MB")){
            return testTax2;
        }

        return null;

    }

    @Override
    public List<Tax> getAllTax() throws FlooringMasteryPersistenceException {

        List<Tax> allTax = new ArrayList<>();
        allTax.add(testTax1);
        allTax.add(testTax2);
        return allTax;

    }

}
