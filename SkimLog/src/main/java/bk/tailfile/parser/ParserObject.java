package bk.tailfile.parser;

public class ParserObject {

	String timeStamp, context, body, logLevel;
	boolean isCollapsible;

	public ParserObject () {
		isCollapsible = false;
	}
	
	public ParserObject (String timeStamp, String logLevel, String context, String body) {
		this.timeStamp = timeStamp;
		this.logLevel = logLevel;
		this.context = context;
		this.body = body;
		isCollapsible = false;
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

	public String getLogLevel() {
		return logLevel;
	}
	
	public boolean getIsCollapsible() {
		return isCollapsible;	
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
	
	public void setIsCollapsible(boolean isCollapsible) {
		this.isCollapsible = isCollapsible;	
	}
	
	public String toString () {
		return timeStamp + " " + logLevel + " " + context + " " + body;
	}
}