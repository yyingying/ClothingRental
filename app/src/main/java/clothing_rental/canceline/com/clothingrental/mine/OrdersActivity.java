package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.data_base.Order;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by kingShin on 2018/3/1.
 */

@Route(path="/mine/Orders")

public class OrdersActivity extends Activity {
    private Button btn_exit;
    private RecyclerView oRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        //退出的按钮
        btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //布局
        oRecyclerView = findViewById(R.id.ordersRecyclerView);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        oRecyclerView.setLayoutManager(layoutManager);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        oRecyclerView.setAdapter(delegateAdapter);

        //查找数据
        BmobQuery<Order> orderBmobQuery = new BmobQuery<>();
        orderBmobQuery.addWhereEqualTo("personID", LoginUtil.getPersonID());
        orderBmobQuery.include("goods");
        orderBmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if(e==null){
                    Toast.makeText(OrdersActivity.this,"find.sucess",Toast.LENGTH_LONG).show();
                    //设置适配器
                    delegateAdapter.addAdapter(new OrdersAdapter(list));
                    delegateAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private class OrdersAdapter extends DelegateAdapter.Adapter<OrdersViewHolder> {
        private List<Order> orderList;

        public OrdersAdapter(List<Order> orders) {
            this.orderList = orders;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @Override
        public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_orders,parent,false);
            return new OrdersViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final OrdersViewHolder holder, int position) {
            final Order order = orderList.get(position);
            holder.odersID.setText("订单号："+ String.valueOf(order.getOrderID()));
            holder.total.setText("合计：￥"+ order.getTotalPrice()+"（含运费）");

            //直接include了good 所以可以直接调用
//            BmobQuery<Goods> goodsBmobQuery = new BmobQuery<>();
//            goodsBmobQuery.addWhereEqualTo("goodsID",order.getGoodsID());
//            goodsBmobQuery.findObjects(new FindListener<Goods>() {
//                @Override
//                public void done(List<Goods> list, BmobException e) {
//                    if(e==null){
//                        Toast.makeText(OrdersActivity.this,"good.find.sucess",Toast.LENGTH_LONG).show();
//                        goods = list.get(0);
//                        Glide.with(getApplicationContext()).load(list.get(0).getPhoto().getUrl()).into(holder.imageView);
//                        holder.name.setText(list.get(0).getName());
//                    }
//                }
//            });

            Glide.with(getApplicationContext()).load(order.getGoods().getPhoto().getUrl()).into(holder.imageView);
            holder.name.setText(order.getGoods().getName());

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(order,holder.getAdapterPosition());
                }
            });

        }

        @Override
        public int getItemCount() {
            return orderList.size();
        }
    }

    private void onItemClick(Order order, int adapterPosition) {
        //订单界面
        //把order和good的信息传过去
        ARouter.getInstance().build("/mine/OrderDetailActivity")
//                .withParcelable("Good",goods)
                .withParcelable("Order",order)
                .navigation(this);

    }

    class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView odersID;
        ImageView imageView;
        TextView name;
        TextView total;
        LinearLayout layout;

        OrdersViewHolder(View itemView) {
            super(itemView);
            odersID = itemView.findViewById(R.id.ordersIDTextView);
            imageView = itemView.findViewById(R.id.ordersImageView);
            name = itemView.findViewById(R.id.ordersTextView);
            total = itemView.findViewById(R.id.ordersMoneyTextView);
            layout = itemView.findViewById(R.id.ordersLinearLayout);
        }
    }
}
