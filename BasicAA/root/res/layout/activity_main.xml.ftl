<?xml version="1.0" encoding="utf-8"?>
<${packageName}.customview.FragmentTabHost
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
	android:id="@+id/tab_host"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".activities.${activityClass}">

	<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <FrameLayout
            android:id="@android:id/tabcontent"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_weight="0"/>

        <FrameLayout
            android:id="@+id/real_content"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"/>
        <!-- tabhost上面一条黑色分割 @drawable/line_shop -->
        <View
            android:id="@+id/view_2"
            android:layout_width="match_parent"
            android:layout_height="0.1dp"
            android:clickable="false"/>
        <!-- 调换framelayout和tabwidget的前后顺序可以分别实现tab的top和在底下的效果 -->
        <TabWidget
            android:id="@android:id/tabs"
            android:layout_width="match_parent"
            android:layout_height="56dp"
            android:background="#fafafa"
            android:divider="@null"/>
    </LinearLayout>

</${packageName}.customview.FragmentTabHost>
