package org.github.xhjcehust.dubbo.proxy.serviceimpl;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.github.xhjcehust.dubbo.proxy.api.DubboInvokerService;
import org.github.xhjcehust.dubbo.proxy.model.DubboInvokerParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xiaohengjin hengjin.xhj@alibaba-inc.com
 * @date 2020/2/23
 */
@Service
public class DubboInvokerServiceImpl implements DubboInvokerService {
    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${application.invoker.name}")
    private String appName;

    @Override
    public Object invoke(DubboInvokerParam param) {

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
