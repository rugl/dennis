package idv.dennis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** @see	Setting the controller annotation such as struts controller.java 
 * 	@see	Here has something difference with @RestController. @Controller will return a html page*/
@Controller
public class Controll
{
	
	/**
	 * @see definition this method is Request method is GET ( method = RequestMethod.GET ).
	 * @see In here I set the "server.context.path = /" and port is 88 in application.properties, that means
	 *      my home page is http://localhost:88
	 * @see value means http://127.0.0.1:88/helloController will go into this method.
	 * @see On the url http://127.0.0.1:88/helloController?name=123, after ? is parameter. 
	 * You can see we use the @RequestParam value = "name", this will catch the parameter tag name's value.
	 * If you don't have any value or parameter tag (name), will use defaultValues is dennis.
	 * That why we set the required = false.
	 */
	@RequestMapping ( value = "/helloController", method = RequestMethod.GET )
	public String hello( @RequestParam ( value = "name", required = false, defaultValue = "dennis" ) String name,
			Model model )
	{
		model.addAttribute( "name", name );
		return "index";
	}
}
