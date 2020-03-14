package com.yiquanxinhe.common.exception;

import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:20
 * @Description: 自定义异常
 */
public class GraphQLCustomException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    private Integer code;

    public GraphQLCustomException(GraphQLErrorResult result) {
        super(result.getDesc());
        this.code = result.getValue();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        extensions.put("code", code);
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
