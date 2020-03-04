<?php
namespace com\github\tncrazvan\jphp;

class StandardInputReader{
    public $headers;
    public $args;
    public $query;
    public $body;
    public function __construct(array $argv){
        $input = fopen('php://stdin','r');
        //$this->data = fread($input,intval($argv[1]));
        $overhead = stream_get_contents($input,intval($argv[1]));
        $overhead = json_decode($overhead,true);
        $this->headers = $overhead[0];
        $this->args = $overhead[1];
        $this->query = $overhead[2];
        $this->body = stream_get_contents($input,intval($argv[2]));
    }
}