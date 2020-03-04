<?php
namespace com\github\tncrazvan\jphp;

use com\github\tncrazvan\jphp\HttpResponse;

interface JphpInterface{
    public function run(array $_HEADERS,array $_ARGS, array $_QUERY,string $_BODY):HttpResponse;
}