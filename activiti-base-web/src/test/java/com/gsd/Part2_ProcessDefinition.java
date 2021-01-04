package com.gsd;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
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
public class Part2_ProcessDefinition {
    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void getDefinitions() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .list();
        for (ProcessDefinition pd: list) {
            System.out.println("-----------------新的流程定义----------------------");
            System.out.println("name:" + pd.getName());
            System.out.println("key:" + pd.getKey());
            System.out.println("resourceName:" + pd.getResourceName());
            System.out.println("deploymentId:" + pd.getDeploymentId());
            System.out.println("version:" + pd.getVersion());
        }
    }

    @Test
    public void delDefinition() {
        String pdId = "e6666b99-38e5-11eb-a9cb-e4029beb2459";
        repositoryService.deleteDeployment(pdId, false);
        System.out.println("删除流程定义成功");
    }
}
