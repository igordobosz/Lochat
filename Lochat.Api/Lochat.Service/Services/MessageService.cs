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
    namespace Lochat.Service.Services
    {
        public interface IMessageService : ICrudService<Message, MessageDto>
        {

        }
        public class MessageService : CrudService<Message, MessageDto>, IMessageService
        {
            public MessageService(IRepository<Message> baseRepository, IMapper mapper) : base(baseRepository, mapper)
            {
            }
        }
    }

}
