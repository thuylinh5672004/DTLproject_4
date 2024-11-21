using Daothithuylinh_2210900036_project2.Models;
using Daothithuylinh_2210900036_project2.ModelView;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Daothithuylinh_2210900036_project2.Controllers
{
    public class CartController : Controller
    {
        private const string CartSessionKey = "CartItems";
        Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

        private ShoppingCart GetCart()
        {
            var cart = Session[CartSessionKey] as ShoppingCart;
            if (cart == null)
            {
                cart = new ShoppingCart();
                Session[CartSessionKey] = cart;
            }
            return cart;
        }

        public ActionResult AddToCart(string maSP, string tenSP, string maDM, decimal giaBan, int soLuongTonKho, string anh, int soLuong)
        {
            var cart = GetCart();
            var item = new CartItem(maSP, tenSP, maDM, giaBan, soLuongTonKho, anh, soLuong);
            cart.AddItem(item);
            return RedirectToAction("Index");
        }

        public ActionResult UpdateCart(string maSP, int soLuongMoi)
        {
            var cart = GetCart();
            var item = cart.Items.FirstOrDefault(i => i.MaSP == maSP);
            if (item != null)
            {
                item.SoLuong = soLuongMoi;
            }
            Session[CartSessionKey] = cart;
            return RedirectToAction("Index");
        }

        public ActionResult DeleteCartItem(string maSP)
        {
            var cart = GetCart();  // Lấy giỏ hàng hiện tại từ session

            var item = cart.Items.FirstOrDefault(x => x.MaSP == maSP);  // Tìm sản phẩm trong giỏ hàng theo mã sản phẩm

            if (item != null)
            {
                cart.Items.Remove(item);  // Nếu sản phẩm tồn tại, xóa nó khỏi giỏ hàng
            }

            // Cập nhật lại giỏ hàng trong session
            Session[CartSessionKey] = cart;

            // Điều hướng lại trang giỏ hàng sau khi xóa sản phẩm
            return RedirectToAction("Index");
        }

        public ActionResult RemoveCartItem(string maSP)
        {
            var cart = GetCart();
            var itemToRemove = cart.Items.FirstOrDefault(x => x.MaSP == maSP);
            if (itemToRemove != null)
            {
                cart.Items.Remove(itemToRemove);
            }
            Session[CartSessionKey] = cart;
            return RedirectToAction("Index", "Cart");
        }

        public ActionResult Index()
        {
            var cart = GetCart();
            return View(cart);
        }

        public ActionResult ThongtinThanhtoan()
        {
            var cart = GetCart();
            ViewBag.MaDH = "DH" + DateTime.Now.ToString("yyMMddHHmm");
            ViewBag.MaKH = "KH001";  // Ví dụ giá trị mã khách hàng
            ViewBag.Ngaydat = DateTime.Now.ToString("dd/MM/yyyy");
            ViewBag.Tongtien = cart.Items.Sum(i => i.SoLuong * i.GiaBan);

            return View(cart);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult ThanhToan(FormCollection form)
        {
            // Lấy thông tin khách hàng từ form
            var tenKhachHang = form["HoTen"];
            var soDienThoai = form["SDT"];
            var diaChi = form["Diachi"];
            var email = form["Email"];

            // Lấy giỏ hàng hiện tại
            var cart = GetCart();

            if (cart.Items.Count == 0)
            {
                TempData["ErrorMessage"] = "Giỏ hàng của bạn đang trống.";
                return RedirectToAction("Index");
            }

            // Tính tổng tiền cho đơn hàng
            decimal totalAmount = cart.Items.Sum(item => item.SoLuong * item.GiaBan);

            // Tạo mã đơn hàng (sử dụng định dạng mã có độ dài 10 ký tự)
            string orderID = "DH" + DateTime.Now.ToString("yyMMddHHmm");

            // Tạo đơn hàng mới với thông tin cần thiết
            DONHANG newOrder = new DONHANG
            {
                MaDH = orderID,
                Hoten = tenKhachHang,
                Diachi = diaChi,
                SDT = soDienThoai,
                Email=email,
                Ngaydat = DateTime.Now,
                Tongtien = totalAmount, // Lưu tổng tiền đơn hàng
                Trangthai = "Chưa thanh toán",
            };

            // Lưu đơn hàng vào cơ sở dữ liệu
            db.DONHANG.Add(newOrder);
            try
            {
                // Lưu dữ liệu vào database
                db.SaveChanges();

                // Thêm chi tiết đơn hàng
                foreach (var item in cart.Items)
                {
                    CHITIETDONHANG ct = new CHITIETDONHANG
                    {
                        MaDH = newOrder.MaDH,
                        MaSP = item.MaSP,
                        SoLuong = item.SoLuong,
                        Giaban = item.GiaBan,
                        ThanhTien = item.SoLuong * item.GiaBan
                    };
                    db.CHITIETDONHANG.Add(ct);
                }

                db.SaveChanges();

                // Xóa giỏ hàng sau khi thanh toán thành công
                Session.Remove(CartSessionKey);

               
            }
            catch (Exception ex)
            {
                // Log exception và hiện thông báo lỗi
                Console.WriteLine(ex.Message);
                TempData["ErrorMessage"] = "Đã có lỗi xảy ra khi lưu đơn hàng. Vui lòng thử lại.";
                return RedirectToAction("CamOn");
            }
            // Điều hướng lại trang cảm ơn
            return RedirectToAction("CamOn");

        }

        public ActionResult CamOn()
        {
            return View();
        }
    }
}
