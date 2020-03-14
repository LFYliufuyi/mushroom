package com.yiquanxinhe.website.interceptor;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import org.springframework.stereotype.Component;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:38
 * @Description:
 */
@Component
public class CustomInstrumentation  extends SimpleInstrumentation {

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
        return new SimpleInstrumentationContext<>();
    }
}


