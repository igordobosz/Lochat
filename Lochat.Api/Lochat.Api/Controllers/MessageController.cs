using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.Services;

namespace Lochat.Api.Controllers
{
    public class MessageController : CrudApiControllerBase<Message, MessageDto, QueryModelBase>
    {
        public MessageController(IMessageService baseService) : base(baseService)
        {
        }
    }
}
