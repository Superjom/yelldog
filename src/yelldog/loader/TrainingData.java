package yelldog.loader;
import java.io.*;
import java.util.*;

import yelldog.Common;
import yelldog.Data;

public class TrainingData {
	// user X items
	public long length;
	public ArrayList<ArrayList <RateNode>> root
		= new ArrayList<ArrayList <RateNode>>();
	public TrainingData(){
		this.length = 0;
		for(int i=0; i<Data.UserNum; ++i){
			this.root.add(new ArrayList<RateNode>());
		}
	}
	
	public void put(int userid, int item, short rate){
		RateNode t = new RateNode(item, rate);
		int user_id = Data.dic.getUserID(userid);
		Common.println("in Memory: uID>rate>item: " + user_id + ">" + item + ">" + rate);
		root.get(user_id).add(t);
		// inc counter
		this.length ++;
	}
}
