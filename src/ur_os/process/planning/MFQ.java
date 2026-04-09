/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.process.planning;

import java.util.ArrayList;
import java.util.Arrays;
import ur_os.process.Process;
import ur_os.process.ProcessState;
import ur_os.system.OS;

/**
 *
 * @author prestamour
 */
public class MFQ extends Scheduler{

    int currentScheduler;
    
    private ArrayList<Scheduler> schedulers;
    //This may be a suggestion... you may use the current sschedulers to create the Multilevel Feedback Queue, or you may go with a more tradicional way
    //based on implementing all the queues in this class... it is your choice. Change all you need in this class.
    
    MFQ(OS os){
        super(os);
        currentScheduler = -1;
        schedulers = new ArrayList();
    }
    
    MFQ(OS os, Scheduler... s){ //Received multiple arrays
        this(os);
        schedulers.addAll(Arrays.asList(s));
        if(s.length > 0)
            currentScheduler = 0;
    }
        
    @Override
    public void addProcess(Process p){
       //Overwriting the parent's addProcess(Process p) method may be necessary in order to decide what to do with process coming from the CPU.
        if (!schedulers.isEmpty()) {
            if (p.getState() == ProcessState.NEW || p.getState() == ProcessState.IO) { 
                p.setPriority(0); // Procesos nuevos o que regresan de I/O van a la primera cola
            } else if (p.getPriority() < schedulers.size() - 1) { 
                p.setPriority(p.getPriority() + 1); // Mover a la siguiente cola si no está en la última
            }
            schedulers.get(p.getPriority()).addProcess(p);
        }
    }
    
    void defineCurrentScheduler(){
        //This methos is siggested to help you find the scheduler that should be the next in line to provide processes... perhaps the one with process in the queue?
        
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {
        boolean processFound = false;

        for (int i = 0; i < schedulers.size(); i++) {
            Scheduler s = schedulers.get(i);

            if (!os.isCPUEmpty()) { 
                Process cpuProcess = os.getProcessInCPU();

                if (cpuProcess.getPriority() == i) { 
                    s.getNext(os.isCPUEmpty());

                    if (os.isCPUEmpty()) { 
                        addProcess(cpuProcess); 
                        i = -1;
                        continue;
                    }
                    processFound = true;
                    break;
                }
            }

            if (!s.isEmpty() && cpuEmpty) { 
                s.getNext(os.isCPUEmpty());
                processFound = true;
                break;
            }
        }
        
    }
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}
