package clothing_rental.canceline.com.clothingrental.details.rental;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.data_base.Goods;
import clothing_rental.canceline.com.clothingrental.data_base.Order;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by kingShin on 2018/3/19.
 */

@Route(path = "/details/rental/RentalActivity")
public class RentalActivity extends BaseActivity {
    private RecyclerView myRecyclerView;
    //private String personID;
    private Button close_btn;
    private Button pay_btn;
    private String totalPrice;
    private Adress orderAddress;
    //goodsID
    @Autowired(name = "Good")
    Goods goods;

    @Autowired(name = "goodsObjectID")
    String goodsObjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        //各个按钮
        close_btn = (Button)findViewById(R.id.closeBtn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pay_btn = (Button) findViewById(R.id.payButton);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginUtil.getAdress()==null){
                    Toast.makeText(RentalActivity.this,"未填写地址，请填写地址后再支付",Toast.LENGTH_LONG).show();
                }else {
                    if(totalPrice == null){
                        Toast.makeText(RentalActivity.this,"未选择租赁时间，请选择后再支付",Toast.LENGTH_LONG).show();
                    }else{
                        //支付界面
                        //暂时当成这里就确认支付
                        final Order order = new Order();
                        order.setOrderID(LoginUtil.getPersonID()+goods.getGoodsID().toString());
                        order.setPersonID(LoginUtil.getPersonID());
                        goods.setObjectId(goodsObjectID);
                        order.setGoods(goods);
                        order.setTotalPrice(totalPrice);
                        order.setName(LoginUtil.getAdress().getPersonName());
                        order.setPhone(LoginUtil.getAdress().getPhone());
                        order.setAddress(LoginUtil.getAdress().getAdress());
                        order.setWeight(1);
                        order.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(RentalActivity.this,"save.success",Toast.LENGTH_LONG).show();
                                    //把order和good传过去订单详情界面
                                    ARouter.getInstance().build("/mine/OrderDetailActivity")
//                                          .withParcelable("Good",goods)
                                            .withParcelable("Order",order)
                                            .navigation(getContext());
                                    finish();
                                }
                            }
                        });
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //得到控件
        myRecyclerView = findViewById(R.id.rental_recycle_view);
        //设置布局管理器
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);

        //设置适配器
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        myRecyclerView.setAdapter(delegateAdapter);

        //各部分的适配器
        delegateAdapter.addAdapter(new GoodsAdapter(goods));
        orderAddress = LoginUtil.getAdress();
        delegateAdapter.addAdapter(new AdressAdapter(orderAddress));
        delegateAdapter.addAdapter(new CountAdapter(goods));
        delegateAdapter.notifyDataSetChanged();
    }

    private class GoodsAdapter extends DelegateAdapter.Adapter<GoodsAdapter.GoodsViewHolder> {
        private String mName;
        private String mUrl;

        //构造函数
        public GoodsAdapter(Goods goods) {
            this.mName = goods.getName();
            this.mUrl = goods.getPhoto().getUrl();
        }

        //布局类型
        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }

        //重写方法，返回一个自定义的Viewholder
        @Override
        public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rental1,parent,false);
            return new GoodsViewHolder(view);
        }

        //填充控件
        @Override
        public void onBindViewHolder(GoodsViewHolder holder, int position) {
            Glide.with(getContext()).load(mUrl).into(holder.imageView_photo);
            holder.textView_name.setText(mName);
        }

        //获取个数
        @Override
        public int getItemCount() {
            return 1;
        }

        //设置显示的位置
        class GoodsViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView_photo;
            private TextView textView_name;

            GoodsViewHolder(View itemView) {
                super(itemView);
                imageView_photo = itemView.findViewById(R.id.photoImageView);
                textView_name = itemView.findViewById(R.id.nameTextView);
            }
        }
    }

    private class AdressAdapter extends DelegateAdapter.Adapter<AdressAdapter.AdressViewHolder> {
        private String mName;
        private String mPhone;
        private String mAdress;
        private int size;

        //构造函数 1、需要知道用户ID才能得知地址 所以要把Person传进来
//        public AdressAdapter(List<Adress> Adress) {
//            this.size = Adress.size();
//            if (size!=0){
//                this.mName = Adress.get(0).getPersonName();
//                this.mPhone = Adress.get(0).getPhone();
//                this.mAdress = Adress.get(0).getAdress();
//            }
//        }
        public AdressAdapter(Adress adress){
            if (adress==null){
                size=0;
            }else {
                size=1;
                this.mName = adress.getPersonName();
                this.mPhone = adress.getPhone();
                this.mAdress = adress.getAdress();
            }
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }

        @Override
        public AdressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rental2,parent,false);
            return new AdressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AdressViewHolder holder, int position) {
            if(size ==0){
                holder.recyclerView_unAdress.setVisibility(View.VISIBLE);
                holder.linearLayout_adress.setVisibility(View.GONE);
                holder.btn_addAdress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginUtil.setFirst_set_address(true);
                        Router.navTo("/details/rental/AddAdressActivity");
                    }
                });

            }else{
                holder.recyclerView_unAdress.setVisibility(View.GONE);
                holder.linearLayout_adress.setVisibility(View.VISIBLE);
                holder.textView_name.setText("收件人："+mName);
                holder.textView_phone.setText("联系方式："+mPhone);
                holder.textView_adress.setText("地址"+mAdress);
                holder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Router.navTo("/mine/AdressManagementActivity");
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        class AdressViewHolder extends RecyclerView.ViewHolder {
            private TextView textView_name;
            private TextView textView_phone;
            private TextView textView_adress;
            private RelativeLayout recyclerView_unAdress;
            private LinearLayout linearLayout_adress;
            private Button btn_addAdress;
            private Button btn;

            public AdressViewHolder(View itemView) {
                super(itemView);
                textView_name = itemView.findViewById(R.id.personNameTextView);
                textView_phone = itemView.findViewById(R.id.phoneTextView);
                textView_adress = itemView.findViewById(R.id.adressTextView);
                recyclerView_unAdress = itemView.findViewById(R.id.unAdress);
                linearLayout_adress = itemView.findViewById(R.id.adress);
                btn_addAdress = itemView.findViewById(R.id.addAdressButton);
                btn = itemView.findViewById(R.id.button);
            }
        }
    }

    private class CountAdapter extends DelegateAdapter.Adapter<CountAdapter.CountViewHolder> {
        private Integer rentalPrice;
        private boolean pressPictureFlag1;
        private boolean pressPictureFlag2;
        private boolean pressPictureFlag3;

        //构造函数
        public CountAdapter(Goods goods) {
            this.rentalPrice = goods.getRental_price();
        }

        //布局类型
        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new SingleLayoutHelper();
        }

        //重写方法，返回一个自定义的Viewholder
        @Override
        public CountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_rental3, parent, false);
            return new CountViewHolder(view);
        }

        //填充控件
        @Override
        public void onBindViewHolder(final CountViewHolder holder, int position) {

            holder.five_img.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (pressPictureFlag1 == false) {
                            holder.textView_rental_price.setText("￥"+(rentalPrice*5));
                            totalPrice = String.valueOf(rentalPrice*5+20);
                            holder.all_txt.setText("总计：￥"+(rentalPrice*5+20));
                            holder.five_img.setBackgroundResource(R.drawable.five_pressed);
                            holder.seven_img.setBackgroundResource(R.drawable.seven);
                            holder.ten_img.setBackgroundResource(R.drawable.ten);
                            pressPictureFlag1 = true;
                            pressPictureFlag2 = false;
                            pressPictureFlag3 = false;
                        } else {
                            holder.five_img.setBackgroundResource(R.drawable.five);
                            pressPictureFlag1 = false;
                            holder.textView_rental_price.setText("日租：￥"+(rentalPrice));
                            holder.all_txt.setText("总计：请选择租赁天数");
                        }
                    }
                    return false;
                }
            });

            holder.seven_img.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (pressPictureFlag2 == false) {
                            holder.textView_rental_price.setText("￥"+(rentalPrice*7));
                            holder.all_txt.setText("总计：￥"+(rentalPrice*7+20));
                            totalPrice = String.valueOf(rentalPrice*7+20);
                            holder.seven_img.setBackgroundResource(R.drawable.seven_pressed);
                            holder.five_img.setBackgroundResource(R.drawable.five);
                            holder.ten_img.setBackgroundResource(R.drawable.ten);
                            pressPictureFlag2 = true;
                            pressPictureFlag1 = false;
                            pressPictureFlag3 = false;
                        } else {
                            holder.seven_img.setBackgroundResource(R.drawable.seven);
                            pressPictureFlag2 = false;
                            holder.textView_rental_price.setText("日租：￥"+(rentalPrice));
                            holder.all_txt.setText("总计：请选择租赁天数");
                        }
                    }
                    return false;
                }
            });

            holder.ten_img.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (pressPictureFlag3 == false) {
                            holder.textView_rental_price.setText("￥"+(rentalPrice*10));
                            totalPrice = String.valueOf(rentalPrice*5+20);
                            holder.all_txt.setText("总计：￥"+(rentalPrice*10+20));
                            holder.ten_img.setBackgroundResource(R.drawable.ten_pressed);
                            holder.five_img.setBackgroundResource(R.drawable.five);
                            holder.seven_img.setBackgroundResource(R.drawable.seven);
                            pressPictureFlag3 = true;
                            pressPictureFlag1 = false;
                            pressPictureFlag2 = false;
                        } else {
                            holder.ten_img.setBackgroundResource(R.drawable.ten);
                            pressPictureFlag3 = false;
                            holder.textView_rental_price.setText("日租：￥"+(rentalPrice));
                            holder.all_txt.setText("总计：请选择租赁天数");
                        }
                    }
                    return false;
                }
            });
        }
        //获取个数
        @Override
        public int getItemCount() {
            return 1;
        }

        //设置显示的位置
        class CountViewHolder extends RecyclerView.ViewHolder {
            private TextView textView_rental_price;
            private ImageView five_img;
            private ImageView seven_img;
            private ImageView ten_img;
            private TextView all_txt;

            CountViewHolder(View itemView) {
                super(itemView);
                textView_rental_price = itemView.findViewById(R.id.rprice_txt);
                five_img = itemView.findViewById(R.id.fiveImageView);
                seven_img = itemView.findViewById(R.id.sevenImageView);
                ten_img = itemView.findViewById(R.id.tenImageView);
                all_txt = itemView.findViewById(R.id.allTextView);
            }
        }
    }
}
