using System;
using System.Collections.Generic;
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
        IEnumerable<TEntity> Get(QueryModel<TEntity> queryModel = null);
        TEntity GetById(long id);
    }
}
