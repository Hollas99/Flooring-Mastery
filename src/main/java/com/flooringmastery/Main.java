package com.flooringmastery;

import com.flooringmastery.controller.FlooringMasteryController;
import com.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl;
import com.flooringmastery.dao.FlooringMasteryProductDaoFileImpl;
import com.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl;
import com.flooringmastery.service.FlooringMasteryService;
import com.flooringmastery.view.FlooringMasteryView;
import com.flooringmastery.view.UserIoConsoleImpl;

public class Main {
    public static void main(String[] args) {
        FlooringMasteryController controller = new FlooringMasteryController(
            new FlooringMasteryView(
                new UserIoConsoleImpl()
            ),
            new FlooringMasteryService(
                new FlooringMasteryTaxDaoFileImpl(),
                new FlooringMasteryProductDaoFileImpl(),
                new FlooringMasteryOrderDaoFileImpl()
            )
        );
        
        controller.run();
    }
}
