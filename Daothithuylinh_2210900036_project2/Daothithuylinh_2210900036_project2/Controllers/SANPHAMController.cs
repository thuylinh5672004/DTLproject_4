using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Daothithuylinh_2210900036_project2.Models;

namespace Daothithuylinh_2210900036_project2.Controllers
{
    public class SANPHAMController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities db = new Daothithuylinh_k22CNTT_2210900036Entities();

        // GET: SANPHAM
        public ActionResult Index()
        {
            var products = new List<SANPHAM>
            {
                new SANPHAM { MaSP = "L01", TenSP = "Áo thun", MaDM = "H01", GiaBan = 100, SoLuongTonKho = 50, Anh = Url.Content("~/Image/Anh1.jpg") },
                new SANPHAM { MaSP = "L02", TenSP = "Quần jean", MaDM = "H02", GiaBan = 90, SoLuongTonKho = 60, Anh = Url.Content("~/Image/Anh2.jpg")},
                new SANPHAM { MaSP = "L03", TenSP = "Áo SơMi", MaDM = "H03", GiaBan = 110, SoLuongTonKho = 78, Anh = Url.Content("~/Image/Anh3.jpg") }
            };

            // Loại bỏ các sản phẩm có mã NULL hoặc thiếu thông tin
            var validProducts = products.Where(p => !string.IsNullOrEmpty(p.MaSP) && p.GiaBan > 0).ToList();

            return View(validProducts);
        }

        // GET: SANPHAM/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SANPHAM sANPHAM = db.SANPHAM.Find(id);
            if (sANPHAM == null)
            {
                return HttpNotFound();
            }
            return View(sANPHAM);
        }

        // GET: SANPHAM/Create
        public ActionResult Create()
        {
            ViewBag.MaDM = new SelectList(db.DANHMUC, "MaDM", "TenDM");
            return View();
        }

        // POST: SANPHAM/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaSP,TenSP,MaDM,GiaBan,SoLuongTonKho,Anh")] SANPHAM sANPHAM)
        {
            if (ModelState.IsValid)
            {
                db.SANPHAM.Add(sANPHAM);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.MaDM = new SelectList(db.DANHMUC, "MaDM", "TenDM", sANPHAM.MaDM);
            return View(sANPHAM);
        }

        // GET: SANPHAM/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SANPHAM sANPHAM = db.SANPHAM.Find(id);
            if (sANPHAM == null)
            {
                return HttpNotFound();
            }
            ViewBag.MaDM = new SelectList(db.DANHMUC, "MaDM", "TenDM", sANPHAM.MaDM);
            return View(sANPHAM);
        }

        // POST: SANPHAM/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaSP,TenSP,MaDM,GiaBan,SoLuongTonKho,Anh")] SANPHAM sANPHAM)
        {
            if (ModelState.IsValid)
            {
                db.Entry(sANPHAM).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.MaDM = new SelectList(db.DANHMUC, "MaDM", "TenDM", sANPHAM.MaDM);
            return View(sANPHAM);
        }

        // GET: SANPHAM/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SANPHAM sANPHAM = db.SANPHAM.Find(id);
            if (sANPHAM == null)
            {
                return HttpNotFound();
            }
            return View(sANPHAM);
        }

        // POST: SANPHAM/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            SANPHAM sANPHAM = db.SANPHAM.Find(id);
            db.SANPHAM.Remove(sANPHAM);
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
