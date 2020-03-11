using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.Services.Lochat.Service.Services;

namespace Lochat.Api.Controllers
{
    public class ChatroomController : CrudApiControllerBase<Chatroom, ChatroomDto>
    {
        public ChatroomController(IChatroomService baseService) : base(baseService)
        {
        }
    }
}
