package tdp.com.quanlynhahang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity {
    //khai báo
    private final String TAG = getClass().getSimpleName();
    private Button btnDangnhap;
    private EditText editTaikhoan;
    private EditText editMatkhau;
    private TextView tvQuenmk;
    private TextView tvDangkyNH;
    private TextView tvThongbao;
    public static final String TEXT ="TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//gán giá trị
        btnDangnhap = (Button) findViewById(R.id.btn_dangnhap);
        editTaikhoan = (EditText) findViewById(R.id.edit_taikhoan);
        editMatkhau = (EditText) findViewById(R.id.edit_matkhau);
        tvQuenmk = (TextView) findViewById(R.id.tv_quenmk);
        tvDangkyNH = (TextView) findViewById(R.id.tv_dangkynh);
        tvThongbao = (TextView) findViewById(R.id.tv_thongbao);
//      get Focus
        editTaikhoan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    //editTaikhoan.setText("", TextView.BufferType.EDITABLE);
                    tvThongbao.setVisibility(View.INVISIBLE);
                }
            }
        });
        editMatkhau.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    editMatkhau.setText("", TextView.BufferType.EDITABLE);
                    tvThongbao.setVisibility(View.INVISIBLE);
                }
            }
        });
//      Ấn Enter đăng nhâp
        editMatkhau.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionID == EditorInfo.IME_ACTION_DONE)) {
//                    //do what you want on the press of 'done'
                    btnDangnhap.performClick();
                }
                return false;
            }
        });

//      Nút đăng nhập
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(editTaikhoan.getText().toString().equals("0937182179") && editMatkhau.getText().toString().equals("admin")) {
                    Toast.makeText(Login.this, "Admin đăng nhập", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainAmActivity.class));
                    String text = editTaikhoan.getText().toString();
//                    startActivity(new Intent(Login.this, MainActivity.class));
                    byExtras(text);
                }else {
                    if(editTaikhoan.getText().toString().equals("01234468678") && editMatkhau.getText().toString().equals("nhanvien")) {
                        Toast.makeText(Login.this, "Nhân viên đăng nhập", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else {
                if (!editTaikhoan.getText().toString().isEmpty() && !editMatkhau.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, R.string.saithongtin, Toast.LENGTH_SHORT).show();
                    tvThongbao.setVisibility(View.VISIBLE);
                    tvThongbao.setText(R.string.saithongtin);

                }else {
                    Toast.makeText(Login.this, R.string.thieuthongtin, Toast.LENGTH_SHORT).show();
                    tvThongbao.setVisibility(View.VISIBLE);
                    tvThongbao.setText(R.string.thieuthongtin);
                }
            }}}
        });
    }

    private void byExtras(String text) {
        Intent intent = new Intent(Login.this,MainAmActivity.class);
        intent.putExtra(TEXT,text);
        startActivity(intent);
    }

//    @Override

//    public void onClick(View view) {
////        Intent intent;
//        switch (view.getId()){
//
////                break;
//            case R.id.btn_dangnhap:
//                if (!editTaikhoan.getText().toString().isEmpty() && !editMatkhau.getText().toString().isEmpty()){
//                    Toast.makeText(Login.this, R.string.dungthongtin, Toast.LENGTH_LONG).show();
//                    tvThongbao.setVisibility(View.VISIBLE);
//                    tvThongbao.setText(R.string.dungthongtin);
//                }else {
//                    Toast.makeText(getApplicationContext(), R.string.thieuthongtin, Toast.LENGTH_LONG).show();
//                    tvThongbao.setVisibility(View.VISIBLE);
//                    tvThongbao.setText(R.string.thieuthongtin);
//                }
//                break;
//                default:
//                    break;
//        }
//    }

//  Đăng ký nhà hàng
    public void tv_nhm(View view) {
        //Toast.makeText(Login.this, "Đăng ký nhà hàng", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Login.this, NHMActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        editTaikhoan.setText("", TextView.BufferType.EDITABLE);
        editMatkhau.setText("", TextView.BufferType.EDITABLE);
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}

