package cloudsim.ext.gui;

import java.io.Serializable;
import java.util.LinkedList;
import ext.suhail.global.sttic.StaticVars;

/**
 * VmAllocationUIElement holds the data specific to a data center VM allocation.
 * 
 * @author Bhathiya Wickremasinghe
 *
 */
public class VmAllocationUIElement implements Serializable {

	private static final long serialVersionUID = 6435150104983448557L;
	
	private DataCenterUIElement dc;
	private int vmCount;
	private long imageSize;
	private int memory;
	private long bw;
	
	public VmAllocationUIElement(){
		
	}
	
	public VmAllocationUIElement(DataCenterUIElement dc, int vmCount, long imageSize, int memory,
			long bw) {
		super();
		this.dc = dc;
		this.vmCount = vmCount;
		this.imageSize = imageSize;
		this.memory = memory;
		this.bw = bw;
	}


	/**
	 * @return the dc
	 */
	public DataCenterUIElement getDc() {
		return dc;
	}


	/**
	 * @param dc the dc to set
	 */
	public void setDc(DataCenterUIElement dc) {
		this.dc = dc;
	}


	/**
	 * @return the vmCount
	 */
	public int getVmCount() {
		return vmCount;
	}
	/**
	 * @param vmCount the vmCount to set
	 */
	public void setVmCount(int vmCount) {
		this.vmCount = vmCount;
                LinkedList<VmAllocationUIElement> list = StaticVars.VmMap.get(StaticVars.currentDataCenter);
                if(StaticVars.rowMap.containsKey(StaticVars.currRow)) {
                    int index = StaticVars.rowMap.get(StaticVars.currRow);
                    list.add(index, StaticVars.modVm);
                    list.remove(index + 1);
                }else {
                    throw new RuntimeException("EXCEPTION : Row number " + StaticVars.currRow + " doesn't exist in the map. Inside method 'setVmCount'");
  
                }
	}
	/**
	 * @return the imageSize
	 */
	public long getImageSize() {
		return imageSize;
	}
	/**
	 * @param imageSize the imageSize to set
	 */
	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
                LinkedList<VmAllocationUIElement> list = StaticVars.VmMap.get(StaticVars.currentDataCenter);
                if(StaticVars.rowMap.containsKey(StaticVars.currRow)) {
                    int index = StaticVars.rowMap.get(StaticVars.currRow);
                    list.add(index, StaticVars.modVm);
                    list.remove(index + 1);
                }else {
                    throw new RuntimeException("EXCEPTION : Row number " + StaticVars.currRow + " doesn't exist in the map. Inside method 'setImageSize'");
                }
	}
	/**
	 * @return the memory
	 */
	public int getMemory() {
		return memory;
	}
	/**
	 * @param memory the memory to set
	 */
	public void setMemory(int memory) {
		this.memory = memory;
                LinkedList<VmAllocationUIElement> list = StaticVars.VmMap.get(StaticVars.currentDataCenter);
                if(StaticVars.rowMap.containsKey(StaticVars.currRow)) {
                    int index = StaticVars.rowMap.get(StaticVars.currRow);
                    list.add(index, StaticVars.modVm);
                    list.remove(index + 1);
                }else {
                    throw new RuntimeException("EXCEPTION : Row number " + StaticVars.currRow + " doesn't exist in the map. Inside method 'setMemory'");
                }
	}
	/**
	 * @return the bw
	 */
	public long getBw() {
		return bw;
	}
	/**
	 * @param bw the bw to set
	 */
	public void setBw(long bw) {
		this.bw = bw;
                LinkedList<VmAllocationUIElement> list = StaticVars.VmMap.get(StaticVars.currentDataCenter);
                if(StaticVars.rowMap.containsKey(StaticVars.currRow)) {
                    int index = StaticVars.rowMap.get(StaticVars.currRow);
                    list.add(index, StaticVars.modVm);
                    list.remove(index + 1);
                }else {
                    throw new RuntimeException("EXCEPTION : Row number " + StaticVars.currRow + " doesn't exist in the map.Inside method 'setBw'");
                }
	}
	
	
}
