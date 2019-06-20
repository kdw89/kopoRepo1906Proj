package edu.polyedu.stylera_0618;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class BookmarkFragment extends Fragment {

    private ListView listview;
    private ListViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.bookmark, container, false);

        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView)layout.findViewById(R.id.listview2);

        //어뎁터 할당
        listview.setAdapter(adapter);


       //adapter를 통한 값 전달
//        for (int i = 0; i < img.length; i++) {
//            adapter.addVO(ContextCompat.getDrawable(getContext(), img[i]), Title[i], Context[i]);
//        }

        return layout;
    }
}