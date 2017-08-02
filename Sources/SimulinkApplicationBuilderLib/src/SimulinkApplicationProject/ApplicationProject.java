/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulinkApplicationProject;

import SimulinkApplicationProject.ApplicationData.ApplicationParametersCollection;
import SimulinkApplicationProject.ApplicationData.ApplicationSignalsCollection;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author braul
 */
public class ApplicationProject
{
    private final static String PROJECT_ROOT_NODE = "SimulinkApplicationProject";
    
    private String Name;
    
    private List<ApplicationTask> Tasks;
    
    private ApplicationSignalsCollection ApplicationSignals;
    
    private ApplicationParametersCollection ApplicationParameters;
    
    public ApplicationProject()
    {
        Name = "";
        Tasks = new ArrayList<>();
        ApplicationSignals = new ApplicationSignalsCollection();
        ApplicationParameters = new ApplicationParametersCollection();
        
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public List<ApplicationTask> getTasks()
    {
        return Tasks;
    }

    public ApplicationSignalsCollection getApplicationSignals()
    {
        return ApplicationSignals;
    }

    public ApplicationParametersCollection getApplicationParameters()
    {
        return ApplicationParameters;
    }
    
    public void writeProjectXml(String FilePath)
    {
        Element nField;
        Element nCollection;
        Element nItem;
        
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try
        {
            //Init the XML document
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.newDocument();
            final Element rootElement = document.createElement(PROJECT_ROOT_NODE);
            document.appendChild(rootElement);
            
            writeObjectProperties(this, document, rootElement);
            
            //Outputs the XML document into a file
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File(FilePath));
	    //final StreamResult result = new StreamResult(System.out);
			
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
	    transformer.transform(source, sortie);
            
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
       
    }
    
    public void readProjectXml(String FilePath)
    {
        final File fXmlFile = new File(FilePath);
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try
        {
             final DocumentBuilder builder = factory.newDocumentBuilder();
             final Document document = builder.parse(fXmlFile);
             document.getDocumentElement().normalize();
             
             Node nProject=document.getDocumentElement();
             
             if(nProject.getNodeName().equals(PROJECT_ROOT_NODE))
             {
                 readObjectProperties(this, nProject);
             }
             else
             {
                 
             }
             
        }
        catch(IOException | ParserConfigurationException | SAXException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void writeObjectProperties(Object Obj, Document oXDoc, Element nParentElement)
    {
        Element nField;
        Object FieldValue;
        
        Class<?> ObjClass = Obj.getClass();
        
        while (!(ObjClass == Object.class))
        {
            Field[] ObjFields = ObjClass.getDeclaredFields();
        
            for (Field oField : ObjFields)
            {
                if(!(Modifier.isStatic(oField.getModifiers()) && Modifier.isFinal(oField.getModifiers()))) //Constant fields not saved
                {
                    try
                    {
                        oField.setAccessible(true);
                        FieldValue=oField.get(Obj);

                        nField = oXDoc.createElement(oField.getName());

                        if (oField.getType().isPrimitive())
                        {
                            nField.setTextContent(String.valueOf(FieldValue));
                        } 
                        else if (oField.getType() == String.class)
                        {
                            nField.setTextContent(String.valueOf(FieldValue));
                        } 
                        else if (oField.getType() == List.class || oField.getType().getSuperclass() == ArrayList.class)
                        {
                            List<Object> oCollection = (List<Object>)FieldValue;

                            for(Object oItem:oCollection)
                            {
                                Element nItem=oXDoc.createElement(oItem.getClass().getName());
                                nField.appendChild(nItem);

                                writeObjectProperties(oItem, oXDoc, nItem);
                            }
                        }
                        else if (oField.getType().isEnum())
                        {
                            nField.setTextContent(String.valueOf(FieldValue));
                        }
                        else
                        {
                            Element nObject=oXDoc.createElement(oField.getType().getName());
                            nField.appendChild(nObject);

                            writeObjectProperties(FieldValue, oXDoc, nObject);
                        }

                        nParentElement.appendChild(nField);

                    } 
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }
            
            ObjClass=ObjClass.getSuperclass();
        }
    }
    
    private void readObjectProperties(Object Obj, Node nObjProperties)
    {
        Class<?> ObjClass = Obj.getClass();
        NodeList ChildNodes = nObjProperties.getChildNodes();
        
        
        for(int i=0; i<ChildNodes.getLength(); i++)
        {
            Node nChild = ChildNodes.item(i);
            
            if (nChild.getNodeType()==Node.ELEMENT_NODE)
            {
                try
                {
                    Field oField = ObjClass.getDeclaredField(nChild.getNodeName());
                    setFieldValue(oField, Obj, nChild);         
                } 
                catch (NoSuchFieldException e)
                {
                    //Field not found in the current class, look in base class
                    Field oField = null;
                    Class<?> oBaseClass = ObjClass.getSuperclass();
                    
                    while ((oField==null) && (oBaseClass != Object.class))
                    {
                        try
                        {
                            oField=oBaseClass.getDeclaredField(nChild.getNodeName());
                            
                        } 
                        catch (NoSuchFieldException | SecurityException e1)
                        {
                            oField=null;
                            oBaseClass = oBaseClass.getSuperclass();
                        }
                    }
                    
                    if(!(oField==null))
                    {
                        try
                        {
                            Object oCastedObj = Class.forName(oBaseClass.getName()).cast(Obj);
                            setFieldValue(oField, oCastedObj, nChild);
                            
                        } 
                        catch (ClassNotFoundException e2)
                        {
                        }
                    }
                }
            }
        }
    }
    
    private void setFieldValue(Field oField, Object Obj, Node nFieldValue)
    {
        oField.setAccessible(true);
                    
        if(oField.getType().isPrimitive())
        {
            try
            {
                if(oField.getType()==boolean.class)
                {
                    boolean boolVal = Boolean.parseBoolean(nFieldValue.getTextContent());
                    oField.setBoolean(Obj, boolVal);
                }
                else if(oField.getType()==byte.class)
                {
                    byte byteVal=Byte.parseByte(nFieldValue.getTextContent());
                    oField.setByte(Obj, byteVal);
                }
                else if(oField.getType()==short.class)
                {
                    short shortVal=Short.parseShort(nFieldValue.getTextContent());
                    oField.setShort(Obj, shortVal);
                }
                else if(oField.getType()==int.class)
                {
                    int intVal = Integer.parseInt(nFieldValue.getTextContent());
                    oField.setInt(Obj, intVal);
                }
                else if(oField.getType()==long.class)
                {
                    long longVal = Long.parseLong(nFieldValue.getTextContent());
                    oField.setLong(Obj, longVal);
                }
                else if(oField.getType()==float.class)
                {
                    float floatVal = Float.parseFloat(nFieldValue.getTextContent());
                    oField.setFloat(Obj, floatVal);
                }
                else if(oField.getType()==double.class)
                {
                    double doubleVal=Double.parseDouble(nFieldValue.getTextContent());
                    oField.setDouble(Obj, doubleVal);
                }
                else
                {
                    //Throw unsupported type exception (char & void)
                }
            } 
            catch (IllegalAccessException | IllegalArgumentException | DOMException e)
            {
            }
        }
        else if (oField.getType()==String.class)
        {
            try
            {
                oField.set(Obj, nFieldValue.getTextContent());
            } 
            catch (IllegalAccessException | IllegalArgumentException | DOMException e)
            {
            }
        }
        else if(oField.getType()==List.class || oField.getType().getSuperclass() == ArrayList.class)
        {
            try
            {
                List<Object> oCollection = (List < Object >)oField.get(Obj);
                NodeList nItemsCollection = nFieldValue.getChildNodes();

                for(int j=0; j<nItemsCollection.getLength(); j++)
                {
                    Node nItem=nItemsCollection.item(j);

                    if(nItem.getNodeType()==Node.ELEMENT_NODE)
                    {
                        try
                        {
                            Object oItem = null;
                            Class<?> oItemClass = Class.forName(nItem.getNodeName());
                            
                            //Cannot use Class.newInstance() on primitive types (such as long) since primitive types do not have constructor without calling argument
                            try
                            {
                                Field oTypeField = oItemClass.getField("TYPE");
                                
                                oItem = oItemClass.getConstructor(String.class).newInstance("0");
                            } 
                            catch (NoSuchFieldException e)
                            {
                                oItem = oItemClass.newInstance();
                            }
                            catch (SecurityException e)
                            {
                            }
                            
                            readObjectProperties(oItem, nItem);
                            oCollection.add(oItem);
                        } 
                        catch (Exception e)
                        {
                        }
                    }
                }
            }
            catch (Exception e)
            {
            }
        }
        else if(oField.getType().isEnum())
        {
            try
            {
                Object enumVal = (Object)Enum.valueOf((Class<Enum>)oField.getType(), nFieldValue.getTextContent());
                oField.set(Obj, enumVal);
            } 
            catch (IllegalAccessException | IllegalArgumentException | DOMException e)
            {
            }
        }
        else //Field is an instance of a specific class
        {
            for(int j=0; j<nFieldValue.getChildNodes().getLength(); j++)
            {
                Node nObject = nFieldValue.getChildNodes().item(j);

                if(nObject.getNodeType()==Node.ELEMENT_NODE)
                {
                    try
                    {
                        Object objVal = oField.get(Obj);
                        readObjectProperties(objVal, nObject);
                        break;
                    }
                    catch (IllegalAccessException | IllegalArgumentException e)
                    {

                    }
                }
            }
        }
    }
}
