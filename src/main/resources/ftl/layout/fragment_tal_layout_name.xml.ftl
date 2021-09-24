<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <include
        android:id="@+id/title_bar"
        layout="@layout/view_common_title_bar" />

    <include
        android:id="@+id/common_page"
        android:layout_below="@+id/title_bar"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        layout="@layout/view_common_page_status" />
</RelativeLayout>