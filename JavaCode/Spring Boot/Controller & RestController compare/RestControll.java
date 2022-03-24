package idv.dennis.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.dennis.service.TestService;

/** @see @RestController = @ResponseBody ＋ @Controller, this annotation will return a JSON data*/
@RestController
public class RestControll
{
	/** @see Auto import and initial class "TestService" than give it a name which is "service" */
	@Autowired
	private TestService service;
	
	
	/**
	 * @see	definition this method is Request method is GET ( method = RequestMethod.GET ).
	 * @see	In here I set the "server.context.path = /" and port is 88 in application.properties, that means my
	 * home page is http://localhost:88
	 * @see	value means http://127.0.0.1:88/helloRest will go into this method.
	 * @see	You will see the helloController method has a String parameter calls name, means on this url http://127.0.0.1:88/hello?name=123
	 * 123 will be catch as parm's name
	 * @param name
	 * @return a JSON data
	 * @testLink	http://localhost:88/hello?name=123
	 */
	@GetMapping ( "/helloRest" )
	public String hello( String name )
	{
		return service.getHelloMessage( name );
	}
}
