using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AutoMapper;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Infrastructure.Interfaces;
using Lochat.Infrastructure.Models;
using Lochat.Service.Dtos;

namespace Lochat.Service.Services
{
    public interface IUserService : ICrudService<User, UserDto>
    {
	    UserDto GetByEmail(string email);
	    Task<UserDto> Register(string email);
    }
    public class UserService : CrudService<User, UserDto>, IUserService
    {
	    public UserService(IRepository<User> baseRepository, IMapper mapper) : base(baseRepository, mapper)
        {

        }

        public UserDto GetByEmail(string email)
        {
	        return _baseRepository.Get(new QueryModel<User>() { Condition = user => user.Email.Equals(email) }).Select(MapToDto).FirstOrDefault();
        }

        public async Task<UserDto> Register(string email)
        {
	        var user = GetByEmail(email);

            if (user == default(UserDto))
            {
	            return await Create(new UserDto() { Email = email, Username = email });
	        }

	        return user;
        }
    }
}
