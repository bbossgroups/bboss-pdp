package org.frameworkset.filter;

import com.frameworkset.orm.transaction.TransactionManager;
import org.frameworkset.platform.security.AccessControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * <p>Title: BSServletRequestListener.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: bbossgroups</p>
 * @Date Jul 24, 2008 11:24:52 AM
 * @author biaoping.yin,尹标平
 * @version 1.0
 */
public class BSServletRequestListener implements javax.servlet.ServletRequestListener
{
	private static final Logger log = LoggerFactory.getLogger(BSServletRequestListener.class);
	  
	public void requestDestroyed(ServletRequestEvent requestEvent) {
//		System.out.println("requestDestroyed Thread.currentThread():" + Thread.currentThread());
//		System.out.println("requestDestroyed tx before:" + TransactionManager.getTransaction());
		
		if(requestEvent.getServletRequest() instanceof HttpServletRequest )
		{
			HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
			String uri = request.getRequestURI();

			 
			
//			if(com.frameworkset.listener.BSServletRequestListener.isInterceptResource(uri))
			{
//				
				boolean state = TransactionManager.destroyTransaction();
				if(state){
					log.warn("A DB transaction leaked in Page ["+ uri +"] has been forcibly destoried. ");
//					System.out.println("A DB transaction leaked in Page ["+ uri +"] has been forcibly destoried. ");
				}
				AccessControl.init(null);

			}
//			else if(uri.endsWith(".frame"))
//			{
//				AccessControl.init(null);
//			}
				
		}		
//		System.out.println("requestDestroyed tx after:" + TransactionManager.getTransaction());
//		
//		System.out.println("requestDestroyed tx after DBUtil.getNumActive():" +DBUtil.getNumActive());
//		System.out.println("requestDestroyed tx after DBUtil.getNumIdle():" +DBUtil.getNumIdle());
	}

	public void requestInitialized(ServletRequestEvent requestEvent) {
		if(requestEvent.getServletRequest() instanceof HttpServletRequest )
		{
			HttpServletRequest request = (HttpServletRequest)requestEvent.getServletRequest();
			String uri = request.getRequestURI();

			
			
//			if(com.frameworkset.listener.BSServletRequestListener.isInterceptResource(uri))
			{	
				boolean state = TransactionManager.destroyTransaction();
				if(state){
					log.warn("A DB transaction leaked before Page ["+ uri +"] has been forcibly destoried. ");
//					System.out.println("A DB transaction leaked before Page ["+ uri +"] has been forcibly destoried. ");
				}
				AccessControl.init(null);

			}
//			else if(uri.endsWith(".frame"))
//			{
//				AccessControl.init(null);
//			}
				
				
		}	
		
	}
	
	
	

}
