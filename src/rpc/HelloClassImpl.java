/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package rpc;

/**
 * 实现
 * @author:zixiao.hzx
 * @version $Id:HelloClassImpl.java,v0.1 2016-05-28 上午11:00 zixiao.hzx Exp $$
 */
public class HelloClassImpl implements HelloClass {

    @Override public String sayHello(String name) {
        System.out.println("hello service:"+name);
        return "hello service"+name;
    }
}
