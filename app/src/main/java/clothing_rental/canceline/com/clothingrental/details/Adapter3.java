package clothing_rental.canceline.com.clothingrental.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;

/**
 * Created by kingShin on 2018/3/14.
 */

public class Adapter3 extends DelegateAdapter.Adapter<Adapter3.MyHolder>{
    private double aID;
    private double aPrice;
    private String aPlace;
    private Context mContext;

    public Adapter3(Goods goods, Context context) {
        super();
        this.aID = goods.getGoodsID();
        this.aPrice = goods.getPrice();
        this.aPlace = goods.getPlace();
        mContext = context;
    }

    @Override
    // 填充onCreateViewHolder方法返回的holder中的控件
    public void onBindViewHolder(Adapter3.MyHolder holder, int position) {
        holder.textView_ID.setText(String.valueOf(aID));
        holder.textView_price.setText(String.valueOf(aPrice));
        holder.textView_place.setText(String.valueOf(aPlace));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    // 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public Adapter3.MyHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        // 填充布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_details_3, null);
        Adapter3.MyHolder holder = new Adapter3.MyHolder(view);
        return holder;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    // 定义内部类继承ViewHolder
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView_ID;
        private TextView textView_price;
        private TextView textView_place;

        public MyHolder(View view) {
            super(view);
            textView_ID = (TextView) view.findViewById(R.id.ID);
            textView_price = (TextView) view.findViewById(R.id.price);
            textView_place = (TextView) view.findViewById(R.id.place);
        }
    }
}
