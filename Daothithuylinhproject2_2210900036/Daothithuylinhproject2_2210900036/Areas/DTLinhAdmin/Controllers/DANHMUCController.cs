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
    public class DANHMUCController : Controller
    {
        private Daothithuylinh_k22CNTT_2210900036Entities2 db = new Daothithuylinh_k22CNTT_2210900036Entities2();

        // GET: DTLinhAdmin/DANHMUC
        public ActionResult DtlIndex()
        {
            return View(db.DANHMUC.ToList());
        }

        // GET: DTLinhAdmin/DANHMUC/Details/5
        public ActionResult DtlDetails(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DANHMUC dANHMUC = db.DANHMUC.Find(id);
            if (dANHMUC == null)
            {
                return HttpNotFound();
            }
            return View(dANHMUC);
        }

        // GET: DTLinhAdmin/DANHMUC/Create
        public ActionResult DtlCreate()
        {
            return View();
        }

        // POST: DTLinhAdmin/DANHMUCs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult DtlCreate([Bind(Include = "MaDM,TenDM")] DANHMUC dANHMUC)
        {
            if (ModelState.IsValid)
            {
                db.DANHMUC.Add(dANHMUC);
                db.SaveChanges();
                return RedirectToAction(" DtlIndex");
            }

            return View(dANHMUC);
        }

        // GET: DTLinhAdmin/DANHMUC/Edit/5
        public ActionResult DtlEdit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DANHMUC dANHMUC = db.DANHMUC.Find(id);
            if (dANHMUC == null)
            {
                return HttpNotFound();
            }
            return View(dANHMUC);
        }

        // POST: DTLinhAdmin/DANHMUC/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult DtlEdit([Bind(Include = "MaDM,TenDM")] DANHMUC dANHMUC)
        {
            if (ModelState.IsValid)
            {
                db.Entry(dANHMUC).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction(" DtlIndex");
            }
            return View(dANHMUC);
        }

        // GET: DTLinhAdmin/DANHMUC/Delete/5
        public ActionResult DtlDelete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DANHMUC dANHMUC = db.DANHMUC.Find(id);
            if (dANHMUC == null)
            {
                return HttpNotFound();
            }
            return View(dANHMUC);
        }

        // POST: DTLinhAdmin/DANHMUC/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            DANHMUC dANHMUC = db.DANHMUC.Find(id);
            db.DANHMUC.Remove(dANHMUC);
            db.SaveChanges();
            return RedirectToAction(" DtlIndex");
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
