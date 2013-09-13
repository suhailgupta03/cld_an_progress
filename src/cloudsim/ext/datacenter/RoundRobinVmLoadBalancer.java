package cloudsim.ext.datacenter;

import java.util.Map;

/**
 * This class implements {@link VmLoadBalancer} with a Round Robin policy.
 * 
 * @author Bhathiya Wickremasinghe
 *
 */
public class RoundRobinVmLoadBalancer extends VmLoadBalancer {
	
	private Map<Integer, VirtualMachineState> vmStatesList;
	private int currVm = -1;
        private DatacenterController dcc;

	public RoundRobinVmLoadBalancer(Map<Integer, VirtualMachineState> vmStatesList, DatacenterController dcc){
		super();
		this.vmStatesList = vmStatesList;
                this.dcc = dcc;
	}
        
	/* (non-Javadoc)
	 * @see cloudsim.ext.VMLoadBalancer#getVM()
	 */
	public int getNextAvailableVm(){
		currVm++;
		if (currVm >= vmStatesList.size()){
			currVm = 0;
		}
		allocatedVm(currVm);
                
		return currVm;	
	}
}
