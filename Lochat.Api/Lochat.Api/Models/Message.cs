using System;

namespace Lochat.Api.Models
{
    public class Message
    {
        public string ID { get; set; }
        public User Author { get; set; }
        public DateTime CreationTime { get; set; }
    }
}