
import java.io.*;
import java.util.*;
import yelldog.loader.*;
import yelldog.parser.*;

public class dog {
	
	public static void main(String[] args)
		throws FileNotFoundException {
		//tryParser();
		sigleLoader();
	}
	
	public static void sigleLoader(){
		RateLoader r = new RateLoader(Eva.TrainingDataPath);
		r.sigleRun();
	}

}
