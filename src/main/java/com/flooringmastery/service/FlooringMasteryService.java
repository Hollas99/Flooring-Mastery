package com.flooringmastery.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.flooringmastery.dao.FlooringMasteryOrderDao;
import com.flooringmastery.dao.FlooringMasteryProductDao;
import com.flooringmastery.dao.FlooringMasteryTaxDao;
import com.flooringmastery.dao.exceptions.FlooringMasteryFailedLoadException;
import com.flooringmastery.model.FlooringMasteryOrder;
import com.flooringmastery.model.FlooringMasteryProduct;

public class FlooringMasteryService {
    private FlooringMasteryTaxDao taxDao;
    private FlooringMasteryProductDao prodDao;
    private FlooringMasteryOrderDao orderDao;

    public FlooringMasteryService(FlooringMasteryTaxDao taxDao, FlooringMasteryProductDao prodDao, FlooringMasteryOrderDao orderDao) {
        this.taxDao = taxDao;
        this.prodDao = prodDao;
        this.orderDao = orderDao;
    }
    
    public void loadDaos() throws FlooringMasteryFailedLoadException {
        List<String> loadErrs = new LinkedList<>();
        
        try {
            taxDao.loadDataFromExternals();
        } catch (FlooringMasteryFailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        try {
            prodDao.loadDataFromExternals();
        } catch (FlooringMasteryFailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        try {
            orderDao.loadFromExternals();
        } catch (FlooringMasteryFailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        if (!loadErrs.isEmpty()) {
            throw new FlooringMasteryFailedLoadException(
                loadErrs.stream().collect(Collectors.joining(", "))
            );
        }
    }

    
    // Check if state has tax data
    public boolean hasTaxDataForStateAbbr(String abbr) {
        return taxDao.hasInfoForStateAbbr(abbr);
    }

    //Get percentage tax info based on state
    public Optional<BigDecimal> percentTaxRateForStateAbbr(String abbr) {
        return taxDao.percentTaxRateForStateAbbr(abbr);
    }
    
    //Returns set of all products
    public Set<FlooringMasteryProduct> productsSet() {
        return prodDao.productsSet();
    }
    
    //Check if product type exists
    public boolean hasProductWithType(String type) {
        return prodDao.hasProductWithType(type);
    }

    //Get product by type
    public Optional<FlooringMasteryProduct> getProductByType(String type) {
        return prodDao.getProductByType(type);
    }

    public Set<FlooringMasteryOrder> ordersSet() {
        return orderDao.ordersSet();
    }
    
    //Returns set of all orders from given date
    public Set<FlooringMasteryOrder> getOrdersByDate(LocalDate date) {
        return orderDao.ordersSet().stream()
                .filter((FlooringMasteryOrder order) -> {
                    return order.getOrderDate().equals(date);
                })
                .collect(Collectors.toSet());
    }

    //Pushes order into collection
    public void pushOrder(FlooringMasteryOrder order) {
        orderDao.pushOrder(order);
    }
}
