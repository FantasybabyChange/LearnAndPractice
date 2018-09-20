package com.fantasybaby.convert;

import com.fantasybaby.domain.Customer;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author reid.liu
 * @date 2018-09-19 19:12
 */
public class JsonConvert implements IConvert{
    public static String bindingFile = "bindlins.xml";
    @Override
    public void convert(File param) {
        try{
            Map<String, Object> properties = new HashMap<String, Object>(3);
            properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindingFile);
            properties.put("eclipselink.media-type", "application/json");
            properties.put("eclipselink.json.include-root", false);
            JAXBContext jc = JAXBContext.newInstance("com.fantasybaby", Customer.class.getClassLoader() , properties);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource json = new StreamSource(param);
            Customer customer = unmarshaller.unmarshal(json, Customer.class).getValue();

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(customer, System.out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String deConvert(Object o) {
        return null;
    }
}
