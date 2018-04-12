package ru.alexandrstal.enginebp.service;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.StateMachineInitializer;

public class EngineService {

    @Autowired
    private StateMachineInitializer stateMachineInitializer;

    private ProcessEngine processEngine;

    public String getCurrentStatusForProcess(String procId){

        ProcessInstance processInstance =   processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(procId).singleResult();

        StateMachine stateMachine = stateMachineInitializer.initMachine(processEngine.getRuntimeService(),procId);

        return stateMachine.getState().toString();

    };

    public void setStateMachineInitializer(StateMachineInitializer stateMachineInitializer) {
        this.stateMachineInitializer = stateMachineInitializer;
    }

    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    public StateMachineInitializer getStateMachineInitializer() {
        return stateMachineInitializer;
    }
}
