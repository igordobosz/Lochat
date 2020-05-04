using System.Collections.Generic;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.Models
{
    public class User : EntityBase   
    {
        public override string Id { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
        public virtual ICollection<Chatroom> Chatrooms { get; set; }
        public virtual ICollection<Message> Messages { get; set; }
    }
}