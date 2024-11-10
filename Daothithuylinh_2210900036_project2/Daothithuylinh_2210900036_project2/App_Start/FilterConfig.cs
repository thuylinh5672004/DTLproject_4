using System.Web;
using System.Web.Mvc;

namespace Daothithuylinh_2210900036_project2
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
