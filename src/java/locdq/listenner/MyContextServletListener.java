/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.listenner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author test
 */
@WebListener("application context listener")
public class MyContextServletListener implements ServletContextListener {

    private final String PATH="/WEB-INF/properties.properties";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {//log4j thực hiện tại đây
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext context = sce.getServletContext();
        String path = context.getRealPath("/");
        Map<String, String> propertymap = readfile(path+PATH);
        context.setAttribute("PROPERTY_MAP", propertymap);
        //log4j
        String log4jConfigFile=context.getInitParameter("log4j-config-location");
        String fullPath=context.getRealPath("/")+File.separator+log4jConfigFile;
        System.out.println(context.getRealPath("/"));
        // sử dụng link động
        System.setProperty("PATH",context.getRealPath("/"));
        PropertyConfigurator.configure(fullPath);
    }

    private Map<String, String> readfile(String path) {
        Map<String, String> propertymap = new HashMap<>();
        FileReader reader = null;
        try {
            reader=new FileReader(path);
            Properties p = new Properties();
            p.load(reader);
            Enumeration keys=p.propertyNames();
            while (keys.hasMoreElements()){
                String key=(String)keys.nextElement();
                propertymap.put(key, p.getProperty(key));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return propertymap;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

}
