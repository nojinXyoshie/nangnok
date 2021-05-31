package com.example.nojinx2.nangnok.util;

import com.example.nojinx2.nangnok.data.DataChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("fetch_chart.php")
    Call<List<DataChart>> getChartInfo();
}
