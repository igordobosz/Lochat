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
    public interface IUserService : ICrudService<User, UserDto>
    {

    }
    public class UserService : CrudService<User, UserDto>, IUserService
    {
        public UserService(IRepository<User> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {
        }
    }
}
