using System;
using System.Collections.Generic;
using System.Text;
using Lochat.Infrastructure.Filters;
using Microsoft.AspNetCore.Mvc;

namespace Lochat.Infrastructure.BaseClasses
{
    [ApiController]
    [ApiExceptionFilter]
    public abstract class ApiControllerBase : ControllerBase
    {
    }
}
