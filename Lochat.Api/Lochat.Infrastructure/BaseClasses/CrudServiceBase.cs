using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Lochat.Infrastructure.Interfaces;

namespace Lochat.Infrastructure.BaseClasses
{
    public abstract class CrudService<TEntity, TDto> : ICrudService<TEntity, TDto>
        where TEntity : class, IEntity where TDto : IDto
    {
        protected readonly IRepository<TEntity> _baseRepository;
        protected readonly IMapper _mapper;

        protected CrudService(IRepository<TEntity> baseRepository, IMapper mapper)
        {
            _baseRepository = baseRepository;
            _mapper = mapper;
        }

        #region ICrudService Members

        public virtual async Task<TDto> Create(TDto dto)
        {
            return MapToDto(await _baseRepository.Create(MapToEntity(dto)));
        }

        public virtual async Task<TDto> Update(TDto dto)
        {
            var oldEntity = _baseRepository.GetById(dto.Id);
            return MapToDto(await _baseRepository.Update(MapToEntity(dto, oldEntity)));
        }

        public virtual async void Delete(TDto dto)
        {
            await _baseRepository.Delete(MapToEntity(dto));
        }

        public virtual TDto GetById(string id)
        {
            return MapToDto(GetEntityById(id));
        }

        public virtual IEnumerable<TDto> Get(QueryModel<TEntity> queryModel = null)
        {
            return MapToDtos(GetEntity(queryModel));
        }

        #endregion
        #region IIntCrudService Members
        public TEntity GetEntityById(string id)
        {
            return _baseRepository.GetById(id);
        }

        public IEnumerable<TEntity> GetEntity(QueryModel<TEntity> queryModel)
        {
            return _baseRepository.Get((queryModel));
        }
        #endregion
        #region Mappers
        protected TEntity MapToEntity(TDto dto, TEntity oldItem = null)
        {
            if (oldItem == null)
            {
                return _mapper.Map<TEntity>(dto);
            }
            return _mapper.Map(dto, oldItem);
        }

        protected TDto MapToDto(TEntity entity)
        {
            return _mapper.Map<TDto>(entity);
        }

        protected IEnumerable<TEntity> MapToEntities(IEnumerable<TDto> dtos)
        {
            return _mapper.Map<IEnumerable<TEntity>>(dtos);
        }

        protected IEnumerable<TDto> MapToDtos(IEnumerable<TEntity> entities)
        {
            return _mapper.Map<IEnumerable<TDto>>(entities);
        }
        #endregion
    }
}
