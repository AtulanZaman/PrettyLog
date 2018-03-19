package bk.tailfile.web;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;
import bk.tailfile.web.ApplicationContextProvider;

@RestController
public class TailController {
	public static Properties properties = new Properties();

	@RequestMapping(value="/submit", method = RequestMethod.POST)
	public String submit(@RequestBody Properties properties){
		this.properties = properties;
		setTailFileSource(this.properties.getFilename());
		return "Settings updated";
	}

	public void setTailFileSource(String filename){
		ApacheCommonsFileTailingMessageProducer fileInboundChannelAdapter = ApplicationContextProvider.getApplicationContext().getBean("fileInboundChannelAdapter",  ApacheCommonsFileTailingMessageProducer.class);
		fileInboundChannelAdapter.stop();
		fileInboundChannelAdapter.setFile(new File(filename));
		fileInboundChannelAdapter.setEnd(true);
		fileInboundChannelAdapter.start();
	}
}
