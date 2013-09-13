
package temp_algo_st;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author user
 */
public class Temp_Algo_Static_Var {
    
    public static Map<String,AlgoHelper> map = new HashMap<String,AlgoHelper>();  
    
    public static Map<String,LinkedList<AlgoAttr>> algoMap = new HashMap<String, LinkedList<AlgoAttr>>();
    
    /**
     * Key : Name of the Data Center
     * Value : Total VMs in that data center.
     */
    public static Map<String,Integer> vmCountMap = new HashMap<String,Integer>();
        
}
