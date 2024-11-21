using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Daothithuylinh_2210900036_project2.ModelView
{
    public class DashboardViewModel
    {

        
        public int TotalOrders { get; set; }
        public decimal DailyRevenue { get; set; }
        public decimal MonthlyRevenue { get; set; }
        public decimal YearlyRevenue { get; set; }
        public List<TopSellingProduct> TopSellingProducts { get; set; }
    }

    public class TopSellingProduct
    {
        public string  ProductId { get; set; }  
        public int TotalSold { get; set; }
    }
}
