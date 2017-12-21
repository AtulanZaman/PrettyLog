
public class ParserObject {

	String timeStamp, context, body;

	public ParserObject () {
		
	}
	
	public ParserObject (String timeStamp, String context, String body) {
		this.timeStamp = timeStamp;
		this.context = context;
		this.body = body;
	}
	
	public void append (String str) {
		body = body + " " + str;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public String getContext() {
		return context;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String toString () {
		return timeStamp + " " + context + " " + body;
	}
}
