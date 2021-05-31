package com.example.nojinx2.nangnok.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataChart;
//import com.example.nojinx2.nangnok.util.ApiClient;
import com.example.nojinx2.nangnok.util.ApiClient;
import com.example.nojinx2.nangnok.util.ApiInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartFragment extends Fragment {

    private PieChart mPieChart;

    public ChartFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        mPieChart = view.findViewById(R.id.pieChart);
        getChart(getArguments().getString("method"));

        return view;
    }

    private void getChart(String method){

        Call<List<DataChart>> call = ApiClient.getApiClient().create(ApiInterface.class).getChartInfo();

        call.enqueue(new Callback<List<DataChart>>() {
            @Override
            public void onResponse(Call<List<DataChart>> call, Response<List<DataChart>> response) {

                if(response.body()!=null){

                    List<PieEntry> pieEntries = new ArrayList<>();

                    for(DataChart chart : response.body()){
                        String jumlah = chart.getJumlahBean().getJumlah();
                        String jumlahx = chart.getJumlahxBean().getJumlahx();
                        Integer persenx = Integer.parseInt(jumlahx);
                        Integer persen = Integer.parseInt(jumlah);
                        pieEntries.add(new PieEntry(persen, chart.getFull_name()));
                    }

                    mPieChart.animateXY(3000, 3000);

                    PieDataSet pieDataSet = new PieDataSet(pieEntries, "Persentase Polling");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieData pieData = new PieData(pieDataSet);

                    mPieChart.setData(pieData);

                    Description description = new Description();
                    description.setText("Persentase Polling");
                    mPieChart.setDescription(description);
                    mPieChart.invalidate();
                }
            }

            @Override
            public void onFailure(Call<List<DataChart>> call, Throwable t) {

            }
        });
    }
}
