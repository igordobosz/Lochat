using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.QueryModels;
using Lochat.Service.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace Lochat.Api.Controllers
{
    public class UserController : CrudApiControllerBase<User, UserDto, UserQueryModel>
    {
	    private new readonly IUserService _baseService;
        public UserController(IUserService baseService) : base(baseService)
        {
	        _baseService = baseService;
        }

        [HttpGet]
        public UserDto GetUser()
        {
	        return _baseService.Get(new UserQueryModel()
	        {
                Email = User.Claims.FirstOrDefault(e => e.Type.Equals(ClaimTypes.Email))?.Value
	        }).FirstOrDefault();
        }
    }
}
