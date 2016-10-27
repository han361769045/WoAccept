<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              xmlns:tools="http://schemas.android.com/tools"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical"
              tools:context=".activities.BaseUltimateRecyclerViewActivity">

    <com.leo.lu.mytitlebar.MyTitleBar
        android:id="@+id/my_title_bar"
        android:layout_width="match_parent"
        android:layout_height="70dp"
        app:mRightTextMarginRight="15dp"
        app:mStatueBarIsTransparent="true"/>


    <com.leo.lu.llrecyclerview.LLRecyclerView
        android:id="@+id/ultimate_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:recyclerViewEmptyView="@layout/empty_view"
        />

</LinearLayout>