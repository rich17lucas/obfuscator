
//import org.apache.logging.log4j.Level
//import org.apache.logging.log4j.LogManager
//import groovy.util.logging.Slf4j
import groovy.util.logging.Log4j2
import org.apache.logging.log4j.Level

//import org.slf4j.*
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import groovy.util.logging.Slf4j.Slf4jLoggingStrategy

@Log4j2
class TagAnonymiser {

    def anoynymise(){
        //log.debugEnabled = true
        def txt_msgs = []
        def key_map = [:]
        def value_map = [:]

        //def log = LogManager.getLogger("")
        //Logger log = LoggerFactory.getLogger("")
        log.level = Level.INFO

        log.info "Hello"

        txt_msgs << [ "name": "Richard", "account": "abc123" ]
        txt_msgs << [ "name": "Imad", "account": "efg456" ]
        txt_msgs << [ "name": "Sue", "account": "aab112" ]

        def test_msgs = txt_msgs

        txt_msgs.each {
            msg ->
            def random = new Random()
            log.debug "${msg.name} ${msg.account}"

            def old_name = msg.name
            def old_account = msg.account

            key_map[(msg.name)] = random.nextInt().toString()
            key_map[(msg.account)] = random.nextInt().toString()

            msg.name = key_map[(msg.name)]
            msg.account = key_map[(msg.account)]

            value_map[(msg.name)] = old_name
            value_map[(msg.account)] = old_account
        }


        log.info txt_msgs.toString ( )
        log.info key_map.toString ( )
        log.info value_map.toString ( )

        log.info "${println "-" * 80}"
        log.info "Decoding Message"

        txt_msgs.each {
            msg ->
            log.debug "Encoded: ${msg.name} ${msg.account}"
            msg.name = value_map[(msg.name)]
            msg.account = value_map[(msg.account)]
            log.debug "Decoded: ${msg.name} ${msg.account}"
        }

        log.info txt_msgs.toString ( )

        assert txt_msgs == test_msgs
    }
}

def tg = new TagAnonymiser().anoynymise()