/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 ALL Rights Reserved
 */
package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author:zixiao.hzx
 * @version $Id:RpcService.java,v0.1 2016-05-28 上午11:18 zixiao.hzx Exp $$
 */
public class RpcService {

    public static void main(String[] args){

        try {
            ServerSocket serverSocket = new ServerSocket(7778);
            //监听线程
            while (true) {
                //获得套接字
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new RpcServiceThread(socket));
                thread.start();

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


}

class RpcServiceThread implements Runnable {

    //获得客户端socket
    private Socket socket;

    public RpcServiceThread(Socket socket) {
        this.socket = socket;
    }

    @Override public void run() {

        try {
            //获得请求参数
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcParam rpcParam = (RpcParam) objectInputStream.readObject();
            //实例化对象,这里由于是接口,所以用一个配置中心确定选用哪个实现
            String clazzImpl=ConfregCenter.clazzImplMap.get(rpcParam.getClazzName());
            Object o = Class.forName(clazzImpl).newInstance();
            //构造方法参数
            Object[] methodParams = rpcParam.getArgs();
            Class[] classes = new Class[methodParams.length];
            for(int i=0;i<methodParams.length;i++){
                Object methodParam = methodParams[i];
                classes[i]= methodParam.getClass();
            }
            Method method = o.getClass().getMethod(rpcParam.getMethodName(), classes);
            //获得结果
            Object result = method.invoke(o, methodParams);
            //将结果传输
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    socket.getOutputStream());
            objectOutputStream.writeObject(result);
            //进行传输
            objectOutputStream.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ConfregCenter {

    public static Map<String, String> clazzImplMap = new HashMap<String, String>();
    static {
        clazzImplMap.put("rpc.HelloClass", "rpc.HelloClassImpl");
    }
}