using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Extensions;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.QueryModels;

namespace Lochat.Service.Services
{
	public interface IMessageService : ICrudService<Message, MessageDto, MessageQueryModel>
    {

    }
    public class MessageService : CrudService<Message, MessageDto, MessageQueryModel>, IMessageService
    {
        public MessageService(IRepository<Message> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {
        }

        protected override Expression<Func<Message, bool>> ConvertQueryModelToFunc(MessageQueryModel model)
        {
	        var pred = base.ConvertQueryModelToFunc(model);
	        if (!string.IsNullOrEmpty(model.AuthorId))
	        {
		        Expression<Func<Message, bool>> expr = message => message.AuthorId == model.AuthorId;
		        pred = pred.And(expr);
	        }

	        return pred;
        }
    }
}
