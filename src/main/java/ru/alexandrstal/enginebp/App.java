package ru.alexandrstal.enginebp;

import org.activiti.engine.task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(App.class, args);

        BPController bpController = ac.getBean(BPController.class);

        Map<String, Object> model = new HashMap<>();

        String id = bpController.startProcess(model);

        System.out.println("start_process " + id);
        List<Task> list = bpController.tasks(id);
        System.out.println("Доступные для процесса таски: " + list);

        String exid = list.get(0).getId();
        System.out.println("Выполняю таск: " + exid);


        bpController.completeTask(exid, model);
        list = bpController.tasks(id);
        System.out.println("Доступные для процесса таски:  " + list);



    }

    @Bean
    public BPController bpController() {
        return new BPController();
    }

}
