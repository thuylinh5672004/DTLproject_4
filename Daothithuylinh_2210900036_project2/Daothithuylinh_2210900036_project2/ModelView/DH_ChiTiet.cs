using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Daothithuylinh_2210900036_project2.ModelView
{
    public class DH_ChiTiet
    {
        public string MaSP { get; set; }

        // Tên sản phẩm, độ dài tối đa là 50 ký tự
        public string TenSP { get; set; }

        // Mã danh mục, độ dài tối đa là 10 ký tự
       
        public decimal GiaBan { get; set; }

       
      
        // Số lượng sản phẩm được thêm vào giỏ hàng
        public int SoLuong { get; set; }
        public decimal ThanhTien => SoLuong * GiaBan;
    }
}