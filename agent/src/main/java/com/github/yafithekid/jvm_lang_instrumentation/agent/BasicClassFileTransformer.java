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
    }

    @Override
    public byte[] transform(ClassLoader loader, String _className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (modifiedClasses.contains(_className)){
            String className = _className.replace("/",".");
            ClassPool cp = ClassPool.getDefault();
            try {
                CtClass ctClass = cp.get(className);
                CtMethod ctMethod = ctClass.getDeclaredMethod("main");
                ctMethod.insertBefore("System.out.println(System.currentTimeMillis());");
                ctMethod.insertAfter("System.out.println(System.currentTimeMillis());");
                classfileBuffer = ctClass.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }

        }
        return classfileBuffer;
    }
}