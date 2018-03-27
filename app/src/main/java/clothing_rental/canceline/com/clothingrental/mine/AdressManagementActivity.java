package clothing_rental.canceline.com.clothingrental.mine;

import android.os.Bundle;
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
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import java.util.List;
import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.base.widget.BaseActivity;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;
import clothing_rental.canceline.com.clothingrental.login.ui.LoginUtil;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by kingShin on 2018/3/22.
 */
@Route(path = "/mine/AdressManagementActivity")
public class AdressManagementActivity extends BaseActivity {
    private Button close_btn;
    private Button addAdress;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_management);

        //关闭按钮
        close_btn = findViewById(R.id.closeBtn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //add地址按钮
        addAdress = findViewById(R.id.addAdressButton);
        addAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtil.setFirst_set_address(false);
                Router.navTo("/details/rental/AddAdressActivity");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fresh();
    }

    private void Fresh() {
        recyclerView = findViewById(R.id.addressManagementRecyclerView);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        virtualLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(virtualLayoutManager);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        BmobQuery<Adress> adressBmobQuery = new BmobQuery<>();
        adressBmobQuery.addWhereEqualTo("userID", LoginUtil.getPersonID());
        adressBmobQuery.findObjects(new FindListener<Adress>() {
            @Override
            public void done(List<Adress> list, BmobException e) {
                if(e==null){
                    Toast.makeText(AdressManagementActivity.this, "sucessFind", Toast.LENGTH_LONG).show();
                    delegateAdapter.addAdapter(new MyAdapter(list));
                    delegateAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    class MyAdapter extends DelegateAdapter.Adapter<MyViewHolder> {
        private List<Adress> adresses;
        public MyAdapter(List<Adress> list) {
            this.adresses = list;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_address_management,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Adress adress = adresses.get(position);
            holder.name.setText("收件人："+adress.getPersonName());
            holder.phone.setText("联系方式："+adress.getPhone());
            holder.place.setText("地址"+adress.getAdress());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final boolean test = LoginUtil.isEqualToAddress(adress);
                    Adress adress1 = new Adress();
                    adress1.setObjectId(adress.getObjectId());
                    adress1.delete(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                               Toast.makeText(AdressManagementActivity.this,"delete.success",Toast.LENGTH_LONG).show();
                               //刷新界面
                                Fresh();

                                if (test==true){LoginUtil.setAdress(null);}
                            }
                        }
                    });
                }
            });
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginUtil.setAdress(adress);
                    finish();
                }
            });

        }

        @Override
        public int getItemCount() {
            return adresses.size();
        }
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone;
        private TextView place;
        private LinearLayout layout;
        private ImageView view;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            phone = itemView.findViewById(R.id.phoneTextView);
            place = itemView.findViewById(R.id.placeTextView);
            layout = itemView.findViewById(R.id.layout_1);
            view = itemView.findViewById(R.id.cancelImageView);
        }
    }
}
