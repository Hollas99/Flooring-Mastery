package com.flooringmastery.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String TYPE;
    private final BigDecimal COST_PER_SQ_FT;
    private final BigDecimal LABOUR_COST_PER_SQ_FT;

    public Product(
        String type, 
        BigDecimal costPerSqFt, 
        BigDecimal laborCostPerSqFt) {
        
        this.TYPE = type;
        this.COST_PER_SQ_FT = costPerSqFt;
        this.LABOUR_COST_PER_SQ_FT = laborCostPerSqFt;
    }

    public String getType() {
        return TYPE;
    }

    public BigDecimal getCostPerSqFt() {
        return COST_PER_SQ_FT;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return LABOUR_COST_PER_SQ_FT;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.TYPE);
        hash = 23 * hash + Objects.hashCode(this.COST_PER_SQ_FT);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.TYPE, other.TYPE)) {
            return false;
        }
        if (!Objects.equals(this.COST_PER_SQ_FT, other.COST_PER_SQ_FT)) {
            return false;
        }
        return Objects.equals(this.LABOUR_COST_PER_SQ_FT, other.LABOUR_COST_PER_SQ_FT);
    }

    @Override
    public String toString() {
        return "FlooringMasteryProduct{" + "TYPE=" + TYPE + ", COST_PER_SQ_FT=" + COST_PER_SQ_FT + ", LABOR_COST_PER_SQ_FT=" + LABOUR_COST_PER_SQ_FT + '}';
    }
}
