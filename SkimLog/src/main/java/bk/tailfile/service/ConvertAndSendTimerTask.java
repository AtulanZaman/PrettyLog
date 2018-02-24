package bk.tailfile.service;
import bk.tailfile.service.*;
import java.util.TimerTask;

public class ConvertAndSendTimerTask extends TimerTask{

FileContentRecordingService service;

	public ConvertAndSendTimerTask(FileContentRecordingService service){
		this.service = service;
	}

	@Override
	public void run(){
		if(this.service.stack.size() > 0){
			this.service.simpMessagingTemplate.convertAndSend("/topic/tailfiles", this.service.stack);
			this.service.stack.clear();
		}
	}
}