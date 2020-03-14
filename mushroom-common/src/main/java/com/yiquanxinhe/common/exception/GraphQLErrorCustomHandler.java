package com.yiquanxinhe.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.execution.InputMapDefinesTooManyFieldsException;
import graphql.execution.NonNullableFieldWasNullError;
import graphql.language.SourceLocation;
import graphql.schema.CoercingParseValueException;
import graphql.servlet.GraphQLErrorHandler;
import graphql.validation.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:22
 * @Description: 异常处理器
 */
@Component
public class GraphQLErrorCustomHandler implements GraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> clientErrors = errors.stream()
                .filter(this::isClientError)
                .collect(Collectors.toList());

        ArrayList<GraphQLError> graphQLErrors = new ArrayList<>();
        for (GraphQLError clientError : clientErrors) {
            GraphQLError newGraphqlError = new GraphQLError() {
                @Override
                public String getMessage() {
                    Class<? extends GraphQLError> aClass = clientError.getClass();
                    if (aClass.equals(NonNullableFieldWasNullError.class)) {
                        return GraphQLErrorResult.GRAPHQL_NULL_ERROR.getDesc();
                    } else if (aClass.equals(CoercingParseValueException.class)) {
                        return GraphQLErrorResult.GRAPHQL_PARSE_ERROR.getDesc();
                    } else if (aClass.equals(InputMapDefinesTooManyFieldsException.class)) {
                        return GraphQLErrorResult.GRAPHQL_DEFINES_FIELDS_ERROR.getDesc();
                    } else if (aClass.equals(ValidationError.class)) {
                        return GraphQLErrorResult.GRAPHQL_VALIDATION_ERROR.getDesc();
                    } else {
                        return GraphQLErrorResult.SYSTEM_INNER_ERROR.getDesc();
                    }
                }

                @Override
                public List<SourceLocation> getLocations() {
                    return clientError.getLocations();
                }

                @Override
                public ErrorType getErrorType() {
                    return clientError.getErrorType();
                }

                @Override
                public List<Object> getPath() {

                    String[] split1 = clientError.getMessage().split("'");
                    String str1 = split1[split1.length - 1];
                    String[] split2 = str1.split("/");
                    String path = split2[0];

                    List<Object> list = new ArrayList<>();
                    list.add(path);

                    return list;
                }


                @Override
                public Map<String, Object> toSpecification() {
                    return clientError.toSpecification();
                }

                @Override
                @JsonInclude(JsonInclude.Include.NON_NULL)
                public Map<String, Object> getExtensions() {
                    return clientError.getExtensions();
                }

                public Integer getCode() {
                    return GraphQLErrorResult.SYSTEM_INNER_ERROR.getValue();
                }
            };
            graphQLErrors.add(newGraphqlError);
        }

        List<GraphQLError> serverErrors = errors.stream()
                .filter(e -> !isClientError(e))
                .map(GraphQLErrorAdapter::new)
                .collect(Collectors.toList());

        List<GraphQLError> e = new ArrayList<>();
        e.addAll(graphQLErrors);
        e.addAll(serverErrors);
        return e;
    }

    private boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
    }

}