package tdp.com.quanlynhahang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainAmActivity extends AppCompatActivity{
    private Button btnThem;
    TextView tvtext;

    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Quản lý nhà hàng - Admin");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_am);
//        Gán giá trị
        btnThem=(Button) findViewById(R.id.btn_them);
        tvtext = (TextView)findViewById(R.id.tv_txt);
//        Intent intent = getIntent();
        setDataByExtras();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAmActivity.this, DanhmucActivity.class));
            }
        });

    }

    private void setDataByExtras() {
        Intent intent = getIntent();
        String text = intent.getStringExtra(Login.TEXT);
        tvtext.setText(text);
    }
}
