package com.jdub1;

import com.jdub1.db.EntityDao;
import com.jdub1.db.HibernateUtil;
import com.jdub1.model.Order;
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
        EntityDao dao = new EntityDao();

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
                dao.saveOrUpdate(order);
            } else if (komenda.equalsIgnoreCase("listuj")) {

                dao.list(Order.class).forEach(System.out::println);
            } else if (komenda.equalsIgnoreCase("dostarczono")) {

                System.out.println("Podaj numer zamowienia: ");
                Long id = Long.parseLong(scanner.nextLine());

                Order delivered = dao.getById(Order.class, id);
                delivered.setTimeDelivered(LocalDateTime.now());
                dao.saveOrUpdate(delivered);
            } else if (komenda.equalsIgnoreCase("zaplacono")){
                System.out.println("Podaj numer zamowienia: ");
                Long id = Long.parseLong(scanner.nextLine());

                Order zaplacono = dao.getById(Order.class, id);

                System.out.println("Podaj Kwote: ");
                double kwota = Double.parseDouble(scanner.nextLine());

                if (dao.getById(Order.class, id).getToPay() == kwota) {

                    zaplacono.setPaid(kwota);
                    dao.saveOrUpdate(zaplacono);
                } else {
                    System.out.println("Za mala kwota !!!");
                }

            } else if (komenda.equalsIgnoreCase("quit")) {
                isworking = false;
            }

        } while (isworking);

    }
}
