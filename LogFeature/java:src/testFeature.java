package LogFeature;

public class testFeature{

	public static void main(String[] args) {
		ParserObject o = new ParserObject("2018-01-06 12:45:35,386", "DEBUG", "[[nexj.model.class.SysReader]",
				"Retrieved 1 instance(s) of BaseEntityTypeEnum in 0 ms", false);
		Feature.setCollapsible(o);
		System.out.print("Collapsible:" + o.collapsible);
		System.out.println("");
		System.out.println("Send this object to client:" + Feature.isFilter(o));
	}
}
