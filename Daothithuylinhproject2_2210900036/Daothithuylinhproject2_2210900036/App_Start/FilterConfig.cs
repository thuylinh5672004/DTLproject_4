using System.Web;
using System.Web.Mvc;

namespace Daothithuylinhproject2_2210900036
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());
        }
    }
}
