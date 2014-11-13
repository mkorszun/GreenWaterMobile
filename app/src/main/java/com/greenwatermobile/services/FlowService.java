package com.greenwatermobile.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.greenwatermobile.bus.EventBus;
import com.greenwatermobile.model.Metrics;
import com.greenwatermobile.model.VariableResponse;
import com.greenwatermobile.storage.MetricsMode;
import com.greenwatermobile.storage.MetricsStorage;
import com.greenwatermobile.tasks.FlowAsyncTask;
import com.greenwatermobile.tasks.FlowAsyncTaskExecutor;

public class FlowService extends Service {

    private static final int FREQUENCY = 500;
    private volatile boolean stopped = false;
    private Handler handler = new Handler();
    private MetricsStorage storage = new MetricsStorage();

    @Override
    public void onCreate() {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!stopped) {
                    execute();
                    handler.postDelayed(this, FREQUENCY);
                }
            }
        };

        handler.postDelayed(runnable, FREQUENCY);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override public void onDestroy() {
        this.stopped = true;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void execute() {

        new FlowAsyncTask(new FlowAsyncTaskExecutor() {
            @Override
            public void onSuccess(VariableResponse response) {
                int daily = storage.saveUsage(response.getResult(), MetricsMode.DAY);
                int weekly = storage.saveUsage(response.getResult(), MetricsMode.WEEK);
                int monthly = storage.saveUsage(response.getResult(), MetricsMode.MONTH);
                int yearly = storage.saveUsage(response.getResult(), MetricsMode.YEAR);
                EventBus.getInstance().post(new Metrics(daily, weekly, monthly, yearly));
            }

            @Override
            public void onFailure(Exception e) {
                EventBus.getInstance().post(e);
            }
        }).execute();
    }
}
