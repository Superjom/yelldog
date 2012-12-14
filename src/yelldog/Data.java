package yelldog;
import java.util.*;
import yelldog.loader.*;


// contain all the shared data
public class Data {
	public static final int UserNum = 480189;
	public static final int ItemNum = 17770;
	// dic ; remove userid gap
	public static Dic dic = new Dic();
	// training data
	public static TrainingData train_data;
	public static ArrayList<ProbeNode> 
			probe_data = new ArrayList<ProbeNode>();;
	// baseline 
	public static double[] bu;
	public static double[] bi;
	// num of items rated by user u
	public static int buNum;
	// num of users who rated item i
	public static int biNum;
	//svd
	public static double[][]  p;
	public static double[][] pu;
	public static double[][] q;
	public static double[][] y;
	public static float mean;
	
	public static void initTrainingData(){
		if (train_data == null){
			train_data = new TrainingData();
		}
	}
}
