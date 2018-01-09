package LogFeature;

import java.util.Arrays;
import java.util.List;

public class Feature {

	static List<String> target = Arrays.asList("nexj.core.controller", "nexj.model.class", "nexj.model.library",
			"nexj.core.rpc.http", "nexj.core.persistence.sql.SQLAdapter", "ERROR", "WARN");
	
	public static void setCollapsible(ParserObject p) {
		if (p.getContext().contains("nexj.core.persistence.sql.SQLAdapter"))
			p.collapsible = true;
	}

	public static boolean isFilter(ParserObject p) {
		for (String filter : target) {
			if ((p.getContext() == null && p.getLogLevel() == null) || (p.getContext().contains(filter))
					|| (p.getLogLevel().contains(filter))) {
				return true;
			}
		}
		return false;
	}
}
