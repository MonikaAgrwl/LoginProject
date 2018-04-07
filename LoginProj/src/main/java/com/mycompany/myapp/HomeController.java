package com.mycompany.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//Code to load properties file
	static HashMap<String,User> clientCredentials = new HashMap<String, User>();
	static HashMap<String,String> adminCredentials = new HashMap<String, String>();
	
	static 
	{
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("userdetails.properties");
		try {
			prop.load(stream);
		} 	catch (IOException e) {
			e.printStackTrace();
		}
		for(String s : prop.getProperty("usernames").split(",")){
			User u = new User(prop.getProperty(s.trim()));
			clientCredentials.put(s.trim(),u );
		}


		for(String s : prop.getProperty("adminuser").split(",")){
			User u = new User(prop.getProperty(s.trim()));
			u.setLoggedIn(false);
			u.setNumberOfLogins(0);
			adminCredentials.put(s.trim(),prop.getProperty(s.trim()) );

		}
		//test:
		/*for(Object s:clientCredentials.keySet()){
			logger.info("Key: "+s+" & value: "+clientCredentials.get(s));
		}

		for(String s:adminCredentials.keySet()){
			logger.info("Key: "+s+" & value: "+adminCredentials.get(s));
		}*/
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	@RequestMapping(value = "/LoginSubmit", method = RequestMethod.POST)
	public ModelAndView login( HttpServletRequest req, @RequestParam(value = "username") String name, @RequestParam(value= "password") String password) 
	{
		ModelAndView model= new ModelAndView("home");
		//Login Authentication logic
		if(clientCredentials.containsKey(name))
		{
			if(clientCredentials.get(name)!=null) {
				if(clientCredentials.get(name).getPassword().equals(password))
				{
					model = new ModelAndView("welcome");
					model.addObject("username", name );
					User u = clientCredentials.get(name);
					u.setLoggedIn(true);
					u.setNumberOfLogins(u.getNumberOfLogins()+1);
				}
			}
		}

		if(adminCredentials.containsKey(name))
		{
			if(adminCredentials.get(name)!=null) {
				if(adminCredentials.get(name).equals(password))
				{
					model = new ModelAndView("userdetails");
					model.addObject("username", name );
					model.addObject("userlist", clientCredentials );

				}
			}
		}

		return model;
	}
	
	

}
