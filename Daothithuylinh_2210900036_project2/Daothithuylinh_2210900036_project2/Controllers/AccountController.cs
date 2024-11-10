using Daothithuylinh_2210900036_project2.Models;
using Daothithuylinh_2210900036_project2.ModelView;
using System;
using System.Linq;
using System.Web.Mvc;

namespace Daothithuylinh_2210900036_project2.Controllers
{
    public class AccountController : Controller
    {
        Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

        // Đăng ký
        [HttpGet]
        public ActionResult Register()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Register(Register model)
        {
            if (ModelState.IsValid)
            {
                // Kiểm tra xem tên đăng nhập hoặc email đã tồn tại chưa
                var userExist = db.User.Any(u => u.Username == model.Username || u.Email == model.Email);
                if (userExist)
                {
                    ModelState.AddModelError("", "Tên đăng nhập hoặc email đã tồn tại.");
                    return View(model);
                }

                // Tạo ID cho người dùng mới (nên là duy nhất)
                var newUserId = Guid.NewGuid().ToString("N").Substring(0, 10); // Sinh ID có độ dài 10 ký tự

                // Tạo người dùng mới
                var user = new User
                {
                    UserId = newUserId,
                    Username = model.Username,
                    Password = model.Password,  // Không mã hóa mật khẩu
                    Email = model.Email
                };

                // Thêm người dùng vào cơ sở dữ liệu
                db.User.Add(user);
                db.SaveChanges();

                // Chuyển hướng sang trang đăng nhập
                return RedirectToAction("Login");
            }

            return View(model);
        }

        // Đăng nhập
        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(Login model)
        {
            if (ModelState.IsValid)
            {
                // Kiểm tra xem tên đăng nhập và mật khẩu có chính xác không
                var user = db.User.SingleOrDefault(u => u.Username == model.Username && u.Password == model.Password);
                if (user != null)
                {
                    // Lưu thông tin người dùng vào session
                    Session["Username"] = user.Username;

                    // Điều hướng đến trang chủ
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    ModelState.AddModelError("", "Tên đăng nhập hoặc mật khẩu không đúng.");
                }
            }

            return View(model);
        }

        // Đăng xuất
        public ActionResult Logout()
        {
            // Xóa session
            Session.Clear();
            return RedirectToAction("Login");
        }
    }
}
