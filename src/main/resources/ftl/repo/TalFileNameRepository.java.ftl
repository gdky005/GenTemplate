package com.xes.teacher.live.ui.${smallName}.repo;

import androidx.lifecycle.MutableLiveData;

import com.xes.teacher.live.api.TLBaseRepository;
import com.xes.teacher.live.network.http.callback.DefaultTLCallback;
import com.xes.teacher.live.ui.${smallName}.bean.${name}DataBean;

import java.util.HashMap;

/**
 * ${name} 的 Repository
 */
public class ${name}Repository extends TLBaseRepository {

    /**
     * 请求 ${name} 的 接口任务
     *
     * @param ${varName}DataBeanMutableLiveData         ${varName}DataBeanMutableLiveData
     * @param params                                    params
     */
    public void request${name}Task(MutableLiveData<${name}DataBean> ${varName}DataBeanMutableLiveData,
                                                 HashMap<String, Object> params) {
        addRequestTask(getTlApi().request${name}Task(getPostRequestToBeanBody(params)),
                new DefaultTLCallback(${varName}DataBeanMutableLiveData, this, ${name}DataBean.class));
    }

}
