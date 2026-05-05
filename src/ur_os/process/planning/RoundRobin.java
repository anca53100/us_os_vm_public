<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.process.planning;

import ur_os.process.Process;
import ur_os.process.ProcessState;
import ur_os.system.InterruptType;
import ur_os.system.OS;

/**
 *
 * @author prestamour
 */
public class RoundRobin extends Scheduler{

    int q;
    int cont;
    boolean autoload; //Parameter useful for multischedulers algorithms, like Priority and MFQ
    
    RoundRobin(OS os){
        super(os);
        q = 5;
        cont=0;
        autoload = false;
    }
    
    RoundRobin(OS os, int q){
        this(os);
        this.q = q;
    }
    
    RoundRobin(OS os, int q, boolean autoload){
        this(os,q);
        this.autoload = autoload;
    }
    

    
    void resetCounter(){
        cont=0;
    }
   
    @Override
    public void getNext(boolean cpuEmpty) {
        if (cpuEmpty) {
            if (!processes.isEmpty()) {
                Process p = processes.remove(0);
                p.setState(ProcessState.CPU); // o RUNNING según tu enum
                os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, p);
                resetCounter();
            }
            return;
        }

        // 2) CPU ocupada -> avanzamos contador de quantum
        cont++;

        // 3) Sólo preemptar si el quantum venció Y hay otro proceso esperando
        if (cont >= q && !processes.isEmpty()) {
            Process current = os.getProcessInCPU();
            if (current != null) {
                // Informamos al OS que el proceso en CPU debe volver a la cola de listos
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, current);
                // NOTA: NO añadir 'current' a `processes` aquí si el OS ya lo reencola.
                // Si tu OS NO reencola al recibir el interrupt, entonces descomenta la línea siguiente:
                // processes.add(current);
            }

            // Despachamos el siguiente en la cola
            Process next = processes.remove(0);
            next.setState(ProcessState.CPU);
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, next);

            resetCounter();
        }
    }
    
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.process.planning;

import ur_os.process.Process;
import ur_os.process.ProcessState;
import ur_os.system.InterruptType;
import ur_os.system.OS;

/**
 *
 * @author prestamour
 */
public class RoundRobin extends Scheduler{

    int q;
    int cont;
    boolean autoload; //Parameter useful for multischedulers algorithms, like Priority and MFQ
    
    RoundRobin(OS os){
        super(os);
        q = 5;
        cont=0;
        autoload = false;
    }
    
    RoundRobin(OS os, int q){
        this(os);
        this.q = q;
    }
    
    RoundRobin(OS os, int q, boolean autoload){
        this(os,q);
        this.autoload = autoload;
    }
    

    
    void resetCounter(){
        cont=0;
    }
   
    @Override
    public void getNext(boolean cpuEmpty) {
        if (cpuEmpty) {
            if (!processes.isEmpty()) {
                Process p = processes.remove(0);
                p.setState(ProcessState.CPU); // o RUNNING según tu enum
                os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, p);
                resetCounter();
            }
            return;
        }

        // 2) CPU ocupada -> avanzamos contador de quantum
        cont++;

        // 3) Sólo preemptar si el quantum venció Y hay otro proceso esperando
        if (cont >= q && !processes.isEmpty()) {
            Process current = os.getProcessInCPU();
            if (current != null) {
                // Informamos al OS que el proceso en CPU debe volver a la cola de listos
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, current);
                // NOTA: NO añadir 'current' a `processes` aquí si el OS ya lo reencola.
                // Si tu OS NO reencola al recibir el interrupt, entonces descomenta la línea siguiente:
                // processes.add(current);
            }

            // Despachamos el siguiente en la cola
            Process next = processes.remove(0);
            next.setState(ProcessState.CPU);
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, next);

            resetCounter();
        }
    }
    
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}
>>>>>>> c122a6936448c9b70ea8a4dbf287ad02e127ea19
