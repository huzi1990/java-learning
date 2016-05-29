/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * rpc客户端程序
 * @author:zixiao.hzx
 * @version $Id:RpcClient.java,v0.1 2016-05-28 下午8:50 zixiao.hzx Exp $$
 */
public class RpcClient {

    public static void main(String[] args) {

        HelloClass helloClass = interfaceProxyFactory
                .createRemoteInterface("localhost", 7778, HelloClass.class);
        System.out.print(helloClass.sayHello("huzi"));
    }

}

/**
 *
 */
class interfaceProxyFactory {

    public static <T> T createRemoteInterface(String ip, int port, Class<T> tClass) {

        RpcClientHandler rpcClientHandler = new RpcClientHandler(ip, port, tClass.getName());

        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[] { tClass },
                rpcClientHandler);
    }

}

/**
 * rpc某类的处理
 */
class RpcClientHandler implements InvocationHandler, Serializable {

    private String ip;

    private int port;

    private String clazzName;

    public RpcClientHandler(String ip, int port, String clazzName) {
        this.ip = ip;
        this.port = port;
        this.clazzName = clazzName;
    }

    @Override public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        Socket socket = new Socket(ip, port);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        //构造请求参数
        RpcParam rpcParam = new RpcParam(clazzName, method.getName(), objects);
        objectOutputStream.writeObject(rpcParam);
        objectOutputStream.flush();
        //获得执行结果
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object result = objectInputStream.readObject();
        //关闭输入输出流
        objectOutputStream.close();
        objectInputStream.close();

        return result;
    }
}