package prettyLog;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	@MessageMapping ("/get")
	@SendTo("/receive/log")
	public LogMessage logMessage () throws Exception {
		return new LogMessage("Hello!");
	}
}