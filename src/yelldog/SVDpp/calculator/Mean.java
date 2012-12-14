package yelldog.SVDpp.calculator;

import java.util.*;
import yelldog.Data;
import yelldog.loader.*;

public class Mean {
	private long rateSum = 0;
	private float mean = 0;
	
	public void cal(){
		sum();
		mean = rateSum / Data.train_data.length;
	}
	
	public float getMean(){
		if (mean == 0){
			cal();
		}
		return mean;
	}
	
	public void sum(){
		ArrayList<RateNode> temnode;
		for (int i=0; i<Data.UserNum; ++i){
			temnode = Data.train_data.root.get(i);
			for (int j=0; j<temnode.size(); ++j){
				//init baseline
				this.rateSum += temnode.get(j).rate;
			}
			 
		}
		
	}
}
