using System;
using System.Collections.Generic;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.Models
{
    public class Chatroom : EntityBase
    {
        public override string Id { get; set; }
        public string OwnerId { get; set; }
        public virtual User Owner { get; set; }
        public string Name { get; set; }
        public DateTime CreationTime { get; set; }
        public DateTime TerminationTime { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public bool IsDynamic { get; set; }
//        public virtual ICollection<User> BannedUsers { get; set; }
        public virtual ICollection<Message> Messages { get; set; }
    }
}