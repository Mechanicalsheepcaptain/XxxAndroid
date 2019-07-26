package com.xxx.t7_25.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xxx.t7_25.R;
import com.xxx.t7_25.bean.UpBean;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    private ImageView miv;
    private Button mBtn;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        miv = view.findViewById(R.id.second_iv);
        mBtn = view.findViewById(R.id.second_btn);
    }

    private void initData() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            ShangChuan();
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            ShangChuan();
        } else {
            Toast.makeText(getContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }
    }
    private void ShangChuan() {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            file = new File(sd, "eee.png");
        }


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        MediaType mediaType = MediaType.parse("image/png");
        RequestBody requestBody = RequestBody.create(mediaType, file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1801B")
                .addFormDataPart("file", file.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .post(multipartBody)
                .url("http://yun918.cn/study/public/file_upload.php")
                .build();

        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("TAG", "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final UpBean upBean = gson.fromJson(string, UpBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (upBean != null) {
                                    if (upBean.getCode() == 200) {
                                        String url = upBean.getData().getUrl();
                                        Glide.with(getActivity()).load(upBean.getData().getUrl()).into(miv);
                                    } else {
                                        Toast.makeText(getActivity(), upBean.getRes(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getActivity(), upBean.getRes(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });


    }

}
