package other.res.layout

import other.ArmsPluginTemplateProviderImpl

fun simpleLayout(provider: ArmsPluginTemplateProviderImpl) = """
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <include layout="@layout/base_title" />
</LinearLayout>
"""