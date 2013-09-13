
package temp_algo_st;

/**
 *
 * @author user
 */
public class AlgoAttr {
    private int vmCount;
    private int weightCount;
    private int requestAllocated;

    public AlgoAttr(int vmCount, int weightCount, int requestAllocated) {
        this.vmCount = vmCount;
        this.weightCount = weightCount;
        this.requestAllocated = requestAllocated;
    }

    public int getVmCount() {
        return vmCount;
    }

    public void setVmCount(int vmCount) {
        this.vmCount = vmCount;
    }

    public int getWeightCount() {
        return weightCount;
    }

    public void setWeightCount(int weightCount) {
        this.weightCount = weightCount;
    }

    public int getRequestAllocated() {
        return requestAllocated;
    }

    public void setRequestAllocated(int requestAllocated) {
        this.requestAllocated = requestAllocated;
    }
}
