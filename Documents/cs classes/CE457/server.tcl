#!/usr/bin/tclsh
proc display sock {

	set data [gets $sock]
	set path public_html[lindex $data 1]
	
	if [file isdirectory $path] {

		set path [file join $path index.html]
	}
	puts "Attempting to retrieve $path..."
	if ![file exists $path] {

		set path public_html/404.html
	}

	if[$path != public_html/small.html]
	{
		set path public_html/404.html
	}

	puts $sock "HTTP/1.1 200 OK"
	puts $sock "Content-Type: text/html\n"
	puts $sock [exec cat $path]
	close $sock
}
proc answer {sock x y} {

fileevent $sock readable [list display $sock]
}
socket -server answer 8080
vwait forever
