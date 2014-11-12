package com.greenwatermobile.tasks;

import com.greenwatermobile.model.VariableResponse;

public interface FlowAsyncTaskExecutor {

    public void onSuccess(VariableResponse response);

    public void onFailure(Exception e);
}
