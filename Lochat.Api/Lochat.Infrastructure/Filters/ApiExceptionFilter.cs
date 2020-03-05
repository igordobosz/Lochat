using System;
using System.Net;
using Lochat.Infrastructure.Exceptions;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace Lochat.Infrastructure.Filters
{
    public class ApiExceptionFilter : ExceptionFilterAttribute
    {
        public override void OnException(ExceptionContext context)
        {
            if (context.Exception is NotFoundException)
            {
                var ex = context.Exception as NotFoundException;

                context.Result = new JsonResult(new ExceptionResponse(){Code = "NotFoundException", Message = ex.Message});
                context.Exception = null;
                context.HttpContext.Response.StatusCode = (int)HttpStatusCode.NotFound;
            }
            else if (context.Exception is BadRequestException)
            {
                var ex = context.Exception as BadRequestException;

                context.Result = new JsonResult(new ExceptionResponse() { Code = "BadRequestException", Message = ex.Message });
                context.Exception = null;
                context.HttpContext.Response.StatusCode = (int)HttpStatusCode.BadRequest;
            }
            else if (context.Exception is NotFoundException)
            {
                var ex = context.Exception as NotFoundException;

                context.Result = new JsonResult(new ExceptionResponse() { Code = "NotFoundException", Message = ex.Message });
                context.Exception = null;
                context.HttpContext.Response.StatusCode = (int) HttpStatusCode.NotFound;
            }


            base.OnException(context);
        }

        public class ExceptionResponse
        {
            public string Code { get; set; }
            public string Message { get; set; }
        }
    }
}