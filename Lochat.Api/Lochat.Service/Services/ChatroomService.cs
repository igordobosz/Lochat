using System;
using System.Collections.Generic;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;

namespace Lochat.Service.Services
{
	public interface IChatroomService : ICrudService<Chatroom, ChatroomDto, QueryModelBase>
    {

    }
    public class ChatroomService : CrudService<Chatroom, ChatroomDto, QueryModelBase>, IChatroomService
    {
        public ChatroomService(IRepository<Chatroom> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {
        }
    }

}
