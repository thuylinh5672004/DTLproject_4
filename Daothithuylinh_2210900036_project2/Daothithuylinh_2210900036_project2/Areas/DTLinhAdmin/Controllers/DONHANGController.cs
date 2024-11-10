using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Daothithuylinh_2210900036_project2.Models;
using Daothithuylinh_2210900036_project2.ModelView;

namespace Daothithuylinh_2210900036_project2.Areas.DTLinhAdmin.Controllers
{
    public class DONHANGController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

        // GET: DTLinhAdmin/DONHANG
        public ActionResult Index()
        {
            var dONHANG = db.DONHANG.Include(d => d.KHACHHANG);
            return View(dONHANG.ToList());
        }

        // GET: DTLinhAdmin/DONHANG/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DONHANG dONHANG = db.DONHANG.Find(id);
            if (dONHANG == null)
            {
                return HttpNotFound();
            }
            return View(dONHANG);
        }

        // GET: DTLinhAdmin/DONHANG/Create
        public ActionResult Create()
        {
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen");
            return View();
        }

        // POST: DTLinhAdmin/DONHANG/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaDH,MaKH,Tongtien,Ngaydat,Trangthai,Hoten,Diachi,SDT")] DONHANG dONHANG)
        {
            if (ModelState.IsValid)
            {
                db.DONHANG.Add(dONHANG);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen", dONHANG.MaKH);
            return View(dONHANG);
        }

        // GET: DTLinhAdmin/DONHANG/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DONHANG dONHANG = db.DONHANG.Find(id);
            if (dONHANG == null)
            {
                return HttpNotFound();
            }
            var chiTietDonHang = from ct in db.CHITIETDONHANG
                                 join sp in db.SANPHAM on ct.MaSP equals sp.MaSP
                                 where ct.MaDH == id  // lọc theo mã đơn hàng
                                 select new DH_ChiTiet
                                 {
                                     MaSP = ct.MaSP,
                                     TenSP = sp.TenSP ?? "Chưa có tên sản phẩm",  // Kiểm tra null cho TenSP
                                     GiaBan = (decimal)ct.Giaban,
                                     SoLuong = (int)ct.SoLuong,
                                     // ThanhTien sẽ được tính tự động qua thuộc tính
                                 };

            ViewBag.donHangChiTiet = chiTietDonHang.ToList();
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen", dONHANG.MaKH);
           

           

            return View(dONHANG);
        }

        // POST: DTLinhAdmin/DONHANG/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaDH,MaKH,Tongtien,Ngaydat,Trangthai,Hoten,Diachi,SDT")] DONHANG dONHANG)
        {
            if (ModelState.IsValid)
            {
                // Tìm đơn hàng từ cơ sở dữ liệu
                var donHang = db.DONHANG.FirstOrDefault(x => x.MaDH == dONHANG.MaDH);
                if (donHang != null)
                {
                    // Cập nhật thông tin trạng thái của đơn hàng
                    donHang.Trangthai = dONHANG.Trangthai;

                    // Đặt trạng thái của donHang là Modified
                    db.Entry(donHang).State = EntityState.Modified;

                    // Lưu thay đổi vào cơ sở dữ liệu
                    db.SaveChanges();
                }

                // Chuyển hướng về danh sách
                return RedirectToAction("Index");
            }

            // Nếu có lỗi, trả lại View với các thông tin đã nhập
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen", dONHANG.MaKH);
            return View(dONHANG);
        }

        // GET: DTLinhAdmin/DONHANG/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DONHANG dONHANG = db.DONHANG.Find(id);
            if (dONHANG == null)
            {
                return HttpNotFound();
            }
            return View(dONHANG);
        }

        // POST: DTLinhAdmin/DONHANG/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            DONHANG dONHANG = db.DONHANG.Find(id);
            db.DONHANG.Remove(dONHANG);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
