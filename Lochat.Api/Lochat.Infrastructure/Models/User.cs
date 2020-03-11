using System.Collections.Generic;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.Models
{
    public class User : EntityBase   
    {
        public override string Id { get; set; } // Phone ID might be used as a key, if not - add another property for it
        public string Username { get; set; }
//        public virtual ICollection<User> Blacklist { get; set; }
        public virtual ICollection<Chatroom> Chatrooms { get; set; }
        public virtual ICollection<Message> Messages { get; set; }
    }
}