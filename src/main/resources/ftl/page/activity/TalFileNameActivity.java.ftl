package com.xes.teacher.live.ui.${smallName}.page.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xes.teacher.live.R;
import com.xes.teacher.live.base.view.BaseViewModelActivity;
import com.xes.teacher.live.databinding.Activity${name}Binding;
import com.xes.teacher.live.ui.${smallName}.page.fragment.${name}Fragment;
import com.xes.teacher.live.ui.${smallName}.viewmodel.${name}ViewModel;

import org.jetbrains.annotations.Nullable;

/**
 * ${name} Activity
 */
public class ${name}Activity extends BaseViewModelActivity<Activity${name}Binding, ${name}ViewModel> {

    public static final String FLAG_${layoutBigName}_ID = "flag_${layoutName}_id";

    private ${name}Fragment ${varName}Fragment;

    @Override
    public void initData(@Nullable Bundle bundle) {
        super.initData(bundle);
        String id = getIntent().getStringExtra(FLAG_${layoutBigName}_ID);
        if (!TextUtils.isEmpty(id)) {
            ${varName}Fragment = ${name}Fragment.newInstance(this, id);
            openPage(${varName}Fragment, R.id.fl_content, false);
        } else {
            ToastUtils.showShort("id：" + id);
        }
    }

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, ${name}Activity.class);
        intent.putExtra(FLAG_${layoutBigName}_ID, id);
        ActivityUtils.startActivity(intent);
    }
}
