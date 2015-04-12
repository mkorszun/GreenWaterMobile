package com.greenwatermobile.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.greenwatermobile.MainActivity;
import com.greenwatermobile.R;
import com.greenwatermobile.SettingsActivity;
import com.greenwatermobile.bus.EventBus;
import com.greenwatermobile.model.Metrics;
import com.squareup.otto.Subscribe;
import java.text.DecimalFormat;

public class FlowFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final DecimalFormat FLOW_FORMAT = new DecimalFormat("#0.0");
    private static final DecimalFormat COST_FORMAT = new DecimalFormat("#0.00");

    private SharedPreferences prefs;

    private TextView dayUsage;
    private TextView weekUsage;
    private TextView monthUsage;
    private TextView yearUsage;

    private TextView dayCost;
    private TextView weekCost;
    private TextView monthCost;
    private TextView yearCost;

    private TextView weekLabel;
    private TextView monthLabel;
    private TextView yearLabel;

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
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        dayUsage = (TextView) view.findViewById(R.id.day_usage);
        weekUsage = (TextView) view.findViewById(R.id.week_usage);
        monthUsage = (TextView) view.findViewById(R.id.month_usage);
        yearUsage = (TextView) view.findViewById(R.id.year_usage);

        dayCost = (TextView) view.findViewById(R.id.day_cost);
        weekCost = (TextView) view.findViewById(R.id.week_cost);
        monthCost = (TextView) view.findViewById(R.id.month_cost);
        yearCost = (TextView) view.findViewById(R.id.year_cost);

        weekLabel = (TextView) view.findViewById(R.id.week_label);
        monthLabel = (TextView) view.findViewById(R.id.month_label);
        yearLabel = (TextView) view.findViewById(R.id.year_label);

        return view;
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

            dayUsage.setText(FLOW_FORMAT.format(metrics.getDaily()) + " L");
            weekUsage.setText(FLOW_FORMAT.format(metrics.getWeekly()) + " L");
            monthUsage.setText(FLOW_FORMAT.format(metrics.getMonthly()) + " L");
            yearUsage.setText(FLOW_FORMAT.format(metrics.getYearly()) + " L");

            dayCost.setText(COST_FORMAT.format(metrics.getDayCost()) + " $");
            weekCost.setText(COST_FORMAT.format(metrics.getWeekCost()) + " $");
            monthCost.setText(COST_FORMAT.format(metrics.getMonthCost()) + " $");
            yearCost.setText(COST_FORMAT.format(metrics.getYearCost()) + " $");

            updateColors(metrics);
        }
    }

    @Subscribe
    public void readFlowError(Exception e) {
        if (isVisible()) {
            //Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void updateColors(Metrics metrics) {

        setDefaultColorsDay();
        setDefaultColorsWeek();
        setDefaultColorsMonth();
        setDefaultColorsYear();

        if (prefs.getBoolean(SettingsActivity.ENABLED, false)) {

            Double dT = Double.valueOf(prefs.getString(SettingsActivity.DAY_THRESHOLD, "1000"));
            Double wT = Double.valueOf(prefs.getString(SettingsActivity.WEEK_THRESHOLD, "1000"));
            Double mT = Double.valueOf(prefs.getString(SettingsActivity.MONTH_THRESHOLD, "1000"));
            Double yT = Double.valueOf(prefs.getString(SettingsActivity.YEAR_THRESHOLD, "1000"));

            if (dT < metrics.getDaily()) {
                dayUsage.setTextColor(Color.RED);
                dayCost.setTextColor(Color.RED);
            }

            if (wT < metrics.getWeekly()) {
                weekUsage.setTextColor(Color.RED);
                weekCost.setTextColor(Color.RED);
                weekLabel.setTextColor(Color.RED);
            }

            if (mT < metrics.getMonthly()) {
                monthUsage.setTextColor(Color.RED);
                monthCost.setTextColor(Color.RED);
                monthLabel.setTextColor(Color.RED);
            }

            if (yT < metrics.getYearly()) {
                yearUsage.setTextColor(Color.RED);
                yearCost.setTextColor(Color.RED);
                yearLabel.setTextColor(Color.RED);
            }
        }
    }

    private void setDefaultColorsDay() {
        dayUsage.setTextColor(0xFF402B2B);
        dayCost.setTextColor(0xFF402B2B);
    }

    private void setDefaultColorsWeek() {
        weekUsage.setTextColor(0xFF393131);
        weekCost.setTextColor(0xFF645757);
        weekLabel.setTextColor(0xFF312222);
    }

    private void setDefaultColorsMonth() {
        monthUsage.setTextColor(0xFF393131);
        monthCost.setTextColor(0xFF645757);
        monthLabel.setTextColor(0xFF312222);
    }

    private void setDefaultColorsYear() {
        yearUsage.setTextColor(0xFF393131);
        yearCost.setTextColor(0xFF645757);
        yearLabel.setTextColor(0xFF312222);
    }
}