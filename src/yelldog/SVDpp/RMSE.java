package yelldog.SVDpp;
import java.util.*;

import yelldog.Data;
import yelldog.loader.*;

public class RMSE {
	private ArrayList<ProbeNode> nodes;
	
	public RMSE(ArrayList<ProbeNode> nodes){
		this.nodes = nodes;
	}
	

	public void cal(){
		int userID;
		ProbeNode curnode;
		for(int i=0; i<nodes.size(); ++i){
			curnode = nodes.get(i);
			userID = curnode.userid;
		}
	}
}
