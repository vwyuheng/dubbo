package com.alibaba.dubbo.main;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class StartJetty {
	
	private static final Logger log = LoggerFactory.getLogger(StartJetty.class);
	
	private static StartJetty  sj = null;
	
	private StartJetty(){
		
	}
	
	public static StartJetty getInstance(){
		if (sj == null) {
			sj = new StartJetty();
		}
		return sj;
	}
	
	public void startJetty(){
		
		Server server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(8090);
		server.setConnectors(new Connector[]{connector});
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase("../dubbo-admin/src/main/webapp");
        webapp.setDescriptor("../dubbo-admin/src/main/webapp/WEB-INF/web.xml");
        webapp.setParentLoaderPriority(true);
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
        
        server.setHandler(webapp);

        try {
			server.start();
			log.info("=====================================================================");
			log.info("======================http://host:8090/index.htm=====================");
			log.info("=========================dubbo-admin started=========================");
			log.info("=====================================================================");
			log.info("====================="+webapp.getContextPath()+"=====================");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args){
		StartJetty.getInstance().startJetty();
	}
}
