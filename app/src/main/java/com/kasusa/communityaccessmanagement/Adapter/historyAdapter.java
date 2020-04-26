package com.kasusa.communityaccessmanagement.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
        import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
        import com.kasusa.communityaccessmanagement.datacls.history;
        import java.util.LinkedList;


/**
 * 定义的item的数据存放结构 mWordList
 */
public class historyAdapter extends RecyclerView.Adapter<historyAdapter.historyViewHolder>   {
    private final LinkedList<history> mWordList;
    private LayoutInflater mInflater;

    public historyAdapter(Context context, LinkedList<history> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    @NonNull
    @Override
    public historyAdapter.historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_history_in_out, parent, false);
        return new historyViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull historyAdapter.historyViewHolder holder, int position) {
        history mCurrent = mWordList.get(position);
        holder.historyname.setText(mCurrent.xiaoqu_name);
        holder.time.setText(mCurrent.action_time);
        if(mCurrent.in)
        {
            holder.In_Out.setText("进入区域");
            holder.In_Out.setTextColor(Color.parseColor("#17b54f"));
        }
        else   holder.In_Out.setText("离开区域");
    }

    @Override
    public int getItemCount() {
        return  mWordList.size();// 返回链表数目
    }


    /**
     * holder可以把数据和view的id绑定在一起。
     */
    class historyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView historyname;
        public final TextView In_Out;
        public final TextView time;
        final historyAdapter historyAdapter;
        public historyViewHolder(View itemView, historyAdapter adapter) {
            super(itemView);
            historyname = itemView.findViewById(R.id.textView43);
            time = itemView.findViewById(R.id.textView44);
            In_Out = itemView.findViewById(R.id.textView45);
            this.historyAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        /**
         * 这个是让你的 item 被点击后的执行的函数。
         */
        @Override
        public void onClick(View v) {
            //什么都不做
        }
    }

}