/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author urvax
 */
public class Order {

    int orderID;
    String customerName;
    String stateCode;
    BigDecimal taxRate;
    String productType;
    BigDecimal area;
    BigDecimal costPerSqFt;
    BigDecimal labourCostPerSqFt;
    BigDecimal materialCost;
    BigDecimal labourCost;
    BigDecimal taxAmount;
    BigDecimal grandTotal;
    LocalDate dueDate;

    public Order(int orderID, String customerName, String stateCode, BigDecimal taxRate, String productType,
            BigDecimal area, BigDecimal costPerSqFt, BigDecimal labourCostPerSqFt,
            BigDecimal materialCost, BigDecimal labourCost, BigDecimal taxAmount, BigDecimal grandTotal, LocalDate dueDate) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.stateCode = stateCode;
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
        this.productType = productType;
        this.area = area.setScale(2, RoundingMode.HALF_UP);
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
        this.labourCostPerSqFt = labourCostPerSqFt.setScale(2, RoundingMode.HALF_UP);
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
        this.labourCost = labourCost.setScale(2, RoundingMode.HALF_UP);
        this.taxAmount = taxAmount.setScale(2, RoundingMode.HALF_UP);
        this.grandTotal = grandTotal.setScale(2, RoundingMode.HALF_UP);
        this.dueDate = dueDate;

    }

    public Order() {

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate.setScale(2, RoundingMode.HALF_UP);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLabourCostPerSqFt() {
        return labourCostPerSqFt;
    }

    public void setLabourCostPerSqFt(BigDecimal labourCostPerSqFt) {
        this.labourCostPerSqFt = labourCostPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.orderID;
        hash = 37 * hash + Objects.hashCode(this.customerName);
        hash = 37 * hash + Objects.hashCode(this.stateCode);
        hash = 37 * hash + Objects.hashCode(this.taxRate);
        hash = 37 * hash + Objects.hashCode(this.productType);
        hash = 37 * hash + Objects.hashCode(this.area);
        hash = 37 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 37 * hash + Objects.hashCode(this.labourCostPerSqFt);
        hash = 37 * hash + Objects.hashCode(this.materialCost);
        hash = 37 * hash + Objects.hashCode(this.labourCost);
        hash = 37 * hash + Objects.hashCode(this.taxAmount);
        hash = 37 * hash + Objects.hashCode(this.grandTotal);
        hash = 37 * hash + Objects.hashCode(this.dueDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateCode, other.stateCode)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labourCostPerSqFt, other.labourCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.labourCost, other.labourCost)) {
            return false;
        }
        if (!Objects.equals(this.taxAmount, other.taxAmount)) {
            return false;
        }
        if (!Objects.equals(this.grandTotal, other.grandTotal)) {
            return false;
        }
        if (!Objects.equals(this.dueDate, other.dueDate)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerName=" + customerName + ", stateCode=" + stateCode + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area + ", costPerSqFt=" + costPerSqFt + ", labourCostPerSqFt=" + labourCostPerSqFt + ", materialCost=" + materialCost + ", labourCost=" + labourCost + ", taxAmount=" + taxAmount + ", grandTotal=" + grandTotal + ", dueDate=" + dueDate + ", localDate=" + '}';
    }

}
