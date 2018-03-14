package clothing_rental.canceline.com.clothingrental.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;

import clothing_rental.canceline.com.clothingrental.R;

/**
 * Created by kingShin on 2018/3/14.
 */

public class Adapter1 extends DelegateAdapter<MyHolder>{
    private String aName;
    private String aUrl;
    private double aPrice;
    private double aRental_price;

    public Adapter1(Data datas){
        super();
        this.aName = datas.getName();
        this.aUrl = datas.getUrl();
        this.aPrice = datas.getPrice();
        this.aRental_price = datas.getRental_price();
    }

    @Override
    // 填充onCreateViewHolder方法返回的holder中的控件
    public void onBindViewHolder(Adapter1.MyHolder holder, int position) {
        // TODO Auto-generated method stub
        holder.Glide.with(context).load(aUrl).into(imageView);
        holder.textView_name.setText(aName);
        holder.textView_price.setText(aPrice);
        holder.textView_rprice.setText(aRental_price);
    }

    @Override
    // 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public Adapter1.MyHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        // 填充布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_details_1, null);
        Adapter1.MyHolder holder = new Adapter1.MyHolder(view);
        return holder;
    }

    // 定义内部类继承ViewHolder
    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView_name;
        private TextView textView_price;
        private TextView textView_rprice;

        public MyHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            textView_name = (TextView)view.findViewById(R.id.name);
            textView_price = (TextView)view.findViewById(R.id.price);
            textView_rprice = (TextView)view.findViewById(R.id.rental_price);

        }

    }
}
