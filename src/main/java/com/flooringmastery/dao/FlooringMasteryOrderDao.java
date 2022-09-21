package com.flooringmastery.dao;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import com.flooringmastery.dao.exceptions.FlooringMasteryFailedExportException;
import com.flooringmastery.dao.exceptions.FlooringMasteryFailedLoadException;
import com.flooringmastery.dao.exceptions.FlooringMasteryFailedSaveException;
import com.flooringmastery.model.FlooringMasteryOrder;

public interface FlooringMasteryOrderDao {
  
     
     //Load orders from external source, if the loading is unsuccessful, throws custom exception
    public void loadFromExternals() throws FlooringMasteryFailedLoadException;   
    
     //Adds a new order to collection
    public void pushOrder(FlooringMasteryOrder order);
    
     //return all orders in this collection 
    public Set<FlooringMasteryOrder> ordersSet();
    
     //Obtain order matching date and number, if not found, return empty instance
    public Optional<FlooringMasteryOrder> getOrderByDateAndNumber(LocalDate date, int num);
    
     //Remove order matching date and number,If no order found,return empty instance
    public Optional<FlooringMasteryOrder> removeOrderByDateAndNumber(LocalDate date, int num);
    

    //Save orders to file, throw custom exception if fails 
    public void saveToExternals() throws FlooringMasteryFailedSaveException;
    
    //Export all orders, throws custom exception if fail
    public void export() throws FlooringMasteryFailedExportException;
}
