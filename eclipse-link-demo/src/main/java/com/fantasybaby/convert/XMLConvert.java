package com.fantasybaby.convert;

import com.fantasybaby.domain.Address;
import com.fantasybaby.domain.Customer;
import com.fantasybaby.domain.PhoneNumber;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.StringWriter;

/**
 * @author reid.liu
 * @date 2018-09-20 10:56
 */
public class XMLConvert<T> implements IConvert<T>{
    @Override
    public void convert(File param) {
    }

    @Override
    public String deConvert(T t) {
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

//            JAXBElement<T> jaxbElement = new JAXBElement(new QName(null, t.getClass().getSimpleName()), t.getClass(), t);
            StringWriter writer = new StringWriter();
            marshaller.marshal(t, writer);
            return writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
