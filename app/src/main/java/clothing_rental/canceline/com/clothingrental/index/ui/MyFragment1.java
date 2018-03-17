package clothing_rental.canceline.com.clothingrental.index.ui;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseFragment;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
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
                if (e == null) {
                    Toast.makeText(getActivity(), "sucess", Toast.LENGTH_LONG).show();
                    mAdaper.clearData();
                    mAdaper.addData(list);
                    mAdaper.notifyDataSetChanged();
                }
            }
        });
    }

    private void onItemClick(Goods good, int position) {
        //do what u want to do
    }

    class MyAdaper extends RecyclerView.Adapter<MyAdaper.MyHolder> {

        private List<Goods> datas = new ArrayList<>();

        public void addData(List<Goods> datas) {
            this.datas.addAll(datas);
        }

        public void addData(Goods data) {
            this.datas.add(data);
        }

        public void clearData() {
            this.datas.clear();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout_fragment1, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyHolder holder, final int position) {
            final Goods data = datas.get(position);
            holder.title.setText(data.getGoodsID());
            Glide.with(getContext()).load(data.getPhoto().getUrl()).into(holder.image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(data, position);
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
