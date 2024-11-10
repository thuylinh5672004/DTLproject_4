using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Daothithuylinh_2210900036_project2.ModelView
{
    public class Donhang
    {
        [Required]
        public string MaDH { get; set; }

        [Required]
        public string Hoten { get; set; }

        [Required]
        public string Diachi { get; set; }

        [Required]
        [Phone]
        public string SDT { get; set; }

        public DateTime Ngaydat { get; set; }
        public string Trangthai { get; set; }
    }
}