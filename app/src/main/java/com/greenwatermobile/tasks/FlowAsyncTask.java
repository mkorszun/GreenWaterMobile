package com.greenwatermobile.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.greenwatermobile.backend.API;
import com.greenwatermobile.backend.APIException;
import com.greenwatermobile.backend.APIFactory;
import com.greenwatermobile.model.VariableResponse;

public class FlowAsyncTask extends AsyncTask<Void, Void, VariableResponse> {

    private Exception exception;
    private FlowAsyncTaskExecutor executor;

    public FlowAsyncTask(FlowAsyncTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    protected VariableResponse doInBackground(Void... params) {
        API api = APIFactory.build();
        try {
            return api.readFlowStatus("53ff6c066667574811392167",
                "6b54cf1b2cdce825b1c42dc18941fc84f2714fd4");
        } catch (APIException e) {
            Log.e("Failed to read flow metric", e.getMessage());
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(VariableResponse variableResponse) {
        if (exception != null) {
            executor.onFailure(exception);
        } else {
            executor.onSuccess(variableResponse);
        }
    }
}
