using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.BaseClasses
{
    public abstract class EntityBase : IEntity
    {
        public abstract string Id { get; set; }
    }
}
