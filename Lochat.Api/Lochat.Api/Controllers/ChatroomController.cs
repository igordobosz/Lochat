using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.QueryModels;
using Lochat.Service.Services;

namespace Lochat.Api.Controllers
{
    public class ChatroomController : CrudApiControllerBase<Chatroom, ChatroomDto, ChatroomQueryModel>
    {
        public ChatroomController(IChatroomService baseService) : base(baseService)
        {
        }

    }
}
