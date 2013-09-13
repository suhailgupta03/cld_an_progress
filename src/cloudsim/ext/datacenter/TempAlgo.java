
package cloudsim.ext.datacenter;

import cloudsim.ext.Constants;
import cloudsim.ext.event.CloudSimEvent;
import cloudsim.ext.event.CloudSimEventListener;
import cloudsim.ext.event.CloudSimEvents;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import temp_algo_st.AlgoHelper;
import temp_algo_st.MemoryComparator;
import temp_algo_st.DepConfAttr;
import temp_algo_st.DepConfList;
import temp_algo_st.Temp_Algo_Static_Var;
import temp_algo_st.AlgoAttr;
import static temp_algo_st.DepConfList.dcConfMap;
import java.io.FileWriter;

/**
 *
 * @author user
 */ 
public class TempAlgo extends VmLoadBalancer implements CloudSimEventListener {
    
    /**
     * Key : Name of the data center
     * Value : List of objects of class 'VmAllocationUIElement'.
     */
    private  Map<String,LinkedList<DepConfAttr>> confMap = new HashMap<String,LinkedList<DepConfAttr>>();
    private Iterator<Integer> availableVms = null;
    private DatacenterController dcc;
    private boolean sorted = false;
    private int currentVM;
    private boolean calledOnce = false;
    private boolean indexChanged = false;
//    private FileWriter fw_1;
//    private FileWriter fw_2;
//    private FileWriter fw_3;
//    private FileWriter fw_4;
//    private FileWriter fw_5;
    
    private Map<String,LinkedList<AlgoAttr>> algoMap = new HashMap<String, LinkedList<AlgoAttr>>();
    private Map<String,AlgoHelper> map = new HashMap<String,AlgoHelper>();  
    private Map<String,Integer> vmCountMap = new HashMap<String,Integer>();
    
    public TempAlgo(DatacenterController dcb) {
        confMap = DepConfList.dcConfMap;
        this.dcc = dcb;
        dcc.addCloudSimEventListener(this);
        if(!this.calledOnce) {
            try {
//                fw_1 = new FileWriter(new File("dc-1.txt"));
//                fw_2 = new FileWriter(new File("dc-2.txt"));
//                fw_3 = new FileWriter(new File("dc-3.txt"));
//                fw_4 = new FileWriter(new File("dc-4.txt"));
//                fw_5 = new FileWriter(new File("dc-5.txt"));
            }catch(Exception exc) {exc.printStackTrace();}
            this.calledOnce = true;
            // Make a new map using dcConfMap that lists 'DataCenter' as a 'key' and 'LinkedList<AlgoAttr>' as 'value'.
            Set<String> keyst =DepConfList.dcConfMap.keySet();
            for(String dataCenter : keyst) {
                LinkedList<AlgoAttr> tmpList = new LinkedList<AlgoAttr>();
                LinkedList<DepConfAttr> list = dcConfMap.get(dataCenter);
                int totalVms = 0;
                for(DepConfAttr o : list) {
                    tmpList.add(new AlgoAttr(o.getVmCount(), o.getMemory()/512, 0));
                    totalVms = totalVms + o.getVmCount();
                }
                Temp_Algo_Static_Var.algoMap.put(dataCenter, tmpList);
                Temp_Algo_Static_Var.vmCountMap.put(dataCenter, totalVms);
            }
            this.algoMap = new HashMap<String, LinkedList<AlgoAttr>>(Temp_Algo_Static_Var.algoMap);
            this.vmCountMap = new HashMap<String,Integer>(Temp_Algo_Static_Var.vmCountMap);
            this.map = new HashMap<String,AlgoHelper>(Temp_Algo_Static_Var.map);
        }
    }
    
    @Override
    public int getNextAvailableVm() {
        synchronized(this) {
            String dataCenter = this.dcc.getDataCenterName();
            int totalVMs = this.vmCountMap.get(dataCenter);
            AlgoHelper ah = (AlgoHelper)this.map.get(dataCenter);
            int lastIndex = ah.getIndex();
            int lastCount = ah.getLastCount();
            LinkedList<AlgoAttr> list = this.algoMap.get(dataCenter);
            AlgoAttr aAtr = (AlgoAttr)list.get(lastIndex);
            indexChanged = false;
            if(lastCount < totalVMs)  {
                if(aAtr.getRequestAllocated() % aAtr.getWeightCount() == 0) {
                    lastCount = lastCount + 1;
                    this.currentVM = lastCount;
                    if(aAtr.getRequestAllocated() == aAtr.getVmCount() * aAtr.getWeightCount()) {
                        lastIndex++;
                        if(lastIndex != list.size()) {
                            AlgoAttr aAtr_N = (AlgoAttr)list.get(lastIndex);
                            aAtr_N.setRequestAllocated(1);
                            this.indexChanged = true;
                        }
                        if(lastIndex == list.size()) {
                            lastIndex = 0;
                            lastCount = 0;
                            this.currentVM = lastCount;
                            AlgoAttr aAtr_N = (AlgoAttr)list.get(lastIndex);
                            aAtr_N.setRequestAllocated(1);
                            this.indexChanged = true;

                        }
                    }
                }
                if(!this.indexChanged) {
                    aAtr.setRequestAllocated(aAtr.getRequestAllocated() + 1);
                }

                this.map.put(dataCenter, new AlgoHelper(lastIndex, lastCount)); 
//                try {
//                    if(dataCenter.compareTo("DC1")==0) {
//                        fw_1.write("Current VM : " + this.currentVM + " for data center : " + dataCenter);
//                        fw_1.write(System.getProperty("line.separator"));
//                    }else if(dataCenter.compareTo("DC2") ==0) {
//                        fw_2.write("Current VM : " + this.currentVM + " for data center : " + dataCenter);
//                        fw_2.write(System.getProperty("line.separator"));
//                    }else if(dataCenter.compareTo("DC3") == 0) {
//                        fw_3.write("Current VM : " + this.currentVM + " for data center : " + dataCenter);
//                        fw_3.write(System.getProperty("line.separator"));
//                    }else if(dataCenter.compareTo("DC4") == 0) {
//                        fw_4.write("Current VM : " + this.currentVM + " for data center : " + dataCenter);
//                        fw_4.write(System.getProperty("line.separator"));
//                    }else if(dataCenter.compareTo("DC5") == 0) {
//                        fw_5.write("Current VM : " + this.currentVM + " for data center : " + dataCenter);
//                        fw_5.write(System.getProperty("line.separator"));
//                    }
//                }catch(Exception exc) {
//                    exc.printStackTrace();
//                }
                //System.out.println("Current VM : " + this.currentVM + " for data center : " + dataCenter);
                return this.currentVM;
            }}

            System.out.println("--------Before final return statement---------");
            return 0;
            
    }   

    @Override
    public void cloudSimEventFired(CloudSimEvent e) {
        if(e.getId() == CloudSimEvents.EVENT_CLOUDLET_ALLOCATED_TO_VM) {
            int vmId = (Integer) e.getParameter(Constants.PARAM_VM_ID);
            System.out.println("+++++++++++++++++++Machine with vmID : " + vmId + " attached");
        }else if(e.getId() == CloudSimEvents.EVENT_VM_FINISHED_CLOUDLET) {
            int vmId = (Integer) e.getParameter(Constants.PARAM_VM_ID);
            //System.out.println("+++++++++++++++++++Machine with vmID : " + vmId + " freed");
        }
    }
}
