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
import clothing_rental.canceline.com.clothingrental.index.model.Item;

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

        mAdaper2.addData(Arrays.asList(
                new Item("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518090959472&di=0b15db54b5538c4d500281d3cd336512&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa1ec08fa513d2697c494b7745efbb2fb4316d850.jpg", "1"),
                new Item("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=129829041,3031230996&fm=200&gp=0.jpg", "2"),
                new Item("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1645868236,1934982672&fm=27&gp=0.jpg", "3"),
                new Item("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2906541843,1492984080&fm=27&gp=0.jpg", "4"),
                new Item("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2958647648,694918788&fm=27&gp=0.jpg", "5"),
                new Item("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2207220898,2461703220&fm=27&gp=0.jpg", "6")
        ));
        mAdaper2.notifyDataSetChanged();
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
