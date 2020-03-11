using System;
using Lochat.Infrastructure.Models;
using Microsoft.EntityFrameworkCore;

namespace Lochat.Infrastructure
{
    public class AppDbContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Chatroom> Chatrooms { get; set; }
        public DbSet<Message> Messages { get; set; }

        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Chatroom>().HasOne(m => m.Owner).WithMany(m => m.Chatrooms)
                .HasForeignKey(k => k.OwnerId).OnDelete(DeleteBehavior.Cascade);
            modelBuilder.Entity<Message>().HasOne(m => m.Chatroom).WithMany(m => m.Messages)
                .HasForeignKey(k => k.ChatroomId).OnDelete(DeleteBehavior.Cascade);
            modelBuilder.Entity<Message>().HasOne(m => m.Author).WithMany(m => m.Messages)
                .HasForeignKey(k => k.AuthorId).OnDelete(DeleteBehavior.Cascade); 
        }
    }
}
