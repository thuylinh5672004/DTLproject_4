using Daothithuylinhproject2_2210900036.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Security;

namespace Daothithuylinhproject2_2210900036.Areas.ModelView
{
    public class AuthService
    {
        private string connectionString = "your_connection_string_here"; // Cập nhật chuỗi kết nối

        public KHACHHANG Login(string email, string matkhau)
        {
            using (var conn = new SqlConnection(connectionString))
            {
                conn.Open();
                var cmd = new SqlCommand("SELECT * FROM KHACHHANG WHERE Email = @Email", conn);
                cmd.Parameters.AddWithValue("@Email", email);

                var reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    var khachHang = new KHACHHANG
                    {
                        MaKH = reader["MaKH"].ToString(),
                        HoTen = reader["HoTen"].ToString(),
                        Matkhau = reader["Matkhau"].ToString(),
                        Email = reader["Email"].ToString()
                    };

                    // Kiểm tra mật khẩu
                    if (khachHang.Matkhau == matkhau)  // Lưu ý: Đây là ví dụ đơn giản. Bạn nên mã hóa mật khẩu trước khi lưu trong DB
                    {
                        return khachHang;
                    }
                }
            }
            return null;  // Nếu không tìm thấy người dùng hoặc mật khẩu sai
        }

        public void Logout()
        {
            FormsAuthentication.SignOut();  // Đăng xuất người dùng
        }
    }
}