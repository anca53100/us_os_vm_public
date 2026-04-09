package ur_os.memory.freememorymagament;

/**
 * FragmentationCalculator
 * 
 * Provides static methods to calculate memory fragmentation.
 * 
 * - EXTERNAL FRAGMENTATION: free memory that exists but is split into
 *   small non-contiguous slots, making it unusable for a large request.
 * 
 * - INTERNAL FRAGMENTATION: wasted space inside an assigned slot when
 *   the process is smaller than the slot given to it.
 */
public class FragmentationCalculator {

    /**
     * Returns the total amount of free memory across all slots.
     */
    public static int getTotalFreeMemory(FreeMemorySlotManager manager) {
        int total = 0;
        for (MemorySlot slot : manager.list) {
            total += slot.getSize();
        }
        return total;
    }

    /**
     * Returns the size of the largest single free slot.
     */
    public static int getLargestFreeSlot(FreeMemorySlotManager manager) {
        int largest = 0;
        for (MemorySlot slot : manager.list) {
            if (slot.getSize() > largest) {
                largest = slot.getSize();
            }
        }
        return largest;
    }

    /**
     * Calculates external fragmentation as a percentage.
     * Formula: ((totalFree - largestSlot) / totalFree) * 100
     */
    public static double getExternalFragmentation(FreeMemorySlotManager manager) {
        int totalFree = getTotalFreeMemory(manager);
        if (totalFree == 0) {
            return 0.0;
        }
        int largestSlot = getLargestFreeSlot(manager);
        return ((double)(totalFree - largestSlot) / totalFree) * 100.0;
    }

    /**
     * Calculates internal fragmentation for a given allocation.
     * Formula: allocatedSize - requestedSize
     */
    public static int getInternalFragmentation(int allocatedSize, int requestedSize) {
        if (allocatedSize < requestedSize) {
            return 0;
        }
        return allocatedSize - requestedSize;
    }

    /**
     * Prints a full fragmentation report to standard output.
     */
    public static void printFragmentationReport(FreeMemorySlotManager manager,
                                                int allocatedSize,
                                                int requestedSize) {
        int totalFree   = getTotalFreeMemory(manager);
        int largestSlot = getLargestFreeSlot(manager);
        double external = getExternalFragmentation(manager);
        int    internal = getInternalFragmentation(allocatedSize, requestedSize);

        System.out.println("========== Fragmentation Report ==========");
        System.out.println("Number of free slots  : " + manager.getSize());
        System.out.println("Total free memory     : " + totalFree + " bytes");
        System.out.println("Largest free slot     : " + largestSlot + " bytes");
        System.out.printf ("External fragmentation: %.2f%%%n", external);
        System.out.println("Allocated size        : " + allocatedSize + " bytes");
        System.out.println("Requested size        : " + requestedSize + " bytes");
        System.out.println("Internal fragmentation: " + internal + " bytes");
        System.out.println("==========================================");
    }
}