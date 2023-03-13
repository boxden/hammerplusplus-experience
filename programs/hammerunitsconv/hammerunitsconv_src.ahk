; This is the source code of the program.
; You will need AutoHotKey to test, debug and compile this script.
; Download it at http://www.autohotkey.com.

; You can modify and redistribute this file any way you want.
; Just make sure you give credit, where and when credit is due :)
; My name and a link to my site and I'd be forever grateful.
; -- Naoki Lambelet
; http://www.naoki.ch - me@naoki.ch


; GLOBAL SETTINGS

; Environment variables are not considered in this program
#NoEnv

; No default tray icon
#NoTrayIcon

; Only one instance is allowed to run
#SingleInstance force

; 2 decimal places for floats
SetFormat, FloatFast, 0.2

; Set the escape character
#EscapeChar \



; VARIABLES

; Program Name
ProgramName := "Hammer Unit Conversion Tool v1.0 by Naoki (me@naoki.ch)"


; Conversion stuff - Used for the grid-unit conversion.

; You can add as many measurement systems as you want
; Regarding MultiplierX_Y, UnitsX_Z, UnitsMultX_Z and ScaleY :
; - X is the measurement system index (here, 1 = metric and 2 = imperial)
; - Y is the scale type index (world, human or skybox)
; - Z is the unit used in a given measurement system (here, meter or centimeter / foot or inch)

; Units are multiplied by these numbers.
; Meters
Multiplier1_1 := 0.01905 ; World
Multiplier1_2 := 0.0254  ; Human
Multiplier1_3 := 0.3048  ; Skybox

; Feet / Inches
Multiplier2_1 := 0.0625  ; World
Multiplier2_2 := 1/12    ; Human
Multiplier2_3 := 1       ; Skybox


; Units as displayed in the status bar
; Metric
Units1_1 := "m"  ; Meters
Units1_2 := "cm" ; Centimeters

; Imperial
Units2_1 := "'"  ; Feet
Units2_2 := "''" ; Inches


; Those are used for conversion between units of the same measurement system
; The values defined in MultiplierX_Y above are those corresponding to the respective Base units below
UnitsMult1_1 := 1   ; Base unit (meter)
UnitsMult1_2 := 100 ; Converted unit (centimeter)

UnitsMult2_1 := 1   ; Base unit (foot)
UnitsMult2_2 := 12  ; Converted unit (inch)


; Scales
Scale1 := "World scale"
Scale2 := "Human scale"
Scale3 := "Skybox scale"


; Default values
MultiplierIndex := 1    ; X
MultiplierSubIndex := 1 ; Y
UnitsSubIndex := 1      ; Z

; Conversion stuff - END


; Wait time between each loop
SleepTime := 10

; The window class, used to identify the program (no need to change)
WindowClass := "ahk_class VALVEWORLDCRAFT"

; Workaround for #IfWinActive-related issue
GroupAdd, WindowClass, %WindowClass%



; MISC

; Manage command-line parameters
Loop, %0%
{
    StringSplit, Params, %A_Index%, % A_Space
    If(Params1 = "nowarning" or Params1 = "-nowarning")
        NoWarning := 1
    Else
        MsgBox, 48, %ProgramName%, Unknown parameter (%Params1%). Skipping.
}



; MAIN STUFF

; "Hammer not running" warning message
If(!NoWarning)
{
    IfWinNotExist, %WindowClass%
    {
        MsgBox, 36, %ProgramName%, % "Hammer needs to be running. Would you like to launch this program anyway ?\n\n(You can avoid this message by adding the -nowarning parameter)"
    }
    IfMsgBox No
        ExitApp
}

; Used as a temporary value holder
Old_SB_Text_4 := ""

; The default text of the left part of the status bar
SB_DefaultText := "For Help, press F1"


Loop
{
	; Get the content of the 4th status bar column, which is the size and the origin
	; of the currently selected object (e.g. "125w 80l 64h @(10 25 96)") and place it in SB_Text_4.
	; This is the data we'll actually use for converting to real-world units.
	StatusBarGetText, SB_Text_4, 4, %WindowClass%

	; Only go through the hassle of updating the status bar if needed,
	; i.e. if either SB_Text_4 has changed or a hotkey has been pressed.
	If((SB_Text_4 <> Old_SB_Text_4) or (HotkeyPressed = 1))
	{
		; No object is selected, write default text in status bar
		If(SB_Text_4 = "")
		{
			SB_Text := SB_DefaultText
		}
		; SB_Text_4 may contain info other than coordinates of the currently selected object,
		; and we're not interested in that. StrLen() was a way to get rid of that info.
		Else If(StrLen(SB_Text_4) > 17)
		{
			; We don't need the information about the object origin
			AtPos := InStr(SB_Text_4, " @")
			ObjectSize := SubStr(SB_Text_4, 2, AtPos-2)

			; Separate the size info into W/L/H and trim the last letter ("w"/"l"/"h")
			StringSplit, SizeArray, ObjectSize, % A_Space
			ObjectWidth  := SubStr(SizeArray1, 1, -1)
			ObjectLength := SubStr(SizeArray2, 1, -1)
			ObjectHeight := SubStr(SizeArray3, 1, -1)

			; Some calculations, depending on the measurement system
			SB_Mult := Multiplier%MultiplierIndex%_%MultiplierSubIndex%*UnitsMult%MultiplierIndex%_%UnitsSubIndex%
			SB_Units := Units%MultiplierIndex%_%UnitsSubIndex%

			; The text to show in the status bar
			SB_Text := "(" . Scale%MultiplierSubIndex% . ")" . " W: " . ObjectWidth*SB_Mult . SB_Units . " L: " . ObjectLength*SB_Mult . SB_Units . " H: " . ObjectHeight*SB_Mult . SB_Units
		}

		; Show the content of SB_Text in the status bar
		ControlSetText, msctls_statusbar321, %SB_Text%, %WindowClass%

		Old_SB_Text_4 := SB_Text_4

		HotkeyPressed := 0
	}
	; No update needed
	Else
	{
		; We need to allow other information to be displayed on the left part of the status bar.
		; For example, info that shows up when browsing through the top-menu.
		; Thus our info is only displayed when the default text ("For Help, press F1")
		; would otherwise be displayed.
		StatusBarGetText, SB_Text_1, 1, %WindowClass%
		If(SB_Text_1 = SB_DefaultText and SB_Text_4 <> "") ; Something is selected
			ControlSetText, msctls_statusbar321, %SB_Text%, %WindowClass%
	}

	; Quit the program if Hammer is closed
	If(SB_Mult) ; Only if the status bar has been updated at least once
	{
		IfWinNotExist, %WindowClass%
			ExitApp
	}

	; Get some rest
	Sleep, %SleepTime%
}


; KEYBOARD SHORTCUTS (HOTKEYS) MANAGEMENT

; This hotkey works from anywhere
; Ctrl+Shift+Q - Quit the program
^+q:: 
	; Before leaving, set the status bar back to the default text.
	ControlSetText, msctls_statusbar321, %SB_DefaultText%, %WindowClass%
	ExitApp
return

; These hotkeys only work when inside Hammer
#IfWinActive, ahk_group WindowClass
{
    ; Ctrl+1 - Switch between scale types
    ^1::
		MultiplierSubIndex++
		If(!Multiplier%MultiplierIndex%_%MultiplierSubIndex%)
			MultiplierSubIndex := 1

		HotkeyPressed := 1
    return

	; Ctrl+2 - Switch between units of the same measurement system
	^2::
		UnitsSubIndex++
		If(!Units%MultiplierIndex%_%UnitsSubIndex%)
			UnitsSubIndex := 1

		HotkeyPressed := 1
	return

	; Ctrl+3 - Switch between measurement systems
	^3::
		MultiplierIndex++
		If(!Multiplier%MultiplierIndex%_%MultiplierSubIndex%)
		{
			If(Multiplier%MultiplierIndex%_1)
				MultiplierSubIndex := 1
			Else
				MultiplierIndex := 1
		}

		HotkeyPressed := 1
	return
}
