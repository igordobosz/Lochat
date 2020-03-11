using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Service.Dtos
{
    public class UserDto : DtoBase
    {
        public override string Id { get; set; } // Phone ID might be used as a key, if not - add another property for it
        public string Username { get; set; }
    }
}
