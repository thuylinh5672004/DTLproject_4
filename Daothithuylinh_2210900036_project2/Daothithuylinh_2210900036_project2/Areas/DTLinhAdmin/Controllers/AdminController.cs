using System.Web.Mvc;

using Daothithuylinh_2210900036_project2.Models;

namespace Daothithuylinh_2210900036_project2.Areas.DTLinhAdmin.Controllers
{
    public class AdminController : Controller
    {
        private const string AdminUsername = "admin";  
        private const string AdminPassword = "@123";  

        // GET: Admin/Login
        public ActionResult Login()
        {
            return View();
        }

        // POST: Admin/Login
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(Admin model)
        {
            if (ModelState.IsValid)
            {
                // Kiểm tra tên đăng nhập và mật khẩu
                if (model.Username == AdminUsername && model.Password == AdminPassword)
                {
                    // Đăng nhập thành công, lưu thông tin admin vào Session
                    Session["AdminUsername"] = model.Username;

                    // Chuyển hướng tới trang Dashboard hoặc quản lý admin
                    return RedirectToAction("Dashboard", "Admin");
                }
                else
                {
                    TempData["ErrorMessage"] = "Tên đăng nhập hoặc mật khẩu không đúng.";
                    return View();
                }
            }

            return View();
        }

        // Đăng xuất Admin
        public ActionResult Logout()
        {
            // Xóa thông tin đăng nhập trong Session
            Session["AdminUsername"] = null;

            // Quay lại trang đăng nhập
            return RedirectToAction("Login", "Admin");
        }

        // Trang Dashboard Admin
        public ActionResult Dashboard()
        {
            // Kiểm tra nếu chưa đăng nhập, chuyển hướng tới trang đăng nhập
            if (Session["AdminUsername"] == null)
            {
                return RedirectToAction("Login", "Admin");
            }

            return View();
        }
    }
}
