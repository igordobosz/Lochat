using System;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.Models
{
    public class Message : EntityBase
    {
        public override string Id { get; set; }
        public string AuthorId { get; set; }
        public virtual User Author { get; set; }
        public string ChatroomId { get; set; }
        public virtual Chatroom Chatroom { get; set; }
        public string Text { get; set; }
        public DateTime CreationTime { get; set; }
    }
}