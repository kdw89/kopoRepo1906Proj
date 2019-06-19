package edu.polyedu.stylera_0618;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SearchFragment extends Fragment {

    private ListView listview;
    private ListViewAdapter adapter;

    private int[] img = {R.drawable.ic_bookmark_black_24dp, R.drawable.ic_collections_black_24dp, R.drawable.ic_dashboard_black_24dp, R.drawable.ic_multiline_chart_black_24dp, R.drawable.ic_home_black_24dp,
            R.drawable.ic_show_chart_black_24dp, R.drawable.ic_notifications_off_black_24dp, R.drawable.ic_settings_black_24dp, R.drawable.ic_business_center_black_24dp};
    private String[] Title = {"qwer","asdf","zxcv","asdf","zxcv","asdf","zxcv","asdf","zxcv"};
    private String[] Context = {"1234","2345","3456","2345","3456","2345","3456","2345","3456"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.search, container, false);

        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView)layout.findViewById(R.id.listview1);

        //어뎁터 할당
        listview.setAdapter(adapter);

        EditText searchText = (EditText)layout.findViewById(R.id.searchItem);


        //adapter를 통한 값 전달
        for (int i = 0; i < img.length; i++) {
            adapter.addVO(ContextCompat.getDrawable(getContext(), img[i]), Title[i], Context[i]);
        }


        return layout;
    }

}
