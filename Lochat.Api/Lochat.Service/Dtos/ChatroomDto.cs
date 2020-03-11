using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Service.Dtos
{
    public class ChatroomDto : DtoBase
    {
        public override string Id { get; set; }
        public string Name { get; set; }
        public DateTime CreationTime { get; set; }
        public DateTime TerminationTime { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public bool IsDynamic { get; set; }
    }
}
