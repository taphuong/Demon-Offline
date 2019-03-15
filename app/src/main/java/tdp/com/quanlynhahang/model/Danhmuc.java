package tdp.com.quanlynhahang.model;

import android.content.Intent;

public class Danhmuc {
    private int mID;
    private String mNguyenLieu;
    private String mTenMonAn;
    private String mDonGia;

    public Danhmuc(String mNguyenLieu, String mTenMonAn, String mDonGia) {
        this.mNguyenLieu = mNguyenLieu;
        this.mTenMonAn = mTenMonAn;
        this.mDonGia = mDonGia;
    }

    public Danhmuc(int mID, String mNguyenLieu, String mTenMonAn, String mDonGia) {
        this.mID = mID;
        this.mNguyenLieu = mNguyenLieu;
        this.mTenMonAn = mTenMonAn;
        this.mDonGia = mDonGia;
    }

    public Danhmuc() {

    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmNguyenLieu() {
        return mNguyenLieu;
    }

    public void setmNguyenLieu(String mNguyenLieu) {
        this.mNguyenLieu = mNguyenLieu;
    }

    public String getmTenMonAn() {
        return mTenMonAn;
    }

    public void setmTenMonAn(String mTenMonAn) {
        this.mTenMonAn = mTenMonAn;
    }

    public String getmDonGia() {
        return mDonGia;
    }

    public void setmDonGia(String mDonGia) {
        this.mDonGia = mDonGia;
    }
}
