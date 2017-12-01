package com.guan;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.util.Arrays;

@ComponentScan(basePackages={"com.guan"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GuanMotanClientApplication {
	private static final Logger log = LoggerFactory.getLogger(GuanMotanClientApplication.class);

	@Inject
	private Environment env;

	public static void main(String[] args) throws UnknownHostException {
		try {
			if(args!=null && "debug".equals(args[0])){
				String filePath="C:\\classloader\\";
				ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
				if(classLoader instanceof org.springframework.boot.devtools.restart.classloader.RestartClassLoader){
					System.out.println("::::::::"+classLoader);
					String jarNameStr="motan-core-1.0.0.jar,motan-transport-netty-1.0.0.jar,"
							+ "motan-registry-consul-1.0.0.jar,motan-registry-zookeeper-1.0.0.jar,motan-springsupport-1.0.0.jar";
					Method add =URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
					add.setAccessible(true);
					for(String jarName:jarNameStr.split(",")){
						File file=new File(filePath+jarName);
						if(!file.exists()){
							continue;
						}
						URL url= file.toURI().toURL();//将File类型转为URL类型，file为jar包路径
						//得到系统类加载器
						//					URLClassLoader urlClassLoader= (URLClassLoader) classLoader;
						//因为URLClassLoader中的addURL方法的权限为protected所以只能采用反射的方法调用addURL方法
						System.out.println("customer load "+jarName+":"+add.invoke(classLoader, new Object[] {url}));
					}

					Class<?> clazz = Class.forName("com.weibo.api.motan.proxy.ProxyFactory", true, classLoader);
					System.out.println("::::::::"+clazz.getClassLoader());

					clazz = Class.forName("com.weibo.api.motan.util.ByteUtil", true, classLoader);
					System.out.println("::::::::"+clazz.getClassLoader());


					clazz = Class.forName("com.guan.rpc.service.GuanService", true, classLoader);
					System.out.println("::::::::"+clazz.getClassLoader());

					clazz = Class.forName("com.guan.rpc.service.JiaService", true, classLoader);
					System.out.println("::::::::"+clazz.getClassLoader());

					System.out.println("::::::::"+classLoader);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		//SpringApplication.run(GuanMotanClientApplication.class, args);
		SpringApplication app = new SpringApplication(GuanMotanClientApplication.class);
		Environment env = app.run(args).getEnvironment();
		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://127.0.0.1:{}\n\t" +
						"External: \thttp://{}:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				Arrays.toString(env.getActiveProfiles()));
		System.out.println("server start...");
	}
}
