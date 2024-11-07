using Daothithuylinhproject2_2210900036.Areas.ModelView;
using Daothithuylinhproject2_2210900036.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace Daothithuylinhproject2_2210900036.Areas.DTLinhAdmin.Controllers
{
    public class CartController : Controller
    {
        private const string CartSessionKey = "CartItems";
        Daothithuylinh_k22CNTT_2210900036Entities2 db = new Daothithuylinh_k22CNTT_2210900036Entities2 ();
        private ShoppingCart GetCart()
        {
            var cart = Session[CartSessionKey] as ShoppingCart;
            if (cart == null)
            {
                cart = new ShoppingCart();
                Session[CartSessionKey]= cart;
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
        // GET: DTLinhAdmin/Cart
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
        { // Lấy thông tin khách hàng từ form
            var customerName = form["CustomerName"];
            var customerEmail = form["CustomerEmail"];
            var customerPhone = form["CustomerPhone"];
            var address = form["Address"];
            var creditCardNumber = form["CreditCardNumber"];

            // Lấy giỏ hàng hiện tại
            var cart = GetCart();

            if (cart.Items.Count == 0)
            {
                TempData["ErrorMessage"] = "Giỏ hàng của bạn đang trống.";
                return RedirectToAction("Index");
            }

            // Tính tổng tiền cho đơn hàng
            decimal totalAmount = cart.Items.Sum(item => item.SoLuong * item.GiaBan);

            // Tạo mã đơn hàng (sử dụng định dạng mã có độ dài 10 ký tự, ví dụ DH12345678)
            string orderID = "DH" + DateTime.Now.ToString("yyMMddHHmm");

            // Tạo đơn hàng mới với thông tin cần thiết
            DONHANG newOrder = new DONHANG
            {
                MaDH = orderID,  // Mã đơn hàng 10 ký tự
                MaKH = customerName.Substring(0, Math.Min(customerName.Length, 10)),  // Mã khách hàng không vượt quá 10 ký tự
                Tongtien = totalAmount,  // Tổng tiền
                Ngaydat = DateTime.Now,  // Ngày đặt hàng
                Trangthai = "Chưa thanh toán"  // Trạng thái đơn hàng
            };

            // Lưu đơn hàng vào cơ sở dữ liệu
            db.DONHANG.Add(newOrder);
            db.SaveChanges();
            return RedirectToAction("/");
        }
           

        
    }
}