package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.flooringmastery.dao.exceptions.FailedLoadException;

public class TaxDaoFileImpl implements TaxDao {
    private final String SRC_FILE;
    private final Map<String, BigDecimal> TAX_MAP;
    
    public TaxDaoFileImpl() {
        this("Data/Taxes.txt");
    }
    
    public TaxDaoFileImpl(String SRC_FILE) {
        this.SRC_FILE = SRC_FILE;
        this.TAX_MAP = new HashMap<>();
    }
    
    @Override
    public void loadDataFromExternals() throws FailedLoadException {
        Scanner reader;
        try {
            reader = new Scanner(new BufferedReader(new FileReader(SRC_FILE)));
        } catch (FileNotFoundException ex) {
            throw new FailedLoadException(
                "Unable to load tax information",
                ex
            );
        }
        
        // Ignore file header
        reader.nextLine();
        while (reader.hasNextLine()) {
            String[] tokens = reader.nextLine().split(",");
            String state = tokens[0];
            BigDecimal rate = new BigDecimal(tokens[2]);
            
            TAX_MAP.put(state, rate);
        }
        
        reader.close();
    }

    @Override
    public boolean hasInfoForStateAbbr(String state) {
        return percentTaxRateForStateAbbr(state).isPresent();
    }

    @Override
    public Optional<BigDecimal> percentTaxRateForStateAbbr(String state) {
        BigDecimal rate = TAX_MAP.get(state);
        if (rate == null) {
            return Optional.empty();
        }
        return Optional.of(rate);
    }

}
