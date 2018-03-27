package clothing_rental.canceline.com.clothingrental.mine;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.List;

import clothing_rental.canceline.com.clothingrental.R;
import clothing_rental.canceline.com.clothingrental.data_base.Message;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by kingShin on 2018/3/1.
 */

@Route(path="/mine/Message")

public class MessageActivity extends Activity{
    private Button btn_exit;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btn_exit = findViewById(R.id.exit_btn);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView2);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        virtualLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(virtualLayoutManager);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        recyclerView.setAdapter(delegateAdapter);

        BmobQuery<Message> messageBmobQuery = new BmobQuery<>();
        messageBmobQuery.addWhereExists("objectId");
        messageBmobQuery.order("-weight,-createAt");
        messageBmobQuery.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> list, BmobException e) {
                if(e==null){
                    Toast.makeText(MessageActivity.this,"success",Toast.LENGTH_LONG).show();
                    delegateAdapter.addAdapter(new MessageAdapter(list));
                    delegateAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MessageActivity.this,"faild",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class MessageAdapter extends DelegateAdapter.Adapter<MessageViewHolder> {
        private List<Message> messages;
        public MessageAdapter(List<Message> list) {
            this.messages = list;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return new LinearLayoutHelper();
        }

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout_message, parent, false);
            return new MessageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MessageViewHolder holder, int position) {
            Message message = messages.get(position);
            holder.textView.setText(message.getMessage());
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }
    }

    private class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MessageViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.message);
        }
    }
}
