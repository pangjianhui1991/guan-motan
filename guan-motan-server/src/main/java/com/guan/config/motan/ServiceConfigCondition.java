package com.guan.config.motan;

import com.google.common.base.Strings;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by zhangjingl02 on 2016/11/17.
 */
public class ServiceConfigCondition  implements Condition {

    /**
     * match [motan.basicservice.exportPort, motan.basicservice.export] config property
     *
     * @see Condition#matches(ConditionContext, AnnotatedTypeMetadata)
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return (!Strings.isNullOrEmpty(env.getProperty("motan.service.exportPort"))
                || !Strings.isNullOrEmpty(env.getProperty("motan.service.export")));
    }

}
