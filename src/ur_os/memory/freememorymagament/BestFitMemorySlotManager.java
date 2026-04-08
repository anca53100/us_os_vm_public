/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class BestFitMemorySlotManager extends FreeMemorySlotManager{
    
    public BestFitMemorySlotManager(int memSize) {
        super(memSize);
    }

    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot bestSlot = null;

        // Recorre toda la lista buscando el slot más pequeño que contenga el proceso
        for (MemorySlot memorySlot : list) {
            if (memorySlot.canContain(size)) {
                if (bestSlot == null || memorySlot.getSize() < bestSlot.getSize()) {
                    bestSlot = memorySlot;
                }
            }
        }

        if (bestSlot == null) {
            System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
            return null;
        }

        // Si el slot es exactamente del tamaño pedido, removerlo de la lista
        if (bestSlot.getSize() == size) {
            list.remove(bestSlot);
            return bestSlot;
        } else {
            // Si es más grande, recortarlo y retornar solo la parte necesaria
            return bestSlot.assignMemory(size);
        }
    }
    
}
