package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@SpringBootApplication
public class App
{



    //public static int myInt = 6;
    //public static String myString2 = "Alez";
    //public static JFormattedTextField numPeriodsField = new JFormattedTextField();
    //public static JFormattedTextField textNameField = new JFormattedTextField();
    //public  static JTextArea editTextArea2 = new JTextArea(myString2);
    public final static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");;
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
        //final ApplicationContext context =
                //new ClassPathXmlApplicationContext("Spring-Module.xml");
         //int myInt=6;
        //final String myString2="Alek";
        //JFormattedTextField numPeriodsField = new JFormattedTextField();
        //numPeriodsField.setValue(new Integer(myInt));
        //numPeriodsField.setColumns(10);
        //numPeriodsField.addPropertyChangeListener("value", new FormattedChangeListener());
            //JTextArea editTextArea = new JTextArea(myInt);

        //textNameField.setValue(new String(myString2));
        //textNameField.setColumns(10);
        //textNameField.addPropertyChangeListener("value", new FormattedChangeListener());


        //editTextArea2.addPropertyChangeListener("value", new FormattedChangeListener());
        //JButton testButton = new JButton("crap");
        //testButton.addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
                //System.out.println("Click detected by Anon Class");
                //CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
                //System.out.println("myInt: "+myInt+" myString2: "+myString2);
                //Customer customer = new Customer(myInt, myString2,23);
                //customerDAO.insert(customer);

            //}
        //});

        //JFrame frame = new JFrame("Listener Test");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(testButton, BorderLayout.WEST);
        //frame.add(numPeriodsField, BorderLayout.EAST);
        //frame.add(textNameField, BorderLayout.SOUTH);
        //frame.pack();
        //frame.setVisible(true);

        //CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        //Customer customer = new Customer(6, "alek",23);
        //customerDAO.insert(customer);

        //Customer customer1 = customerDAO.findByCustomerId(1);
        //System.out.println(customer1);

    }

    //@Override
    //public void propertyChange(PropertyChangeEvent e) {
    //    Object source = e.getSource();
    //    if (source == numPeriodsField) {
    //        myInt = ((Number)numPeriodsField.getValue()).intValue();
    //    }
    //    //re-compute payment and update field...
    //}

    public static void  postToDB(String name, int id, int age){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        System.out.println("incid: "+id+" incstring: "+name);
        Customer customer = new Customer(id, name, age);
        customerDAO.insert(customer);

    }

    public static Customer getFromDB(int id){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findByCustomerId(id);
    }

    public static java.util.List<Customer> getAllCustomers(){
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        return customerDAO.findAllCustomers();
    }
}

