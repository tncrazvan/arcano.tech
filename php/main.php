<?php
require_once 'vendor/autoload.php';
use com\github\tncrazvan\jphp\Jphp;
use com\github\tncrazvan\jphp\Status;
use com\github\tncrazvan\jphp\HttpResponse;
use com\github\tncrazvan\jphp\JphpInterface;
new Jphp($argv,new class() implements JphpInterface{
    public function run($_HEADERS,$_ARGS,$_QUERY,$_BODY):HttpResponse{
        file_put_contents($_QUERY["filename"],$_BODY);
        return new HttpResponse(Status::SUCCESS,[],"welcome");
    }
});