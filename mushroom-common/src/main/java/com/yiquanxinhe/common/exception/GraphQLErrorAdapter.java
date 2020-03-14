package com.yiquanxinhe.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:21
 * @Description: 异常适配器
 */
public class GraphQLErrorAdapter implements GraphQLError {

    private GraphQLError error;

    public GraphQLErrorAdapter(GraphQLError error) {
        this.error = error;
    }

    public Integer getCode(){
        if (error.getExtensions()==null) {
            return GraphQLErrorResult.SYSTEM_INNER_ERROR.getValue();
        }
        return (Integer) error.getExtensions().get("code");
    }

    @Override
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Map<String, Object> getExtensions() {
        return null;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public ErrorType getErrorType() {
        return error.getErrorType();
    }

    @Override
    public List<Object> getPath() {
        return error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }

    @Override
    public String getMessage() {
        if (error.getExtensions()==null) {
            return GraphQLErrorResult.SYSTEM_INNER_ERROR.getDesc();
        }
        return (error instanceof ExceptionWhileDataFetching) ? ((ExceptionWhileDataFetching) error).getException().getMessage() : error.getMessage();
    }
}
