using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.BaseClasses;

namespace Lochat.Service.QueryModels
{
	public class ChatroomQueryModel : QueryModelBase
	{
		public string OwnerId { get; set; }
		public double? UserLatitude { get; set; }
		public double? UserLongitude { get; set; }
	}
}
