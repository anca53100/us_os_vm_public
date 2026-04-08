/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.process.planning;

import ur_os.process.Process;
import ur_os.system.InterruptType;
import ur_os.system.OS;

/**
 *
 * @author prestamour
 */
public class SJF_NP extends Scheduler{

    
    SJF_NP(OS os){
        super(os);
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {
       if (cpuEmpty && !processes.isEmpty()) {
            // Buscar directamente el proceso con menor ráfaga usando un recorrido
            Process shortest = null;
            for (Process p : processes) {
                if (shortest == null) {
                    shortest = p;
                } else if (p.getRemainingTimeInCurrentBurst() < shortest.getRemainingTimeInCurrentBurst()) {
                    shortest = p;
                } else if (p.getRemainingTimeInCurrentBurst() == shortest.getRemainingTimeInCurrentBurst()) {
                    shortest = tieBreaker(shortest, p); // desempate
                }
            }

            // Si encontramos uno, lo quitamos y lo mandamos a CPU
            if (shortest != null) {
                processes.remove(shortest);
                os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, shortest);
            }
        }
        
    }
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive
    
}
