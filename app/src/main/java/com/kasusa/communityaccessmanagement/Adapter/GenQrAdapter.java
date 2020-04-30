package com.kasusa.communityaccessmanagement.Adapter;

import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

import com.kasusa.communityaccessmanagement.GenQRActivity;
import com.kasusa.communityaccessmanagement.ListGenQrActivity;
import com.kasusa.communityaccessmanagement.R;
import com.kasusa.communityaccessmanagement.datacls.Dataclass;
        import com.kasusa.communityaccessmanagement.datacls.xiaoqu;
        import java.util.LinkedList;


/**
 * 定义的item的数据存放结构 mWordList
 */
public class GenQrAdapter extends RecyclerView.Adapter<GenQrAdapter.GenQrHolder>   {
    private final LinkedList<xiaoqu> mWordList;
    private LayoutInflater mInflater;

    public GenQrAdapter(Context context, LinkedList<xiaoqu> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    @NonNull
    @Override
    public GenQrAdapter.GenQrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_xiaoqu, parent, false);
        return new GenQrHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GenQrAdapter.GenQrHolder holder, int position) {
        xiaoqu mCurrent = mWordList.get(position);
        holder.xiaoquname.setText(mCurrent.name);
        holder.prov.setText(mCurrent.province);
        holder.city.setText(mCurrent.city);
        holder.area.setText(mCurrent.area);

    }

    @Override
    public int getItemCount() {
        return  mWordList.size();// 返回链表数目
    }


    /**
     * holder可以把数据和view的id绑定在一起。
     */
    class GenQrHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView xiaoquname;
        public final TextView city;
        public final TextView prov;
        public final TextView area;
        final GenQrAdapter GenQrAdapter;
        public GenQrHolder(View itemView, GenQrAdapter adapter) {
            super(itemView);
            xiaoquname = itemView.findViewById(R.id.textView31);
            prov = itemView.findViewById(R.id.textView32);
            city = itemView.findViewById(R.id.textView33);
            area = itemView.findViewById(R.id.textView34);
            this.GenQrAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        /**
         * 这个是让你的 item 被点击后的执行的函数。
         */
        @Override
        public void onClick(View v) {
            //获取你的正在点击的项目,并且保存 (我这里保存到的Dataclass是一个全局的Data取用类)
            int mPosition = getLayoutPosition();
            Dataclass.thexiaoqu = mWordList.get(mPosition);
            Intent intent = new Intent(v.getContext(), GenQRActivity.class);
            v.getContext().startActivity(intent);
            GenQrAdapter.notifyDataSetChanged();
        }
    }

}