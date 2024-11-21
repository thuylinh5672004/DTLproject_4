using System;
using System.Linq;
using System.Web.Mvc;
using Daothithuylinh_2210900036_project2.Models;
using Daothithuylinh_2210900036_project2.ModelView;

namespace Daothithuylinh_2210900036_project2.Areas.DTLinhAdmin.Controllers
{
    public class DTLDashboardController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

        // GET: DTLinhAdmin/DTLDashboard
        public ActionResult Index()
        {
            // Tổng số đơn hàng
            int totalOrders = db.DONHANG.Count();  // Count() trả về kiểu int nên không cần ép kiểu

            // Doanh thu trong ngày (Sử dụng ?? 0 để tránh null)
            decimal dailyRevenue = db.DONHANG
    .Where(o => o.Ngaydat.HasValue &&
                o.Ngaydat.Value.Year == DateTime.Now.Year &&
                o.Ngaydat.Value.Month == DateTime.Now.Month &&
                o.Ngaydat.Value.Day == DateTime.Now.Day)
    .Sum(o => o.CHITIETDONHANG.Sum(c => c.ThanhTien)) ?? 0;
            // Doanh thu trong tháng (Sử dụng ?? 0 để tránh null)
            decimal monthlyRevenue = db.DONHANG
                .Where(o => o.Ngaydat.HasValue && o.Ngaydat.Value.Month == DateTime.Now.Month && o.Ngaydat.Value.Year == DateTime.Now.Year)
                .Sum(o => o.CHITIETDONHANG.Sum(c => c.ThanhTien ?? 0));

            // Doanh thu trong năm (Sử dụng ?? 0 để tránh null)
            decimal yearlyRevenue = db.DONHANG
                .Where(o => o.Ngaydat.HasValue && o.Ngaydat.Value.Year == DateTime.Now.Year)
                .Sum(o => o.CHITIETDONHANG.Sum(c => c.ThanhTien ?? 0));

            // Sản phẩm bán chạy nhất
            // Sản phẩm bán chạy nhất
            var topSellingProducts = db.CHITIETDONHANG
                .GroupBy(c => c.MaSP)
                .Select(g => new
                {
                    ProductId = g.Key,  // Đây là giá trị MaSP của nhóm
                    TotalSold = g.Sum(x => (x.SoLuong ?? 0))  // Tính tổng số lượng đã bán
                })
                .OrderByDescending(g => g.TotalSold)
                .Take(5)
                .ToList()
                .Select(g => new TopSellingProduct
                {
                    ProductId = g.ProductId,  // Đặt ProductId từ đối tượng ẩn danh vào TopSellingProduct
                    TotalSold = g.TotalSold
                })
                .ToList();


            // Tạo ViewModel và gán giá trị
            var dashboardViewModel = new DashboardViewModel
            {
                TotalOrders = totalOrders,
                DailyRevenue = dailyRevenue,
                MonthlyRevenue = monthlyRevenue,
                YearlyRevenue = yearlyRevenue,
                TopSellingProducts = topSellingProducts
            };

            // Truyền ViewModel vào View
            return View(dashboardViewModel);
        }
    }
}
