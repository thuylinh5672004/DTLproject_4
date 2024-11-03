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
    public class THANHTOANController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities2 db = new Daothithuylinh_k22CNTT_2210900036Entities2();

        // GET: DTLinhAdmin/THANHTOAN
        public ActionResult Index()
        {
            var tHANHTOAN = db.THANHTOAN.Include(t => t.DONHANG);
            return View(tHANHTOAN.ToList());
        }

        // GET: DTLinhAdmin/THANHTOAN/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            THANHTOAN tHANHTOAN = db.THANHTOAN.Find(id);
            if (tHANHTOAN == null)
            {
                return HttpNotFound();
            }
            return View(tHANHTOAN);
        }

        // GET: DTLinhAdmin/THANHTOAN/Create
        public ActionResult Create()
        {
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH");
            return View();
        }

        // POST: DTLinhAdmin/THANHTOAN/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "MaTT,MaDH,PhuongThucTT,NgayTT,Sotien")] THANHTOAN tHANHTOAN)
        {
            if (ModelState.IsValid)
            {
                db.THANHTOAN.Add(tHANHTOAN);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", tHANHTOAN.MaDH);
            return View(tHANHTOAN);
        }

        // GET: DTLinhAdmin/THANHTOAN/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            THANHTOAN tHANHTOAN = db.THANHTOAN.Find(id);
            if (tHANHTOAN == null)
            {
                return HttpNotFound();
            }
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", tHANHTOAN.MaDH);
            return View(tHANHTOAN);
        }

        // POST: DTLinhAdmin/THANHTOAN/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "MaTT,MaDH,PhuongThucTT,NgayTT,Sotien")] THANHTOAN tHANHTOAN)
        {
            if (ModelState.IsValid)
            {
                db.Entry(tHANHTOAN).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.MaDH = new SelectList(db.DONHANG, "MaDH", "MaKH", tHANHTOAN.MaDH);
            return View(tHANHTOAN);
        }

        // GET: DTLinhAdmin/THANHTOAN/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            THANHTOAN tHANHTOAN = db.THANHTOAN.Find(id);
            if (tHANHTOAN == null)
            {
                return HttpNotFound();
            }
            return View(tHANHTOAN);
        }

        // POST: DTLinhAdmin/THANHTOAN/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            THANHTOAN tHANHTOAN = db.THANHTOAN.Find(id);
            db.THANHTOAN.Remove(tHANHTOAN);
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
