package bk.tailfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.TimerTask;
import java.util.Timer;
import bk.tailfile.feature.*;
import bk.tailfile.parser.*;
import bk.tailfile.service.ConvertAndSendTimerTask;

@Service
public class FileContentRecordingService {
	@Autowired
	public SimpMessagingTemplate simpMessagingTemplate;
	public List<ParserObject> stack = new ArrayList<ParserObject>();
	public Timer timer = new Timer();
	public Feature feature = new Feature();

	public void sendLinesToTopic(String line) {
		ParserObject p = ParseLog.parser(line);
		if(feature.isFilter(p)){
			stack.add(p);
			if(stack.size() == 300){
				this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", stack);
				stack.clear();
			}
			this.timer.cancel();
			this.timer = new Timer();
			this.timer.schedule(
				new ConvertAndSendTimerTask(this),
				1000L
			);
		}
	}
}
