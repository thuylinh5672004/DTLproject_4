using Daothithuylinhproject2_2210900036.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Daothithuylinhproject2_2210900036.Controllers
{
    public class ShoppingCartController : Controller
    {
        // Giỏ hàng tĩnh để lưu trữ
        private static ShoppingCart cart = new ShoppingCart();

        // Xem giỏ hàng
        public ActionResult Index()
        {
            return View(cart.GetItems());
        }

        // Thêm sản phẩm vào giỏ hàng
        public ActionResult AddToCart(string maSanPham, string tenSanPham, decimal? giaBan, int soLuong, string anh)
        {
            if (!giaBan.HasValue) // Kiểm tra xem giá bán có giá trị không
            {
                return RedirectToAction("Index"); // Hoặc xử lý thông báo lỗi tùy ý
            }

            // Nếu giaBan có giá trị, thực hiện thêm sản phẩm vào giỏ hàng
            cart.AddItem(maSanPham, tenSanPham, giaBan.Value, soLuong, anh);
            return RedirectToAction("Index");
        }
        // Cập nhật số lượng sản phẩm trong giỏ hàng
        [HttpPost]
        public ActionResult UpdateQuantity(string maSP, int soLuong)
        {
            // Cập nhật số lượng sản phẩm theo mã sản phẩm
            cart.UpdateItemQuantity(maSP, soLuong);
            return RedirectToAction("Index");
        }

        // Xóa sản phẩm khỏi giỏ hàng
        public ActionResult RemoveFromCart(string maSP)
        {
            // Xóa sản phẩm khỏi giỏ hàng theo mã sản phẩm
            cart.RemoveItem(maSP);
            return RedirectToAction("Index");
        }

        // Thanh toán (chỉ là mẫu, chưa xử lý thực tế)
        public ActionResult Checkout()
        {
            // Xử lý thanh toán ở đây
            cart.Clear();  // Xóa giỏ hàng sau khi thanh toán
            return View();
        }
    }
}
