package yelldog.loader;

import java.io.*;
import java.util.*;

import yelldog.Common;
import yelldog.Eva;
import yelldog.parser.*;

public class ProbeLoader {
	ProbeParser parser = new ProbeParser();
	public ProbeLoader(){
		
	}
	
	public void run(){
		try{
			File file = new File(Eva.ProbePath);
			Scanner input = new Scanner(file);
			this.parser.initInput(input);
			this.parser.parse();
		}  catch (FileNotFoundException e){
			Common.println("Error: can't list file!");
		}
	}
}
