package com.example.a2210900036_daothithuylinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a2210900036_daothithuylinh.model.Sanpham36;

import java.io.Serializable;

public class ThemSuaActivity extends AppCompatActivity {
    Intent intent; // Intent to receive data
    EditText edtMaSP, edtTenSP, edtSoLuong, edtDonGia;
    Button btnThemSua, btnThoat;
    String trangthai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_sua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }
    private void addEvent() {
        btnThemSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new Sanpham36 object to pass data
                Sanpham36 sanpham = new Sanpham36();

                // Set the values from the EditText fields
                sanpham.setMaSP(edtMaSP.getText().toString());
                sanpham.setTenSP(edtTenSP.getText().toString());
                sanpham.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                sanpham.setDonGia(Double.parseDouble(edtDonGia.getText().toString()));

                // If adding a new product
                if (trangthai.equals("THEM")) {
                    intent.putExtra("SANPHAM", (Serializable) sanpham);
                    setResult(114, intent);
                    finish();
                }
                // If editing an existing product
                else {
                    intent.putExtra("SANPHAM", (Serializable) sanpham);
                    setResult(115, intent);
                    finish();
                }
            }
        });

        // Handle the exit button
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addView() {
        intent = getIntent();
        trangthai = intent.getStringExtra("TRANGTHAI");
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnThemSua = findViewById(R.id.btnThemSua);
        btnThoat = findViewById(R.id.btnThoat);

        if (trangthai.equals("THEM")) {
            btnThemSua.setText("Thêm");
        } else {
            btnThemSua.setText("Sửa");

            // Get the existing Sanpham36 object to edit
            Sanpham36 sanpham = (Sanpham36) intent.getSerializableExtra("SANPHAM");
            edtMaSP.setText(sanpham.getMaSP());
            edtMaSP.setEnabled(false);  // Disable editing the MaSP
            edtTenSP.setText(sanpham.getTenSP());
            edtSoLuong.setText(String.valueOf(sanpham.getSoLuong()));
            edtDonGia.setText(String.valueOf(sanpham.getDonGia()));
        }
    }
}
