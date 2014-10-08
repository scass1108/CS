#!/usr/bin/tclsh

array set POST {who unspecified age "a mystery"}
array set POST [split $env(POST_DATA) &=]

puts "HTTP/1.1 200 OK\nContent-Type: text/html\n"
puts "<html><head><title>My Form</title></head>
<body><FORM METHOD=POST ACTION=form.tcl>
Your name: <input name=who value = $POST(who)><br>
Your age: <input name=age value = $POST(age)><br>
<input type=submit>

<hr> Your name is $POST(who) and your age is $POST(age)

</FORM></body></html>"
