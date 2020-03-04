<?php
namespace com\github\tncrazvan\jphp;

class Jphp{
    public function __construct(array $argv,JphpInterface $callback){
        $reader = new StandardInputReader($argv);

        $_HEADERS = &$reader->headers;
        $_ARGS = &$reader->args;
        $_QUERY = &$reader->query;
        $_BODY = &$reader->body;

        $response = $callback->run($_HEADERS,$_ARGS,$_QUERY,$_BODY);

        echo $response->status."\n";
        foreach($response->headers as &$header){
            echo $header."\n";
        }
        echo "\n";
        echo $response->body;
    }
}

