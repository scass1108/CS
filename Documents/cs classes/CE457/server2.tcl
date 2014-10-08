#!/usr/bin/tclsh
proc display sock {
	#set data [gets $sock]
	
	fconfigure $sock -blocking 0
	set everything [read $sock]
	regexp {(.*?)\n(.*?)\n\n(.*)$} $everything -> data HTTP POST
	set ::env(HTTP_REQUEST) $HTTP
	set ::env(POST_DATA) $POST
	
	set path public_html[lindex $data 1]
	
	regexp {(.*?)\?(.*)$} $path -> path ::env(QUERY_STRING)
	
	if [file isdirectory $path] {
		set path [file join $path index.html]
	}
	puts "Attempting to retrieve $path..."
	if ![file exists $path] {
		set path public_html/404.html
		
	}
	
	if [file executable $path] {
		set path |$path
	} else {
		puts $sock "HTTP/1.1 200 OK"
	puts "not executable"
		puts $sock "Content-Type: [mimetype $path]\n"
	}
	set inchan [open $path]	
	
	fconfigure $inchan -translation binary
	fconfigure $sock -translation binary
	fcopy $inchan $sock
	close $inchan; close $sock
}

array set type {
	.jpg image/jpeg .jpeg image/jpeg
	.png image/png .gif image/gif
	.html text/html .txt text/plain
	.css text/css
	.js application/javascript .pdf application/pdf
}
proc mimetype file {
	set e [string tolower [file extension $file]]
	if [info exists ::type($e)] { return $::type($e) }
	return application/octet-stream
}

proc answer {sock x y} {
	fileevent $sock readable [list display $sock]
}
socket -server answer 8080
vwait forever
