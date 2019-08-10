package org.github.xhjcehust.dubbo.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(value = {"classpath:applicationContext.xml"})
@SpringBootApplication
public class DubboInvokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboInvokerApplication.class, args);
	}

}
