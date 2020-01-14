# JFC
JFC converter allows to convert files from one format to another one. 
###Getting Started
At the beginning it is necessary download the project from [GitHub repository](https://github.com/Enrique-C/JFC.git)

####Prerequisites
Windows Environment: Server 2019 Standard.

* Intellij idea 2019.3 (Community Edition)
* Git version  2.24.0.windows.2
* java version 1.8.0_171
* [xampp](https://www.apachefriends.org/xampp-files/7.4.1/xampp-windows-x64-7.4.1-0-VC15-installer.exe) version 7.4.1
* gradle version 5.2.1
* [ImageMagick](https://imagemagick.org/download/binaries/ImageMagick-7.0.9-16-Q16-x64-dll.exe) version 7.0.9-9 Q16 x64 2019-12-17
* [ffmpeg](https://ffmpeg.org/releases/ffmpeg-4.2.2.tar.bz2) version git-2019-12-10-e73688e
* [GhostScript](https://github.com/ArtifexSoftware/ghostpdl-downloads/releases/download/gs950/gs950w64.exe) version 9.50
* [LibreOffice](https://www.libreoffice.org/download/portable-versions/) version 6.3.3 Portable MultilingualStandard
* [Exiftool](https://exiftool.org/exiftool-11.81.zip) version 11.81
* springframework.boot version '1.5.7.RELEASE'
* springfox-swagger2:2.8.0
* json web token:jjwt:0.2

#### Installing

For starting to setup the environment.
* Turn off firewall.
* Install Java.
* Install Intellij idea.
* Install Git.
* Install Xampp.
* Install GhostScript.
* Install LibreOffice in thirdparty project directory.

### Post Installing

* Setup MySql database management.
    * Execute xampp-control.exe as Admin user.
    * Start MySql and Apache.
    * Access to phpMyAdmin using a browser(chrome, firefox).
    * Create a new database called **jfc**.

###Deployment
Add gradle dependencies in the project using **build.gradle**

###Versioning
We use **git** for versioning. For the versions available, see the repository.

###Authors
Paolo Sandoval - Trainer

AT-11 Students.

###License
This project is licensed under the Fundacion-jala License - see [Fundacion-jala](http://fundacion-jala.org)