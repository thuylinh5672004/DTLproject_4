using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;
using System.Linq;

namespace Daothithuylinhproject2_2210900036.Models
{
    public partial class LoginModel : DbContext
    {
        public LoginModel()
            : base("name=LoginModel4")
        {
        }

        public virtual DbSet<DtlTaiKhoan> DtlTaiKhoan { get; set; }
        public virtual DbSet<sysdiagrams> sysdiagrams { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }
    }
}
