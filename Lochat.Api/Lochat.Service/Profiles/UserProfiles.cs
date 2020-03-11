using System;
using System.Collections.Generic;
using System.Text;
using AutoMapper;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;

namespace Lochat.Service.Profiles
{
    public class UserProfiles : Profile
    {
        public UserProfiles()
        {
            CreateMap<User, UserDto>();
            CreateMap<UserDto, User>();
        }
    }
}
