package com.gsd;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Part1_Deployment {
    @Autowired
    private RepositoryService repositoryService;
    @Test
    public void initDeploymentBpmn() {
        String fileName = "BPMN/part1_deployment.bpmn";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(fileName)
                .name("流程部署测试bpmn")
                .deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void initDeploymentBpmnZip() {
        InputStream fileInputStrom = this.getClass().getClassLoader().getResourceAsStream("BPMN/part1_deployment_v2.zip");
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStrom);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("流程部署测试bpmnV2")
                .deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void getDeployments() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment de: list) {
            System.out.println("id:" + de.getId());
            System.out.println("name:" + de.getName());
            System.out.println("key:" + de.getKey());
            System.out.println("time:" + de.getDeploymentTime());
        }
    }
}
