package com.guan.config.motan;

import com.google.common.base.Strings;
import com.weibo.api.motan.config.ExtConfig;
import com.weibo.api.motan.config.springsupport.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConditionalOnProperty(name = "motan.enabled")
public class MotanGonfiguration {

    /** 注册中心配置bean名称 */
    private static final String REGISTRY_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-registry_";
    /** 协议配置bean名称 */
    private static final String PROTOCOL_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-protocol_";

    /**
     * define AnnotationBean
     */
    @Bean
    public AnnotationBean annotationBean(@Value("${motan.annotation.package}") String scanPackage) {
        AnnotationBean annotationBean = new AnnotationBean();
        if (!Strings.isNullOrEmpty(scanPackage)) {
            annotationBean.setPackage(scanPackage);
        }
        return annotationBean;
    }

    /**
     * define RegistryConfigBean
     */
    @Bean(name = REGISTRY_CONFIG_BEAN_NAME)
    public RegistryConfigBean registryConfig(RegistryConfigProperties registryConfig) {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setName(REGISTRY_CONFIG_BEAN_NAME);
        if (!Strings.isNullOrEmpty(registryConfig.getRegProtocol())) {
            config.setRegProtocol(registryConfig.getRegProtocol());
        }
        if (!Strings.isNullOrEmpty(registryConfig.getAddress())) {
            config.setAddress(registryConfig.getAddress());
        }
        if (registryConfig.getPort() != null) {
            config.setPort(registryConfig.getPort());
        }
        if (registryConfig.getConnectTimeout() != null) {
            config.setConnectTimeout(registryConfig.getConnectTimeout());
        }
        if (registryConfig.getRequestTimeout() != null) {
            config.setRequestTimeout(registryConfig.getRequestTimeout());
        }
        if (registryConfig.getRegistrySessionTimeout() != null) {
            config.setRegistrySessionTimeout(registryConfig.getRegistrySessionTimeout());
        }
        if (registryConfig.getRegistryRetryPeriod() != null) {
            config.setRegistryRetryPeriod(registryConfig.getRegistryRetryPeriod());
        }
        if (!Strings.isNullOrEmpty(registryConfig.getCheck())) {
            config.setCheck(registryConfig.getCheck());
        }
        if (registryConfig.getRegister() != null) {
            config.setRegister(registryConfig.getRegister());
        }
        if (registryConfig.getSubscribe() != null) {
            config.setSubscribe(registryConfig.getSubscribe());
        }
        if (registryConfig.getDefaultConfig() != null) {
            config.setDefault(registryConfig.getDefaultConfig());
        }
        return config;
    }

    /**
     * define ProtocolConfigBean
     *
     * 属性来自Motan的配置文档
     * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
     *
     * 注释的代码，在Motan0.21版本中已不支持这些属性设置，可使用表示相同意义的参数设置
     */
    @Bean(name = PROTOCOL_CONFIG_BEAN_NAME)
    public ProtocolConfigBean protocolConfig(ProtocolConfigProperties protocolConfig) {
        ProtocolConfigBean config = new ProtocolConfigBean();
        // 如果未配置，则默认设置为motan
        if (!Strings.isNullOrEmpty(protocolConfig.getName())) {
            config.setName(protocolConfig.getName());
        } else {
            config.setName("motan");
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getSerialization())) {
            config.setSerialization(protocolConfig.getSerialization());
        }
        if (protocolConfig.getIothreads() != null) {
            config.setIothreads(protocolConfig.getIothreads());
        }
        if (protocolConfig.getRequestTimeout() != null) {
            config.setRequestTimeout(protocolConfig.getRequestTimeout());
        }
        if (protocolConfig.getMinClientConnection() != null) {
            config.setMinClientConnection(protocolConfig.getMinClientConnection());
        }
        if (protocolConfig.getMaxClientConnection() != null) {
            config.setMaxClientConnection(protocolConfig.getMaxClientConnection());
        }
        if (protocolConfig.getMinWorkerThread() != null) {
            config.setMinWorkerThread(protocolConfig.getMinWorkerThread());
        }
        if (protocolConfig.getMaxClientConnection() != null) {
            config.setMaxClientConnection(protocolConfig.getMaxClientConnection());
        }
        if (protocolConfig.getMaxContentLength() != null) {
            config.setMaxContentLength(protocolConfig.getMaxContentLength());
        }
        if (protocolConfig.getMaxServerConnection() != null) {
            config.setMaxServerConnection(protocolConfig.getMaxServerConnection());
        }
        if (protocolConfig.getPoolLifo() != null) {
            config.setPoolLifo(protocolConfig.getPoolLifo());
        }
        if (protocolConfig.getLazyInit() != null) {
            config.setLazyInit(protocolConfig.getLazyInit());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getEndpointFactory())) {
            config.setEndpointFactory(protocolConfig.getEndpointFactory());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getCluster())) {
            config.setCluster(protocolConfig.getCluster());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getLoadbalance())) {
            config.setLoadbalance(protocolConfig.getLoadbalance());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getHaStrategy())) {
            config.setHaStrategy(protocolConfig.getHaStrategy());
        }
        if (protocolConfig.getWorkerQueueSize() != null) {
            config.setWorkerQueueSize(protocolConfig.getWorkerQueueSize());
        }
        if (protocolConfig.getAcceptConnections() != null) {
            config.setAcceptConnections(protocolConfig.getAcceptConnections());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getProxy())) {
            config.setProxy(protocolConfig.getProxy());
        }
        if (!Strings.isNullOrEmpty(protocolConfig.getFilter())) {
            config.setFilter(protocolConfig.getFilter());
        }
        if (protocolConfig.getRetries() != null) {
            config.setRetries(protocolConfig.getRetries());
        }
        if (protocolConfig.getAsync() != null) {
            config.setAsync(protocolConfig.getAsync());
        }
//        config.setqueuesize
//        config.setaccepts
//        config.setdispatcher
//        config.setserver
//        config.setclient
        if (protocolConfig.getDefaultConfig() != null) {
            config.setDefault(protocolConfig.getDefaultConfig());
        }
//        config.setswitcherservice
//        config.sethearBeatFactory

        //增加最大最小线程数设置
        if(protocolConfig.getMaxWorkerThread() != null){
            config.setMaxWorkerThread(protocolConfig.getMaxWorkerThread());
        }
        if(protocolConfig.getMinWorkerThread() != null){
            config.setMinWorkerThread(protocolConfig.getMinWorkerThread());
        }

        return config;
    }

    /**
     * define BasicServiceConfigBean
     *
     * 属性来自Motan的配置文档
     * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
     *
     * 挑了一些属性，不全，后续补全
     */
    @Bean
    public BasicServiceConfigBean baseServiceConfig(ServiceConfigProperties basicServiceConfig, RegistryConfigBean registryConfigBean) {
        BasicServiceConfigBean config = new BasicServiceConfigBean();

        if (!Strings.isNullOrEmpty(basicServiceConfig.getExport())) {
            config.setExport(basicServiceConfig.getExport());
        } else {
            // 未设置export，使用ProtocolConfigBeanName : port暴露
            if (Strings.isNullOrEmpty(basicServiceConfig.getExportPort())) {
                throw new RuntimeException("need service export port...");
            }
            config.setExport(PROTOCOL_CONFIG_BEAN_NAME + ":" + basicServiceConfig.getExportPort());
        }

        if (!Strings.isNullOrEmpty(basicServiceConfig.getExtConfigId())) {
            ExtConfig extConfig = new ExtConfig();
            extConfig.setId(basicServiceConfig.getExtConfigId());
            config.setExtConfig(extConfig);
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getProxy())) {
            config.setProxy(basicServiceConfig.getProxy());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getGroup())) {
            config.setGroup(basicServiceConfig.getGroup());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getVersion())) {
            config.setVersion(basicServiceConfig.getVersion());
        }
        if (Objects.nonNull(basicServiceConfig.getThrowException())) {
            config.setThrowException(basicServiceConfig.getThrowException());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getApplication())) {
            config.setApplication(basicServiceConfig.getApplication());
        }
        if (Objects.nonNull(basicServiceConfig.getShareChannel())) {
            config.setShareChannel(basicServiceConfig.getShareChannel());
        }
        if (basicServiceConfig.getAsync() != null) {
        	config.setAsync(basicServiceConfig.getAsync());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getRegistry())) {
            // 追加内部的注册配置bean
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicServiceConfig.getRegistry());
        } else {
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
        }
        if (Objects.nonNull(basicServiceConfig.getAccessLog())) {
            config.setAccessLog(basicServiceConfig.getAccessLog());
        }
        if (Objects.nonNull(basicServiceConfig.getUsegz())) {
            config.setUsegz(basicServiceConfig.getUsegz());
        }
        if (Objects.nonNull(basicServiceConfig.getMingzSize())) {
            config.setMingzSize(basicServiceConfig.getMingzSize());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getCodec())) {
            config.setCodec(basicServiceConfig.getCodec());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getFilter())) {
            config.setFilter(basicServiceConfig.getFilter());
        }
        if (!Strings.isNullOrEmpty(basicServiceConfig.getModule())) {
            config.setModule(basicServiceConfig.getModule());
        }
        if (basicServiceConfig.getActives() != null) {
            config.setActives(basicServiceConfig.getActives());
        }
        if (basicServiceConfig.getRegister() != null) {
            config.setRegister(basicServiceConfig.getRegister());
        }

        return config;
    }

    /**
     * define BasicRefererConfigBean
     *
     * 属性来自Motan的配置文档
     * @see https://github.com/weibocom/motan/blob/master/docs/wiki/zh_configuration.md
     *
     * 挑了一些属性，不全，后续补全
     */
    @Bean
    public BasicRefererConfigBean baseRefererConfig(RefererConfigProperties basicRefererConfig) {
        BasicRefererConfigBean config = new BasicRefererConfigBean();

        config.setProtocol(PROTOCOL_CONFIG_BEAN_NAME);
        if (!Strings.isNullOrEmpty(basicRefererConfig.getGroup())) {
            config.setGroup(basicRefererConfig.getGroup());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getModule())) {
            config.setModule(basicRefererConfig.getModule());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getApplication())) {
            config.setApplication(basicRefererConfig.getApplication());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getRegistry())) {
            // 追加内部的注册配置bean
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicRefererConfig.getRegistry());
        } else {
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getCheck())) {
            config.setCheck(basicRefererConfig.getCheck());
        }
        if (basicRefererConfig.getAccessLog() != null) {
            config.setAccessLog(basicRefererConfig.getAccessLog());
        }
        if (basicRefererConfig.getRetries() != null) {
            config.setRetries(basicRefererConfig.getRetries());
        }
        if (basicRefererConfig.getThrowException() != null) {
            config.setThrowException(basicRefererConfig.getThrowException());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getId())) {
            config.setId(basicRefererConfig.getId());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getVersion())) {
            config.setVersion(basicRefererConfig.getVersion());
        }
        if (basicRefererConfig.getShareChannel() != null) {
            config.setShareChannel(basicRefererConfig.getShareChannel());
        }
        if (basicRefererConfig.getRequestTimeout() != null) {
            config.setRequestTimeout(basicRefererConfig.getRequestTimeout());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getFilter())) {
            config.setFilter(basicRefererConfig.getFilter());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getExtConfigId())) {
            ExtConfig extConfig = new ExtConfig();
            extConfig.setId(basicRefererConfig.getExtConfigId());
            config.setExtConfig(extConfig);
        }
        if (basicRefererConfig.getActives() != null) {
            config.setActives(basicRefererConfig.getActives());
        }
        if (basicRefererConfig.getAsync() != null) {
        	config.setAsync(basicRefererConfig.getAsync());
        }
        if (basicRefererConfig.getAsync() != null) {
            config.setAsync(basicRefererConfig.getAsync());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getCodec())) {
            config.setCodec(basicRefererConfig.getCodec());
        }
        if (basicRefererConfig.getUsegz() != null) {
            config.setUsegz(basicRefererConfig.getUsegz());
        }
        if (basicRefererConfig.getMingzSize() != null) {
            config.setMingzSize(basicRefererConfig.getMingzSize());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getProxy())) {
            config.setProxy(basicRefererConfig.getProxy());
        }
        if (!Strings.isNullOrEmpty(basicRefererConfig.getMock())) {
            config.setMock(basicRefererConfig.getMock());
        }
        return config;
    }
}
