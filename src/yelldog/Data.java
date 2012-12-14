package yelldog;
import java.util.*;
import yelldog.loader.*;


// contain all the shared data
public class Data {
	public static final int UserNum = 480189;
	public static final int ItemNum = 17770;
	public static final int K = 50;
	// dic ; remove userid gap
	public static Dic dic = new Dic();
	// training data
	public static TrainingData train_data = null;
	public static ArrayList<ProbeNode> 
			probe_data = new ArrayList<ProbeNode>();;
	// baseline 
	public static double[] bu = null;
	public static double[] bi = null;
	// num of items rated by user u
	public static int[] buNum = null;
	// num of users who rated item i
	public static int[] biNum = null;
	//svd
	public static double[][]  p = null;
	public static double[][] pu = null;
	public static double[][] puTem = null;
	public static double[][] q = null;
	public static double[][] y = null;
	public static float mean = 0;
	
	public static int maxStep = 5;
	
	
	public static void initTrainingData(){
		if (train_data == null){
			train_data = new TrainingData();
		}
	}
	// before training init all data
	public static void initAll(){
		bu = new double[UserNum];
		bi = new double[ItemNum];
		
		buNum = new int[UserNum];
		biNum = new int[ItemNum];
		
		p = new double[UserNum][K];
		puTem = new double[UserNum][K];
		
		q = new double[ItemNum][K];
		y = new double[ItemNum][K];
	}
}
