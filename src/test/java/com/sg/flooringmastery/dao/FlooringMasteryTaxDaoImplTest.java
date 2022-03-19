/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Inventory;
import com.sg.flooringmastery.dto.Tax;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author urvax
 */
public class FlooringMasteryTaxDaoImplTest {

    FlooringMasteryTaxDao dao;
    
    public FlooringMasteryTaxDaoImplTest() {
        dao = new FlooringMasteryTaxDaoImpl("testTax.txt");

    }

    @BeforeEach
    public void setUp() throws Exception {
        String fileName = "testTax.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter out = new PrintWriter(fileWriter);
        out.println("StateCode,State,StateTaxRtae\n"
                + "BC,British Columbia,4.45\n"
                + "AB,Alberta,9.25\n"
                + "ON,Ontario,6.00\n"
                + "SK,Saskatchewan,25.00\n"
                + "MB,Manitoba,7.00\n"
                + "QC,Quebec,2.30\n"
                + "NB,New Brunswick,2.5\n"
                + "NS,Nova Scotia,5.00\n"
                + "PE,Prince Edward Island,4.35\n"
                + "NL,Newfoundland and Labrador,5.30\n"
                + "YT,Yukon,3.2\n"
                + "NT,Northwest Territories,3.2\n"
                + "NU,Nunavut,4.8");

        out.flush();
        out.close();

    }

    @Test
    public void testGetTax() throws Exception {
        String stateCode = "BC";
        String stateName = "British Columbia";
        BigDecimal taxRate = new BigDecimal("4.45");

        Tax testTax = new Tax(stateCode, stateName, taxRate);
        this.dao.getTax(stateCode);
        Tax fileTax = dao.getTax(stateCode);
        assertTrue(testTax.equals(fileTax));
    }
    
    @Test
    public void testGetAllInventory() throws Exception {
        String stateCode = "BC";
        String stateName = "British Columbia";
        BigDecimal taxRate = new BigDecimal("4.45");

        Tax testTax = new Tax(stateCode, stateName, taxRate);
        List<Tax> allTax = dao.getAllTax();
        assertTrue(allTax.contains(testTax));
        assertEquals(allTax.size(),13,"all inventory should be 13");
    }

}
