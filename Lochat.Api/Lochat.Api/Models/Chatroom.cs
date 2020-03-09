using System;
using System.Collections.Generic;

namespace Lochat.Api.Models
{
    public class Chatroom
    {
        public string ID { get; set; }
        public User Owner { get; set; }
        public DateTime CreationTime { get; set; }
        public DateTime TerminationTime { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public bool IsDynamic { get; set; }
        public ICollection<User> BannedUsers { get; set; }
    }
}