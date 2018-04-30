@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

import java.util.concurrent.ThreadLocalRandom
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import static groovyx.net.http.Method.POST

def cli = new CliBuilder(usage: 'showdate.groovy -[hu]')

cli.with {
    h longOpt: 'help', 'Show usage information'
    u longOpt: 'url', args: 1, argName: 'url', 'URL to post click data to'
}

def options = cli.parse(args)
if (!options) return

if (options.h) {
    cli.usage()
    return
}

final String urlString = options.u
final NUM_USERS = 100

while(true) {
    int id = ThreadLocalRandom.current().nextInt(0, NUM_USERS)
    String action = ['page-view', 'click'][ThreadLocalRandom.current().nextInt(0, 2)]

    def data = [userId: id, action: action]

    def http = new HTTPBuilder(urlString)
    http.request(POST) {
        uri.path = urlString
        body = data
        requestContentType = ContentType.JSON
    }

    println "Posting json data: $data"
}
