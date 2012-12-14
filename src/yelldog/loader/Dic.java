package yelldog.loader;
import java.util.*;

public class Dic {
	private Map<Integer, Integer> user_to_id = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> id_to_user = new HashMap<Integer, Integer>();
	private int length;
	public Dic(){
		length = 0;
	}
	public int getUserID(int user){
		if (this.user_to_id.containsKey(user)){
			return this.user_to_id.get(user);
		}
		this.user_to_id.put(user, length);
		this.id_to_user.put(length, user);
		this.length ++;
		return this.length-1;
	}
	
	public int IDtoUser(int id){
		return this.id_to_user.get(id);
	}
}
