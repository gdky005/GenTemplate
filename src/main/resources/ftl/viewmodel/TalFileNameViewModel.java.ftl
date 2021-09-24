package com.xes.teacher.live.ui.${smallName}.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.xes.teacher.live.base.viewmodel.BaseViewModel;
import com.xes.teacher.live.constant.RequestParamsConstants;
import com.xes.teacher.live.ui.${smallName}.bean.ExamDetailDataBean;
import com.xes.teacher.live.ui.${smallName}.repo.ExaminationPaperRepository;

import java.util.HashMap;

/**
 * ${name} 的 ViewModel
 */
public class ${name}ViewModel extends BaseViewModel<${name}Repository> {

    public MutableLiveData<${name}DataBean> ${varName}DataBeanMutableLiveData = new MutableLiveData<>();

    /**
     * 请求数据
     * @param id id
     */
    public void request${name}(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(RequestParamsConstants.KEY_ITEMID, id);
        getRepository().request${name}Task(${varName}DataBeanMutableLiveData, params);
    }
}
