package com.github.yafithekid.jvm_lang_instrumentation.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

public class BasicClassFileTransformer implements ClassFileTransformer {
    private Set<String> modifiedClasses;

    public BasicClassFileTransformer(){
        modifiedClasses = new HashSet<>();
        modifiedClasses.add("com/github/yafithekid/jvm_lang_instrumentation/java/Main");
        modifiedClasses.add("com/github/yafithekid/jvm_lang_instrumentation/scala/Main");
        modifiedClasses.add("com/github/yafithekid/jvm_lang_instrumentation/groovy/Main");
        modifiedClasses.add("com/github/yafithekid/jvm_lang_instrumentation/spring/Application");
    }

    @Override
    public byte[] transform(ClassLoader loader, String _className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (modifiedClasses.contains(_className)){
            String className = _className.replace("/",".");
            ClassPool cp = ClassPool.getDefault();
            try {
                CtClass ctClass = cp.get(className);
                CtMethod ctMethod;
                if (_className.endsWith("Application")){
                    System.out.println("here");
                    //its the spring controller, so monitor the hello
                    ctMethod = ctClass.getDeclaredMethod("hello");
                } else if (_className.endsWith("DispatcherServlet")) {
                    ctMethod = ctClass.getDeclaredMethod("doDispatch");
                } else {
                    ctMethod = ctClass.getDeclaredMethod("main");
                }
                ctMethod.addLocalVariable("__a",CtClass.longType);
                ctMethod.addLocalVariable("__b",CtClass.longType);
                ctMethod.insertBefore("__a = (System.currentTimeMillis());");
                ctMethod.insertAfter("__b = (System.currentTimeMillis());");
                ctMethod.insertAfter("System.out.println(\""+ctMethod.getName()+" executed in \"+(__b-__a)+\"ms\");");
                classfileBuffer = ctClass.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }

        }
        return classfileBuffer;
    }
}
