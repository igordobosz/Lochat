using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Service.Dtos
{
    public class UserDto : DtoBase
    {
        public override string Id { get; set; }
        public string Username { get; set; }
        public string Email { get; set; }
    }
}
