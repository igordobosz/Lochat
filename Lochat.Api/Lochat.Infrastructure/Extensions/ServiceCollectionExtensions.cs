using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Microsoft.Extensions.DependencyInjection;

namespace Lochat.Infrastructure.Extensions
{
    public static class ServiceCollectionExtensions
    {
        public static void RegisterServicesOfAssemblies(this IServiceCollection services, IEnumerable<Assembly> assemblies)
        {
            services.Scan(scan => scan.FromAssemblies(assemblies)
                .AddClasses(classes => classes.AssignableTo(typeof(ICrudService<,>)))
                .AsImplementedInterfaces()
                .AddClasses(classes => classes.AssignableTo(typeof(IRepository<>)))
                .AsImplementedInterfaces());

//            foreach (Assembly assembly in assemblies)
//            {
//                var diInitializerType = assembly.GetTypes().FirstOrDefault(e => typeof(IDIInitializer).IsAssignableFrom(e));
//                if (diInitializerType != null)
//                {
//                    var diInitializer = (IDIInitializer)Activator.CreateInstance(diInitializerType);
//                    diInitializer.Configure(services);
//                }
//            }
        }
    }
}
