package bk.tailfile.web;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.integration.file.tail.FileTailingMessageProducerSupport;
import bk.tailfile.web.ApplicationContextProvider;

@RestController
public class TailController {
	public Properties properties = new Properties();

	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String test(@RequestBody Properties properties){
		this.properties = properties;
		setTailFileSource(this.properties.getFilename());
		System.out.println(this.properties);
		return "Success!";
	}

	public void setTailFileSource(String filename){
		FileTailingMessageProducerSupport fileInboundChannelAdapter = ApplicationContextProvider.getApplicationContext().getBean("fileInboundChannelAdapter",  FileTailingMessageProducerSupport.class);
		fileInboundChannelAdapter.stop();
		fileInboundChannelAdapter.setFile(new File(filename));
		fileInboundChannelAdapter.start();
	}
}
