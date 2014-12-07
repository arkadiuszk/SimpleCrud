package config;

import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Strings;

import eu.kiszczak.crud.manager.ManagerDB;

public class Configuration {
	
	public ManagerDB createConnection(){
		ManagerDB managerDB = null;
		Properties pro = new Properties();
		
		try {
			pro.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			String host = pro.getProperty("host","localhost");
			String port = pro.getProperty("port", "27017");
			String dbName = pro.getProperty("dbName");
			String collectionName = pro.getProperty("collectionName");
			
			if(Strings.isNullOrEmpty(dbName) ||
					Strings.isNullOrEmpty(collectionName)){
				throw new RuntimeException("Some settings are missing");
			}
			
			
			System.out.println("Create connection with parameters "
					+ "host :"+ host+
					", port :"+ port+
					", dbName :"+ dbName+
					", collectionName :"+collectionName);
			managerDB = new ManagerDB(host, Integer.valueOf(port), dbName, collectionName);	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return managerDB;
	}
}
