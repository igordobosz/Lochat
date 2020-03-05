using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Infrastructure.Interfaces
{
    public interface ICrudService<TEntity, TDto> where TEntity : class, IEntity where TDto : IDto
    {
        Task<TDto> Create(TDto dto);
        Task<TDto> Update(TDto dto);
        void Delete(TDto dto);
        TDto GetById(long id);
        IEnumerable<TDto> Get(QueryModel<TEntity> queryModel = null);
    }
}
