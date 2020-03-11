using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lochat.Infrastructure.Exceptions;
using Lochat.Infrastructure.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Lochat.Infrastructure.BaseClasses
{
    public class RepositoryBase<TEntity> : IRepository<TEntity> where TEntity : EntityBase
    {
        private readonly AppDbContext _dbContext;

        public RepositoryBase(AppDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public virtual async Task<TEntity> Create(TEntity entity)
        {
            _dbContext.Add(entity);
            await _dbContext.SaveChangesAsync();
            return GetById(entity.Id);
        }

        public virtual async Task<TEntity> Update(TEntity entity)
        {

            var result = GetQuery().Update(entity);
            await _dbContext.SaveChangesAsync();

            return GetById(entity.Id);
        }


        public virtual async Task Delete(TEntity entity)
        {
            var oldEntity = GetQuery().FirstOrDefault(e => e.Id == entity.Id);
            if (oldEntity == null)
            {
                throw new NotFoundException("Entity not found");
            }

            GetQuery().Remove(oldEntity);
            await _dbContext.SaveChangesAsync();
        }

        public virtual IEnumerable<TEntity> Get(QueryModel<TEntity> queryModel = null)
        {
            var query = GetQuery().ToList();
            if (queryModel != null)
            {
                query = query.Where(queryModel.Condition).ToList();
            }

            return query;
        }

        public virtual TEntity GetById(string id)
        {
            var entity = GetQuery().FirstOrDefault(e => e.Id.Equals(id));
            if (entity == null)
            {
                throw new NotFoundException("Entity not found.");
            }

            return entity;
        }

        protected virtual DbSet<TEntity> GetQuery()
        {
            return _dbContext.Set<TEntity>();
        }
    }
}
