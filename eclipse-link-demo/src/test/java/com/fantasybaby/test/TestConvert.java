package com.fantasybaby.test;

import com.fantasybaby.convert.IConvert;
import com.fantasybaby.convert.JsonConvert;
import com.fantasybaby.convert.XMLConvert;
import com.fantasybaby.domain.Address;
import com.fantasybaby.domain.Customer;
import com.fantasybaby.domain.PhoneNumber;
import com.fantasybaby.file.FileLoader;
import org.junit.Test;

import java.io.File;

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
        String path = new FileLoader().getPath("json.txt");
        IConvert convert = new JsonConvert();
        convert.convert(new File(path));
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
