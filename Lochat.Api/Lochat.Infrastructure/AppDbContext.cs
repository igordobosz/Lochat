using System;
using Microsoft.EntityFrameworkCore;

namespace Lochat.Infrastructure
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {

        }
    }
}
