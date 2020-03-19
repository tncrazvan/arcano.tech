<?php
chdir(dirname(__FILE__));
require_once 'vendor/autoload.php';
use com\github\tncrazvan\jphp\Jphp;
use com\github\tncrazvan\jphp\Status;
use com\github\tncrazvan\jphp\HttpResponse;
use com\github\tncrazvan\jphp\JphpInterface;

new Jphp($argv,new class implements JphpInterface {
    public function run(array $_HEADERS,array $_ARGS, array $_QUERY,string $_BODY):HttpResponse{
        return new HttpResponse(Status::SUCCESS,[],"hello world");
    }
});