package com.flooringmastery.dao;

import java.util.Optional;
import java.util.Set;

import com.flooringmastery.dao.exceptions.FlooringMasteryFailedLoadException;
import com.flooringmastery.model.FlooringMasteryProduct;


public interface FlooringMasteryProductDao {
    
    //Loads product data
    public void loadDataFromExternals() throws FlooringMasteryFailedLoadException;
    
    //Return set of all products
    public Set<FlooringMasteryProduct> productsSet();
    
    //Check if product with type exists
    public boolean hasProductWithType(String type);
    
    //Gets product with type
    public Optional<FlooringMasteryProduct> getProductByType(String type);
}
