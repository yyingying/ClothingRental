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

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
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
    private VirtualLayoutManager virtualLayoutManager;
    private DelegateAdapter delegateAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(virtualLayoutManager = new VirtualLayoutManager(getContext()));
        recyclerView2.setAdapter(delegateAdapter = new DelegateAdapter(virtualLayoutManager));

        BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
        goodsBmobQuery.addWhereExists("objectId");
        goodsBmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "sucess", Toast.LENGTH_LONG).show();
                    delegateAdapter.clear();
                    delegateAdapter.addAdapter(new GoodsAdapter(list,getContext()));
                    delegateAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
