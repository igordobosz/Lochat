using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Helpers;
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
		        Expression<Func<Chatroom, bool>> expr = chatroom => chatroom.OwnerId.Equals(model.OwnerId);
		        pred = Expression.Lambda<Func<Chatroom, bool>>(Expression.And(pred, expr));
	        }

	        return pred;
        }

        public override IEnumerable<ChatroomDto> Get(ChatroomQueryModel model = null)
        {
	        var items = base.Get(model);
	        if (model.UserLatitude != null && model.UserLongitude != null && model.MaxDistance != null)
	        {
		        items = items.Where(chatroom => DistanceHelper.HaversineDistance(model.UserLatitude.Value, model.UserLongitude.Value, chatroom.Latitude, chatroom.Longitude, model.MaxDistance.Value));
	        }

	        return items;
        }
    }

}
