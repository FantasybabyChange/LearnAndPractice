package com.fantasybaby.test;

import com.fantasybaby.context.ThreadLocalContext;
import com.fantasybaby.convert.IConvert;
import com.fantasybaby.convert.JsonConvert;
import com.fantasybaby.convert.XMLConvert;
import com.fantasybaby.domain.Address;
import com.fantasybaby.domain.Customer;
import com.fantasybaby.domain.PhoneNumber;
import com.fantasybaby.file.FileLoader;
import org.junit.Test;

import java.util.List;

/**
 * @author reid.liu
 * @date 2018-09-19 19:18
 */
public class TestConvert {
    @Test
    public void loadFile(){
        String s = new FileLoader().readFileToString("json.txt");
        System.out.println(s);
    }
    @Test
    public void convertToObject(){
        ThreadLocalContext.set("bindlins.xml");
        String s = new FileLoader().readFileToString("json.txt");
        IConvert<Customer> convert = new JsonConvert();
        List<Customer> customer = convert.convert(s, Customer.class);
        for (Customer customer1 : customer) {
            System.out.println(customer1);
        }
    }
    @Test
    public void convertToList(){
        try {
            ThreadLocalContext.set("bindlins1.xml");
            String s = new FileLoader().readFileToString("json1.txt");
            IConvert<Customer> convert = new JsonConvert();
            List<Customer> customer = convert.convert(s, Customer.class);
            for (Customer customer1 : customer) {
                System.out.println(customer1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void convertToXmlStr(){
        Customer customer = new Customer();
        customer.setFirstName("Jane Doe");
        Address address = new Address();
        address.setStreet("123 Any Street");
        customer.setAddress(address);

        PhoneNumber workPhoneNumber = new PhoneNumber();
        workPhoneNumber.setType("work");
        workPhoneNumber.setNumber("613-555-1111");
        customer.getPhoneNumbers().add(workPhoneNumber);

        PhoneNumber cellPhoneNumber = new PhoneNumber();
        cellPhoneNumber.setType("cell");
        cellPhoneNumber.setNumber("613-555-2222");
        customer.getPhoneNumbers().add(cellPhoneNumber);

        // Step 2 - Convert the Domain Model to XML
        IConvert<Customer> convert = new XMLConvert();
        String s = convert.deConvert(customer);
        System.out.println(s);
    }
}
