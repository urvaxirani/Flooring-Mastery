/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author urvax
 */
public class Inventory {
    
    String productType;
    BigDecimal costPerSqFt;
    BigDecimal labourCostPerSqFt;
    
    public Inventory(String productType, BigDecimal costPerSqFt, BigDecimal labourCostPerSqFt) {
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.labourCostPerSqFt = labourCostPerSqFt;
    }
    
    public Inventory() {
        
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLabourCostPerSqFt() {
        return labourCostPerSqFt;
    }

    public void setLabourCostPerSqFt(BigDecimal labourCostPerSqFt) {
        this.labourCostPerSqFt = labourCostPerSqFt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.productType);
        hash = 41 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 41 * hash + Objects.hashCode(this.labourCostPerSqFt);
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
        final Inventory other = (Inventory) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labourCostPerSqFt, other.labourCostPerSqFt)) {
            return false;
        }
        return true;
    }
    
    
    
}
