package clothing_rental.canceline.com.clothingrental.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.data_base.Order;
import clothing_rental.canceline.com.clothingrental.details.rental.AdressAdapter;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;

/**
 * Created by kingShin on 2018/3/22.
 */

@Route(path = "/mine/OrderDetailActivity")
public class OrderDetailActivity extends BaseActivity {
    private Button close_btn;
    private TextView orderID;
    private ImageView picture;
    private TextView name;
    private TextView total;
    private RecyclerView recyclerView;

//    @Autowired(name = "Good")
//    Goods goods;

    @Autowired(name = "Order")
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        close_btn = findViewById(R.id.closeBtn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        orderID = findViewById(R.id.ordersIDDetailsTextView);
        orderID.setText("订单号："+order.getOrderID());

        picture = findViewById(R.id.ordersDetailsImageView);
        Glide.with(getContext()).load(order.getGoods().getPhoto().getUrl()).into(picture);

        name = findViewById(R.id.ordersDetailsTextView);
        name.setText(order.getGoods().getName());

        total = findViewById(R.id.ordersDetailsMoneyTextView);
        total.setText("合计：￥"+order.getTotalPrice()+"（含运费）");

        recyclerView = findViewById(R.id.orderDetailRecyclerView);
        VirtualLayoutManager vlayout = new VirtualLayoutManager(getContext());
        recyclerView.setLayoutManager(vlayout);
        DelegateAdapter delegateAdapter = new DelegateAdapter(vlayout);
        recyclerView.setAdapter(delegateAdapter);

        delegateAdapter.addAdapter(new Adress1Adapter(order.getName(),order.getPhone(),order.getAddress()));
        delegateAdapter.notifyDataSetChanged();
    }

    private class Adress1Adapter extends DelegateAdapter.Adapter<Adress1ViewHolder> {
        private String name;
        private String phone;
        private String address;

        public Adress1Adapter(String name1,String phone1,String adress1) {
            this.name = name1;
            this.phone = phone1;
            this.address = adress1;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }

        @Override
        public Adress1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rental2,parent,false);
            return new Adress1ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Adress1ViewHolder holder, int position) {
                holder.recyclerView_unAdress.setVisibility(View.GONE);
                holder.button.setVisibility(View.GONE);
                holder.linearLayout_adress.setVisibility(View.VISIBLE);
                holder.textView_name.setText("收件人："+ name);
                holder.textView_phone.setText("联系方式："+ phone);
                holder.textView_adress.setText("地址"+ address);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    private class Adress1ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_name;
        private TextView textView_phone;
        private TextView textView_adress;
        private RelativeLayout recyclerView_unAdress;
        private LinearLayout linearLayout_adress;
        private Button button;

        public Adress1ViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.personNameTextView);
            textView_phone = itemView.findViewById(R.id.phoneTextView);
            textView_adress = itemView.findViewById(R.id.adressTextView);
            recyclerView_unAdress = itemView.findViewById(R.id.unAdress);
            linearLayout_adress = itemView.findViewById(R.id.adress);
            button = itemView.findViewById(R.id.button);
        }
    }
}
