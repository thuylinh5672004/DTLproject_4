namespace Daothithuylinhproject2_2210900036.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("DtlTaiKhoan")]
    public partial class DtlTaiKhoan
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int DtlId { get; set; }

        [StringLength(50)]
        public string DtlUserName { get; set; }

        [StringLength(50)]
        public string DtlPassword { get; set; }

        [StringLength(50)]
        public string DtlFullName { get; set; }

        public int? DtlAge { get; set; }

        [StringLength(50)]
        public string DtlEmail { get; set; }

        [StringLength(50)]
        public string DtlPhone { get; set; }

        public bool? DtlStatus { get; set; }
    }
}
