package bk.tailfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import bk.tailfile.feature.*;
import bk.tailfile.parser.*;

@Service
public class FileContentRecordingService {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void sendLinesToTopic(String line) {
		ParserObject p = ParseLog.parser(line);
		Feature.setCollapsible(p);
		/*if(Feature.isFilter(p)){*/
			this.simpMessagingTemplate.convertAndSend("/topic/tailfiles", p);
		/*}*/
	}
}
