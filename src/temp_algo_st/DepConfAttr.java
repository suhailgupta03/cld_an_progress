package temp_algo_st;

/**
 *
 * @author user
 */
public class DepConfAttr {
    
        private int vmCount;
	private long imageSize;
	private int memory;
	private long bw;

    public DepConfAttr(int vmCount, long imageSize, int memory, long bw) {
        this.vmCount = vmCount;
        this.imageSize = imageSize;
        this.memory = memory;
        this.bw = bw;
    }       

    public int getVmCount() {
        return vmCount;
    }

    public void setVmCount(int vmCount) {
        this.vmCount = vmCount;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public long getBw() {
        return bw;
    }

    public void setBw(long bw) {
        this.bw = bw;
    }
        
        
}
