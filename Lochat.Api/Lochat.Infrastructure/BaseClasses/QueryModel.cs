using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.BaseClasses
{
    public class QueryModel<TEntity> where TEntity : IEntity
    {
        public Func<TEntity, bool> Condition { get; set; }
    }
}
