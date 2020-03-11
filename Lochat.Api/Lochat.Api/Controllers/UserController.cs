using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace Lochat.Api.Controllers
{
    public class UserController : CrudApiControllerBase<User, UserDto>
    {
        public UserController(IUserService baseService) : base(baseService)
        {
        }
    }
}
