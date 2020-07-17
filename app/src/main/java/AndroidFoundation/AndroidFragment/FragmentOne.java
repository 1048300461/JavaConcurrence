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

public class FragmentOne extends Fragment {
    private Button mBtn;

    //设置按钮点击的回调
    public interface FOneBtnClickListener{
        void onFOneBtnClick();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        mBtn = view.findViewById(R.id.to_two);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() instanceof FOneBtnClickListener){
                    ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
                }
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
