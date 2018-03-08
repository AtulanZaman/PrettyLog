package bk.tailfile.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TailController {
	public Properties properties = new Properties();

	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String test(@RequestBody Properties properties){
		this.properties = properties;
		System.out.println(this.properties);
		return "Success!";
	}
}
