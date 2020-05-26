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
	public interface IMessageService : ICrudService<Message, MessageDto, QueryModelBase>
    {

    }
    public class MessageService : CrudService<Message, MessageDto, QueryModelBase>, IMessageService
    {
        public MessageService(IRepository<Message> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {
        }
    }
}
