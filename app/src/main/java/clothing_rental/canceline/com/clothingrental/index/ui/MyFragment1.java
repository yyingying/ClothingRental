package clothing_rental.canceline.com.clothingrental.index.ui;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseFragment;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.index.model.Item;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

/**
 * Created by kingShin on 2018/2/7.
 */
public class MyFragment1 extends BaseFragment {

    private RecyclerView recyclerView;
    private MyAdaper mAdaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mAdaper = new MyAdaper());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));

        BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
        goodsBmobQuery.addWhereExists("objectId");
        goodsBmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(),"sucess",Toast.LENGTH_LONG).show();
                    String[]url = new  String[10];
                    String[]goodsID = new String[10];
                    for (int i=0;i<10;i++){
                        url[i]=list.get(i).getPhoto().getUrl();
                        goodsID[i]=list.get(i).getGoodsID().toString();
                        mAdaper.addData(new Item(url[i],goodsID[i]));
                    }
                    mAdaper.notifyDataSetChanged();
                }
            }
        });
    }

    class MyAdaper extends RecyclerView.Adapter<MyAdaper.MyHolder> {

        private List<Item> datas = new ArrayList<>();

        public void addData(List<Item> datas) {
            this.datas.addAll(datas);
        }

        public void addData(Item data) {
            this.datas.add(data);
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout_fragment1, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, int position) {
            Item data = datas.get(position);
            holder.title.setText(data.getTitle());
            Glide.with(getContext()).load(data.getUrl()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView image;

            public MyHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.titleText);
                image = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
