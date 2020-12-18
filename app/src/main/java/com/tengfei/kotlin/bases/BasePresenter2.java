package com.tengfei.kotlin.bases;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangxiulong on 16/10/26.
 */

public class BasePresenter2<T> {
    private IViewHolder viewHolder;

    protected T iView;
//    protected ServiceManager mServiceManager;

    public BasePresenter2(T v) {
        viewHolder = new IViewHolder(v);
        iView = viewHolder.hold();
//        mServiceManager = SDMoneyApplication.getInstance().getServiceManager();
    }

    public final void onResume() {
        viewHolder.enable();
        onShow();
    }

    protected void onShow() {
    }

    public final void onPause() {
        onHide();
        viewHolder.diable();
    }

    protected void onHide() {
    }


    private class IViewHolder implements InvocationHandler {
        T receiver;
        boolean enable;

        public IViewHolder(T view) {
            this.receiver = view;
            enable = false;
        }

        T hold() {
            return (T) Proxy.newProxyInstance(
                    receiver.getClass().getClassLoader(),
                    receiver.getClass().getInterfaces(),
                    this
            );
        }



        void enable() {
            enable = true;
        }

        void diable() {
            enable = false;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (receiver == null) {
                return null;
            }

            ViewMethod view = method.getAnnotation(ViewMethod.class);
            if (view != null && view.ignore()) {
                return method.invoke(receiver, args);
            }

            if (enable) {
                return method.invoke(receiver, args);
            }

            return null;
        }
    }
}
