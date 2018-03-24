package clothing_rental.canceline.com.clothingrental.index.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;

/**
 * Created by kingShin on 2018/3/24.
 */

 public class GoodsAdapter extends DelegateAdapter.Adapter<GoodsAdapter.GoodsViewHolder> {
    private List<Goods> datas;
    private Context mContext;

    public GoodsAdapter(List<Goods> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StaggeredGridLayoutHelper(2);
    }

    @Override
    public GoodsAdapter.GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_fragment1, parent, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GoodsAdapter.GoodsViewHolder holder, int position) {
        final Goods data = datas.get(position);
        holder.title.setText(String.valueOf(data.getGoodsID()));
        Glide.with(mContext).load(data.getPhoto().getUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(data, holder.getAdapterPosition());
            }
        });
    }

    private void onItemClick(Goods good, int position) {
        //do what u want to do
        ARouter.getInstance().build("/details/DetailsActivity")
                .withParcelable("Good", good)
                .withString("goodsObjectID",good.getObjectId())
                .navigation(mContext);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        GoodsViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
