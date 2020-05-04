using System;
using System.ComponentModel.DataAnnotations;
using System.Threading.Tasks;
using Google.Apis.Auth;
using Google.Apis.Auth.OAuth2;
using Lochat.Api.Helpers;
using Lochat.Api.Options;
using Lochat.Infrastructure.BaseClasses;
using Lochat.Service.Dtos;
using Lochat.Service.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;

namespace Lochat.Api.Controllers
{
	public class AuthorizationController : ApiControllerBase
	{
		private readonly AuthenticationOptions _authenticationOptions;
		private readonly IUserService _userService;
		public AuthorizationController(IOptions<AuthenticationOptions> settings, IUserService userService)
		{
			_authenticationOptions = settings.Value;
			_userService = userService;
		}
		#region Public methods

		[HttpPost]
		[ProducesDefaultResponseType]
		public async Task<ActionResult<AuthenticateResponse>> Google([FromBody] GoogleAuthenticateRequest googleAuthenticateRequest)
		{
			try
			{
				var authResult = await GoogleJsonWebSignature.ValidateAsync(googleAuthenticateRequest.IdToken, new GoogleJsonWebSignature.ValidationSettings()
				{
					Audience = new[] {_authenticationOptions.ClientId}
				});
				var user = await _userService.InsertOrUpdate(new UserDto() {Email = authResult.Email, Username = authResult.Email});

				return Ok(
					new AuthenticateResponse()
						{AccessToken = JwtTokenHelper.GenerateToken(user.Id, user.Username, user.Email, AuthenticationMethod.Google)}
					);

			}
			catch (Exception e)
			{
				return BadRequest(e.Message);
			}
		}

		#endregion

		public class GoogleAuthenticateRequest
		{
			[Required]
			public string IdToken { get; set; }
		}

		public class AuthenticateResponse
		{
			public string AccessToken { get; set; }
		}
	}
}