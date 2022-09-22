package com.flooringmastery.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.dao.exceptions.FailedLoadException;
import com.flooringmastery.model.Order;
import com.flooringmastery.model.Product;

public class FlooringService {
    private TaxDao taxDao;
    private ProductDao prodDao;
    private OrderDao orderDao;

    public FlooringService(TaxDao taxDao, ProductDao prodDao, OrderDao orderDao) {
        this.taxDao = taxDao;
        this.prodDao = prodDao;
        this.orderDao = orderDao;
    }
    
    public void loadDaos() throws FailedLoadException {
        List<String> loadErrs = new LinkedList<>();
        
        try {
            taxDao.loadDataFromExternals();
        } catch (FailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        try {
            prodDao.loadDataFromExternals();
        } catch (FailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        try {
            orderDao.loadFromExternals();
        } catch (FailedLoadException ex) {
            loadErrs.add(ex.getMessage());
        }
        
        if (!loadErrs.isEmpty()) {
            throw new FailedLoadException(
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
    public Set<Product> productsSet() {
        return prodDao.productsSet();
    }
    
    //Check if product type exists
    public boolean hasProductWithType(String type) {
        return prodDao.hasProductWithType(type);
    }

    //Get product by type
    public Optional<Product> getProductByType(String type) {
        return prodDao.getProductByType(type);
    }

    public Set<Order> ordersSet() {
        return orderDao.ordersSet();
    }
    
    //Returns set of all orders from given date
    public Set<Order> getOrdersByDate(LocalDate date) {
        return orderDao.ordersSet().stream()
                .filter((Order order) -> {
                    return order.getOrderDate().equals(date);
                })
                .collect(Collectors.toSet());
    }

    //Pushes order into collection
    public void pushOrder(Order order) {
        orderDao.pushOrder(order);
    }
}
