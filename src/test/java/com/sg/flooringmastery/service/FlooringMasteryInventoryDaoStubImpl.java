/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Inventory;
import com.sg.flooringmastery.dao.FlooringMasteryInventoryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author urvax
 */
public class FlooringMasteryInventoryDaoStubImpl implements FlooringMasteryInventoryDao {

    public Inventory testInventory;

    //Wood,5.15,4.75
    FlooringMasteryInventoryDaoStubImpl() {
        this.testInventory = new Inventory("Wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
   }

    @Override
    public Inventory getProductType(String Product) throws FlooringMasteryPersistenceException {

        return testInventory;

    }

    @Override
    public List<Inventory> getAllInventory() throws FlooringMasteryPersistenceException {

        List<Inventory> allInventory = new ArrayList<>();
        allInventory.add(testInventory);
        return allInventory;
        
    }

}
