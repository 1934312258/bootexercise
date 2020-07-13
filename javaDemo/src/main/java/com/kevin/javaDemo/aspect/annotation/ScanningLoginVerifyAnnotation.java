package com.kevin.javaDemo.aspect.annotation;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author kevin
 * @date 2020-7-9 8:55
 * @description todo
 **/
@Component
public class ScanningLoginVerifyAnnotation {
    private static final String PACKAGE_NAME="com.kevin.javaDemo";
    private static final String RESOURCE_PATTERN="/**/*.class";

    @PostConstruct
    public void scanning() throws IOException, ClassNotFoundException {
        String pattern= ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+ ClassUtils.convertClassNameToResourcePath(PACKAGE_NAME)
                +RESOURCE_PATTERN;

        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        Resource []resources=resolver.getResources(pattern);
        for(Resource resource:resources){
            if(resource.isReadable()){
                String className=getClassName(resource.getURL().toString());
                Class cls=ScanningLoginVerifyAnnotation.class.getClassLoader().loadClass(className);
                for(Method method:cls.getMethods()){
                    LoginVerify requestCode=method.getAnnotation(LoginVerify.class);
                    if(requestCode!=null){
                        LoginVerifyMapping.add(className+"."+method.getName());
                    }
                }
            }
        }
    }
    private String getClassName(String resourceUrl){
        String url=resourceUrl.replace("/",".");
        url=url.replace("\\",".");
        url=url.split("com.kevin")[1];
        url=url.replace(".class","");
        return "com.kevin"+url.trim();
    }
}
