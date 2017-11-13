package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author husongzhen
 */
public class LayoutProxyClass {

    /**
     * 类元素
     */
    public TypeElement mTypeElement;

    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;

    /**
     * FieldViewBinding类型的集合
     */
    private LayoutBinding bindViews;

    public void setBindViews(LayoutBinding bindViews) {
        this.bindViews = bindViews;
    }


    public LayoutProxyClass(TypeElement mTypeElement, Elements mElementUtils) {
        this.mTypeElement = mTypeElement;
        this.mElementUtils = mElementUtils;
    }


    private static final String viewPackage = "android.widget";

    /**
     * proxytool.IProxy
     */
    public static final ClassName IPROXY = ClassName.get("com.allen.code.bindview_api", "ILayoutProxy");


    /**
     * android.view.View
     */
    public static final String SUFFIX = "$$Layout";

    /**
     * 用于生成代理类
     */
    public JavaFile generateProxy() {

        //生成public void inject(final T target, View root)方法
        MethodSpec.Builder injectMethodBuilder = MethodSpec.methodBuilder("inject")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL);
        injectMethodBuilder.addStatement("activity.setContentView(R.layout.$N)", bindViews.getLayoutName());
        for (ViewModel item : bindViews.getViewModels()) {
            injectMethodBuilder.addStatement("$N = ($N)activity.findViewById(R.id.$N)", item.getId(), item.getViewType(), item.getId());
        }
        // 添加以$$Proxy为后缀的类
        TypeSpec.Builder builder = TypeSpec.classBuilder(mTypeElement.getSimpleName() + SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                //添加父接口
                .addSuperinterface(ParameterizedTypeName.get(IPROXY, TypeName.get(mTypeElement.asType())))
                //把inject方法添加到该类中
                .addMethod(injectMethodBuilder.build());

        for (ViewModel item : bindViews.getViewModels()) {
            FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(viewPackage, item.getViewType()), item.getId())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .build();
            builder.addField(fieldSpec);
        }

        TypeSpec typeSpec = builder.build();
        //添加包名
        String packageName = mElementUtils.getPackageOf(mTypeElement).getQualifiedName().toString();
        //生成Java文件
        return JavaFile.builder(packageName, typeSpec).build();
    }

    private String getViewIdName(ViewModel item) {
        return item.getId() + "Id";
    }


}