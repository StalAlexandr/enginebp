package ru.alexandrstal.enginebp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alexandrstal.enginebp.service.EngineService;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        EngineService service = ctx.getBean(EngineService.class);

        System.out.println(service.getCurrentStatusForProcess("10001"));


       // System.out.println ("init " + service.getStateMachineInitializer());
       //  ProcessEngineFactoryBean processEngineFactoryBean = ctx.getBean(ProcessEngineFactoryBean.class);
       //  ProcessEngine processEngine = processEngineFactoryBean.getObject();
        /*
        Map processModel = new HashMap();
        ProcessInstance processInstance =  processEngine.getRuntimeService().startProcessInstanceByKey("helloWorld", processModel);
        processEngine.getRuntimeService().setVariable(processInstance.getId(),"currentStatus", 1000);

        StateMachineObjectSupport stateMachineObjectSupport;
*/
       /*
        ProcessInstance processInstance =   processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId("10001").singleResult();
        System.out.println(processEngine.getRuntimeService().getVariable("10001","currentStatus"));
*/
    }


}
