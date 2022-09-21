package com.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.Optional;

import com.flooringmastery.dao.exceptions.FlooringMasteryFailedLoadException;

public interface FlooringMasteryTaxDao {
	
	//Loads tax file
    public void loadDataFromExternals() throws FlooringMasteryFailedLoadException;

    //Check for tax information
    public boolean hasInfoForStateAbbr(String state);
    
    //Get percentage tax rate for provided state
    public Optional<BigDecimal> percentTaxRateForStateAbbr(String state);
}
