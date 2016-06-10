package com.github.yafithekid.jvm_lang_instrumentation.agent;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new BasicClassFileTransformer());
    }
}
