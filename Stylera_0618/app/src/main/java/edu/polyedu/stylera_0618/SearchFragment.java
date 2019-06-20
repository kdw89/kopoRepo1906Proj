package edu.polyedu.stylera_0618;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchFragment extends Fragment {

    private ListView listview;
    private ListViewAdapter adapter;

    String url = "http://192.168.23.110/test/item.php";
    // PHP를 읽어올때 사용할 변수
    public GettingItemPHP gPHP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.search, container, false);

        //변수 초기화
        adapter = new ListViewAdapter();
        listview = (ListView) layout.findViewById(R.id.listview1);

        //어뎁터 할당
        listview.setAdapter(adapter);

        gPHP = new GettingItemPHP();
        gPHP.execute(url);

        EditText editTextFilter = (EditText)layout.findViewById(R.id.searchItem) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                ((ListViewAdapter)listview.getAdapter()).getFilter().filter(filterText);
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

    class GettingItemPHP extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... url) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(url[0]);
                HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null)
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                // results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");

                for (int i = 0; i < results.length(); ++i) {
                    JSONObject temp = results.getJSONObject(i);
                    adapter.addItem(ContextCompat.getDrawable(getContext(), R.drawable.ic_home_black_24dp), temp.get("itemname").toString(), temp.get("price").toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("AAAAAAAAAAAAAAAAAAA", e.toString());
            }
        }
    }
}
