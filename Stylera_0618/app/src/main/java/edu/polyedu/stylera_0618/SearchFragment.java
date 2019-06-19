package edu.polyedu.stylera_0618;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SearchFragment extends Fragment {

    private ListView listview;
    private ListViewAdapter adapter;
    private int[] img = {R.drawable.search, R.drawable.user, R.drawable.pass};
    private String[] Title = {"qwer","asdf","zxcv"};
    private String[] Context = {"1234","2345","3456"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.search, container, false);
        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView)layout.findViewById(R.id.listview1);

        //어뎁터 할당
        listview.setAdapter(adapter);

        //adapter를 통한 값 전달
        for (int i = 0; i < img.length; i++) {
            adapter.addVO(ContextCompat.getDrawable(getContext(), img[i]), Title[i], Context[i]);
        }
        return layout;
    }
}
