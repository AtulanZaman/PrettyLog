package LogFeature;

public class ParserObject {

	String timeStamp, logLevel, context, body;
	Boolean collapsible;

	public ParserObject(String timeStamp, String logLevel, String context, String body, Boolean collapsible) {
		this.timeStamp = timeStamp;
		this.logLevel = logLevel;
		this.context = context;
		this.body = body;
		this.collapsible = collapsible;
	}

	public String getContext() {
		return context;
	}

	public String getLogLevel() {
		return logLevel;
	}
}
