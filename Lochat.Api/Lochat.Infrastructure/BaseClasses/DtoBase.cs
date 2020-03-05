using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.BaseClasses
{
    public abstract class DtoBase : IDto
    {
        public abstract long Id { get; set; }
    }
}
