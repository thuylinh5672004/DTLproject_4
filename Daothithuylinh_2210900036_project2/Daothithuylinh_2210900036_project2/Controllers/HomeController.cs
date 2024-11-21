using Daothithuylinh_2210900036_project2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Daothithuylinh_2210900036_project2.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            // Tạo đối tượng của database context
            Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

            // Lấy danh sách sản phẩm từ bảng SANPHAM trong cơ sở dữ liệu
            var products = db.SANPHAM.ToList();

            // Thêm các sản phẩm tĩnh vào danh sách 'products'
            products.Add(new SANPHAM { MaSP = "L01", TenSP = "Áo thun", MaDM = "H01", GiaBan = 100, SoLuongTonKho = 50, Anh = Url.Content("~/Image/Anh1.jpg") });
            products.Add(new SANPHAM { MaSP = "L02", TenSP = "Quần jean", MaDM = "H02", GiaBan = 90, SoLuongTonKho = 60, Anh = Url.Content("~/Image/Anh2.jpg") });
            products.Add(new SANPHAM { MaSP = "L03", TenSP = "Áo SơMi", MaDM = "H03", GiaBan = 110, SoLuongTonKho = 78, Anh = Url.Content("~/Image/Anh3.jpg") });
            products.Add(new SANPHAM { MaSP = "L04", TenSP = "Áo Phông", MaDM = "H04", GiaBan = 150, SoLuongTonKho = 68, Anh = Url.Content("~/Image/Anh4.jpg") });

            // Trả về view với danh sách sản phẩm
            return View(products);
        }

        // Trang giới thiệu về cửa hàng
        public ActionResult About()
        {
            ViewBag.Message = "Giới thiệu về cửa hàng bán quần áo của chúng tôi.";
            return View();
        }

        // Trang liên hệ
        public ActionResult Contact()
        {
            ViewBag.Message = "Liên hệ với chúng tôi.";
            return View();
        }
    }
}
