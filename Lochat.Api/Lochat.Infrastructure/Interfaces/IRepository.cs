using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Infrastructure.Interfaces
{
    public interface IRepository<TEntity> where TEntity : IEntity
    {
        Task<TEntity> Create(TEntity dto);
        Task<TEntity> Update(TEntity dto);
        Task Delete(TEntity dto);
        IEnumerable<TEntity> Get(Expression<Func<TEntity, bool>> queryFunc = null);
        TEntity GetById(string id);
    }
}
