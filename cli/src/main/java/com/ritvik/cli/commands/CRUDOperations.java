package com.ritvik.cli.commands;

import com.ritvik.cli.services.OrderService;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.component.StringInput;
import org.springframework.shell.standard.*;

import java.util.logging.Logger;

@ShellComponent
@ShellCommandGroup(value = "CRUD Commands")
public class CRUDOperations extends  AbstractShellComponent{
        Logger log = Logger.getLogger(CRUDOperations.class.getName());
        private boolean signedIn;

        @Autowired
        @Lazy
        private LineReader lineReader;

        @Autowired
        private OrderService service;

        @ShellMethod(value = "add order to database", key = "add")
        @ShellMethodAvailability("signInCheck")
        public void addOrder()
        {
                String orderElement = this.lineReader.readLine("What you want to buy: ");
                Integer price = Integer.valueOf(this.lineReader.readLine("What is it's price: "));
                String buyer = this.lineReader.readLine("What is your name: ");
                System.out.println(service.insertOrder(buyer,orderElement,price));;
        }
        @ShellMethod(value = "delete order to database", key = "delete")
        @ShellMethodAvailability("signInCheck")
        public void deleteOrder()
        {
                String id = this.lineReader.readLine("What is your order (ID): ");
                System.out.println(service.deleteOrderById(id));
        }
        @ShellMethod(value = "update order to database", key = "update")
        @ShellMethodAvailability("signInCheck")
        public void updateOrder()
        {
                String id = this.lineReader.readLine("What you want to update (ID): ");
                String orderElement = this.lineReader.readLine("What you want to buy: ");
                Integer price = Integer.valueOf(this.lineReader.readLine("What is it's price: "));
                String buyer = this.lineReader.readLine("What is your name: ");
                System.out.println(service.updateOrderById(id,buyer,orderElement,price));
        }
        @ShellMethod(value = "read an order from database", key = "read")
        @ShellMethodAvailability("signInCheck")
        public void readOrder()
        {
                String id = this.lineReader.readLine("What is your order (ID): ");
                System.out.println(service.findOrderById(id));
        }

        @ShellMethod(value = "read all order from database", key = "all")
        @ShellMethodAvailability("signInCheck")
        public void allOrders()
        {
                System.out.println(service.getAllOrders());
        }
        @ShellMethod(value = "sign in to the database", key = "login")
        public void signIn()
        {
                this.signedIn = true;
                log.info("Signed In!");
        }
        public Availability signInCheck()
        {
                return signedIn ?  Availability.available() : Availability.unavailable("Must be signed in first");
        }
}
