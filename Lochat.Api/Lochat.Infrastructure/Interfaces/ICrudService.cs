using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Infrastructure.Interfaces
{
    public interface ICrudService<TEntity, TDto, TQueryModel> where TEntity : class, IEntity where TDto : IDto where TQueryModel : class, IQueryModel
    {
        Task<TDto> Create(TDto dto);
        Task<TDto> Update(TDto dto);
        Task Delete(TDto dto);
        TDto GetById(string id);
        IEnumerable<TDto> Get(TQueryModel queryModel);
    }
}
