using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Extensions;

namespace Lochat.Infrastructure.Helpers
{
	public class DistanceHelper
	{
		public static bool HaversineDistance(double pos1Lat, double pos1Long, double pos2Lat, double pos2Long, double maxDistance)
		{
			double R = 6371;
			var lat = (pos2Lat - pos1Lat).ToRadians();
			var lng = (pos2Long - pos1Long).ToRadians();
			var h1 = Math.Sin(lat / 2) * Math.Sin(lat / 2) +
			         Math.Cos(pos1Lat.ToRadians()) * Math.Cos(pos2Lat.ToRadians()) *
			         Math.Sin(lng / 2) * Math.Sin(lng / 2);
			var h2 = 2 * Math.Asin(Math.Min(1, Math.Sqrt(h1)));
			return R * h2 < maxDistance;
		}
    }
}
