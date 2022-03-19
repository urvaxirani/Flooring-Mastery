/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author urvax
 */
public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryOrderDao {

    public Order order1;
    public Order order2;

    public FlooringMasteryOrderDaoStubImpl() {
        this.order1 = new Order();
        order1.setOrderID(1);
        order1.setCustomerName("Harry");
        order1.setStateCode("NU");
        order1.setTaxRate(new BigDecimal("4.8"));
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("150"));
        order1.setCostPerSqFt(new BigDecimal("5.15"));
        order1.setLabourCostPerSqFt(new BigDecimal("4.75"));
        order1.setMaterialCost(new BigDecimal("772.5"));
        order1.setLabourCost(new BigDecimal("712.5"));
        order1.setTaxAmount(new BigDecimal("7128"));
        order1.setGrandTotal(new BigDecimal("8613"));
        order1.setDueDate(LocalDate.now().plusDays(1));

        this.order2 = new Order();
        order2.setOrderID(1);
        order2.setCustomerName("arshdeep");
        order2.setStateCode("MB");
        order2.setTaxRate(new BigDecimal("7"));
        order2.setProductType("Wood");
        order2.setArea(new BigDecimal("250"));
        order2.setCostPerSqFt(new BigDecimal("5.15"));
        order2.setLabourCostPerSqFt(new BigDecimal("4.75"));
        order2.setMaterialCost(new BigDecimal("1287.5"));
        order2.setLabourCost(new BigDecimal("1187.5"));
        order2.setTaxAmount(new BigDecimal("17325"));
        order2.setGrandTotal(new BigDecimal("19800"));
        order2.setDueDate(LocalDate.now().plusDays(1));

    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {

        return order1;

    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryPersistenceException {

        return order2;

    }

    @Override
    public Order removeOrder(Order order) throws FlooringMasteryPersistenceException {

        return order2;

    }

    @Override
    public Order getAnOrder(LocalDate date, int orderID) throws FlooringMasteryPersistenceException {

        return order1;

    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        List<Order> allOrders = new ArrayList<>();
        allOrders.add(order1);
        return allOrders;
    }

    @Override
    public void exportAllData() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
