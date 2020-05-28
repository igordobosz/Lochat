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

        protected override IEnumerable<Chatroom> ApplyQueryModel(IEnumerable<Chatroom> items, ChatroomQueryModel model)
        {
	        items =  base.ApplyQueryModel(items, model);
	        if (!string.IsNullOrEmpty(model.OwnerId))
	        {
		        items = items.Where(chatroom => chatroom.OwnerId.Equals(model.OwnerId));
	        }

	        if (model.UserLatitude != null && model.UserLongitude != null && model.MaxDistance != null)
	        {
		        items = items.Where(chatroom => DistanceHelper.HaversineDistance(model.UserLatitude.Value, model.UserLongitude.Value, chatroom.Latitude, chatroom.Longitude, model.MaxDistance.Value));
	        }

	        return items;
        }
    }

}
