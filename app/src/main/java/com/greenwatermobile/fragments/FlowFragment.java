package com.greenwatermobile.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.greenwatermobile.MainActivity;
import com.greenwatermobile.R;
import com.greenwatermobile.bus.EventBus;
import com.greenwatermobile.model.Metrics;
import com.squareup.otto.Subscribe;

public class FlowFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FlowFragment newInstance(int sectionNumber) {
        FlowFragment fragment = new FlowFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        EventBus.getInstance().register(this);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Subscribe
    public void readFlowSuccess(Metrics metrics) {
        if (isVisible()) {
            TextView dayUsage = (TextView) getActivity().findViewById(R.id.day_usage);
            TextView weekUsage = (TextView) getActivity().findViewById(R.id.week_usage);
            TextView monthUsage = (TextView) getActivity().findViewById(R.id.month_usage);
            TextView yearUsage = (TextView) getActivity().findViewById(R.id.year_usage);

            dayUsage.setText(Integer.toString(metrics.getDaily()));
            weekUsage.setText(Integer.toString(metrics.getWeekly()));
            monthUsage.setText(Integer.toString(metrics.getMonthly()));
            yearUsage.setText(Integer.toString(metrics.getYearly()));
        }
    }

    @Subscribe
    public void readFlowError(Exception e) {
        if (isVisible()) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}