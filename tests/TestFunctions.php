<?php
use PHPUnit\Framework\TestCase;

require(dirname(__FILE__).'/src/index.php');

class TestFunctions extends TestCase
{
    public function testName(): void
    {
        $this->expectOutputString('Simple PHP Website');
        siteName();
    }

    public function testVersion(): void
    {
        $this->expectOutputString('v2.0');
        siteVersion();
    }
}