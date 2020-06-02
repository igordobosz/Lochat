using System;
using System.Collections.Generic;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;

namespace Lochat.Service.Profiles
{
    public class MessageProfiles : Profile
    {
        public MessageProfiles()
        {
	        CreateMap<Message, MessageDto>()
                .ForMember(dest => dest.AuthorName, opt => opt.MapFrom(src => src.Author.Username));
            CreateMap<MessageDto, Message>();
        }
    }
}
