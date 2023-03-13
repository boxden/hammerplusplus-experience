HAMMER UNITS CONVERSION TOOL V1.0 FOR ALL SOURCE-BASED GAMES (30/07/2009)



Information
###########

This handy little piece of software seamlessly integrates into Hammer and allows
on-the-fly conversion of Source Engine units into metric or imperial units.
It turns Hammer's rarely-used left-side of the status bar into a non-obtrusive
real-time converter, showing the dimensions of an object in chosen units
as you work on it, be it creating or resizing it in one of the 2D views.
It proves incredibly useful when creating life-sized environments.

The conversion ratios come from data available on this page of the VDC
(Valve Developer Community) wiki: http://developer.valvesoftware.com/wiki/Dimensions.
As is explained, architecture (as well as props), human character models
and 3D Skyboxes all work on different scales. Using this tool, you can easily
switch between world-scale, human-scale and skybox-scale all within Hammer.



Installation
############

Simply extract the contents of the zip file to a folder on your computer
and run hammerunitsconv.exe while Hammer is running. Then you can for example
create a brush or select an existing one to see the converter in action.



Keyboard shortcuts
##################

These hotkeys only work when inside Hammer :

  CTRL+1 : Toggle between world-scale, human-scale and skybox-scale units.

  CTRL+2 : Toggle between units of the same measurement system
  (i.e. between meters and centimeters if using the metric system
  and between feet and inches if using the imperial system).

  CTRL+3 : Toggle between the metric system and the imperial system.


This hotkey works from anywhere :

  CTRL+Shift+Q : Exit the program (this doesn't affect Hammer).
  Note that the program automatically exits when Hammer is closed.



Optional command-line parameters
################################

If you wish to make a change to any of the parameters listed below, create
a shortcut to the application, right-click said shortcut and select Properties.
There, under the Shortcut tab, add one or more of the following options
at the end of the Target textbox, so that it looks for example like this :
"C:\Program Files\HammerUnitsConversionTool\hammerunitsconv.exe" "nowarning"

  "nowarning" : skips the warning that shows up when you try to run the program
  while Hammer is not running.



More info and interesting stuff
###############################

I wrote this program using AutoHotKey (http://www.autohotkey.com), a script language
based on AutoIt. The source (.ahk file) is included in the zip file. You can modify,
compile and redistribute the code any way you want, without even necessarily having
to share your modified source if you don't want to (although that wouldn't hurt
anyone). Just make sure you give credit :)

This program works without known issues and is able to handle any number
of instances of Hammer running concurrently.

Please note that a desktop resolution of 1280x1024 or higher is required. Lower
resolutions unfortunately can't display the conversion information in its entirety.

No responsibility taken if anything goes wrong regarding anything anywhere anytime.

Program created by Naoki Lambelet - http://www.naoki.ch - me@naoki.ch
