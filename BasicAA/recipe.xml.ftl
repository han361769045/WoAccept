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
				   

	<instantiate from="root/src/app_package/MyApplication.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/MyApplication.java" />				   
	
	<instantiate from="root/src/app_package/FragmentTabHost.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/customview/FragmentTabHost.java" />
				 
	<instantiate from="root/src/app_package/MyTitleBar.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/customview/MyTitleBar.java" />
	
	<instantiate from="root/src/app_package/BaseFragment.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/fragments/BaseFragment.java" />
	
    <instantiate from="root/src/app_package/BaseActivity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/activities/BaseActivity.java" />
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
