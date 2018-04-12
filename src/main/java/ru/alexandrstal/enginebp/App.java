package ru.alexandrstal.enginebp;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        ProcessEngineFactoryBean processEngineFactoryBean = ctx.getBean(ProcessEngineFactoryBean.class);
        ProcessEngine processEngine = processEngineFactoryBean.getObject();

        Map processModel = new HashMap();
        ProcessInstance processInstance =  processEngine.getRuntimeService().startProcessInstanceByKey("helloWorld", processModel);
        processEngine.getRuntimeService().setVariable(processInstance.getId(),"currentStatus", 1000);

        /*
        ProcessInstance processInstance =   processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId("10001").singleResult();
        System.out.println(processEngine.getRuntimeService().getVariable("10001","currentStatus"));
*/
    }


    @Bean
    public BPController bpController() {
        return new BPController();
    }

}
