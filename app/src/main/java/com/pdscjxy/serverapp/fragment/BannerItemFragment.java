package com.pdscjxy.serverapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.pdscjxy.serverapp.MainActivity;
import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.fragment.base.BaseFragment;
import com.pdscjxy.serverapp.util.PrefUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/10/26.
 */

public class BannerItemFragment extends BaseFragment{
    @Bind(R.id.image)
    ImageView image;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setView(R.layout.fragment_baner);
//        initView();
        initData();
    }

    private void initData() {
        // TODO Auto-generated method stub
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            int index = bundle.getInt("index");
            if(index==0){
                image.setImageResource(R.mipmap.guidepage_1);
            }else if(index==1){
                image.setImageResource(R.mipmap.guidepage_2);
            }else{
                image.setImageResource(R.mipmap.guidepage_3);
                image.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // TODO Auto-generated method stub
                        if(event.getAction()==MotionEvent.ACTION_DOWN){
                            PrefUtils.setPrefBoolean(getActivity(), "isFirst", false);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                        return true;
                    }
                });
            }
        }
    }
//
//    private void initView() {
//        // TODO Auto-generated method stub
//        image = (ImageView) findViewById(R.id.image);
//    }
}