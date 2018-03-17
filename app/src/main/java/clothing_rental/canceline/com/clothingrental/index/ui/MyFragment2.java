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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.index.model.Item;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.support.v7.widget.StaggeredGridLayoutManager.HORIZONTAL;
import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

/**
 * Created by kingShin on 2018/2/7.
 */

public class MyFragment2 extends Fragment {
    private RecyclerView recyclerView2;
    private MyFragment2.MyAdaper mAdaper2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setAdapter(mAdaper2 = new MyFragment2.MyAdaper());
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));

        BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
        goodsBmobQuery.addWhereExists("objectId");
        goodsBmobQuery.setLimit(6);
        goodsBmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if (e==null){
//                    for (int i=0;i<6;i++){
//                        mAdaper.addData(new Item(list.get(0).getPhoto().getUrl(), list.get(0).getGoodsID()));
//                    }

//                    mAdaper2.addData(Arrays.asList(
//                            new Item(list.get(0).getPhoto().getUrl(), list.get(0).getGoodsID()),
//                            new Item(list.get(1).getPhoto().getUrl(), list.get(1).getGoodsID()),
//                            new Item(list.get(2).getPhoto().getUrl(), list.get(2).getGoodsID()),
//                            new Item(list.get(3).getPhoto().getUrl(), list.get(3).getGoodsID()),
//                            new Item(list.get(4).getPhoto().getUrl(), list.get(4).getGoodsID()),
//                            new Item(list.get(5).getPhoto().getUrl(), list.get(5).getGoodsID())
//                    ));
//                    mAdaper2.notifyDataSetChanged();
                }
            }
        });
    }

    class MyAdaper extends RecyclerView.Adapter<MyFragment2.MyAdaper.MyHolder> {

        private List<Item> datas = new ArrayList<>();

        public void addData(List<Item> datas) {
            this.datas.addAll(datas);
        }

        public void addData(Item data) {
            this.datas.add(data);
        }

        @Override
        public MyFragment2.MyAdaper.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout_fragment1, null);
            return new MyFragment2.MyAdaper.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyFragment2.MyAdaper.MyHolder holder, int position) {
            Item data = datas.get(position);
            holder.title.setText(data.getTitle());
            Glide.with(getContext()).load(data.getUrl()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    holder.image.setImageDrawable(resource);
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                }
            });
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
