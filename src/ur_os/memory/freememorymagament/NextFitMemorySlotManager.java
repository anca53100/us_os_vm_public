package ur_os.memory.freememorymagament;

/**
 * Next Fit Memory Slot Manager
 * 
 * Starts searching for a free slot from the position where the last
 * allocation was made, wrapping around to the beginning if necessary.
 * 
 * Advantage over First Fit: avoids exhausting slots at the beginning
 * of memory, distributing usage more uniformly.
 */
public class NextFitMemorySlotManager extends FreeMemorySlotManager {

    private int lastIndex;

    public NextFitMemorySlotManager(int memSize) {
        super(memSize);
        lastIndex = 0;
    }

    @Override
    public MemorySlot getSlot(int size) {

        int total = list.size();

        if (total == 0) {
            System.out.println("Error - No free memory slots available");
            return null;
        }

        if (lastIndex >= total) {
            lastIndex = 0;
        }

        for (int offset = 0; offset < total; offset++) {
            int i = (lastIndex + offset) % total;
            MemorySlot memorySlot = list.get(i);

            if (memorySlot.canContain(size)) {
                lastIndex = i;

                if (memorySlot.getSize() == size) {
                    list.remove(i);
                    if (lastIndex >= list.size()) {
                        lastIndex = 0;
                    }
                    return memorySlot;
                } else {
                    return memorySlot.assignMemory(size);
                }
            }
        }

        System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
        return null;
    }
}