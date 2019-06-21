package edu.polyedu.stylera_0618;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookmarkFragment extends Fragment {

    private ListView listview;
    private BMListViewAdapter adapter;
    String url = "http://192.168.23.110/test/bookmark_select.php";
    // PHP를 읽어올때 사용할 변수
    public GettingBookmarkPHP gPHP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.bookmark, container, false);

        //변수 초기화
        adapter = new BMListViewAdapter();
        listview = (ListView)layout.findViewById(R.id.listview2);

        //어뎁터 할당
        listview.setAdapter(adapter);

        gPHP = new GettingBookmarkPHP();
        gPHP.execute(url);

        return layout;
    }

    class GettingBookmarkPHP extends AsyncTask<String, Integer, String> {

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
                    int imagId = getResources().getIdentifier(temp.get("image").toString(),"drawable", getActivity().getPackageName());

                    adapter.addItem(ContextCompat.getDrawable(getContext(), imagId), temp.get("itemname").toString(), temp.get("price").toString());
                }
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("AAAAAAAAAAAAAAAAAAA", e.toString());
            }
        }
    }

}