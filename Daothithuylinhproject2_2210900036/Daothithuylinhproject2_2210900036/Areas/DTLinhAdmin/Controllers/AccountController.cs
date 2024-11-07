using Daothithuylinhproject2_2210900036.Areas.ModelView;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace Daothithuylinhproject2_2210900036.Areas.DTLinhAdmin.Controllers
{
    public class AccountController : Controller
    {
        private AuthService authService = new AuthService();
        // GET: Account/Login
        public ActionResult Login()
        {
            return View();
        }

        // POST: Account/Login
        [HttpPost]
        public ActionResult Login(string email, string matkhau)
        {
            var khachHang = authService.Login(email, matkhau);
            if (khachHang != null)
            {
                // Đăng nhập thành công, lưu thông tin người dùng vào session
                Session["MaKH"] = khachHang.MaKH;
                Session["HoTen"] = khachHang.HoTen;

                // Tạo cookie đăng nhập (optional)
                FormsAuthentication.SetAuthCookie(khachHang.MaKH, false);

                return RedirectToAction("Index", "Home");
            }
            else
            {
                // Nếu đăng nhập thất bại
                ViewBag.ErrorMessage = "Đăng nhập không thành công, vui lòng kiểm tra lại email hoặc mật khẩu.";
                return View();
            }
        }

        // Đăng xuất
        public ActionResult Logout()
        {
            authService.Logout();
            Session.Clear();  // Xóa thông tin session

            return RedirectToAction("Login", "Account");
        }
        protected void Application_AuthenticateRequest(object sender, EventArgs e)
        {
            if (Session["MaKH"] != null)
            {
                // Người dùng đã đăng nhập, có thể xử lý quyền truy cập
            }
            else
            {
                // Chuyển hướng về trang đăng nhập nếu chưa đăng nhập
                if (!Request.Url.AbsolutePath.Contains("Login"))
                {
                    Response.Redirect("/Account/Login");
                }
            }
        }
        // GET: DTLinhAdmin/Account
        public ActionResult Index()
        {
            return View();
        }
    }
}