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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.search, container, false);

        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView) layout.findViewById(R.id.listview1);

        //어뎁터 할당
        listview.setAdapter(adapter);

        EditText searchText = (EditText) layout.findViewById(R.id.searchItem);

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_collections_black_24dp),
                "Sam Smith", "I'm not the only one.\r\nStay with me.\r\n") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_dashboard_black_24dp),
                "Bryan Adams", "heaven.\r\nI do it for you.") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_multiline_chart_black_24dp),
                "Eric Clapton", "Tears in heaven.\r\nChange the world.") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_home_black_24dp),
                "Gary Moore", "Still got the blues.\r\nOne day.") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_business_center_black_24dp),
                "Helloween", "A tale that wasn't right.\r\nI want out.") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_off_black_24dp),
                "Adele", "Hello.\r\nSomeone like you.") ;

        adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.floral),
                "lsj", "whaaaa.\r\nSomeone like you.") ;


        EditText editTextFilter = (EditText)layout.findViewById(R.id.searchItem) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                ((ListViewAdapter)listview.getAdapter()).getFilter().filter(filterText) ;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;

        return layout;

    }

}
