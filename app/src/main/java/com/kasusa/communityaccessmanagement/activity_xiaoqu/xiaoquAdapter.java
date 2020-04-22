package com.kasusa.communityaccessmanagement.activity_xiaoqu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
import com.kasusa.communityaccessmanagement.datacls.xiaoqu;
import java.util.LinkedList;


/**
 * 定义的item的数据存放结构 mWordList
 */
public class xiaoquAdapter extends RecyclerView.Adapter<xiaoquAdapter.xiaoquViewHolder>   {
    private final LinkedList<xiaoqu> mWordList;
    private LayoutInflater mInflater;

    public xiaoquAdapter(Context context, LinkedList<xiaoqu> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    @NonNull
    @Override
    public xiaoquAdapter.xiaoquViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_xiaoqu, parent, false);// 上面的R.layout.wordlist_item 更换为自己写的item xml文件
        return new xiaoquViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull xiaoquAdapter.xiaoquViewHolder holder, int position) {//  这里是初始化item的时候用的settext函数,更改为自己需要的
        xiaoqu mCurrent = mWordList.get(position); // mCurrent是现在的链表指针位置,根据你的链表类型,更改mCurrent类型
        holder.xiaoquname.setText(mCurrent.name);
        holder.prov.setText(mCurrent.province);
        holder.city.setText(mCurrent.city);
        holder.area.setText(mCurrent.area);

    }

    @Override
    public int getItemCount() {
        return  mWordList.size();
    }


    class xiaoquViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView xiaoquname;
        public final TextView city;
        public final TextView prov;
        public final TextView area;
        final xiaoquAdapter xiaoquAdapter;
        public xiaoquViewHolder(View itemView, xiaoquAdapter adapter) {
            super(itemView);
            xiaoquname = itemView.findViewById(R.id.textView31);
            prov = itemView.findViewById(R.id.textView32);
            city = itemView.findViewById(R.id.textView33);
            area = itemView.findViewById(R.id.textView34);
            this.xiaoquAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {// 设定点击一个item要做的事情
            int mPosition = getLayoutPosition();
            Dataclass.thexiaoqu = mWordList.get(mPosition);
            Log.println(Log.INFO,"meow","click item thexiaoqu : "+Dataclass.thexiaoqu.toString());
            Intent intent = new Intent(v.getContext(), MakeSureXiaoquActivity.class);
            v.getContext().startActivity(intent);
            xiaoquAdapter.notifyDataSetChanged();
        }
    }

}