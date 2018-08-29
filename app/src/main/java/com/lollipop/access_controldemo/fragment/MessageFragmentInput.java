package com.lollipop.access_controldemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lollipop.access_controldemo.R;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MessageFragmentInput extends Fragment {

    private int pos ;

//    private TextView tv_title;
//    private TextView tv_content;
//    private TextView tv_time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view ;

        switch (getPos()){
            default:view = inflater.inflate(R.layout.message_input,null , false);break;
            case 0:view = inflater.inflate(R.layout.message_input,null , false);break;
            case 1:view = inflater.inflate(R.layout.message_input2,null , false);break;
            case 2:view = inflater.inflate(R.layout.message_input3,null , false);break;
            case 3:view = inflater.inflate(R.layout.message_input6,null , false);break;
            case 4:view = inflater.inflate(R.layout.message_input5,null , false);break;
            case 5:view = inflater.inflate(R.layout.message_input7,null , false);break;
        }

        return view;
    }

//    private void initView(View view) {
//        tv_title = view.findViewById(R.id.message_input_tv_title);
//        tv_content = view.findViewById(R.id.message_input_tv_content);
//        tv_time = view.findViewById(R.id.message_input_tv_time);
//    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public int getPos(){
        return this.pos;
    }
}
