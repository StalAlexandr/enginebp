package org.springframework.statemachine.support;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;
import ru.alexandrstal.enginebp.exception.BPMNEngineException;

/**
 * Инициализирует стейт-машину для процесса исходя из текущего статуса заявки
 */

@Service
public class StateMachineInitializer {

    // ключи переменных процесса - статус заявки
    public static final String APPLICATION_STATUS = "APPLICATION_STATUS";

    // ключи переменных процесса - стейт-машина
    public static final String STATEMACHINE = "STATEMACHINE";


    @Autowired
    private StateMachineFactory<String, String> stateMachineFactory;

    public StateMachine initMachine(RuntimeService runtimeService, String processId){
        if (runtimeService.getVariable(processId, STATEMACHINE)!=null){
            return (StateMachine) runtimeService.getVariable(processId, STATEMACHINE);
        };
        String currentStatus = (String)runtimeService.getVariable(processId, APPLICATION_STATUS);

        if (currentStatus==null){
            throw new BPMNEngineException("can't get app status!");
        }

        StateMachine stateMachine = stateMachineFactory.getStateMachine();

        if (!(stateMachine instanceof AbstractStateMachine)){
            throw new BPMNEngineException("state machine illegial instance!");
        }

        State<String, String> state = findState(stateMachine, currentStatus);

        // protected метод!
       ((AbstractStateMachine) stateMachine).setCurrentState(state,null, null, false, stateMachine);

        runtimeService.setVariable(processId, STATEMACHINE, stateMachine);

        return stateMachine;

    }

    private State<String, String> findState(StateMachine<String,String> stateMachine, String status) {
        for (State<String, String> state : stateMachine.getStates()) {
            if (state.getId() == status) {
                return state;
            }
        }
        throw new BPMNEngineException("Specified status does not equate to valid State");
    }

    public void setStateMachineFactory(StateMachineFactory<String, String> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }
}
