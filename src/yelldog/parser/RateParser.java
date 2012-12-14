package yelldog.parser;
import java.util.*;
import java.io.*;

import yelldog.Common;
import yelldog.Data;
import yelldog.loader.*;

public class RateParser {
	public int curMovieID;
	private Scanner input;
	
	//constructor
	public RateParser(){
	}
	
	//set input
	public void initInput(Scanner input){
		this.input = input;
	}
	
	public void parse(){
		//read first line and trans movieID
		_parseMovieID();
		_parseRates();
	}
	
	private void _parseMovieID(){
		String line = input.nextLine();
		String[] id_s = line.split(":");
		int id = Integer.parseInt(id_s[0]);
		this.curMovieID = id;
	}
	
	private void _parseRates(){
		//parse remaining lines
		//each line for a record
		String line;
		String[] id_s;
		int user_id;
		short rate;
		while(input.hasNextLine()){
			line = input.nextLine();
			Common.println("line: " + line);
			id_s = line.split(",");
			user_id = Integer.parseInt(id_s[0]);
			rate = Short.parseShort(id_s[1]);
			Common.println("get userid>rate>item: " +
					user_id + ">" + rate + ">" + this.curMovieID);
			//put record to list
			Data.train_data.put(user_id, this.curMovieID, rate);
		}
	}
}
