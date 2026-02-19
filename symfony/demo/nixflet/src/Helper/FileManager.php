<?php

namespace App\Helper;

use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;

class FileManager
{

    public function __construct(private readonly SluggerInterface $slugger) {}

    public function upload(UploadedFile $file, string  $dir, string $baseName, ?string $oldFileToDelete = null): string
    {
        $newFileName =  sprintf('%s-%s.%s', mb_strtolower($this->slugger->slug($baseName)), uniqid(), $file->guessExtension());
        $file->move($dir, $newFileName);

        if($oldFileToDelete && file_exists($dir.'/'.$oldFileToDelete)) {
            unlink($dir.'/'.$oldFileToDelete);
        }

        return $newFileName;
    }
}
