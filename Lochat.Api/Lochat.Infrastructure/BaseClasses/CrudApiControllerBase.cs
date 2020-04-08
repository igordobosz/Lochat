using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Lochat.Infrastructure.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Lochat.Infrastructure.BaseClasses
{
    [Authorize]
    public abstract class CrudApiControllerBase<TEntity, TDto> : ApiControllerBase where TEntity : class, IEntity where TDto : IDto
    {
        protected readonly ICrudService<TEntity, TDto> _baseService;
        protected CrudApiControllerBase(ICrudService<TEntity, TDto> baseService)
        {
            _baseService = baseService;
        }

        [HttpPost]
        public async Task<TDto> Create(TDto dto)
        {
            return await _baseService.Create(dto);
        }

        [HttpPut]
        public async Task<TDto> Update(TDto dto)
        {
            return await _baseService.Update(dto);
        }

        [HttpDelete]
        public async Task Delete(TDto dto)
        {
            _baseService.Delete(dto);
        }

        [HttpGet]
        public TDto GetById(string id)
        {
            return _baseService.GetById(id);
        }
    }
}
