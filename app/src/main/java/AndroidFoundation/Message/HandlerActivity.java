package AndroidFoundation.Message;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaconcurrence.R;

public class HandlerActivity extends AppCompatActivity {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    tv.setText("Hello handler! ");
                    break;
            }
        }
    };

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 耗时操作
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();

        Looper me = Looper.myLooper();
    }
}