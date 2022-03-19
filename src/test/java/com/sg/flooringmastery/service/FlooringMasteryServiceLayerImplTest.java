/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Inventory;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.dao.FlooringMasteryInventoryDao;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author urvax
 */
public class FlooringMasteryServiceLayerImplTest {

    public FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerImplTest() {
//        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderDaoStubImpl();
//        FlooringMasteryInventoryDao inventoryDao = new FlooringMasteryInventoryDaoStubImpl();
//        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoStubImpl();
//        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
//
//        this.service = new FlooringMasteryServiceLayerImpl(orderDao, auditDao, taxDao, inventoryDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringMasteryServiceLayer.class);
    }

    @Test
    public void testAddGetValidOrder() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "Harry";
        String stateName = "NU";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("150");

        Order currentOrder = new Order();
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            Order submittedOrder = service.addOrder(currentOrder);
            Order retrievedOrder = service.getAnOrder(dueDate, 1);
            assertEquals(submittedOrder, retrievedOrder, "submitted order should be equal to retrieved order");

        } catch (FlooringMasteryDataValidationException | FlooringMasteryPersistenceException | FlooringMasteryOrderNotFoundException e) {
            fail("Valid order should not throw any exceptions");
        }

    }

    @Test
    public void testGetAllOrders() {

        try {
            LocalDate dueDate = LocalDate.now().plusDays(1);
            Order currentOrder = service.getAnOrder(dueDate, 1);
            List<Order> allOrders = service.getAllOrders(dueDate);

            assertEquals(allOrders.size(), 1, "all orders should contain only 1 order");
            assertTrue(allOrders.contains(currentOrder), "all orders should contain all order");

        } catch (FlooringMasteryPersistenceException | FlooringMasteryOrderNotFoundException e) {

            fail("getting an order should not throw any exceptions");

        }

    }

    @Test
    public void testEditOrder() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "arshdeep";
        String stateName = "MB";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("250");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            assertEquals(service.editOrder(currentOrder), currentOrder, "currentorder should equsl edited order");

        } catch (FlooringMasteryPersistenceException | FlooringMasteryDataValidationException e) {
            fail("editing order with valid data should npt throw any exceptions");
        }

    }

    @Test
    public void testRemoveOrder() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "arshdeep";
        String stateName = "MB";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("250");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            Order removedOrder = service.removeOrder(currentOrder);

        } catch (FlooringMasteryPersistenceException | FlooringMasteryDataValidationException e) {
            fail("removing order should not through any exceptions");
        }
    }

    @Test
    public void testInvalidArea() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "Harry";
        String stateName = "NU";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("99");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            fail("invalid area should have thrown an exception");
        } catch (FlooringMasteryPersistenceException e) {
            fail("wrong exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }

    }

    @Test
    public void testInvalidStateCode() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "Harry";
        String stateName = "FL";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("150");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            fail("invalid statecode should have thrown an exception");
        } catch (FlooringMasteryPersistenceException e) {
            fail("wrong exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }

    @Test
    public void testInvalidCustomerName() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "*^Harry&#";
        String stateName = "NU";
        String productType = "Wood";
        BigDecimal area = new BigDecimal("150");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            fail("invalid customer name should have thrown an exception");
        } catch (FlooringMasteryPersistenceException e) {
            fail("wrong exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }

    @Test
    public void testInvalidProductType() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        String customerName = "Harry";
        String stateName = "NU";
        String productType = "Leather";
        BigDecimal area = new BigDecimal("99");

        Order currentOrder = new Order();
        currentOrder.setOrderID(1);
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        try {
            currentOrder = service.getUnconfirmedOrder(currentOrder);
            fail("invalid product type should have thrown an exception");
        } catch (FlooringMasteryPersistenceException e) {
            fail("wrong exception was thrown");
        } catch (FlooringMasteryDataValidationException e) {
            return;
        }
    }

    @Test
    public void testGetInventory() {
        String productType = "Wood";
        BigDecimal costPerSqFt = new BigDecimal("5.15");
        BigDecimal labourCostPerSqFt = new BigDecimal("4.75");

        Inventory testInventory = new Inventory(productType, costPerSqFt, labourCostPerSqFt);

        try {
            List<Inventory> retrievedInventory = service.getAllInventory();
            assertTrue(retrievedInventory.size() == 1, "retrievedInventory should only contain 1 item");
            assertTrue(retrievedInventory.contains(testInventory), "testinventory should");
        } catch (FlooringMasteryPersistenceException e) {
            fail("getting valid inventory should not throw any exception");
        }

    }

    @Test
    public void testGetTax() {

        Tax testTax1 = new Tax("NU", "Nunavat", new BigDecimal("4.8"));
        Tax testTax2 = new Tax("MB", "Manitoba", new BigDecimal("7.00"));

        try {
            List<Tax> allTaxes = service.getAllTax();
            assertTrue(allTaxes.size() == 2, "tax should only contain 2 item");
            assertTrue(allTaxes.contains(testTax1), "alltaxs should contain testtax1");
            assertTrue(allTaxes.contains(testTax2), "alltaxes should contain testtax2");

        } catch (FlooringMasteryPersistenceException e) {
            fail("getting valid taxes should not throw any exception");
        }

    }
}
