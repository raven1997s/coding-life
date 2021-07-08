package com.raven.common.utils;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author yiliang
 * @Date 2020/8/23
 */
public class XmlUtils {

    private XmlUtils() {

    }

    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();
        SAXReader reader = new SAXReader();
        try {
            InputStream in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            root.elements().stream().forEach(element -> {
                Element e = (Element) element;
                map.put(e.getName(), e.getText());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String objectToXml(Object message, String rootElement) {
        XStream xStream = new XStream();
        if (StringUtils.isNoneBlank(rootElement)) {
            //替换根元素
            xStream.alias(rootElement, message.getClass());
        }
        return xStream.toXML(message);
    }
}
