using System;
using System.Collections.Generic;
using System.Linq.Expressions;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.QueryModels;

namespace Lochat.Service.Services
{
	public interface IChatroomService : ICrudService<Chatroom, ChatroomDto, ChatroomQueryModel>
    {

    }
    public class ChatroomService : CrudService<Chatroom, ChatroomDto, ChatroomQueryModel>, IChatroomService
    {
        public ChatroomService(IRepository<Chatroom> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {
        }

        protected override Expression<Func<Chatroom, bool>> ConvertQueryModelToFunc(ChatroomQueryModel model)
        {
	        var pred = base.ConvertQueryModelToFunc(model);
	        if (!string.IsNullOrEmpty(model.OwnerId))
	        {
		        Expression<Func<Chatroom, bool>> expr = user => user.OwnerId.Equals(model.OwnerId);
		        pred = Expression.Lambda<Func<Chatroom, bool>>(Expression.And(pred, expr));
	        }

//	        if (model.UserLatitude != null && model.UserLongitude != null)
//	        {
//
//	        }

	        return pred;
        }
    }

}
