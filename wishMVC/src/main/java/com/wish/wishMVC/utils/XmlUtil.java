package com.wish.wishMVC.utils;

import com.thoughtworks.xstream.XStream;

public class XmlUtil {
		public static String toXml(Object obj){  
            XStream xstream=new XStream(); 
            return xstream.toXML(obj);
	}
}
