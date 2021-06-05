package com.carworld;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppCtxUtil implements ApplicationContextAware{
	
	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext appCtx) throws BeansException {
		AppCtxUtil.appCtx = appCtx;
	}

	public static ApplicationContext getAppCtx() {
		return appCtx;
	}
}
