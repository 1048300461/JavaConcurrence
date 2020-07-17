package AndroidFoundation.AndroidFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.WorkKnowledge.R;

public class FragmentTwo extends Fragment {
    private Button mBtn;
    private FTwoBtnClickListener fTwoBtnClickListener;

    public interface FTwoBtnClickListener{
        void onFTwoBtnClick();
    }

    //设置回调接口
    public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener){
        this.fTwoBtnClickListener = fTwoBtnClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);

        mBtn = v.findViewById(R.id.to_three);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fTwoBtnClickListener != null){
                    fTwoBtnClickListener.onFTwoBtnClick();
                }
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
