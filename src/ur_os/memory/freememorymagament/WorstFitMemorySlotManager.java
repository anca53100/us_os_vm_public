/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class WorstFitMemorySlotManager extends FreeMemorySlotManager {

    public WorstFitMemorySlotManager(int memSize) {
        super(memSize);
    }

    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot worstSlot = null;

        // Recorre toda la lista buscando el slot más grande disponible
        for (MemorySlot memorySlot : list) {
            if (memorySlot.canContain(size)) {
                if (worstSlot == null || memorySlot.getSize() > worstSlot.getSize()) {
                    worstSlot = memorySlot;
                }
            }
        }

        if (worstSlot == null) {
            System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
            return null;
        }

        if (worstSlot.getSize() == size) {
            list.remove(worstSlot);
            return worstSlot;
        } else {
            return worstSlot.assignMemory(size);
        }
    }
}