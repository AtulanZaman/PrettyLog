package bk.tailfile.web;

import java.util.List;

public class Properties{
	private String filename;
	private List<String> filters;

	public Properties (){}

	public Properties (String filename, List<String> filters){
		this.filename = filename;
		this.filters = filters;
	}

	public String getFilename(){
		return filename;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public List<String> getFilters(){
		return filters;
	}

	public void setFilters(List<String> filters){
		this.filters = filters;
	}

	public String toString (){
		return "Properties: filename={"+filename+"} filters:"+filters;
	}
}