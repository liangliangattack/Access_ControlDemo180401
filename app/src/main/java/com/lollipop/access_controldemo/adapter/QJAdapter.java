package com.lollipop.access_controldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lollipop.access_controldemo.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/2/22.
 */

public class QJAdapter extends RecyclerView.Adapter<QJAdapter.MyViewHolder>{

    private SimpleDateFormat sdf;
    private String str;
    private QJAdapter.OnItemClickListener mlistener;
    private QJAdapter.OnItemLongClickListener mlonglistener;

    private List<String> list;
    private Context mcontext;

    public QJAdapter(List<String> list , Context mcontext ,OnItemClickListener listener ,
                     OnItemLongClickListener longClickListener){
        this.list = list;
        this.mcontext = mcontext;
        this.mlistener = listener;
        this.mlonglistener = longClickListener;
    }

    @Override
    public QJAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.linear_item_qj, null);

        switch(viewType){
            case 0:
                QJAdapter.MyViewHolder myViewHolder3= new QJAdapter.MyViewHolder(view);
                myViewHolder3.tv_title.setText("事假");
                myViewHolder3.tv_content.setText("2018/3/8");
                return myViewHolder3;
            case 1:
                QJAdapter.MyViewHolder myViewHolder1= new QJAdapter.MyViewHolder(view);
                myViewHolder1.tv_title.setText("病假");
                myViewHolder1.tv_content.setText("2018/3/1");
                return myViewHolder1;
            //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));

            //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
//            //case 0:return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
            default:
//                Toast.makeText(mcontext, viewType+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(mcontext, viewType+"商店", Toast.LENGTH_SHORT).show();
//                Toast.makeText(mcontext, viewType+"的规划", Toast.LENGTH_SHORT).show();
//                MyLinearCenterAdapter.MyViewHolder myViewHolder3= new MyLinearCenterAdapter.MyViewHolder(view);
//                myViewHolder1.tv_title.setText("kjshdkj");
//                myViewHolder1.tv_content.setText("哦i啊u为广佛i");
//                myViewHolder1.tv_time.setText("lkhjsdkjhfgkj khasduhfaksh卡还是科技孵化");
//                myViewHolder1.iv_item.setImageResource(R.drawable.car);
//                return myViewHolder1;
        }

        return new QJAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            default:
                return 10;
        }
    }

    @Override
    public void onBindViewHolder(QJAdapter.MyViewHolder holder, final int position) {
        //设置各个控件的点击事件
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(position);
            }
        });

        holder.ll_main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mlonglistener.onLongClick(position);
                return true;
            }
        });
        //
//        sdf = new SimpleDateFormat("    hh   :   mm   :   ss");
//        str = sdf.format(new Date());
//        holder.tv_time.setText(str);


    }

    @Override
    public int getItemCount() {
        //return list.size();
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll_main;
        private TextView tv_title ,  tv_content;
        //private ImageView iv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_main = itemView.findViewById(R.id.ll_main);
            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_content = itemView.findViewById(R.id.tv_item_content);
            //iv_item = itemView.findViewById(R.id.iv_item);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

    public interface OnItemLongClickListener{
        void onLongClick(int pos);
    }
}
