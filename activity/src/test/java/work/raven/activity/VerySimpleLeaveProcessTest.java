package work.raven.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.ArrayEquals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 * date: 2023/5/10 10:39
 *
 * @author raven
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerySimpleLeaveProcessTest {

    @Test
    public void testStartProcess(){
        // 创建流程引擎，使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();

        // 部署流程定义文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("xxxxxx").deploy();

        // 验证已部署流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        Assert.assertEquals("helloworld", processDefinition.getKey());

        // 启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloworld");
        Assert.assertNotNull(processInstance);
        System.out.println("pid = " + processInstance.getId() + " , pdod=" + processInstance.getProcessDefinitionId());
    }
}