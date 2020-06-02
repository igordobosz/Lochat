using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Models;

namespace Lochat.Service.Dtos
{
    public class MessageDto : DtoBase
    {
        public override string Id { get; set; }
        public string AuthorId { get; set; }
        public string ChatroomId { get; set; }
        public string Text { get; set; }
        public DateTime CreationTime { get; set; }
        public string AuthorName { get; set; }
    }
}
