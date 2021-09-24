package com.xes.teacher.live.ui.${smallName}.page.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.xes.teacher.live.base.view.BaseViewModelFragment;
import com.xes.teacher.live.databinding.Fragment${name}Binding;
import com.xes.teacher.live.ui.${smallName}.viewmodel.${name}ViewModel;
import com.xes.teacher.live.ui.${smallName}.bean.${name}DataBean;

import org.jetbrains.annotations.NotNull;

import static com.xes.teacher.live.ui.${smallName}.page.activity.${name}Activity.FLAG_${layoutBigName}_ID;

/**
 * ${name} Fragment
 */
public class ${name}Fragment extends BaseViewModelFragment<Fragment${name}Binding, ${name}ViewModel> {

    private String id = "";

    private ${name}DataBean ${varName}Bean;

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);

        id = getArguments().getString(FLAG_${layoutBigName}_ID);
    }

    @Override
    public void initViews(@NotNull View view) {
        viewBinding.titleBar.ivBack.setOnClickListener(v -> mActivity.finish());
        viewBinding.titleBar.tvTitle.setText("${name}");
    }


    public static ${name}Fragment newInstance(Context context, String id) {
        Bundle bundle = new Bundle();
        bundle.putString(FLAG_${layoutBigName}_ID, id);
        return (${name}Fragment) ${name}Fragment.instantiate(context, ${name}Fragment.class.getName(), bundle);
    }
}
