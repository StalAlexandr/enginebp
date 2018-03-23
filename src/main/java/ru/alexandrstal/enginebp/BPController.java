package ru.alexandrstal.enginebp;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BPController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    public String startProcess(Map<String, Object> processModel){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorld", processModel);

        return processInstance.getId();
    }

    public List<Task> tasks(String id){
        return taskService.createTaskQuery()
                .processInstanceId(id)
                .orderByTaskName().desc()
                .list();
    }


    public void completeTask( String taskId,  Map<String, Object> model){
        taskService.complete(taskId, model);
    }


}
