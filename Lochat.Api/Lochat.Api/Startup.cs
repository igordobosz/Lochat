using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using AutoMapper;
using Google.Apis.Auth;
using Lochat.Api.Options;
using Lochat.Infrastructure;
using Lochat.Infrastructure.Extensions;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;

namespace Lochat.Api
{
    public class Startup
    {
        #region Nested Classes 
        public class ModuleInfo
        {
            public string Name { get; set; }
            public Assembly Assembly { get; set; }
        }
        #endregion

        private readonly string[] _modulesNames = { "Lochat.Service", "Lochat.Infrastructure" };
        private readonly IList<ModuleInfo> _modules;
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
            _modules = new List<ModuleInfo>();
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo() {Title = "Lochat Api", Version = "v1"});
            });

            LoadModules();

            services.RegisterServicesOfAssemblies(_modules.Select(e => e.Assembly));
            services.AddAutoMapper(_modules.Select(e => e.Assembly));

            services.AddDbContext<AppDbContext>(options =>
                options.UseLazyLoadingProxies().UseMySql(Configuration.GetConnectionString("DefaultConnection"),
                    b => Assembly.GetExecutingAssembly()));

            services.AddOptions();
            services.Configure<AuthenticationOptions>(Configuration.GetSection(OptionsSectionTypes.Authentication));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseSwagger();

            app.UseSwaggerUI(c =>
            {
                c.SwaggerEndpoint("/swagger/v1/swagger.json", "My API V1");
            });

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void LoadModules()
        {
            AppDomain.CurrentDomain.GetAssemblies().
                Where(e => _modulesNames.Contains(e.GetName().Name)).ToList().
                ForEach(e => _modules.Add(new ModuleInfo()
                {
                    Assembly = e,
                    Name = e.GetName().Name
                }));
        }
    }
}
