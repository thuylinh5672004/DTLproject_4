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
        // GET: Cart
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
            // Lấy giỏ hàng từ session
            var cart = GetCart();

            // Cập nhật số lượng cho sản phẩm trong giỏ hàng
            var item = cart.Items.FirstOrDefault(i => i.MaSP == maSP);
            if (item != null)
            {
                item.SoLuong = soLuongMoi;  // Cập nhật số lượng
            }

            // Lưu lại giỏ hàng đã cập nhật vào session
            Session[CartSessionKey] = cart;

            // Quay lại trang giỏ hàng
            return RedirectToAction("Index");
        }
        // GET: /Cart
        public ActionResult Index()
        {
            var cart = GetCart();
            return View(cart);
        }
        //thanh toán
        public ActionResult ThongtinThanhtoan()
        {
            var cart = GetCart();
            return View(cart);
        }
        public ActionResult ThanhToan(FormCollection form)
        {
            // Lấy thông tin khách hàng từ form
            var customerName = form["CustomerName"];
            var customerPhone = form["CustomerPhone"];
            var address = form["Address"];

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
                MaDH = orderID,  // Mã đơn hàng 10 ký tự
                Hoten = customerName,  // Họ tên khách hàng
                Diachi = address,  // Địa chỉ giao hàng
                SDT = customerPhone,  // Số điện thoại
                Ngaydat = DateTime.Now,  // Ngày đặt hàng
                Trangthai = "Chưa thanh toán",  // Trạng thái đơn hàng

            };

            // Lưu đơn hàng vào cơ sở dữ liệu
            db.DONHANG.Add(newOrder);
            try
            {
                // Lưu dữ liệu vào database
                db.SaveChanges();

                // Thêm chi tiết đơn hàng
                var latestOrder = db.DONHANG.OrderByDescending(x => x.Ngaydat).FirstOrDefault();

                if (latestOrder != null)
                {
                    foreach (var item in cart.Items)
                    {
                        CHITIETDONHANG ct = new CHITIETDONHANG
                        {
                            MaDH = latestOrder.MaDH,  // Mã đơn hàng
                            MaSP = item.MaSP,  // Mã sản phẩm
                            SoLuong = item.SoLuong,  // Số lượng
                            Giaban = item.GiaBan,  // Giá bán
                            ThanhTien = item.SoLuong * item.GiaBan  // Thành tiền
                        };

                        db.CHITIETDONHANG.Add(ct);
                    }
                    // Lưu chi tiết đơn hàng vào database
                    db.SaveChanges();
                }

                // Xóa giỏ hàng sau khi thanh toán thành công
                Session.Remove(CartSessionKey);

                return RedirectToAction("Index");
            }
            catch (System.Data.Entity.Validation.DbEntityValidationException ex)
            {
                foreach (var validationErrors in ex.EntityValidationErrors)
                {
                    foreach (var validationError in validationErrors.ValidationErrors)
                    {
                        Console.WriteLine($"Property: {validationError.PropertyName} Error: {validationError.ErrorMessage}");
                    }
                }

                // Xử lý khi có lỗi xảy ra
                TempData["ErrorMessage"] = "Đã có lỗi xảy ra khi lưu đơn hàng. Vui lòng kiểm tra thông tin và thử lại.";
                return RedirectToAction("/");
            }  

        }
    }
}