package com.jdub1;

import com.jdub1.db.EntityDao;
import com.jdub1.db.HibernateUtil;
import com.jdub1.model.Order;
import com.jdub1.service.OrderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isworking = true;
        OrderService orderService = new OrderService();

        do {
            String komenda = scanner.nextLine();
            if (komenda.equalsIgnoreCase("dodaj")) {

                Order order = new Order();
                System.out.println("Podaj co zamawiasz: ");
                String tresc = scanner.nextLine();
                order.setDescription(tresc);

                System.out.println("Podaj ilosc osob: ");
                int ilosc = Integer.parseInt(scanner.nextLine());
                order.setPeopleCount(ilosc);

                System.out.println("Podaj numer stolika: ");
                int nrStolika = Integer.parseInt(scanner.nextLine());
                order.setTableNumber(nrStolika);

                System.out.println("Do zaplaty: ");
                double doZaplaty = Double.parseDouble(scanner.nextLine());
                order.setToPay(doZaplaty);
                orderService.add(order);
            } else if (komenda.equalsIgnoreCase("listuj")) {

                orderService.findAll().forEach(System.out::println);
            } else if (komenda.equalsIgnoreCase("dostarczono")) {

                System.out.println("Podaj numer zamowienia: ");
                Long id = Long.parseLong(scanner.nextLine());

                orderService.delivered(id);
            } else if (komenda.equalsIgnoreCase("zaplacono")){
                System.out.println("Podaj numer zamowienia: ");
                Long id = Long.parseLong(scanner.nextLine());

                System.out.println("Podaj Kwote: ");
                double kwota = Double.parseDouble(scanner.nextLine());

                orderService.paid(id, kwota);

            } else if (komenda.equalsIgnoreCase("quit")) {
                isworking = false;
            }

        } while (isworking);

    }
}
