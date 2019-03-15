package tdp.com.quanlynhahang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tdp.com.quanlynhahang.R;
import tdp.com.quanlynhahang.model.Danhmuc;

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<Danhmuc> listDanhmuc;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Danhmuc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listDanhmuc = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_danhmuc,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvID = (TextView)convertView.findViewById(R.id.tv_id);
            viewHolder.tvNguyenLieu = (TextView)convertView.findViewById(R.id.tv_nguyenlieu);
            viewHolder.tvMonAn = (TextView)convertView.findViewById(R.id.tv_monan);
            viewHolder.tvDonGia = (TextView)convertView.findViewById(R.id.tv_dongia);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        Danhmuc danhmuc = listDanhmuc.get(position);
        viewHolder.tvID.setText(String.valueOf(danhmuc.getmID()));
        viewHolder.tvNguyenLieu.setText(danhmuc.getmNguyenLieu());
        viewHolder.tvMonAn.setText(danhmuc.getmTenMonAn());
        viewHolder.tvDonGia.setText(danhmuc.getmDonGia());


        return convertView;
    }

    private class ViewHolder {
        private TextView tvID;
        private TextView tvNguyenLieu;
        private TextView tvMonAn;
        private TextView tvDonGia;
    }
}
