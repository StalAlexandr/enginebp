package ru.alexandrstal.enginebp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Calendar;

public class MyServiceTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("MyServiceTask!!!!");
        Long someData = Calendar.getInstance().getTimeInMillis() % 2;
        delegateExecution.setVariable("someData", someData);
    }
}
