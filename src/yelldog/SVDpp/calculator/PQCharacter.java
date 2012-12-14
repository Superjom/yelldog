package yelldog.SVDpp.calculator;

import yelldog.Data;

// init p q
public class PQCharacter {
	// just assign to 1
	public void init(){
		for(int i=0; i<Data.UserNum; ++i){
			initEach(Data.p[i]);
		}
		for(int i=0; i<Data.ItemNum; ++i){
			initEach(Data.q[i]);
		}
	}
	
	public void initEach(double[] p){
		for(int i=0; i<p.length; ++i){
			//just assign to 1
			p[i] = 1;
		}
	}
}
