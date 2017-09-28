package com.example;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class ClickMethodViewBinding {


    private ExecutableElement clickElement;

    private String methodName;
    private int[] ids;
    private boolean mParameterEixt;

    private String mParameterName;


    public String getMethodName() {
        return methodName;
    }

    public int[] getIds() {
        return ids;
    }

    public boolean ismParameterEixt() {
        return mParameterEixt;
    }

    public String getmParameterName() {
        return mParameterName;
    }

    public ClickMethodViewBinding(Element clickElement) {
        this.clickElement = (ExecutableElement) clickElement;
        OnClick viewById = clickElement.getAnnotation(OnClick.class);
        ids = viewById.value();
        methodName = clickElement.getSimpleName().toString();

        List<? extends VariableElement> parameters = this.clickElement.getParameters();
        if (parameters.size() > 1) {  //参数不能超过1个
            throw new IllegalArgumentException(
                    String.format("The method annotated with @%s must less two parameters", OnClick.class.getSimpleName()));
        }


        if (parameters.size() == 1) {
            VariableElement element = parameters.get(0);
            if (!element.asType().toString().equals(ProxyClass.VIEW.toString())) {
                throw new IllegalArgumentException(
                        String.format("The method parameter must be %s type", ProxyClass.VIEW.toString()));
            }
            mParameterEixt = true;
            mParameterName = element.getSimpleName().toString();
        }


    }
}