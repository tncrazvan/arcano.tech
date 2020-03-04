<?php
namespace com\github\tncrazvan\jphp;

class Status{
    //STATUS STRINGS
    const 
        //INFORMATINOAL RESPONSES
        CONTINUE = "HTTP/1.1 100 Continue",
        SWITCHING_PROTOCOLS = "HTTP/1.1 101 Switching Protocols",
        PROCESSING = "HTTP/1.1 102 Processing",

        //SUCCESS
        SUCCESS = "HTTP/1.1 200 OK",
        CREATED = "HTTP/1.1 201 CREATED",
        ACCEPTED = "HTTP/1.1 202 ACCEPTED",
        NON_AUTHORITATIVE_INFORMATION = "HTTP/1.1 203 Non-Authoritative Information",
        NO_CONTENT = "HTTP/1.1 204 No Content",
        RESET_CONTENT = "HTTP/1.1 205 Reset Content",
        PARTIAL_CONTENT = "HTTP/1.1 206 Partial Content",
        MULTI_STATUS = "HTTP/1.1 207 Multi-Status",
        ALREADY_REPORTED = "HTTP/1.1 208 Already Reported",
        IM_USED = "HTTP/1.1 226 IM Used",

        //REDIRECTIONS
        MULTIPLE_CHOICES = "HTTP/1.1 300 Multiple Choices",
        MOVED_PERMANENTLY = "HTTP/1.1 301 Moved Permanently",
        FOUND = "HTTP/1.1 302 Found",
        SEE_OTHER = "HTTP/1.1 303 See Other",
        NOT_MODIFIED = "HTTP/1.1 304 Not Modified",
        USE_PROXY = "HTTP/1.1 305 Use Proxy",
        SWITCH_PROXY = "HTTP/1.1 306 Switch Proxy",
        TEMPORARY_REDIRECT = "HTTP/1.1 307 Temporary Redirect",
        PERMANENT_REDIRECT = "HTTP/1.1 308 Permanent Redirect",

        //CLIENT ERRORS
        BAD_REQUEST = "HTTP/1.1 400 Bad Request",
        UNAUTHORIZED = "HTTP/1.1 401 Unauthorized",
        PAYMENT_REQUIRED = "HTTP/1.1 402 Payment Required",
        FORBIDDEN = "HTTP/1.1 403 Forbidden",
        NOT_FOUND = "HTTP/1.1 404 Not Found",
        METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method Not Allowed",
        NOT_ACCEPTABLE = "HTTP/1.1 406 Not Acceptable",
        PROXY_AUTHENTICATION_REQUIRED = "HTTP/1.1 407 Proxy Authentication Required",
        REQUEST_TIMEOUT = "HTTP/1.1 408 Request Timeout",
        CONFLICT = "HTTP/1.1 409 Conflict",
        GONE = "HTTP/1.1 410 Gone",
        LENGTH_REQUIRED = "HTTP/1.1 411 Length Required",
        PRECONDITION_FAILED = "HTTP/1.1 412 Precondition Failed",
        PAYLOAD_TOO_LARGE = "HTTP/1.1 413 Payload Too Large",
        URI_TOO_LONG = "HTTP/1.1 414 URI Too Long",
        UNSUPPORTED_MEDIA_TYPE = "HTTP/1.1 415 Unsupported Media Type",
        RANGE_NOT_SATISFIABLE = "HTTP/1.1 416 Range Not Satisfiable",
        EXPECTATION_FAILED = "HTTP/1.1 417 Expectation Failed",
        IM_A_TEAPOT = "HTTP/1.1 418 I'm a teapot",
        MISDIRECTED_REQUEST = "HTTP/1.1 421 Misdirected Request",
        UNPROCESSABLE_ENTITY = "HTTP/1.1 422 Unprocessable Entity",
        LOCKED = "HTTP/1.1 423 Locked",
        FAILED_DEPENDENCY = "HTTP/1.1 426 Failed Dependency",
        UPGRADE_REQUIRED = "HTTP/1.1 428 Upgrade Required",
        PRECONDITION_REQUIRED = "HTTP/1.1 429 Precondition Required",
        TOO_MANY_REQUESTS = "HTTP/1.1 429 Too Many Requests",
        REQUEST_HEADER_FIELDS_TOO_LARGE = "HTTP/1.1 431 Request Header Fields Too Large",
        UNAVAILABLE_FOR_LEGAL_REASONS = "HTTP/1.1 451 Unavailable For Legal Reasons",

        //SERVER ERRORS
        INTERNAL_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error",
        NOT_IMPLEMENTED = "HTTP/1.1 501 Not Implemented",
        BAD_GATEWAY = "HTTP/1.1 502 Bad Gateway",
        SERVICE_UNAVAILABLE = "HTTP/1.1 503 Service Unavailable",
        GATEWAY_TIMEOUT = "HTTP/1.1 504 Gateway Timeout",
        HTTP_VERSION_NOT_SUPPORTED = "HTTP/1.1 505 HTTP Version Not Supported",
        VARIANT_ALSO_NEGOTATIES = "HTTP/1.1 506 Variant Also Negotiates",
        INSUFFICIENT_STORAGE = "HTTP/1.1 507 Insufficient Storage",
        LOOP_DETECTED = "HTTP/1.1 508 Loop Detected",
        NOT_EXTENDED = "HTTP/1.1 510 Not Extended",
        NETWORK_AUTHENTICATION_REQUIRED = "HTTP/1.1 511 Network Authentication Required";
}