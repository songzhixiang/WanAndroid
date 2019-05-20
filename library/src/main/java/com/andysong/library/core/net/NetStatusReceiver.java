package com.andysong.library.core.net;



import com.andysong.library.core.MethodManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
public class NetStatusReceiver {

    private NetType mNetType;

    private Map<Object, List<MethodManager>> mListMap;

    public NetStatusReceiver() {
        mNetType = NetType.NONE;
        mListMap = new HashMap<>();

    }

    protected void post(NetType netType){
        //所有的注册类
        Set<Object> objects = mListMap.keySet();
        this.mNetType = netType;
        for (Object clazz:
             objects) {
            List<MethodManager> methodManagers = mListMap.get(clazz);
            executeInvoke(clazz,methodManagers);
        }
    }

    private void executeInvoke(Object clazz,List<MethodManager> methodManagers) {
        if (null!=methodManagers){
            for (MethodManager methodManager: methodManagers) {
                if (methodManager.getParameterClz().isAssignableFrom(mNetType.getClass())){
                    switch (methodManager.getAnnotationNetType()){
                        case AUTO:
                            invoke(methodManager,clazz,mNetType);
                            break;
                        case NONE:
                            if (mNetType == NetType.NONE) {
                                invoke(methodManager, clazz, mNetType);
                            }
                            break;

                        case WIFI:
                            if (mNetType == NetType.WIFI || mNetType == NetType.NONE) {
                                invoke(methodManager, clazz, mNetType);
                            }
                            break;

                        case MOBILE:
                            if (mNetType == NetType.MOBILE || mNetType == NetType.NONE) {
                                invoke(methodManager, clazz, mNetType);
                            }
                            break;

                            default:
                                break;

                    }
                }
            }
        }

    }

    private void invoke(MethodManager methodManager, Object clazz, NetType netType) {
        Method execute = methodManager.getMethod();
        try {
            execute.invoke(clazz,netType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void registerObserver(Object context){
        List<MethodManager> methodManagers = mListMap.get(context);
        if (methodManagers == null){
            methodManagers = findAnnotationMethod(context);
            mListMap.put(context,methodManagers);
        }
        executeInvoke(context,methodManagers);
    }

    private List<MethodManager> findAnnotationMethod(Object context) {

        List<MethodManager> methodManagerList = new ArrayList<>();
        Class<?> aClass = context.getClass();
        Method[] methods = aClass.getMethods();

        for (Method method :
                methods) {
            NetSubscribe netSubscribe = method.getAnnotation(NetSubscribe.class);
            if (netSubscribe == null){
            }
            Type genericReturnType = method.getGenericReturnType();
            if (!"void".equalsIgnoreCase(genericReturnType.toString())){
                throw new IllegalArgumentException("you" + method.getName() + "return value must be void");
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1){
                throw new IllegalArgumentException("you" +method.getName() + "need a parameter NetType");
            }

            MethodManager methodManager  =new MethodManager(parameterTypes[0],netSubscribe.netType(),method);
            methodManagerList.add(methodManager);

        }
        return methodManagerList;
    }


    public void unRegisterObserver(Object context){
        if (!mListMap.isEmpty()){
            mListMap.remove(context);
        }
    }


    public void unRegisterAllObserver(){
        if (!mListMap.isEmpty()){
            mListMap.clear();
            mListMap = null;
        }
    }


}
