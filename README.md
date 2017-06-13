# log-search
## What it is
**log-search** is a tiny command line java application that helps you to to search through log/text files with the pattern:  
level=info response_body="" request_to="\<url\>" response_headers= response_status="\<code\>"
## How to use
Just go to terminal and execute "java -jar \<log-search-jar-name.jar\> \<logFilePath\>"  
**\<log-search-jar-name.jar\>** -> name of log-search jar. ex: log-search-0.0.1-SNAPSHOT.jar  
**\<logFilePath\>** -> full path to the log file. ex: C:\myFolder\log.txt
## Output
At the end of execution, it will print the following results to console:
 - Most 3 called urls with quantity
 - Every response status with quantity
## Structure
**br.com.moip.service.FileService** - service responsable for read and parse the log file.  
**br.com.moip.service.MathService** - service responsable for calculate requests and status based on FileService parsed file.  
**br.com.moip.service.PrinterService** - service responsable for print required outputs.  
**br.com.moip.model.FileResult** - class to hold file parsed information.
## Performance statistics
It was executed in a dell laptop **core i7 5500U 2.40GHz** with **16GB RAM** running **windows 7**.  
When runned against a **450Mb** logfile, it took about **7,5 seconds** to came up with results below:  
https://eagerhaystack.com - 72000  
https://surrealostrich.com.br - 70464  
https://grimpottery.net.br - 70272  
200 - 136032  
201 - 134592  
204 - 133248  
400 - 138240  
404 - 141504  
500 - 137088  
503 - 139296  

URL COUNT: 960000  
STATUS COUNT: 960000  
START: 1497335151259  
END: 1497335158916  
ELAPSED: 7657

When runned against a **900Mb** logfile, it took about **14 seconds** to came up with results below:
https://eagerhaystack.com - 143998
https://surrealostrich.com.br - 140928
https://grimpottery.net.br - 140542
200 - 272061
201 - 269174
204 - 266491
400 - 276475
404 - 283005
500 - 274175
503 - 278584
URL COUNT: 1919965
STATUS COUNT: 1919965
START: 1497348363156
END: 1497348377332
ELAPSED: 14176