package AndroidFoundation.AndroidFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.WorkKnowledge.R;

public class FragmentActivity extends AppCompatActivity {

    private Button fg1, fg2;
    private FragmentManager manager;
    private FragmentOne fragment1;
    private FragmentTwo fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initView();

        fragment1 = new FragmentOne();
        fragment2 = new FragmentTwo();

        //初始化FragmentManager对象
        manager = getSupportFragmentManager();

        //使用FragmentManager对象用来开启一个Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();

        //默认显示fragment1
        transaction.add(R.id.myframelayout, fragment1).commit();


        //对button事件进行监听
        fg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.myframelayout, fragment1).commit();
            }
        });

        fg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.myframelayout, fragment2).commit();
            }
        });
    }

    private void initView() {
        fg1 = findViewById(R.id.fg1);
        fg2 = findViewById(R.id.fg2);
    }
}