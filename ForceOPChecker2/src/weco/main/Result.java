package weco.main;

import java.io.File;

public class Result {
	
	public File file;
	public String message;
	public int warningLvl;
	public Category category;
	
public Result(File file, String line, Category category, int warningLvl){
	this.file = file;
	this.message = line;
	this.warningLvl = warningLvl;
	this.category = category;
}

}
