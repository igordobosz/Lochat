using System.Collections.Generic;

namespace Lochat.Api.Models
{
    public class User
    {
        public string ID { get; set; } // Phone ID might be used as a key, if not - add another property for it
        public string Username { get; set; }
        public ICollection<User> Blacklist { get; set; }
    }
}