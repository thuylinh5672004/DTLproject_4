using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Daothithuylinhproject2_2210900036.Models;

namespace Daothithuylinhproject2_2210900036.Areas.DTLinhAdmin.Controllers
{
    public class CHITIETDONHANGController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities2 db = new Daothithuylinh_k22CNTT_2210900036Entities2();

        // GET: DTLinhAdmin/CHITIETDONHANG
        public ActionResult Index()
        {
            var cHITIETDONHANG = db.CHITIETDONHANG.Include(c => c.DONHANG).Include(c => c.SANPHAM);
            return View(cHITIETDONHANG.ToList());
        }

        // GET: DTLinhAdmin/CHITIETDONHANG/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            CHITIETDONHANG cHITIETDONHANG = db.CHITIETDONHANG.Find(id);
            if (cHITIETDONHANG == null)
            {
                return HttpNotFound();
            }
            return View(cHITIETDONHANG);
        }

        // GET: DTLinhAdmin/CHITIETDONHANG/Create
        public ActionResult Create()
        {
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH");
            ViewBag.MaSP = new SelectList(db.SANPHAM, "MaSP", "TenSP");
            return View();
        }

        // POST: DTLinhAdmin/CHITIETDONHANG/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaChiTietDH,MaDH,MaSP,Tongtien,Ngaydat")] CHITIETDONHANG cHITIETDONHANG)
        {
            if (ModelState.IsValid)
            {
                db.CHITIETDONHANG.Add(cHITIETDONHANG);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", cHITIETDONHANG.MaDH);
            ViewBag.MaSP = new SelectList(db.SANPHAM, "MaSP", "TenSP", cHITIETDONHANG.MaSP);
            return View(cHITIETDONHANG);
        }

        // GET: DTLinhAdmin/CHITIETDONHANG/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            CHITIETDONHANG cHITIETDONHANG = db.CHITIETDONHANG.Find(id);
            if (cHITIETDONHANG == null)
            {
                return HttpNotFound();
            }
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", cHITIETDONHANG.MaDH);
            ViewBag.MaSP = new SelectList(db.SANPHAM, "MaSP", "TenSP", cHITIETDONHANG.MaSP);
            return View(cHITIETDONHANG);
        }

        // POST: DTLinhAdmin/CHITIETDONHANG/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaChiTietDH,MaDH,MaSP,Tongtien,Ngaydat")] CHITIETDONHANG cHITIETDONHANG)
        {
            if (ModelState.IsValid)
            {
                db.Entry(cHITIETDONHANG).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", cHITIETDONHANG.MaDH);
            ViewBag.MaSP = new SelectList(db.SANPHAM, "MaSP", "TenSP", cHITIETDONHANG.MaSP);
            return View(cHITIETDONHANG);
        }

        // GET: DTLinhAdmin/CHITIETDONHANG/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            CHITIETDONHANG cHITIETDONHANG = db.CHITIETDONHANG.Find(id);
            if (cHITIETDONHANG == null)
            {
                return HttpNotFound();
            }
            return View(cHITIETDONHANG);
        }

        // POST: DTLinhAdmin/CHITIETDONHANG/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            CHITIETDONHANG cHITIETDONHANG = db.CHITIETDONHANG.Find(id);
            db.CHITIETDONHANG.Remove(cHITIETDONHANG);
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
