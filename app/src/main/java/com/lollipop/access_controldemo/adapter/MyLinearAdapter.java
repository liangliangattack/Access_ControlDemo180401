package com.lollipop.access_controldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lollipop.access_controldemo.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/2/19.
 */

public class MyLinearAdapter extends RecyclerView.Adapter<MyLinearAdapter.MyViewHolder>
        {

    private SimpleDateFormat sdf;
    private String str;
    private OnItemClickListener mlistener;
    private OnItemLongClickListener mlonglistener;

    private List<String> list;
    private Context mcontext;

    public MyLinearAdapter(List<String> list , Context mcontext , OnItemClickListener listener ,
                           OnItemLongClickListener longClickListener){
        this.list = list;
        this.mcontext = mcontext;
        this.mlistener = listener;
        this.mlonglistener = longClickListener;
    }

    @Override
    public MyLinearAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false);

        switch(viewType){
            case 0:
                MyViewHolder myViewHolder3= new MyViewHolder(view);
                myViewHolder3.tv_title.setText("上班迟到通知！");
                myViewHolder3.tv_content.setText("由于昨天上班迟到，算半天矿工！"+"\n"+
                        "另注：在规定时间内迟到10分钟者算迟到，5分钟以上算早退；迟到一个小时以上算半天矿工。如遇特殊情况，需及时请假并告知原因。");
                myViewHolder3.tv_time.setText("2018/3/8");
                myViewHolder3.iv_item.setImageResource(R.drawable.delay);
                return myViewHolder3;
            case 1:
                MyViewHolder myViewHolder= new MyViewHolder(view);
                myViewHolder.tv_title.setText("召开会议");
                myViewHolder.tv_content.setText("下午到一楼会议厅开会！");
                myViewHolder.tv_time.setText("2018/3/7");
                myViewHolder.iv_item.setImageResource(R.drawable.bg);
                return myViewHolder;
            case 2:
                MyViewHolder myViewHolder4= new MyViewHolder(view);
                myViewHolder4.tv_title.setText("天气转冷，暴雨");
                myViewHolder4.tv_content.setText("天气预报显示这两天会有下雨，请大家注意保暖，并且上班别迟到！");
                myViewHolder4.tv_time.setText("2018/3/6");
                myViewHolder4.iv_item.setImageResource(R.drawable.bg);
                return myViewHolder4;
                //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
            case 3:
                MyViewHolder myViewHolder5= new MyViewHolder(view);
                myViewHolder5.tv_title.setText("停电通知");
                myViewHolder5.tv_content.setText("由于电缆的维护，造成公司短时间停电，预计1个小时以内来电，特此通知");
                myViewHolder5.tv_time.setText("2018/2/27");
                myViewHolder5.iv_item.setImageResource(R.drawable.bg);
                return myViewHolder5;
                //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
            case 4:
                MyViewHolder myViewHolder6= new MyViewHolder(view);
                myViewHolder6.tv_title.setText("关于报销通知");
                myViewHolder6.tv_content.setText("报销条件：1．报销条件是指公司日常生产用辅助材料．后勤福利．差旅费．业务招待费．办公用的电脑耗材（如打印纸、传真纸、墨盒、硒鼓、磁盘等）．办公文具（如笔、计算器、文件夹、便笺等）．生产．办公场所的零星维修费用和办公过程中发生的各项杂费等。2．需要购买物品的部门，必须填写《申购单》报主管领导审批，同意后由公司指定的员工购买，" +
                        "票据报销时须附申购单，如有变更，及时通知财务部备案。办公用品报销必须附物品清单。");
                myViewHolder6.tv_time.setText("2018/2/20");
                myViewHolder6.iv_item.setImageResource(R.drawable.bg);
                return myViewHolder6;
                //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
            case 5:
                MyViewHolder myViewHolder2= new MyViewHolder(view);
                myViewHolder2.tv_title.setText("狗年吉祥！ 万事如意！！！");
                myViewHolder2.tv_content.setText("天狗守护你；春风洋溢你；家人关心你；" +
                        "爱情滋润你；财神宠信你；朋友忠于你；我会祝福你；幸运之星永远照着你！");
                myViewHolder2.tv_time.setText("2018/2/16");
                myViewHolder2.iv_item.setImageResource(R.drawable.dog);
                return myViewHolder2;
                //return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
//            //case 0:return new MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.linear_item , parent , false));
            default:
                Toast.makeText(mcontext, viewType+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(mcontext, viewType+"商店", Toast.LENGTH_SHORT).show();
                Toast.makeText(mcontext, viewType+"的规划", Toast.LENGTH_SHORT).show();
                MyViewHolder myViewHolder1= new MyViewHolder(view);
                myViewHolder1.tv_title.setText("kjshdkj");
                myViewHolder1.tv_content.setText("哦i啊u为广佛i");
                myViewHolder1.tv_time.setText("lkhjsdkjhfgkj khasduhfaksh卡还是科技孵化");
                myViewHolder1.iv_item.setImageResource(R.drawable.car);
                return myViewHolder1;
        }
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
    public void onBindViewHolder(MyLinearAdapter.MyViewHolder holder, final int position) {
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
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll_main;
        private TextView tv_title , tv_time , tv_content;
        private ImageView iv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_main = itemView.findViewById(R.id.ll_main);
            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_time = itemView.findViewById(R.id.tv_item_time);
            tv_content = itemView.findViewById(R.id.tv_item_content);
            iv_item = itemView.findViewById(R.id.iv_item);
        }
    }

//            public class MyViewHolder2 extends RecyclerView.ViewHolder{
//
//                private LinearLayout ll_main;
//                private TextView tv_title , tv_time , tv_content;
//                private ImageView iv_item;
//
//                public MyViewHolder2(View itemView) {
//                    super(itemView);
//                    ll_main = itemView.findViewById(R.id.ll_main);
//                    tv_title = itemView.findViewById(R.id.tv_item_title);
//                    tv_time = itemView.findViewById(R.id.tv_item_time);
//                    tv_content = itemView.findViewById(R.id.tv_item_content);
//                    iv_item = itemView.findViewById(R.id.iv_item);
//                }
//            }
//
//            public class MyViewHolder3 extends RecyclerView.ViewHolder{
//
//                private LinearLayout ll_main;
//                private TextView tv_title , tv_time , tv_content;
//                private ImageView iv_item;
//
//                public MyViewHolder3(View itemView) {
//                    super(itemView);
//                    ll_main = itemView.findViewById(R.id.ll_main);
//                    tv_title = itemView.findViewById(R.id.tv_item_title);
//                    tv_time = itemView.findViewById(R.id.tv_item_time);
//                    tv_content = itemView.findViewById(R.id.tv_item_content);
//                    iv_item = itemView.findViewById(R.id.iv_item);
//                }
//            }
//
//            public class MyViewHolder4 extends RecyclerView.ViewHolder{
//
//                private LinearLayout ll_main;
//                private TextView tv_title , tv_time , tv_content;
//                private ImageView iv_item;
//
//                public MyViewHolder4(View itemView) {
//                    super(itemView);
//                    ll_main = itemView.findViewById(R.id.ll_main);
//                    tv_title = itemView.findViewById(R.id.tv_item_title);
//                    tv_time = itemView.findViewById(R.id.tv_item_time);
//                    tv_content = itemView.findViewById(R.id.tv_item_content);
//                    iv_item = itemView.findViewById(R.id.iv_item);
//                }
//            }
//
//            public class MyViewHolder5 extends RecyclerView.ViewHolder{
//
//                private LinearLayout ll_main;
//                private TextView tv_title , tv_time , tv_content;
//                private ImageView iv_item;
//
//                public MyViewHolder5(View itemView) {
//                    super(itemView);
//                    ll_main = itemView.findViewById(R.id.ll_main);
//                    tv_title = itemView.findViewById(R.id.tv_item_title);
//                    tv_time = itemView.findViewById(R.id.tv_item_time);
//                    tv_content = itemView.findViewById(R.id.tv_item_content);
//                    iv_item = itemView.findViewById(R.id.iv_item);
//                }
//            }
//
//            public class MyViewHolder6 extends RecyclerView.ViewHolder{
//
//                private LinearLayout ll_main;
//                private TextView tv_title , tv_time , tv_content;
//                private ImageView iv_item;
//
//                public MyViewHolder6(View itemView) {
//                    super(itemView);
//                    ll_main = itemView.findViewById(R.id.ll_main);
//                    tv_title = itemView.findViewById(R.id.tv_item_title);
//                    tv_time = itemView.findViewById(R.id.tv_item_time);
//                    tv_content = itemView.findViewById(R.id.tv_item_content);
//                    iv_item = itemView.findViewById(R.id.iv_item);
//                }
//            }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

    public interface OnItemLongClickListener{
        void onLongClick(int pos);
    }
}
