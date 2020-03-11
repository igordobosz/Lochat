using System;
using System.Collections.Generic;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;

namespace Lochat.Service.Profiles
{
    public class ChatroomProfiles : Profile
    {
        public ChatroomProfiles()
        {
            CreateMap<Chatroom, ChatroomDto>();
            CreateMap<ChatroomDto, Chatroom>();
        }
    }
}
