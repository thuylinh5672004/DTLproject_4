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
    public class DONHANGController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities2 db = new Daothithuylinh_k22CNTT_2210900036Entities2();

        // GET: DTLinhAdmin/DONHANGs
        public ActionResult Index()
        {
            var dONHANG = db.DONHANG.Include(d => d.KHACHHANG);
            return View(dONHANG.ToList());
        }

        // GET: DTLinhAdmin/DONHANGs/Details/5
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

        // GET: DTLinhAdmin/DONHANGs/Create
        public ActionResult Create()
        {
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen");
            return View();
        }

        // POST: DTLinhAdmin/DONHANGs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaDH,MaKH,Tongtien,Ngaydat,Trangthai")] DONHANG dONHANG)
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

        // GET: DTLinhAdmin/DONHANGs/Edit/5
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
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen", dONHANG.MaKH);
            return View(dONHANG);
        }

        // POST: DTLinhAdmin/DONHANGs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaDH,MaKH,Tongtien,Ngaydat,Trangthai")] DONHANG dONHANG)
        {
            if (ModelState.IsValid)
            {
                db.Entry(dONHANG).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.MaKH = new SelectList(db.KHACHHANG, "MaKH", "HoTen", dONHANG.MaKH);
            return View(dONHANG);
        }

        // GET: DTLinhAdmin/DONHANGs/Delete/5
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

        // POST: DTLinhAdmin/DONHANGs/Delete/5
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
