package bk.tailfile.web;

public class Properties{
	private String filename;

	public Properties (){}

	public Properties (String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String toString (){
		return "Properties: "+filename;
	}
}