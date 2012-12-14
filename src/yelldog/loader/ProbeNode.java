package yelldog.loader;

import yelldog.Data;

public class ProbeNode {
	public int userid;
	public short rate;
	public int item;
	
	public ProbeNode(int userid, int item){
		// trans user_id
		this.userid = Data.dic.getUserID(userid);
		this.rate = 0;
		this.item = item;
	}
}
