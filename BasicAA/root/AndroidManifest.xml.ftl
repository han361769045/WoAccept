<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

	    <uses-permission android:name="android.permission.INTERNET"/>

    <application
	 android:name=".MyApplication_"
	>
        <activity android:name="activities.${activityClass}_"
            <#if generateActivityTitle!true>
                    android:label="@string/app_name"
            </#if>>
            <#if (isLauncher && launcher) && !(isLibraryProject!false)>
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </#if>
        </activity>
    </application>
</manifest>
