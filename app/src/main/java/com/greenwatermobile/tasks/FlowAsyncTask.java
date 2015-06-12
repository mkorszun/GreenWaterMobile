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
            return api.readFlowStatus("53ff62066667574836592567",
                "681cf8726431e6526bc09d78f5fe4182f675db42");
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
