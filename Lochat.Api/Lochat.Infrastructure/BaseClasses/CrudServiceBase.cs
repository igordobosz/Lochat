using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection.Metadata.Ecma335;
using System.Threading.Tasks;
using AutoMapper;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;

namespace Lochat.Infrastructure.BaseClasses
{
	public abstract class CrudService<TEntity, TDto, TQueryModel> : ICrudService<TEntity, TDto, TQueryModel>
		where TEntity : class, IEntity where TDto : IDto where TQueryModel : class, IQueryModel
	{
		#region Constructors

		protected CrudService(IRepository<TEntity> baseRepository, IMapper mapper)
		{
			_baseRepository = baseRepository;
			_mapper = mapper;
		}

		#endregion
		#region Protected methods

		protected virtual IEnumerable<TEntity> ApplyQueryModel(IEnumerable<TEntity> items, TQueryModel model)
		{
			if (!string.IsNullOrEmpty(model.Id))
				return items.Where(e => e.Id.Equals(model.Id));
			return items;
		}

		#endregion
		protected readonly IRepository<TEntity> _baseRepository;
		protected readonly IMapper _mapper;

		#region ICrudService Members

		public virtual async Task<TDto> Create(TDto dto)
		{
			dto.Id = Guid.NewGuid().ToString();
			return MapToDto(await _baseRepository.Create(MapToEntity(dto)));
		}

		public virtual async Task<TDto> Update(TDto dto)
		{
			var oldEntity = _baseRepository.GetById(dto.Id);
			return MapToDto(await _baseRepository.Update(MapToEntity(dto, oldEntity)));
		}

		public virtual Task Delete(TDto dto)
		{
			return _baseRepository.Delete(MapToEntity(dto));
		}

		public virtual TDto GetById(string id)
		{
			return MapToDto(GetEntityById(id));
		}

		public virtual IEnumerable<TDto> Get(TQueryModel queryModel = null)
		{
			return MapToDtos(GetEntity(queryModel));
		}

		#endregion
		#region IIntCrudService Members

		public TEntity GetEntityById(string id)
		{
			return _baseRepository.GetById(id);
		}

		public IEnumerable<TEntity> GetEntity(TQueryModel queryModel)
		{
			return ApplyQueryModel(_baseRepository.Get(), queryModel);
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
			return _mapper.Map<IEnumerable<TDto>>(entities.ToList());
		}

		#endregion
	}
}