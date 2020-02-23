package org.github.xhjcehust.dubbo.proxy.controller;

import org.github.xhjcehust.dubbo.proxy.api.DubboInvokerService;
import org.github.xhjcehust.dubbo.proxy.model.DubboInvokerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaohengjin<xiaohengjin@corp.netease.com>
 * @date 2019/7/26
 */
@Controller
@RequestMapping("/dubboInvoker")
public class DubboInvokerController {

    @Autowired
    private DubboInvokerService dubboInvokerService;

    private final static String SAMPLE_REQ = "{\n" +
            "    \"interfaceName\": \"org.github.xhjcehust.dubbo.provider.api.EchoService\",\n" +
            "    \"methodName\": \"echoPojo\",\n" +
            "    \"argTypes\": [\n" +
            "        \"org.github.xhjcehust.dubbo.provider.model.Pojo\"\n" +
            "    ],\n" +
            "    \"argObjects\": [\n" +
            "        {\n" +
            "            \"count\": 1,\n" +
            "            \"value\": \"val\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"version\": \"1.0\",\n" +
            "    \"group\": \"test\",\n" +
            "    \"attachments\": {\n" +
            "        \"key\": \"value\"\n" +
            "    }\n" +
            "}";

    @RequestMapping("/index")
    @ResponseBody
    public Object index(@RequestBody(required = false) DubboInvokerParam param) {
        if (param == null) {
            return SAMPLE_REQ;
        }

        String errMsg = checkFields(param);

        if (errMsg != null) {
            return errMsg;
        }

        return dubboInvokerService.invoke(param);
    }

    private String checkFields(DubboInvokerParam param) {
        if (param.getInterfaceName() == null) {
            return "interfaceName is Required, sample request = \n" + SAMPLE_REQ;
        }

        if (param.getMethodName() == null) {
            return "methodName is Required, sample request = \n" + SAMPLE_REQ;
        }

        if (param.getArgTypes() == null) {
            param.setArgTypes(new String[]{});
        }

        if (param.getArgObjects() == null) {
            param.setArgObjects(new Object[]{});
        }

        if (param.getArgTypes().length != param.getArgObjects().length) {
            return "paramTypes.length is not equal to paramArgs.length, sample request = \n" + SAMPLE_REQ;
        }

        return null;
    }

}
