package clothing_rental.canceline.com.clothingrental.details.rental;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.base.util.Router;
import clothing_rental.canceline.com.clothingrental.data_base.Adress;

/**
 * Created by kingShin on 2018/3/22.
 */

public class AdressAdapter extends DelegateAdapter.Adapter<AdressAdapter.AdressViewHolder> {
    private String mName;
    private String mPhone;
    private String mAdress;
    private int size;
    private Context mContext;

    //构造函数 1、需要知道用户ID才能得知地址 所以要把Person传进来
    public AdressAdapter(List<Adress> Adress,Context context) {
        this.size = Adress.size();
        if (size!=0){
            this.mName = Adress.get(0).getPersonName();
            this.mPhone = Adress.get(0).getPhone();
            this.mAdress = Adress.get(0).getAdress();
            mContext = context;
        }
    }

//    public AdressAdapter(Adress Adress,Context context) {
//        size = 1;
//        this.mName = Adress.getPersonName();
//        this.mPhone = Adress.getPhone();
//        this.mAdress = Adress.getAdress();
//        mContext = context;
//    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @Override
    public AdressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rental2,parent,false);
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
                    Router.navTo("/details/rental/AddAdressActivity");
                }
            });

        }else{
            holder.recyclerView_unAdress.setVisibility(View.GONE);
            holder.linearLayout_adress.setVisibility(View.VISIBLE);
            holder.textView_name.setText(mName);
            holder.textView_phone.setText(mPhone);
            holder.textView_adress.setText(mAdress);
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

        public AdressViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.personNameTextView);
            textView_phone = itemView.findViewById(R.id.phoneTextView);
            textView_adress = itemView.findViewById(R.id.adressTextView);
            recyclerView_unAdress = itemView.findViewById(R.id.unAdress);
            linearLayout_adress = itemView.findViewById(R.id.adress);
            btn_addAdress = itemView.findViewById(R.id.addAdressButton);
        }
    }
}
