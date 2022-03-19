/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

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
public class FlooringMasteryView {

    private UserIO io;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("\n *************************************************************");
        io.print("* << FLOORING PROGRAM >>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Export all Data");
        io.print("* 6. Quit");
        io.print("\n *************************************************************");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo(List<Tax> allTaxes, List<Inventory> allInventory) {
        LocalDate dueDate = io.readLocalDate("Please enter the order date. (Must be in the future!) (MM/dd/yyyy) :");//should be in future not the local date- userIO
        String customerName = io.readString("Please enter your full name");
        displayStates(allTaxes);
        String stateName = io.readString("Please enter your state");
        displayInventory(allInventory);
        String productType = io.readString("Please enter the product type");
        BigDecimal area = io.readBigDecimal("Please enter the area: The area must be a positive decimal");// area should be more than 100 sq fr- serIO

        Order currentOrder = new Order();
        currentOrder.setDueDate(dueDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateCode(stateName);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        return currentOrder;
    }

    public void displayStates(List<Tax> allTaxes) {
        io.print("We service the following states: ");
        String allStates = "";
        for (Tax tax : allTaxes) {
            String state = tax.getStateCode();
            allStates += state + "  ";
        }
        io.print(allStates);
    }

    public void displayInventory(List<Inventory> allInventory) {
        io.print("We offer the following products: ");
        String allProducts = "";
        for (Inventory inventory : allInventory) {
            String product = inventory.getProductType();
            allProducts += product + "  ";
        }
        io.print(allProducts);
    }

    public boolean userConfirms(Order order) {
        io.print(" Date: " + order.getDueDate().format(formatter));
        io.print("Customer: " + order.getCustomerName() + "| State: " + order.getTaxRate() + order.getStateCode());
        io.print("Product: " + order.getProductType() + "| Area: " + order.getArea() + "SqFt.");
        io.print("CostPerSqFt: $" + order.getProductType() + order.getCostPerSqFt() + "| Labour Cost Per SqFt.: $"
                + order.getProductType() + order.getLabourCostPerSqFt());
        io.print("MaterialCost: $" + order.getMaterialCost() + " | Labour Cost Per SqFt.: $" + order.getLabourCost());
        io.print("Tax: $" + order.getTaxAmount() + " | Total: $" + order.getGrandTotal());
        String userResponse = io.readString("Are you sure you want to submit this order? (Y/N)");
        if (userResponse.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    public void displaySuccessfullAdd(Order order) {
        io.print("Successfully Added OrderNumber: " + order.getOrderID());
    }

    public void displayMessage(String message) {
        io.print(message);
    }

    public int itemSelection() {
        return io.readInt("Please Enter Selection");
    }

    public void displayOrder(Order order) {

        io.print("OrderID: " + order.getOrderID() + "| Date: " + order.getDueDate().format(formatter));
        io.print("Customer: " + order.getCustomerName() + "| State: " + order.getTaxRate() + order.getStateCode());
        io.print("Product: " + order.getProductType() + "| Area: " + order.getArea() + "SqFt.");
        io.print("CostPerSqFt: $" + order.getProductType() + order.getCostPerSqFt() + "| Labour Cost Per SqFt.: $"
                + order.getProductType() + order.getLabourCostPerSqFt());
        io.print("MaterialCost: $" + order.getMaterialCost() + " | Labour Cost Per SqFt.: $" + order.getLabourCost());
        io.print("Tax: $" + order.getTaxAmount() + " | Total: $" + order.getGrandTotal());

    }

    public Order editOrderInfo(Order order, List<Tax> allTaxes, List<Inventory> allInventory) {
        String customerName;
        String stateCode;
        BigDecimal area = new BigDecimal("0");
        String product;
        String areaString;
        io.print("Customer Name: " + order.getCustomerName());
        customerName = io.readString("Please enter new Name or leave blank to keep an existing value ");
        if (customerName.isBlank()) {
            customerName = order.getCustomerName();
        }
        io.print("Order State: " + order.getStateCode());
        displayStates(allTaxes);
        stateCode = io.readString("Please enter new state or leave blank to keep an existing value ");
        if (stateCode.isBlank()) {
            stateCode = order.getStateCode();
        }
        io.print("Product Type: " + order.getProductType());
        displayInventory(allInventory);
        product = io.readString("Please enter new product type or leave blank to keep an existing value ");
        if (product.isBlank()) {
            product = order.getProductType();
        }
        io.print("Area: " + order.getArea());
        boolean invalidArea = true;

        while (invalidArea) {
            areaString = io.readString("Please enter the area or leave blank to keep an existing value ");
            if (areaString.isBlank()) {
                area = order.getArea();
                invalidArea = false;
            } else {
                try {
                    area = new BigDecimal(areaString);
                    if (area.compareTo(new BigDecimal("100")) >= 0) {
                        invalidArea = false;
                    } else {
                        io.print("Please enter area of atlease 100 Sq.Ft.");
                    }

                } catch (NumberFormatException e) {
                    io.print("Please enter valid area");
                }
            }
        }

        order.setArea(area);
        order.setCustomerName(customerName);
        order.setProductType(product);
        order.setStateCode(stateCode);

        return order;
    }

    public boolean confirmDelete(Order order) {
        String userResponse = io.readString("Are you sure you want to delete order #" + order.getOrderID() + " (Y/N)? ");
        if (userResponse.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    public void displayOrderList(List<Order> orderList) {//composition
        if (orderList.isEmpty()) {
            io.print("No orders exist for this date");
        }
        for (Order order : orderList) {

            io.print("OrderID: " + order.getOrderID() + "| Date: " + order.getDueDate().format(formatter));
            io.print("Customer: " + order.getCustomerName() + " | State: " + order.getTaxRate() + order.getStateCode());
            io.print("Product: " + order.getProductType() + " | Area: " + order.getArea() + "SqFt.");
            io.print("CostPerSqFt: $" + order.getProductType() + order.getCostPerSqFt() + "| Labour Cost Per SqFt.: $"
                    + order.getProductType() + order.getLabourCostPerSqFt());
            io.print("MaterialCost: $" + order.getMaterialCost() + " | Labour Cost Per SqFt.: $" + order.getLabourCost());
            io.print("Tax: $" + order.getTaxAmount() + " | Total: $" + order.getGrandTotal());

        }
        io.readString("Please hit enter to continue.");
    }

    public LocalDate getUserDate() {
        return io.readLocalDate("Please enter the date: ");
    }

    public int getOrderID() {
        return io.readInt("Please enter order ID: ");
    }

    public void displayexitBanner() {
        io.print("GoodBye");
    }

    public void displaySuccessfullEdit(Order confirmedOrder) {
        io.print("Successfully Edited OrderNumber: " + confirmedOrder.getOrderID());

    }

}
