<%@ page import="org.frameworkset.elasticsearch.ElasticSearchHelper" %>
<%@ page import="org.frameworkset.elasticsearch.client.ClientInterface" %>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
	ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
	//验证环境,获取es状态
	//String result = clientUtil.executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);

	//判断索引类型是否存在，抛出异常表示不存在，正常返回表示存在
	boolean exist1 = clientUtil.existIndiceType("twitter","tweet");
	out.println("twitter  tweet type exist:"+exist1);
	//判读索引是否存在，抛出异常表示不存在，正常返回表示存在
	exist1 = clientUtil.existIndice("twitter");
	out.println("twitter exist:"+exist1);
	boolean exist = clientUtil.existIndice("agentinfo");
	out.println("agentinfo exist:"+exist);

%>
<div>

</div>