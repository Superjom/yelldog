package yelldog.parser;

import java.util.Scanner;
import yelldog.loader.*;
import yelldog.Common;
import yelldog.Data;

public class ProbeParser {
	private Scanner input;
	private int curMovieID;
	
	//set input
	public void initInput(Scanner input){
		this.input = input;
	}	
	
	private boolean _isMovieLine(String s){
		return s.indexOf(":") > -1;
	}
	
	public void parse(){
		String line;
		while(input.hasNextLine()){
			line = input.nextLine();
			if (this._isMovieLine(line)){
				this._parseMovieID();
			}else{
				this._parseRates();
			}
		}
	}
	// a single line
	private void _parseMovieID(){
		String line = input.nextLine();
		String[] id_s = line.split(":");
		int id = Integer.parseInt(id_s[0]);
		this.curMovieID = id;
	}
	// a single line
	private void _parseRates(){
		//parse remaining lines
		//each line for a record
		String line;
		int user_id;
		
		line = input.nextLine();
		user_id = Integer.parseInt(line);
		ProbeNode temnode = new ProbeNode(user_id, this.curMovieID);
		Data.probe_data.add(temnode);
	}	
}
