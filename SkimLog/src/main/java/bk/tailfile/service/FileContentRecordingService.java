package bk.tailfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.TimerTask;
import bk.tailfile.feature.*;
import bk.tailfile.parser.*;
import bk.tailfile.service.ConvertAndSendTimerTask;

@Service
public class FileContentRecordingService {
	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;
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
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(
			new ConvertAndSendTimerTask(this),
			1000L,
			1000L,
			TimeUnit.MILLISECONDS
		);
	}
}
