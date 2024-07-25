package com.pearson.pix.exception;
import org.apache.xerces.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.PropertyResourceBundle;

/**
 * Insert the type's description here.
 * Creation date: (1/5/2002 12:52:19 PM)
 * @author: Administrator
 */
public class XMLBundle {

    private Document obj_DocumentBundle = null;

    private String sBundleName = "";

    private static String MODULE_ID = "id";

    private static String XML_MAP = "XMLMappings";

    
    /**
     * XMLBundle constructor comment.
     */
    public XMLBundle() {
        super();
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/5/2002 1:09:34 PM)
     * @return whl.polaris.common.XMLBundle
     * @param sPropertyBundle java.lang.String
     * @param sBundleType java.lang.String
     */
    public static XMLBundle getBundle(String sPropertyBundle) throws AppException 
    {
        //	DOMParser obj_DOMParser = new DOMParser();
        //	if(sBundleType != (Any possible type)) throw new MissingResourceException
        XMLBundle objXMLBundle = new XMLBundle();
        try 
        {
            DOMParser obj_DOMParser = new DOMParser();
            
            obj_DOMParser.parse(new InputSource(Loader.load(sPropertyBundle)));
            objXMLBundle.sBundleName = sPropertyBundle;
            objXMLBundle.obj_DocumentBundle = obj_DOMParser.getDocument();
            return objXMLBundle;
        }
            catch (SAXException objSAXException) 
            {
                AppException objAppException = new AppException();
		        objAppException.setSErrorId(Exceptions.SAX_EXCEPTION);
  	 	        objAppException.getErrorDetails(Exceptions.SAX_EXCEPTION);
				objAppException.performErrorAction(objSAXException,"XMLBundle,getBundle");
				throw objAppException;
            }
           
            catch (IOException objIOException)
            {
            	AppException objAppException = new AppException();
		        objAppException.setSErrorId(Exceptions.IO_EXCEPTION);
  	 	        objAppException.getErrorDetails(Exceptions.IO_EXCEPTION);
				objAppException.performErrorAction(objIOException,"XMLBundle,getBundle");
 	   	    	throw objAppException;
            }

       
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/5/2002 1:28:21 PM)
     * @return java.lang.Object
     * @param sKey java.lang.String
     */
    public Properties getObject(String sKey) throws NoSuchElementException {

        // return properties class with the values for the key
        Properties obj_Properties = new Properties();

        // get the properties bundle to find the element to search for
        PropertyResourceBundle obj_XMLProperty =
            (PropertyResourceBundle) PropertyResourceBundle.getBundle(XML_MAP);

        // name of the element that will be searched
        String sCategory = "";

        // flag to identify whether it is having both text and attributes
        boolean bBoth = false;
        String sNodeName = "";

        // get the value of the tag from the properties file
        sCategory = obj_XMLProperty.getString(this.sBundleName);



        // if the value is not in the properties file log the error
        if (sCategory == null || sCategory == "") {
            return null;
        }

        // get the root element of the XML document 
        Element obj_RootElement = obj_DocumentBundle.getDocumentElement();

        //  search for the elements having the search tag name
        NodeList obj_NodeListElements = obj_RootElement.getElementsByTagName(sCategory);

        if (obj_NodeListElements != null) {
            // check each element for the required key
            for (int i = 0; i < obj_NodeListElements.getLength(); i++) {

                // cast the current node to element
                Element obj_TempElement = (Element) obj_NodeListElements.item(i);

                // get the attribute that identifies each tag
                String sModuleName = obj_TempElement.getAttribute(MODULE_ID);

                // check the current tag is the search tag
                if (sModuleName.equals(sKey)) {

                    // get all the nodes in the matched tag
                    NodeList obj_ChildNodeList = obj_TempElement.getChildNodes();

                    // read all the tags and put it into properties
                    for (int j = 0; j < obj_ChildNodeList.getLength(); j++) {

                        // get the current node
                        Node obj_TempNode = obj_ChildNodeList.item(j);

                        if (obj_TempNode != null) {

                            // extract the text value of the node
                            NodeList obj_TextNodeList = obj_TempNode.getChildNodes();
                            if (obj_TextNodeList != null) {
                                for (int n = 0; n < obj_TextNodeList.getLength(); n++) {
                                    if (obj_TextNodeList.item(n).getNodeType() == Node.TEXT_NODE) {
                                        obj_Properties.setProperty(
                                            obj_TempNode.getNodeName(),
                                            obj_TextNodeList.item(n).getNodeValue());
                                        bBoth = true;
                                    }
                                }
                            }

                            // get the attributes of the current node
                            NamedNodeMap obj_AttrNamedNodeMap = obj_TempNode.getAttributes();
                            if (obj_AttrNamedNodeMap != null) {
                                // get all the attributes and put it to the properties
                                // in the format "tagname@attribute" and value of attribute
                                for (int k = 0; k < obj_AttrNamedNodeMap.getLength(); k++) {
                                    Node obj_TempAttrNode = obj_AttrNamedNodeMap.item(k);

                                    // in case of node having both text attributes value
                                    // will be in format "nodename@attributevalue" "textvalue"
                                    if (bBoth == true
                                        && obj_Properties.getProperty(obj_TempNode.getNodeName()) != null) {
                                        obj_Properties.setProperty(
                                            obj_TempNode.getNodeName() + "@" + obj_TempAttrNode.getNodeValue(),
                                            obj_Properties.getProperty(obj_TempNode.getNodeName()));
                                        obj_Properties.remove(obj_TempNode.getNodeName());
                                        bBoth = false;
                                    }
                                    else {
                                        obj_Properties.setProperty(
                                            obj_TempNode.getNodeName() + "@" + obj_TempAttrNode.getNodeName(),
                                            obj_TempAttrNode.getNodeValue());
                                    }
                                }
                            } // end of if loop
                        } // end of if loop
                    }
                    // return the value immediately after the first match
                    return obj_Properties;
                }
            } // end of for loop
        } // end of if loop
        // Exception in case of no match
        throw new NoSuchElementException(sKey);
    }
}
