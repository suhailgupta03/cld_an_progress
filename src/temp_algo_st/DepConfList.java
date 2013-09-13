
package temp_algo_st;
import cloudsim.ext.gui.DataCenterUIElement;
import cloudsim.ext.gui.VmAllocationUIElement;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
        

/**
 *
 * @author user
 */
public class DepConfList {

    /**
     * Key : Attribute name
     * Value : Attribute.
     * There is a single key named 'VmAllocationUIElement' but different objects of class 'VmAllocationUIElement'
     * as value.
     */    
    public static Map<Object,Object> map = new HashMap<Object,Object>();
    /**
     * Key : Row number
     * Value : Row Object. 
     * Row Object is a HashMap. Hash Map contains object of class 'VmAllocationUIElement'
     */
    public static Map<Object,Object> listMap = new HashMap<Object,Object>();
    
    private static LinkedList<Object> listDataCenters = new LinkedList<Object>();
    
    /**
     * Key : name of the data center
     * Value : A Linked List that contains a list of objects of class DepConfAttr.
     */
    public static Map<String,LinkedList<DepConfAttr>> dcConfMap = new HashMap<String,LinkedList<DepConfAttr>>();
    
    
    public static Map<Object, Object> getMap() {
        return map;
    }

    /**
     * This function either sets or resets the map.
     * @param row : Key
     * @param map : Value
     */
    public static void setMap(int row,Map<Object, Object> map) {
        //System.out.println("Row : " + row);
        DepConfList.map = map;
        //System.out.println("Size of map : " + DepConfList.map.size() );
        if(listMap.containsKey(row)) {
            listMap.remove(row);
        }
        listMap.put(row, DepConfList.map);
        //System.out.println("Size of listMap : " + listMap.size());
        clubSameDC(listMap);
//        Set<Object> rowNumbers = listMap.keySet();
//        Iterator i = rowNumbers.iterator();
//        while(i.hasNext()) {
//            int r = (Integer)i.next();
//            HashMap hm = (HashMap)listMap.get(r);
//            VmAllocationUIElement v = (VmAllocationUIElement)hm.get("VmAllocationUIElement");
////            System.out.println("Bandwidth : " + v.getBw());
////            System.out.println("DC : " + v.getDc());
////            System.out.println("ImageSize : " + v.getImageSize());
////            System.out.println("Memory : " + v.getMemory());
//            System.out.println("Vm Count : " + v.getVmCount() + " for " + v.getDc());
////            System.out.println("------------------------------");
//        }
    }
    
    public static void clubSameDC(Map<Object,Object> listMap) {
        Set<Object> rowNumbers = listMap.keySet();
        Iterator i = rowNumbers.iterator();
        while(i.hasNext()) {
            int r = (Integer)i.next();
            HashMap hm = (HashMap)listMap.get(r);
            VmAllocationUIElement vm = (VmAllocationUIElement)hm.get("VmAllocationUIElement");
            if(!listDataCenters.contains(vm.getDc())) {
                listDataCenters.add(vm.getDc()); // Lists the unique Data Centers
            }
        }
        //System.out.println("List Data Centers : " + listDataCenters);
        prioritzeVM(listMap, listDataCenters);
    }
    
    public static void prioritzeVM(Map<Object,Object> listMap,LinkedList<Object> ldc) {
        Iterator ldci = ldc.iterator();
        Map<String,LinkedList<DepConfAttr>> tmpMap = new HashMap<String,LinkedList<DepConfAttr>>();
        while(ldci.hasNext()) {
            String dcName = ldci.next().toString();
            LinkedList<DepConfAttr> tmpList = new LinkedList<DepConfAttr>();
            Set<Object> rowNumbers = listMap.keySet();
            Iterator mi = rowNumbers.iterator();
            while(mi.hasNext()) {
                int r = (Integer)mi.next();
                HashMap hm = (HashMap)listMap.get(r);
                VmAllocationUIElement vm = (VmAllocationUIElement)hm.get("VmAllocationUIElement");
                if(vm.getDc().toString().compareTo(dcName) == 0) {
                    DepConfAttr dca = new DepConfAttr(vm.getVmCount(), vm.getImageSize(), vm.getMemory(), vm.getBw());
                    tmpList.add(dca);
                    //System.out.println("Data center name is " + dcName + " and VM Count is : " + vm.getVmCount());
                }
            }
            /*
            int size = tmpList.size();
            int elementBC = 0; // elemen t being checked
            int startIndex = 1;
            for (DepConfAttr dca : tmpList) {
                long maxMem = dca.getMemory(); // Let this be the maximum memory
                for(int i = startIndex ; i < size ; i++) {
                    DepConfAttr dcaTmp = tmpList.get(i);
                    if(maxMem < dcaTmp.getMemory()) {
                        tmpList.add(elementBC, dcaTmp);
                        tmpList.remove(i+1);
                        maxMem = tmpList.get(elementBC).getMemory();                        
                    }
                }
                elementBC++;
                startIndex++;
                size--;
            }*/
            tmpMap.put(dcName, tmpList);
        }
        DepConfList.dcConfMap = tmpMap;// This map has a Key : Datacenter name (String) and a value as List object
                                       // containing objects of class DepConfAttr

        // For each data center sort the list
        Set<String> keys =DepConfList.dcConfMap.keySet();
        for(String dataCenter : keys) {
            Collections.sort(dcConfMap.get(dataCenter), new MemoryComparator());
        }
        
        Set<String> keySet = DepConfList.dcConfMap.keySet();
        for(String s : keySet) {
            Temp_Algo_Static_Var.map.put(s, new AlgoHelper(0, -1)); // index = 0 ; lastCount = -1 ;
        }
        
//        //System.out.println(DepConfList.dcConfMap.values());
//        Set<String> keySet = DepConfList.dcConfMap.keySet();
////        //System.out.println("@@@Key-Set@@@" + keySet); 
//                        
//        for(String s : keySet) { //System.out.println(s);
//            LinkedList<DepConfAttr> l = (LinkedList<DepConfAttr>)DepConfList.dcConfMap.get(s);
//            System.out.println("Data Center : " +  s);
//            //System.out.println("Size : " + l.size());
//            for(DepConfAttr o : l) {
//                System.out.println("-----start----");
//                System.out.println("Name of the Data Center : " + s);
//                System.out.println("Bandwidth : " + o.getBw());
//                System.out.println("Image Size : " + o.getImageSize());
//                System.out.println("Memory : " + o.getMemory());
//                System.out.println("VM Count : " + o.getVmCount());
//                System.out.println("----end-----");
//            }
//        }
    }
}
