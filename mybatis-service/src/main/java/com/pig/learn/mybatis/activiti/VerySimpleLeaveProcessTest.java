package com.pig.learn.mybatis.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class VerySimpleLeaveProcessTest {
    public static void main(String[] args) {

       ProcessEngine engine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();



        RepositoryService repositoryService = engine.getRepositoryService();

        repositoryService.createDeployment().addClasspathResource("diagrams/leave/leave.bpmn").deploy();


        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();


        System.out.println(processDefinition.getKey());

        RuntimeService runtimeService = engine.getRuntimeService();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");

        System.out.println("pid="+processInstance.getId()+",pdid="+processInstance.getProcessDefinitionId());

    }
}
