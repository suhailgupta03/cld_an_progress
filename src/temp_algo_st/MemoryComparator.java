
package temp_algo_st;

import java.util.Comparator;

/**
 *
 * @author user
 */
public class MemoryComparator implements Comparator<DepConfAttr>  {

    @Override
    public int compare(DepConfAttr o1, DepConfAttr o2) {
        return o2.getMemory() - o1.getMemory();
    }
}
