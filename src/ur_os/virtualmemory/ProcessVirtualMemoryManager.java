<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;
import ur_os.memory.paging.PageTable;

/**
 *
 * @author user
 */
public abstract class ProcessVirtualMemoryManager {
    
    ProcessVirtualMemoryManagerType type;
    
    public abstract int getVictim(LinkedList<Integer> memoryAccesses, PageTable pt);
    
    
    
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.LinkedList;
import ur_os.memory.paging.PageTable;

/**
 *
 * @author user
 */
public abstract class ProcessVirtualMemoryManager {
    
    ProcessVirtualMemoryManagerType type;
    
    public abstract int getVictim(LinkedList<Integer> memoryAccesses, PageTable pt);
    
    
    
}
>>>>>>> c122a6936448c9b70ea8a4dbf287ad02e127ea19
