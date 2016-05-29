/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package rpc;

import java.io.Serializable;

/**
 * rpc参数,包括类名,方法名,请求参数数组
 * @author:zixiao.hzx
 * @version $Id:RpcParam.java,v0.1 2016-05-28 上午11:47 zixiao.hzx Exp $$
 */
public class RpcParam implements Serializable{

    private static final long serialVersionUID = -6079202387537517332L;

    private String clazzName;

    private String methodName;

    private Object[] args;

    public RpcParam(String clazzName, String methodName, Object[] args) {
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.args = args;
    }

    public RpcParam() {
    }

    /**
     * Getter method for property clazzName
     *
     * @return property value of clazzName
     */
    public String getClazzName() {
        return clazzName;
    }

    /**
     * Setter method for property clazzName.
     *
     * @param clazzName value to be assigned to property clazzName
     */
    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    /**
     * Getter method for property methodName
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property methodName.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property args
     *
     * @return property value of args
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Setter method for property args.
     *
     * @param args value to be assigned to property args
     */
    public void setArgs(Object[] args) {
        this.args = args;
    }
}
