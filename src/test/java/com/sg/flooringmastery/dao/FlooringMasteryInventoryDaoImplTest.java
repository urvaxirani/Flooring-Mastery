/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Inventory;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author urvax
 */
public class FlooringMasteryInventoryDaoImplTest {

    FlooringMasteryInventoryDao dao;

    public FlooringMasteryInventoryDaoImplTest() {
        dao = new FlooringMasteryInventoryDaoImpl("testInventory.txt");
    }

    @BeforeEach
    public void setUp() throws Exception {
        String fileName = "testInventory.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter out = new PrintWriter(fileWriter);
        out.println("ProductType,CostPerSquareFoot,LaborCostPerSquareFoot\n"
                + "Carpet,2.25,2.10\n"
                + "Laminate,1.75,2.10\n"
                + "Tile,3.50,4.15\n"
                + "Wood,5.15,4.75\n"
                + "Concrete,1.50,4.50\n"
                + "Rugs,2.0,3.30\n"
                + "Plywood,2.4,3.50\n"
                + "Marble,6.80,7.80\n"
                + "Cement,3.0,4.80\n"
                + "Vinyl Tile,5.75,6.82");

        out.flush();
        out.close();

    }

    @Test
    public void testGetInventory() throws Exception {
        String productType = "Carpet";
        BigDecimal costPerSqFt = new BigDecimal("2.25");
        BigDecimal labourCostPerSqFt = new BigDecimal("2.10");

        Inventory testInventory = new Inventory(productType, costPerSqFt, labourCostPerSqFt);
        this.dao.getProductType(productType);
        Inventory fileInventory = dao.getProductType(productType);
        assertTrue(testInventory.equals(fileInventory));
    }

    @Test
    public void testGetAllInventory() throws Exception {
        String productType = "Carpet";
        BigDecimal costPerSqFt = new BigDecimal("2.25");
        BigDecimal labourCostPerSqFt = new BigDecimal("2.10");

        Inventory testInventory = new Inventory(productType, costPerSqFt, labourCostPerSqFt);
        List<Inventory> allInventory = dao.getAllInventory();
        assertTrue(allInventory.contains(testInventory));
        assertEquals(allInventory.size(),10,"all inventory should be 10");
    }

}
