package com.gsd;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Part3_ProcessInstance {
    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void initProcessInstance() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_task1", "bkey001");
        System.out.println("流程实例Id:" + processInstance.getProcessDefinitionId());
    }

    @Test
    public void getProcessInstances() {
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance pi: processInstances) {
            System.out.println("-------------流程实例-------------");
            System.out.println("ProcessInstanceId:" + pi.getProcessInstanceId());
            System.out.println("ProcessDefinitionId:" + pi.getProcessDefinitionId());
            System.out.println("isEnded:" + pi.isEnded());
            System.out.println("isSuspended:" + pi.isSuspended());
        }
    }

    @Test
    public void activeProcessInstance() {
        /*runtimeService.suspendProcessInstanceById("c1b99a7a-4321-11eb-b8c8-e4029beb2459");
        System.out.println("挂起流程实例");*/
        runtimeService.activateProcessInstanceById("c1b99a7a-4321-11eb-b8c8-e4029beb2459");
        System.out.println("激活流程实例");
    }

    @Test
    public void delProcessInstance() {
        runtimeService.deleteProcessInstance("c1b99a7a-4321-11eb-b8c8-e4029beb2459", "删着玩");
        System.out.println("删除流程实例");
    }
}
