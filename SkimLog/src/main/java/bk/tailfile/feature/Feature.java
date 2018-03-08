package bk.tailfile.feature;

import bk.tailfile.web.TailController;
import java.util.Arrays;
import java.util.List;
import bk.tailfile.parser.ParserObject;

public class Feature {
	
	public void setCollapsible(ParserObject p) {
		if (p.getContext().contains("nexj.core.persistence.sql.SQLAdapter"))
			p.setIsCollapsible(true);
	}

	public boolean isFilter(ParserObject p) {
		List<String> target = TailController.properties.getFilters();
		for (String filter : target) {
			if ((p.getContext() == "" && p.getLogLevel() == "") || (p.getContext().contains(filter))
					|| (p.getLogLevel().contains(filter))) {
				return true;
			}
		}
		return false;
	}
}