using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection.Metadata.Ecma335;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;
using Lochat.Service.QueryModels;

namespace Lochat.Service.Services
{
    public interface IUserService : ICrudService<User, UserDto, UserQueryModel>
    {
	    Task<UserDto> Register(string email);
    }
    public class UserService : CrudService<User, UserDto, UserQueryModel>, IUserService
    {
	    public UserService(IRepository<User> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {

        }

	    public async Task<UserDto> Register(string email)
        {
	        var user = Get(new UserQueryModel()
	        {
                Email =  email
	        }).FirstOrDefault();

            if (user == default)
            {
	            return await Create(new UserDto() { Email = email, Username = email });
	        }

	        return user;
        }

	    protected override IEnumerable<User> ApplyQueryModel(IEnumerable<User> items, UserQueryModel model)
	    {
		    items = base.ApplyQueryModel(items, model);
		    if (!string.IsNullOrEmpty(model.Email))
		    {
			    items = items.Where(user => user.Email.Equals(model.Email));
		    }

		    return items;
	    }
    }
}
