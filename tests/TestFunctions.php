<?php
use PHPUnit\Framework\TestCase;

require('/app/src/index.php');

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
        siteName();
    }
}