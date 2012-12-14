package yelldog.loader;

import java.io.*;
import java.util.*;

import yelldog.Common;
import yelldog.Data;

import yelldog.parser.*;
public class RateLoader {

	private String path;
	private RateParser parser;
	
	public RateLoader(String path){
		// share common data with other
		this.path = path;
		Data.initTrainingData();
		this.parser = new RateParser();

	}
	
	public File[] getFileList()
		throws FileNotFoundException{
		File path = new File(this.path);
		return path.listFiles();
	}
	
	public void sigleRun(){
		try{
			File[] files = getFileList();
			for(File f : files){
				Common.println("parse File: " + f.getName());
				Scanner input = new Scanner(f);
				this.parser.initInput(input);
				this.parser.parse();
			}
			
		} catch (FileNotFoundException e){
			Common.println("Error: can't list file!");
		}
		
	}
}
