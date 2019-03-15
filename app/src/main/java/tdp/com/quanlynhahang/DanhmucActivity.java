package tdp.com.quanlynhahang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.baoyz.swipemenulistview.SwipeMenu;
//import com.baoyz.swipemenulistview.SwipeMenuCreator;
//import com.baoyz.swipemenulistview.SwipeMenuItem;
//import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

import tdp.com.quanlynhahang.adapter.CustomAdapter;
import tdp.com.quanlynhahang.data.DBManager;
import tdp.com.quanlynhahang.model.Danhmuc;


public class DanhmucActivity extends Activity {
    //    Khai báo
    private EditText editId;
    private EditText editNguyenLieu;
    private EditText editTenMon;
    private EditText editDonGia;
    private Button btnThem;
    private Button btnCapNhat;
    private ListView lvDanhmuc;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Danhmuc> danhmuclist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhmuc);
        dbManager = new DBManager(this);
        initWidget();

        danhmuclist = dbManager.getallDanhmuc();
        setAdapter();
//        Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTenMon.getText().toString().isEmpty() && !editDonGia.getText().toString().isEmpty() && !editNguyenLieu.getText().toString().isEmpty()) {
                    Danhmuc danhmuc = createDanhMuc();
                    if (danhmuc != null) {
                        dbManager.addDanhmuc(danhmuc);
                    }
                    updateLishDanhmuc();
                    setAdapter();
                    Toast.makeText(DanhmucActivity.this, R.string.dathem, Toast.LENGTH_SHORT).show();
                    clearEditText();

                } else {
                    Toast.makeText(DanhmucActivity.this, R.string.thieuthongtin, Toast.LENGTH_SHORT).show();

                }


            }
        });
//        Chọn danh mục
        lvDanhmuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Danhmuc danhmuc = danhmuclist.get(position);
                editId.setText(String.valueOf(danhmuc.getmID()));
                editTenMon.setText(danhmuc.getmTenMonAn());
                editNguyenLieu.setText(danhmuc.getmNguyenLieu());
                editDonGia.setText(danhmuc.getmDonGia());
                btnThem.setEnabled(false);
                btnCapNhat.setEnabled(true);

            }
        });
//        Trượt xóa


//        Cập nhật
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danhmuc danhmuc = new Danhmuc();
                danhmuc.setmID(Integer.parseInt(String.valueOf(editId.getText())));
                danhmuc.setmTenMonAn(editTenMon.getText() + "");
                danhmuc.setmNguyenLieu(editNguyenLieu.getText() + "");
                danhmuc.setmDonGia(String.valueOf(editDonGia.getText()));
                int result = dbManager.updateDanhmuc(danhmuc);
                if (result > 0) {
                    updateLishDanhmuc();
                }
                clearEditText();
                btnThem.setEnabled(true);
                btnCapNhat.setEnabled(false);
                Toast.makeText(DanhmucActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }
        });
//        Hold danh mục
        lvDanhmuc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Danhmuc danhmuc = danhmuclist.get(position);
                //Toast.makeText(DanhmucActivity.this, "Longclick "+danhmuc.getmID(), Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(DanhmucActivity.this);
                dialog.setTitle("Xóa món ăn");
                dialog.setContentView(R.layout.dialog_xoa);
                TextView tvXoa = dialog.findViewById(R.id.tv_xoa);
                TextView tvKhong = dialog.findViewById(R.id.tv_khong);
                TextView tvXoaSTT = dialog.findViewById(R.id.tv_xoastt);

                String text1 = "<font color='black'>Bạn có muốn xóa món </font>";
                String text2 = danhmuc.getmTenMonAn();
                String text3 = "<font color='black'> này không?</font>";
                tvXoaSTT.setText(Html.fromHtml(text1+text2+text3), TextView.BufferType.SPANNABLE);
                //tvXoaSTT.setText("Bạn có muốn xóa món <" + danhmuc.getmTenMonAn() + "> hay không ?");
                dialog.show();

                tvXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Danhmuc danhmuc = danhmuclist.get(position);
                        int result = dbManager.deleteDanhmuc(danhmuc.getmID());
                        updateLishDanhmuc();
                        if (result > 0) {
                            Toast.makeText(DanhmucActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                            clearEditText();
                            dialog.dismiss();
                            btnThem.setEnabled(true);
                            btnCapNhat.setEnabled(false);
                        }
                    }
                });
                tvKhong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearEditText();
                        dialog.dismiss();
                        btnThem.setEnabled(true);
                        btnCapNhat.setEnabled(false);
                    }
                });
//                Danhmuc danhmuc = danhmuclist.get(position);
//                int result = dbManager.deleteDanhmuc(danhmuc.getmID());
//                updateLishDanhmuc ();
//                if (result>0){
//                    Toast.makeText(DanhmucActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
//
//                }
                return false;
            }
        });
    }

    private Danhmuc createDanhMuc() {
        String nguyenlieu = editNguyenLieu.getText().toString();
        String tenmon = String.valueOf(editTenMon.getText());
        String dongia = editDonGia.getText() + "";
        Danhmuc danhmuc = new Danhmuc(nguyenlieu, tenmon, dongia);

        return danhmuc;
    }

    //        Gán giá trị
    private void initWidget() {
        editNguyenLieu = (EditText) findViewById(R.id.edit_nguyenlieu);
        editTenMon = (EditText) findViewById(R.id.edit_tenmon);
        editDonGia = (EditText) findViewById(R.id.edit_dongia);
        btnThem = (Button) findViewById(R.id.btn_them);
        btnCapNhat = (Button) findViewById(R.id.btn_capnhat);
//        lvDanhmuc = (SwipeMenuListView) findViewById(R.id.lv_danhmuc);
        lvDanhmuc = (ListView) findViewById(R.id.lv_danhmuc);
        editId = (EditText) findViewById(R.id.edit_id);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(DanhmucActivity.this, R.layout.item_list_danhmuc, danhmuclist);
            lvDanhmuc.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvDanhmuc.setSelection(customAdapter.getCount() - 1);
        }

    }

    public void updateLishDanhmuc() {
        danhmuclist.clear();
        danhmuclist.addAll(dbManager.getallDanhmuc());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }
    public void clearEditText(){
        editId.setText("", TextView.BufferType.EDITABLE);
        editTenMon.setText("", TextView.BufferType.EDITABLE);
        editNguyenLieu.setText("", TextView.BufferType.EDITABLE);
        editDonGia.setText("", TextView.BufferType.EDITABLE);
    }

}
