package bk.tailfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import bk.tailfile.feature.*;
import bk.tailfile.parser.*;

@Service
public class FileContentRecordingService {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	public List<ParserObject> stack = new ArrayList<ParserObject>();

	public void sendLinesToTopic(String line) {
		ParserObject p = ParseLog.parser(line);
		Feature.setCollapsible(p);
		/*if(Feature.isFilter(p)){*/
		stack.add(p);
		if(stack.size() == 100){
			this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", stack);
			stack.clear();
		}
			
		/*}*/	
		/*}*/
	}
}
