package yelldog.SVDpp.calculator;

import yelldog.loader.*;
import yelldog.Data;
import java.util.*;
// init bi bu
public class Baseline {
	public void init()
	{
		int ru_size;
		RateNode temnode;
		for (int i=0; i<Data.UserNum; ++i){
			ru_size = Data.train_data.root.get(i).size();
			for(int j=0; j<ru_size; ++j){
				temnode = Data.train_data.root.get(i).get(j);
				// should cal mean first
				Data.bi[temnode.item] += temnode.rate - Data.mean;
				//inc counter of users who rated item i
				Data.biNum[temnode.item] += 1;
			}
		}// end for
		
		for(int i=0; i<Data.ItemNum; ++i){
			if(Data.biNum[i] >= 1){
				Data.bi[i] /= (Data.bi[i] + 25);
			}else{
				Data.bi[i]= 0.0; 
			}
		}// end for
		
		for(int i=0; i<Data.UserNum; ++i){
			ru_size = Data.train_data.root.get(i).size();
			for (int j=0; j < ru_size; ++j){
				temnode = Data.train_data.root.get(i).get(j);
				// calculate bias
				Data.bu[i] += temnode.rate - Data.mean - Data.bi[temnode.item];
				// inc counter the num of items rated by user u
				Data.buNum[i] += 1;
			}
		}// end for
		
		for(int i=0; i < Data.UserNum; ++i){
			// loose rate
			if(Data.buNum[i] >= 1){
				Data.bu[i] /= (Data.bu[i] + 10);
			}
			else{
				Data.bu[i] = 0.0;
			}
		}
	}
}
