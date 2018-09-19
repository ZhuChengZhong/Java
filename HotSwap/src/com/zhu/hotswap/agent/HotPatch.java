package com.zhu.hotswap.agent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HotPatch {
	public static void premain(String agentArgs, Instrumentation inst) {
		 ScheduledExecutorService ses=Executors.newScheduledThreadPool(1);
		 ses.scheduleAtFixedRate(new HotCheckAndSwapTask(inst),5,5, TimeUnit.SECONDS);
	}
}
