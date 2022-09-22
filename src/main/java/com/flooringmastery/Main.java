package com.flooringmastery;

import com.flooringmastery.controller.FlooringController;
import com.flooringmastery.dao.OrderDaoFileImpl;
import com.flooringmastery.dao.ProductDaoFileImpl;
import com.flooringmastery.dao.TaxDaoFileImpl;
import com.flooringmastery.service.FlooringService;
import com.flooringmastery.view.View;
import com.flooringmastery.view.UserIOConsoleImpl;

public class Main {
    public static void main(String[] args) {
        FlooringController controller = new FlooringController(
            new View(
                new UserIOConsoleImpl()
            ),
            new FlooringService(
                new TaxDaoFileImpl(),
                new ProductDaoFileImpl(),
                new OrderDaoFileImpl()
            )
        );
        
        controller.run();
    }
}
