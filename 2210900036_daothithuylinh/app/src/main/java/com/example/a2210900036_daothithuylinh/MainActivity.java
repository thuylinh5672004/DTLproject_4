package com.example.a2210900036_daothithuylinh;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.a2210900036_daothithuylinh.model.Sanpham36;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a2210900036_daothithuylinh.model.Sanpham36;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    String dbName = "Sanpham36.db"; // Tên DB theo yêu cầu
    String dbPath = "/databases/";
    SQLiteDatabase db = null;
    AdapterProduct adapter; // Đổi thành AdapterProduct
    ListView lvSanPham36; // Đổi thành lvProduct
    Button btnThem;

    Sanpham36 product; // Đổi thành Product
    int posUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        xuLyCopy();
        addView();
        hienthiSanPham();
        addEvent();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    private void addView() {
        lvSanPham36 = findViewById(R.id.lvSanPham36); // ID kết thúc bằng 36
        adapter = new AdapterProduct(MainActivity.this, android.R.layout.activity_list_item);
        lvSanPham36.setAdapter(adapter);
        btnThem = findViewById(R.id.btnThem36); // ID kết thúc bằng 36
        registerForContextMenu(lvSanPham36);
    }

    private void xuLyCopy() {
        try {
            File dbFile = getDatabasePath(dbName);
            if (!dbFile.exists()) {
                copyDataFromAsset();
                Toast.makeText(MainActivity.this, "Sao chép cơ sở dữ liệu thành công", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(MainActivity.this, "Cơ sở dữ liệu đã tồn tại", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("Lỗi", e.toString());
        }
    }

    private void copyDataFromAsset() {
        try {
            InputStream myInput = getAssets().open(dbName);
            String outFileName = getApplicationInfo().dataDir + dbPath + dbName;
            File f = new File(getApplicationInfo().dataDir + dbPath);
            if (!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception ex) {
            Log.e("Lỗi", ex.toString());
        }
    }

    private void hienthiSanPham() {
        db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham36", null);
        while (cursor.moveToNext()) {
            String maSP = cursor.getString(0);
            String tenSP = cursor.getString(1);
            int soLuong = cursor.getInt(2);
            double donGia = cursor.getDouble(3);
            adapter.add(new Sanpham36(maSP, tenSP, soLuong, donGia));
        }
    }

    private void addEvent() {
        btnThem.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThemSuaActivity.class);
            intent.putExtra("TRANGTHAI", "THEM");
            startActivityForResult(intent, 113);
        });
        lvSanPham36.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                product = adapter.getItem(i);
                posUpdate = i;
                return false;
            }
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSua) {
            if (product != null) {
                Intent intent = new Intent(MainActivity.this, ThemSuaActivity.class);
                intent.putExtra("TRANGTHAI", "SUA");
                intent.putExtra("PRODUCT", (Serializable) product);
                startActivityForResult(intent, 113);
            }
            return true;
        }

        if (item.getItemId() == R.id.mnuXoa) {
            if (product != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", (dialogInterface, i) -> {
                    try {
                        int rowsDeleted = db.delete("SanPham36", "MaSP=?", new String[]{product.getMaSP()});
                        if (rowsDeleted > 0) {
                            adapter.remove(product);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("Error", "Lỗi khi xóa sản phẩm: " + e.toString());
                    }
                });
                builder.setNegativeButton("Không", null);
                builder.create().show();
            }
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            Log.e("onActivityResult", "Dữ liệu trả về null");
            return; // Thoát nếu không có dữ liệu trả về
        }

        Sanpham36 ctNew = (Sanpham36) data.getSerializableExtra("CONTACT");
        if (ctNew == null) {
            Log.e("onActivityResult", "Không thể lấy đối tượng Contact từ Intent");
            return; // Thoát nếu không lấy được đối tượng
        }

        // Xử lý thêm mới
        if (requestCode == 113 && resultCode == 114) {
            try {
                adapter.add(ctNew); // Cập nhật ListView
                ContentValues values = new ContentValues();
                values.put("Ma", ctNew.getMaSP());
                values.put("Ten", ctNew.getTenSP());
                values.put("so luong ", ctNew.getSoLuong());
                values.put("DonGia", ctNew.getDonGia());
                if (db.insert("Contact", null, values) > 0) {
                    Toast.makeText(MainActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {Log.e("Error", "Lỗi khi thêm mới: " + e.toString());
            }
        }

        // Xử lý cập nhật
        if (requestCode == 113 && resultCode == 115) {
            try {
                ContentValues values = new ContentValues();
                values.put("TenSP", ctNew.getTenSP()); // Sử dụng phương thức getTenSP()
                values.put("SoLuong", ctNew.getSoLuong()); // Sử dụng phương thức getSoLuong()
                values.put("DonGia", ctNew.getDonGia()); // Sử dụng phương thức getDonGia()

                // Cập nhật cơ sở dữ liệu
                int rowsUpdated = db.update("SanPham36", values, "MaSP=?", new String[]{ctNew.getMaSP()});
                if (rowsUpdated > 0) {
                    // Cập nhật đối tượng trong danh sách hiển thị
                    Sanpham36 sanpham = adapter.getItem(posUpdate);
                    if (sanpham != null) {
                        sanpham.setTenSP(ctNew.getTenSP());
                        sanpham.setSoLuong(ctNew.getSoLuong());
                        sanpham.setDonGia(ctNew.getDonGia());
                    }
                    adapter.notifyDataSetChanged(); // Thông báo dữ liệu đã thay đổi
                    Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Error", "Lỗi khi cập nhật: " + e.toString());
            }
        }

    }
}