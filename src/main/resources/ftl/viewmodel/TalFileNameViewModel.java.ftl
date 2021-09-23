package com.xes.teacher.live.ui.${name}.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.xes.teacher.live.base.viewmodel.BaseViewModel;
import com.xes.teacher.live.constant.RequestParamsConstants;
import com.xes.teacher.live.ui.${name}.bean.ExamDetailDataBean;
import com.xes.teacher.live.ui.${name}.repo.ExaminationPaperRepository;

import java.util.HashMap;

/**
 * ${name} 的 ViewModel
 */
public class ${name}ViewModel extends BaseViewModel<${name}Repository> {

    public MutableLiveData<${name}DataBean> ${name}DataBeanMutableLiveData = new MutableLiveData<>();

    /**
     * 请求数据
     * @param id id
     */
    public void request${name}(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(RequestParamsConstants.KEY_ITEMID, id);
        getRepository().request${name}Task(${name}DataBeanMutableLiveData, params);
    }
}
