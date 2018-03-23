package clothing_rental.canceline.com.clothingrental.index.ui;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseFragment;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by kingShin on 2018/2/7.
 */
public class MyFragment1 extends BaseFragment {

    private RecyclerView recyclerView;
    private VirtualLayoutManager vlayout;
    private DelegateAdapter mAdaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(vlayout = new VirtualLayoutManager(getContext()));
        recyclerView.setAdapter(mAdaper = new DelegateAdapter(vlayout));

        BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
        goodsBmobQuery.addWhereExists("objectId");
        goodsBmobQuery.findObjects(new FindListener<Goods>() {
            @Override
            public void done(List<Goods> list, BmobException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "sucess", Toast.LENGTH_LONG).show();
                    mAdaper.clear();
                    mAdaper.addAdapter(new BannerAdapter());
                    mAdaper.addAdapter(new GoodsAdapter(list));
                    mAdaper.notifyDataSetChanged();
                }
            }
        });
    }

    private void onItemClick(Goods good, int position) {
        //do what u want to do
        ARouter.getInstance().build("/details/DetailsActivity")
                .withParcelable("Good", good)
                .withString("goodsObjectID",good.getObjectId())
                .navigation(getContext());
    }

    class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {
        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }

        @Override
        public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BannerViewHolder(new ImageView(getContext()));
        }

        @Override
        public void onBindViewHolder(BannerViewHolder holder, int position) {
            holder.banner.setImageResource(R.drawable.logo);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {

            ImageView banner;

            BannerViewHolder(ImageView itemView) {
                super(itemView);
                banner = itemView;
            }
        }
    }

    class GoodsAdapter extends DelegateAdapter.Adapter<GoodsAdapter.GoodsViewHolder> {

        private final List<Goods> datas;

        private GoodsAdapter(List<Goods> datas) {
            this.datas = datas;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new StaggeredGridLayoutHelper(2);
        }

        @Override
        public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout_fragment1, parent, false);
            return new GoodsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final GoodsViewHolder holder, int position) {
            final Goods data = datas.get(position);
            holder.title.setText(String.valueOf(data.getGoodsID()));
            Glide.with(getContext()).load(data.getPhoto().getUrl()).into(holder.image);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(data, holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class GoodsViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView image;

            GoodsViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.titleText);
                image = itemView.findViewById(R.id.imageView);
            }
        }
    }
}
