using System.Web.Mvc;

namespace Daothithuylinhproject2_2210900036.Areas.DTLinhAdmin
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
                new { action = "Index", id = UrlParameter.Optional },
                namespaces: new[] { "Daothithuylinhproject2_2210900036.Areas.DTLinhAdmin.Controllers" }
                
            );
        }
    }
}