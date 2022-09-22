package com.flooringmastery.dao;

import java.util.Optional;
import java.util.Set;

import com.flooringmastery.dao.exceptions.FailedLoadException;
import com.flooringmastery.model.Product;


public interface ProductDao {
    
    //Loads product data
    public void loadDataFromExternals() throws FailedLoadException;
    
    //Return set of all products
    public Set<Product> productsSet();
    
    //Check if product with type exists
    public boolean hasProductWithType(String type);
    
    //Gets product with type
    public Optional<Product> getProductByType(String type);
}
