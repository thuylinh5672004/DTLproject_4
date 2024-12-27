package com.example.a2210900036_daothithuylinh;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a2210900036_daothithuylinh.model.Sanpham36;

import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Sanpham36> {
    Activity context;
    int resource;

    // Constructor
    public AdapterProduct(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource = resource;
    }

    // Overriding getView to customize how each item is displayed in the ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate the view for each item if not already done
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        // Get the current product
        Sanpham36 product = getItem(position);
        if (product != null) {
            // Find and set the views in the layout
            TextView txtMaSP = convertView.findViewById(R.id.txtMaSP);
            TextView txtTenSP = convertView.findViewById(R.id.txtTenSP);
            TextView txtSoLuong = convertView.findViewById(R.id.txtSoLuong);
            TextView txtDonGia = convertView.findViewById(R.id.txtDonGia);
            TextView txtThanhTien = convertView.findViewById(R.id.txtThanhTien);

            // Calculate Thanh Tien
            double thanhTien = product.getSoLuong() * product.getDonGia();
            if (product.getSoLuong() > 10) {
                thanhTien *= 0.9; // Giảm giá 10%
            }

            // Set data into the views
            txtMaSP.setText(String.valueOf(product.getMaSP()));
            txtTenSP.setText(product.getTenSP());
            txtSoLuong.setText(String.valueOf(product.getSoLuong()));
            txtDonGia.setText(String.format("%.2f", product.getDonGia()));
            txtThanhTien.setText(String.format("%.2f", thanhTien));
        }

        return convertView;
    }


}