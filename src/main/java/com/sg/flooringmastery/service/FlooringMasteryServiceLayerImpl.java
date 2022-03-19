/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryInventoryDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sg.flooringmastery.dto.Inventory;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author urvax
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao dao;
    FlooringMasteryAuditDao auditDao;
    FlooringMasteryTaxDao taxDao;
    FlooringMasteryInventoryDao inventoryDao;
    private int orderIndex = 0;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao dao, FlooringMasteryAuditDao auditDao, FlooringMasteryTaxDao taxDao, FlooringMasteryInventoryDao inventoryDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        this.taxDao = taxDao;
        this.inventoryDao = inventoryDao;
    }

    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MMDDYYYY");
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM-DD-YYYY");

    @Override
    public Order getUnconfirmedOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {

        validateOrder(order);
        Inventory product = inventoryDao.getProductType(order.getProductType());
        Tax state = taxDao.getTax(order.getStateCode());
        order.setTaxRate(state.getTaxRate());
        order.setStateCode(state.getStateCode());
        order.setProductType(product.getProductType());
        order.setLabourCostPerSqFt(product.getLabourCostPerSqFt());
        order.setCostPerSqFt(product.getCostPerSqFt());
        BigDecimal cost = order.getCostPerSqFt().multiply(order.getArea());
        BigDecimal labourCost = order.getLabourCostPerSqFt().multiply(order.getArea());

        order.setLabourCost(labourCost);
        order.setMaterialCost(cost);

        BigDecimal stateTax = (cost.add(labourCost)).multiply(order.getTaxRate());
        order.setTaxAmount(stateTax);
        BigDecimal grandTotal = (cost).add(labourCost).add(stateTax);
        order.setGrandTotal(grandTotal);
        return order;

    }

    public Order addOrder(Order confirmedOrder) throws FlooringMasteryPersistenceException {
        LocalDate orderDate = confirmedOrder.getDueDate();
        List<Order> allOrders = dao.getAllOrders(orderDate);
        int defaultOrderID = 0;
        for (Order order : allOrders) {
            if (order.getOrderID() > defaultOrderID) {
                defaultOrderID = order.getOrderID();
            }
        }
        defaultOrderID++;
        confirmedOrder.setOrderID(defaultOrderID);
        Order completedOrder = dao.addOrder(confirmedOrder);
        auditDao.writeAuditEntry("Order" + confirmedOrder.getOrderID() + " has been placed!");
        return completedOrder;
    }

    @Override
    public Order editOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {
        Order editedOrder = dao.editOrder(order);
        auditDao.writeAuditEntry("Order" + order.getOrderID() + " has been edited!");
        return editedOrder;
    }

    @Override
    public Order removeOrder(Order order) throws FlooringMasteryPersistenceException {

        Order removedOrder = dao.removeOrder(order);
        auditDao.writeAuditEntry("Order" + order + "Removed");
        return removedOrder;
    }

    @Override
    public Order getAnOrder(LocalDate date, int orderID) throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException {
        Order getOrder = dao.getAnOrder(date, orderID);
        if (getOrder == null) {
            throw new FlooringMasteryOrderNotFoundException("Order " + orderID + " Does not exist for " + date);
        }
        return getOrder;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return dao.getAllOrders(date);
    }

    @Override
    public void exportAllData() throws FlooringMasteryPersistenceException {
        dao.exportAllData();
    }

    @Override
    public List<Inventory> getAllInventory() throws FlooringMasteryPersistenceException {
        return inventoryDao.getAllInventory();
    }

    @Override
    public List<Tax> getAllTax() throws FlooringMasteryPersistenceException {
        return taxDao.getAllTax();
    }

    public void validateOrder(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException {
        String cusName = order.getCustomerName();
        BigDecimal orderArea = order.getArea();
        BigDecimal area = new BigDecimal("100");
        String productType = order.getProductType().toLowerCase();
        if (productType.length() > 0) {
            productType = productType.substring(0, 1).toUpperCase() + productType.substring(1, productType.length());
        }
        String stateCode = order.getStateCode();
        LocalDate dueDate = order.getDueDate();
        LocalDate prevDate = LocalDate.now();

        if (cusName.isBlank() || !cusName.matches("^[a-zA-Z0-9., ]+$")) {
            throw new FlooringMasteryDataValidationException("Name must not be blank and must not contain special characters");
        }

        if (orderArea.compareTo(area) < 0) {
            throw new FlooringMasteryDataValidationException("The area must be atleast 100 Sq.Ft.");
        }

        if (inventoryDao.getProductType(productType) == null) {
            throw new FlooringMasteryDataValidationException("Product is invalid");
        }
        if (taxDao.getTax(stateCode.toUpperCase()) == null) {
            throw new FlooringMasteryDataValidationException("State is invalid");
        }

        if (!dueDate.isAfter(prevDate)) {
            throw new FlooringMasteryDataValidationException("Date must be in the future");
        }

    }

}
