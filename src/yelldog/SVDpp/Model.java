package yelldog.SVDpp;

import java.math.*;
import yelldog.Common;
import yelldog.Data;
import yelldog.SVDpp.calculator.*;

// the svd++ model
// use gradient descent technique to estimate every parameter
public class Model {
	
	private double curRMSE = 0.0; 
	
	public Model(){
		//init all parameters
		Baseline baseline = new Baseline();
		Mean mean = new Mean();
		PQCharacter pq = new PQCharacter();
		Common.println("begin to init parameters");
		mean.cal();
		baseline.init();
		pq.init();
		Common.println("end init parameters");
	}
	
	public double predict(int u, int i){
		int ruNum = Data.train_data.root.get(u).size();
		double ret;
		if(ruNum >= 1){
			//have some train data
			ret = Data.mean + Data.bu[u] + Data.bi[i]
					//Point multiplication
				+ this.dot(Data.puTem[u], Data.q[i]);
		} else
			ret = Data.bu[u] + Data.bi[i];
		if(ret < 1.0) ret = 1;
		if (ret > 5.0) ret = 5;
		return ret;
	}
	
	private void calPuTem(int u){
		// P_u + |N(u)|^{-1/2} \sum_{j\inN(u)}{y_i}}
		// only concerns u
		// save the value for accelation
		double sumy ;
		int ruNum = Data.train_data.root.get(u).size();
		double sqrtNu = 1.0/ Math.sqrt(
				Data.train_data.root.get(u).size());
		for(int k=0; k<Data.K; ++k){
			sumy = 0.0;
			for(int i=0; i<ruNum; ++i){
				//for every item
				int item = Data.train_data.root.get(u).get(i).item;
				sumy += Data.y[item][k];
			}
			Data.puTem[u][k] = Data.puTem[u][k] + sqrtNu * sumy;
		}
	}// end calPuTem
	private double dot(double[] A, double[] B){
		double sum = 0;
		for(int i=0; i<A.length; ++i){
			sum += A[i] * B[i];
		}
		return sum;
	}	
	
}
