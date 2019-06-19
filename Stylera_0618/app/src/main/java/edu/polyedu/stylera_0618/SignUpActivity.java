package edu.polyedu.stylera_0618;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;

public class SignUpActivity extends AppCompatActivity {
    private EditText data1, data2;
    private Button btn_send, btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        NetworkUtil.setNetworkPolicy();
        data1 = (EditText)findViewById(R.id.submit_idText);
        data2 = (EditText)findViewById(R.id.submit_pwText);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PHPRequest request = new PHPRequest("http://192.168.56.101/test/data_insert.php");
                    String result = request.PhPtest(String.valueOf(data1.getText()),String.valueOf(data2.getText()));
                    if(result.equals("1")){
                        Toast.makeText(getApplication(),"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplication(),"문제가 발생했습니다. 관리자에게 문의하세요",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });

        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

