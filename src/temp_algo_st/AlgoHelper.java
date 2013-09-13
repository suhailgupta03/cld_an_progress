
package temp_algo_st;

/**
 *
 * @author user
 */
public class AlgoHelper {
    private int index;
    private int lastCount;
    
    public AlgoHelper(int index, int lastCount) {
        this.index = index;
        this.lastCount = lastCount;
    }  

    public int getIndex() {
        return index;
    }

    public int getLastCount() {
        return lastCount;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLastCount(int lastCount) {
        this.lastCount = lastCount;
    }
}
