package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import com.flooringmastery.dao.exceptions.FailedLoadException;
import com.flooringmastery.model.Product;

public class ProductDaoFileImpl implements ProductDao {
    private final String SRC_FILE;
    private final Map<String, Product> PRODUCTS_MAP;
    
    public ProductDaoFileImpl() {
        this("Data/Products.txt");
    }
    
    public ProductDaoFileImpl(String SRC_FILE) {
        this.SRC_FILE = SRC_FILE;
        PRODUCTS_MAP = new HashMap<>();
    }
    
    @Override
    public void loadDataFromExternals() throws FailedLoadException {
        Scanner reader;
        try {
            reader = new Scanner(new BufferedReader(new FileReader(SRC_FILE)));
        } catch (FileNotFoundException ex) {
            throw new FailedLoadException(
                "Unable to load product information"
            );
        }
        reader.nextLine();
        
        while(reader.hasNextLine()) {
            String[] tokens = reader.nextLine().split(",");
            String type = tokens[0];
            BigDecimal costPerSqFt = new BigDecimal(tokens[1]);
            BigDecimal laborCostPerSqFt = new BigDecimal(tokens[2]);
            
            PRODUCTS_MAP.put(
                type,
                new Product(type, costPerSqFt, laborCostPerSqFt)
            );
        }
        
        reader.close();
    }

    @Override
    public Set<Product> productsSet() {
        return Set.copyOf(PRODUCTS_MAP.values());
    }

    @Override
    public boolean hasProductWithType(String type) {
        return PRODUCTS_MAP.containsKey(type);
    }

    @Override
    public Optional<Product> getProductByType(String type) {
        Product product = PRODUCTS_MAP.get(type);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }
}
