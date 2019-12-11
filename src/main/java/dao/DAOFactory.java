package dao;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class DAOFactory {
    public static Object getDAO(String id){
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(DAOFactory.class.getClassLoader().getResourceAsStream("applicationContext.xml"));
            Element element = (Element) document.selectSingleNode("//bean[@id='"+id+"']");
            Class clazz = Class.forName(element.attributeValue("class"));
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}