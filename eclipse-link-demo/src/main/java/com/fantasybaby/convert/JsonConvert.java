package com.fantasybaby.convert;

import com.fantasybaby.context.ThreadLocalContext;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.oxm.json.JsonStructureSource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.*;

/**
 * @author reid.liu
 * @date 2018-09-19 19:12
 */
public class JsonConvert<T> implements IConvert<T>{
    public static String bindingFile = "bindlins.xml";

    @Override
    public List<T> convert(String param,Class cls) {
        try{
            Map<String, Object> properties = new HashMap(2);
            properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, ThreadLocalContext.get());

            JAXBContext jc = JAXBContextFactory
                    .createContext(new Class[] { cls }, properties);
            Unmarshaller un = jc.createUnmarshaller();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(param);
            Iterator<JsonNode> iterator = actualObj.iterator();
            Map<String,JsonNode> map = new HashMap();
            List<JsonNode> js = new ArrayList<>();
            while (iterator.hasNext()){
                JsonNode next = iterator.next();
                JsonNode key1 = next.get("first-name");
                JsonNode key2 = next.get("last-name");
                String s1 = key1.textValue();
                String s2 = key2.textValue();
                JsonNode jsonNode = map.get(s1 + s2);
                if(jsonNode == null){
//                    jsonNode.
                }
            }
            StringReader reader = new StringReader(param);
            // Parse the JSON
            JsonReader jsonReader = Json.createReader(reader);
            // Unmarshal Root Level JsonArray
            JsonArray customersArray = jsonReader.readArray();
            JsonStructureSource arraySource = new JsonStructureSource(customersArray);
//            StreamSource source = new StreamSource(reader);
            JAXBElement unmarshal = un.unmarshal(arraySource, cls);
            return (List<T>) unmarshal.getValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public T convertArray(String param,Class cls) {
        try{
            Map<String, Object> properties = new HashMap(2);
            properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindingFile);

            JAXBContext jc = JAXBContextFactory
                    .createContext(new Class[] { cls }, properties);
            Unmarshaller un = jc.createUnmarshaller();
            StringReader reader = new StringReader(param);
            StreamSource source = new StreamSource(reader);
            JAXBElement<T> elem = (JAXBElement<T>) un.unmarshal(source,cls);
            return  elem.getValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deConvert(T t) {
        return null;
    }
}
