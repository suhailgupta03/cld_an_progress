package ext.suhail.global.sttic;

import cloudsim.ext.gui.VmAllocationUIElement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author user
 */
public class StaticVars {
    
    /**
     * Key : Name of the Data Center
     * Value : A Linked List containing object of type 'VmAllocationUIElement'.
     */
    public static Map<String,LinkedList<VmAllocationUIElement>> VmMap = new HashMap<String,LinkedList<VmAllocationUIElement>>();
   
    /**
     * Key : Row Number
     * Value : Index of the row object in the 'LinkedList<VmAllocationUIElement>' .
     */
    public static Map<Integer,Integer> rowMap = new HashMap<Integer,Integer>();
    
    /**
     * This variable tells the current row modified in the deployment configuration table.
     */
    public static int currRow = 0;
    
    /**
     * This variable tells the Data Center selected using the Combo Box in the deployment configuration table.
     */
    public static String currentDataCenter = null;
    
    /**
     * This variable tells keeps the modified 'VmAllocationUIElement' object in the deployment configuration table.
     */
    public static VmAllocationUIElement modVm = null;
}
