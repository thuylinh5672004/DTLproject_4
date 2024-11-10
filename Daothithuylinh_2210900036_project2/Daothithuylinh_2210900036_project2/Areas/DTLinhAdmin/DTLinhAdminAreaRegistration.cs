using System.Web.Mvc;

namespace Daothithuylinh_2210900036_project2.Areas.DTLinhAdmin
{
    public class DTLinhAdminAreaRegistration : AreaRegistration 
    {
        public override string AreaName 
        {
            get 
            {
                return "DTLinhAdmin";
            }
        }

        public override void RegisterArea(AreaRegistrationContext context) 
        {
            context.MapRoute(
                "DTLinhAdmin_default",
                "DTLinhAdmin/{controller}/{action}/{id}",
                new { action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}