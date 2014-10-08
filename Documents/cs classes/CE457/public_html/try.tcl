#!/usr/bin/tclsh

puts "HTTP/1.1 200 OK\nContent-Type: text/html\n"

puts "<html><head><title>What time is it?</title></head>"
puts "<body>"
puts "<h3>The time is [clock format [clock seconds]]</h3>"

puts "<table border=1>"
foreach a [array names env] {
	puts "<tr><td> $a </td><td> $env($a) </td></tr>"
}
puts "</table>"

puts "</body></html>"
