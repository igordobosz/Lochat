using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Filters;
using Microsoft.AspNetCore.Mvc;

namespace Lochat.Infrastructure.BaseClasses
{
    [ApiController]
    [Route("[controller]/[action]")]
    [ApiExceptionFilter]
    public abstract class ApiControllerBase : ControllerBase
    {
    }
}
