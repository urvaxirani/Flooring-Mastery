/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author urvax
 */
public class FlooringMasteryOrderDaoImplTest {

    FlooringMasteryOrderDao dao;
    static File testDirectory = new File("testOrders");
        

    public FlooringMasteryOrderDaoImplTest() {
        dao = new FlooringMasteryOrderDaoImpl("testOrders/");
        testDirectory.mkdir();
    }

    @BeforeEach
    public void setUp() throws Exception {
        testDirectory.mkdir();
        LocalDate tommorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = tommorrow.format(formatter);
        String fileName = "Orders_" + dateString + ".txt";
        FileWriter fileWriter = new FileWriter("testOrders/" + fileName);
        PrintWriter out = new PrintWriter(fileWriter);
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n"
                + "1,arshdeep,MB,7.00,Wood,250.00,5.15,4.75,1287.50,1187.50,17325.00,19800.00");

        out.flush();
        out.close();

    }
    
    @AfterAll
    public static void tearDownClass() throws Exception{
        String[] fileList = testDirectory.list();
        for(String fileName : fileList){
            File currentFile = new File(testDirectory.getPath() + fileName);
            currentFile.delete();
        }
        testDirectory.delete();
    }

    @Test
    public void testAddGetOrder() throws Exception {
        int orderID = 2;
        String customerName = "Harry";
        String stateCode = "NU";
        BigDecimal taxRate = new BigDecimal("4.8");
        String productType = "Rugs";
        BigDecimal area = new BigDecimal("400");
        BigDecimal costPerSqFt = new BigDecimal("2");
        BigDecimal labourCostPerSqFt = new BigDecimal("3.30");
        BigDecimal materialCost = new BigDecimal("800");
        BigDecimal labourCost = new BigDecimal("1320");
        BigDecimal taxAmount = new BigDecimal("10176");
        BigDecimal grandTotal = new BigDecimal("12296");
        LocalDate dueDate = LocalDate.now().plusDays(1);

        Order testOrder = new Order(orderID, customerName, stateCode, taxRate, productType, area, costPerSqFt, labourCostPerSqFt,
                materialCost, labourCost, taxAmount, grandTotal, dueDate);
        this.dao.addOrder(testOrder);
        Order fileOrder = dao.getAnOrder(dueDate, orderID);
        assertTrue(testOrder.equals(fileOrder));
    }

    @Test
    public void testEditGetAllOrders() throws Exception {
        int orderID = 1;
        String customerName = "Harry";
        String stateCode = "NU";
        BigDecimal taxRate = new BigDecimal("4.8");
        String productType = "Rugs";
        BigDecimal area = new BigDecimal("400");
        BigDecimal costPerSqFt = new BigDecimal("2");
        BigDecimal labourCostPerSqFt = new BigDecimal("3.30");
        BigDecimal materialCost = new BigDecimal("800");
        BigDecimal labourCost = new BigDecimal("1320");
        BigDecimal taxAmount = new BigDecimal("10176");
        BigDecimal grandTotal = new BigDecimal("12296");
        LocalDate dueDate = LocalDate.now().plusDays(1);

        Order testOrder = new Order(orderID, customerName, stateCode, taxRate, productType, area, costPerSqFt, labourCostPerSqFt,
                materialCost, labourCost, taxAmount, grandTotal, dueDate);
        dao.editOrder(testOrder);
        List<Order> orders = dao.getAllOrders(dueDate);
        assertEquals(orders.size(), 1);
        assertTrue(orders.contains(testOrder));

    }

    @Test
    public void testRemoveOrder() throws Exception{
        int orderID = 1;
        String customerName = "arshdeep";
        String stateCode = "MB";
        BigDecimal taxRate = new BigDecimal("7.00");
        String productType = "Wood";
        BigDecimal area = new BigDecimal("250");
        BigDecimal costPerSqFt = new BigDecimal("5.15");
        BigDecimal labourCostPerSqFt = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("1287.50");
        BigDecimal labourCost = new BigDecimal("1187.50");
        BigDecimal taxAmount = new BigDecimal("17325");
        BigDecimal grandTotal = new BigDecimal("19800");
        LocalDate dueDate = LocalDate.now().plusDays(1);
        
        Order testOrder = new Order(orderID, customerName, stateCode, taxRate, productType, area, costPerSqFt, labourCostPerSqFt,
                materialCost, labourCost, taxAmount, grandTotal, dueDate);
        dao.removeOrder(testOrder);
        List<Order> orders = dao.getAllOrders(dueDate);
        assertEquals(orders.size(), 0);
        
    }

}
