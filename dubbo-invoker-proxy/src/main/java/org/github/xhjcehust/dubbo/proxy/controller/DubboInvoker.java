package org.github.xhjcehust.dubbo.proxy.controller;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.github.xhjcehust.dubbo.proxy.model.DubboInvokerParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * @author xiaohengjin<xiaohengjin@corp.netease.com>
 * @date 2019/7/26
 */
@Controller
@RequestMapping("/dubboInvoker")
public class DubboInvoker {

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${application.invoker.name}")
    private String appName;

    @RequestMapping("/index")
    @ResponseBody
    public Object index(@RequestBody(required = false) DubboInvokerParam param) {
        if (param == null) {
            return "{\n" +
                    "\t\"interfaceName\": \"org.github.xhjcehust.dubbo.provider.api.EchoService\",\n" +
                    "\t\"methodName\": \"echoString\",\n" +
                    "\t\"argTypes\": [\n" +
                    "\t\t\"java.lang.String\"\n" +
                    "\t],\n" +
                    "\t\"argObjects\": [\n" +
                    "      \"abcd\"\n" +
                    "\t],\n" +
                    "  \"version\": \"1.0\",\n" +
                    "  \"group\": \"test\",\n" +
                    "  \"attachments\": {\n" +
                    "  \t\"key\": \"value\"\n" +
                    "  }\n" +
                    "}";
        }

        String errMsg = checkFields(param);

        if (errMsg != null) {
            return errMsg;
        }

        return doInvoke(param);
    }

    private String checkFields(DubboInvokerParam param) {
        if (param.getInterfaceName() == null) {
            return "interfaceName is Required";
        }

        if (param.getMethodName() == null) {
            return "methodName is Required";
        }

        if (param.getArgTypes() == null) {
            param.setArgTypes(new String[]{});
        }

        if (param.getArgObjects() == null) {
            param.setArgObjects(new Object[]{});
        }

        if (param.getArgTypes().length != param.getArgObjects().length) {
            return "paramTypes.length is not equal to paramArgs.length";
        }

        return null;
    }


    private Object doInvoke(DubboInvokerParam param) {

        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(new ApplicationConfig(appName));

        reference.setInterface(param.getInterfaceName());
        reference.setVersion(param.getVersion());
        reference.setGeneric(true);
        reference.setGroup(param.getGroup());

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(registryAddress);
        reference.setRegistry(registry);

        GenericService genericService = reference.get();

        Map<String, String> attachments = param.getAttachments();
        if (attachments != null) {
            RpcContext.getContext().setAttachments(attachments);
        }
        return genericService.$invoke(param.getMethodName(), param.getArgTypes(), param.getArgObjects());
    }
}
