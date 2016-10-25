<?xml version="1.0"?>
<recipe>

    <merge from="root/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    <merge from="root/res/values/manifest_strings.xml.ftl"
             to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

<#if useFragment>
    <#include "recipe_fragment.xml.ftl" />
    
</#if>


<#if isNewProject>

	<merge from="root/build.gradle.ftl"
                   to="${escapeXmlAttribute(projectOut)}/build.gradle" />

	<instantiate from="root/res/values/ids.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/values/ids.xml" />

	<instantiate from="root/res/values/arrays.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/values/arrays.xml" />					
				   
	
	<instantiate from="root/res/layout/activity_main.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
				   
    <instantiate from="root/res/layout/tab_indicator.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${tab_indicator}.xml" />					
				   
	<!--MyApplication -->	
	<instantiate from="root/src/app_package/MyApplication.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/MyApplication.java" />				   
	
	
	<!--customview -->	
	<instantiate from="root/src/app_package/FragmentTabHost.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/customview/FragmentTabHost.java" />

	
	<!--adapters -->
	<instantiate from="root/src/app_package/BaseRecyclerViewAdapter.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/adapters/BaseRecyclerViewAdapter.java" />	
				 
	<instantiate from="root/src/app_package/BaseUltimateRecyclerViewAdapter.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/adapters/BaseUltimateRecyclerViewAdapter.java" />	
	
	<!--items -->
	<instantiate from="root/src/app_package/ItemView.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/items/ItemView.java" />	
				 
	<instantiate from="root/src/app_package/BaseViewHolder.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/items/BaseViewHolder.java" />

	<!--model -->
	<instantiate from="root/src/app_package/BaseModel.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/model/BaseModel.java" />	
				 
	<instantiate from="root/src/app_package/BaseModelJson.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/model/BaseModelJson.java" />		
				 
	<instantiate from="root/src/app_package/PagerResult.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/model/PagerResult.java" />		
	
	<!--listener -->			 
	<instantiate from="root/src/app_package/OttoBus.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/listener/OttoBus.java" />			 
				 
	<!--prefs-->	
	<instantiate from="root/src/app_package/MyPrefs.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/prefs/MyPrefs.java" />		
				 
	<!--rest -->			 
	<instantiate from="root/src/app_package/MyRestClient.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyRestClient.java" />
				 
	<instantiate from="root/src/app_package/MyResponseErrorHandlerBean.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyResponseErrorHandlerBean.java" />
				 
    <instantiate from="root/src/app_package/MyGsonHttpMessageConverter.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyGsonHttpMessageConverter.java" />				 
	
	<instantiate from="root/src/app_package/MyErrorHandler.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyErrorHandler.java" />	
				 
	<instantiate from="root/src/app_package/MyBackgroundTask.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyBackgroundTask.java" />			 
				 
	<instantiate from="root/src/app_package/MyInterceptor.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyInterceptor.java" />

    <instantiate from="root/src/app_package/MyOkHttpClientHttpRequestFactory.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/rest/MyOkHttpClientHttpRequestFactory.java" />
				 
	<!--fragments -->
	<instantiate from="root/src/app_package/BaseFragment.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/fragments/BaseFragment.java" />
	

	<instantiate from="root/src/app_package/BaseUltimateRecyclerViewFragment.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/fragments/BaseUltimateRecyclerViewFragment.java" />
	
	<!--activities -->
    <instantiate from="root/src/app_package/BaseActivity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/activities/BaseActivity.java" />
	 <instantiate from="root/src/app_package/BaseUltimateRecyclerViewActivity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/activities/BaseUltimateRecyclerViewActivity.java" />			 					 
				 
				 
				 
    <open file="${escapeXmlAttribute(srcOut)}/activities/BaseActivity.java" />
	
	 <instantiate from="root/src/app_package/MainActivity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/activities/${activityClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/activities/${activityClass}.java" />

<#else>
	<instantiate from="root/src/app_package/SimpleActivity.java.ftl"
					to="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />
	<open file="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />
</#if>
   
<#if useFragment>
    <open file="${escapeXmlAttribute(resOut)}/layout/${fragmentLayoutName}.xml" />
<#else>
    <open file="${escapeXmlAttribute(resOut)}/layout/${simpleLayoutName}.xml" />
</#if>

</recipe>
