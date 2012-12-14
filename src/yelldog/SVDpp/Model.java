package yelldog.SVDpp;

import java.math.*;
import yelldog.Common;
import yelldog.Data;
import yelldog.Eva;
import yelldog.SVDpp.calculator.*;

// the svd++ model
// use gradient descent technique to estimate every parameter
public class Model {
	
	private double curRmse = 0.0;
	private double preRmse = 0.0;
	
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
	
	public void run(){
		double pui = 0.0;
		
		for(int step=0; step<Data.maxStep; ++step){
			double rmse = 0.0;
			int n = 0;
			for(int u=0; u<Data.UserNum; ++u){
				double[] sumQE = new double[Data.K];
				//cal puTemp
				this.calPuTem(u);
				int ruNum = Data.train_data.root.get(u).size();
				//iteration
				for(int i=0; i<ruNum; ++i){
					// true rate
					short rui = Data.train_data.root.get(u).get(i).rate;
					int item = Data.train_data.root.get(u).get(i).item;
					short rate = Data.train_data.root.get(u).get(i).rate;
					double bui = Data.mean + Data.bu[u] + Data.bi[item];
					pui = this.predict(u, item);
					
					double eui = rui - pui;
					
					//if is nan
					rmse += eui*eui;
					n++;
					//update bias
					this.updateBias(i, item, eui);
					//update p q
					this.upatePQ(rui, item, eui, sumQE);
					//update y
					this.updateY(rui, ruNum, sumQE);
				}
			}//end for u
			curRmse = Math.sqrt(rmse/n);
			
			if(curRmse >= preRmse && step >= 3) 
				break;
			else
				preRmse = curRmse;
			Common.println("step>preRmse>curRmse: " + step + ">" + preRmse
					 + ">" + curRmse);
			
			// gradually loose inc
			this.loose();
		}// end for step
	}// end run
	
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
	
	private void updateBias(int u, int item, double eui){
		Data.bu[u] += Eva.gamma1 * (eui - Eva.alphe6 * Data.bu[u]);
		Data.bi[item] += Eva.gamma1 * (eui - Eva.alphe6 * Data.bi[item]);
	}
	
	private void upatePQ(int u, int item, double eui, double[] sumQE){
		for(int k=0; k<Data.K; ++k){
			Data.p[u][k] += Eva.gamma2 * (eui*Data.q[item][k] - Eva.alpha7*Data.p[u][k]);
			Data.q[item][k] += Eva.gamma2 * (eui*Data.pu[u][k] - Eva.alpha7*Data.q[item][k]);
			sumQE[k] += eui * Data.q[item][k];
		}
	}
	
	private void loose(){
		Eva.gamma1 *= Eva.looser;
		Eva.gamma2 *= Eva.looser;
		Eva.gamma3 *= Eva.looser;
	}
	
	private void updateY(int u, int ruNum, double[] sumQE){
		double tem = 1/ Math.sqrt(ruNum);
		for(int i=0; i<ruNum; ++i){
			int item = Data.train_data.root.get(u).get(i).item;
			for(int k=0; k<Data.K; ++k){
				Data.y[item][k] += Eva.gamma2 * (tem * sumQE[k] - Eva.alpha7 * Data.y[item][k]);
			}
		}
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
