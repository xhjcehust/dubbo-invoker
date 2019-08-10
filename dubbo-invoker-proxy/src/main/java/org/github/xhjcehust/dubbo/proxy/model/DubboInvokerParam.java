package org.github.xhjcehust.dubbo.proxy.model;

import java.util.Map;

/**
 * @author xiaohengjin<xiaohengjin@corp.netease.com>
 * @date 2019/7/31
 */
public class DubboInvokerParam {

    private String interfaceName;

    private String methodName;

    private String group;

    private String version;

    private String[] argTypes;

    private Object[] argObjects;

    private Map<String, String> attachments;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(String[] argTypes) {
        this.argTypes = argTypes;
    }

    public Object[] getArgObjects() {
        return argObjects;
    }

    public void setArgObjects(Object[] argObjects) {
        this.argObjects = argObjects;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }
}
