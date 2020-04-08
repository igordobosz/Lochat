using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.IdentityModel.Tokens;

namespace Lochat.Api.Helpers
{
	public enum AuthenticationMethod
	{
		Google,
	}
	public static class JwtTokenHelper
	{
		public static string SECRET = "S,s#|(W`eZ0wx$a-kUk:fADi.qTEtZu_]O8pJ2U-M,|]|$$9S(LH^[DY_<ce(76l";
		public static string GenerateToken(string userId, string username, string email, AuthenticationMethod authenticationMethod)
		{
			var tokenHandler = new JwtSecurityTokenHandler();
			var key = Encoding.ASCII.GetBytes(SECRET);
			var tokenDescriptor = new SecurityTokenDescriptor
			{
				Subject = new ClaimsIdentity(new Claim[]
				{
					new Claim(ClaimTypes.NameIdentifier, userId),
					new Claim(ClaimTypes.Email, email),
					new Claim(ClaimTypes.Name, username),
					new Claim(ClaimTypes.AuthenticationMethod, authenticationMethod.ToString()), 
				}),
				Expires = DateTime.UtcNow.AddDays(7),
				SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
			};
			return tokenHandler.WriteToken(tokenHandler.CreateToken(tokenDescriptor));
		}
	}
}
