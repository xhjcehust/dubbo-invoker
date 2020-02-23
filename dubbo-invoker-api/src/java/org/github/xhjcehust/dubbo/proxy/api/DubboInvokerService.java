package org.github.xhjcehust.dubbo.proxy.api;

import org.github.xhjcehust.dubbo.proxy.model.DubboInvokerParam;

/**
 * @author xiaohengjin hengjin.xhj@alibaba-inc.com
 * @date 2020/2/23
 */
public interface DubboInvokerService {

    Object invoke(DubboInvokerParam param);
}
