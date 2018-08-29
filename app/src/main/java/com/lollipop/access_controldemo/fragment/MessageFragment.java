package com.lollipop.access_controldemo.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lollipop.access_controldemo.R;
import com.lollipop.access_controldemo.adapter.MyLinearAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<String> list;


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_message, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        recyclerView.addItemDecoration(new MyDecoration());//设置周边样式


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<String>();

        for(int i=0;i<30;i++){
            list.add(" "+(char)i);
        }

        recyclerView.setAdapter(new MyLinearAdapter(list, getContext(), new MyLinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                //设置点击
                //Toast.makeText(getContext(), "CLICK.." + pos, Toast.LENGTH_SHORT).show();
                MessageFragmentInput messageFragmentInput = new MessageFragmentInput();
                messageFragmentInput.setPos(pos);

                //Fragmnet的跳转
                getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.ll_basic, messageFragmentInput)
                    .commit();
            }
        }
                //设置长时间点击事件
                , new MyLinearAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(int pos) {
                Toast.makeText(getContext(), "LongCLICK.." + pos, Toast.LENGTH_SHORT).show();
            }
        }));
        return view;
    }

    class MyDecoration extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //设置分割线
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }

}
