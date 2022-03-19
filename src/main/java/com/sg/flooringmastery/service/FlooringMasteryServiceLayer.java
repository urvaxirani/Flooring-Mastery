/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Inventory;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author urvax
 */
public interface FlooringMasteryServiceLayer {

    public Order getUnconfirmedOrder(Order order)
            throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException;

    public Order addOrder(Order order)
            throws FlooringMasteryPersistenceException;

    public Order editOrder(Order order)
            throws FlooringMasteryDataValidationException, FlooringMasteryPersistenceException;

    Order removeOrder(Order order)
            throws FlooringMasteryPersistenceException;

    Order getAnOrder(LocalDate date, int orderID)
            throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException;

    List<Order> getAllOrders(LocalDate date)
            throws FlooringMasteryPersistenceException;

    public void exportAllData()
            throws FlooringMasteryPersistenceException;

    public List<Inventory> getAllInventory()
            throws FlooringMasteryPersistenceException;

    public List<Tax> getAllTax()
            throws FlooringMasteryPersistenceException;

}
