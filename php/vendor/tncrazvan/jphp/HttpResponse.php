<?php
namespace com\github\tncrazvan\jphp;

class HttpResponse{
    public $status,$headers,$body;
    public function __construct(string $status,array $headers,string $body){
        $this->status = $status;
        $this->headers = $headers;
        $this->body = $body;
    }
}