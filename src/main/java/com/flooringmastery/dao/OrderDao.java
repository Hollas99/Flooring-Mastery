package com.flooringmastery.dao;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import com.flooringmastery.dao.exceptions.FailedExportException;
import com.flooringmastery.dao.exceptions.FailedLoadException;
import com.flooringmastery.dao.exceptions.FailedSaveException;
import com.flooringmastery.model.Order;

public interface OrderDao {
  
     
     //Load orders from external source, if the loading is unsuccessful, throws custom exception
    public void loadFromExternals() throws FailedLoadException;   
    
     //Adds a new order to collection
    public void pushOrder(Order order);
    
     //return all orders in this collection 
    public Set<Order> ordersSet();
    
     //Obtain order matching date and number, if not found, return empty instance
    public Optional<Order> getOrderByDateAndNumber(LocalDate date, int num);
    
     //Remove order matching date and number,If no order found,return empty instance
    public Optional<Order> removeOrderByDateAndNumber(LocalDate date, int num);
    

    //Save orders to file, throw custom exception if fails 
    public void saveToExternals() throws FailedSaveException;
    
    //Export all orders, throws custom exception if fail
    public void export() throws FailedExportException;
}
